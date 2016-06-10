package com.edu.t2f.contentprovider;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by tuan on 06/08/2016.
 */
public class DetailActivity extends AppCompatActivity {

    TextView cNameView, pNumberView;
    ImageView photoContainer;
    String phoneNumber, contactName;
    Cursor cur;
    ContentResolver cr;
    private ViewGroup viewGroup;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail);
        LayoutTransition l = new LayoutTransition();
        l.enableTransitionType(LayoutTransition.CHANGING);
        viewGroup = (ViewGroup) findViewById(R.id.detailContainerView);
        viewGroup.setLayoutTransition(l);

        setLayoutAnim_slidedownfromtop(viewGroup, this);

        Bitmap image = null;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ArrayList<String> extra = getIntent().getStringArrayListExtra(ContactsActivity.EXTRA_MESSAGE);
        contactName = extra.get(0).toString();
        phoneNumber = extra.get(1).toString();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // For the main activity, make sure the app icon in the action bar
            // does not behave as a button
            ActionBar actionBar = getActionBar();
            if(actionBar != null)
                actionBar.setHomeButtonEnabled(true);
        }


        // Create the text, number and image view
        cNameView = (TextView) findViewById(R.id.detailText);
        pNumberView = (TextView) findViewById(R.id.pnumber);
        photoContainer = (ImageView) findViewById(R.id.imageView);

        // position them out of view
//        cNameView.setTranslationY(-200);
        cNameView.setText(contactName);
//        pNumberView.setTranslationX(-300);
        pNumberView.setText(phoneNumber);

        // initialize the contact table, check it and load image
        try{
            if(extra.size() > 2){
                if(extra.get(2) != null)
                    readContact(Long.valueOf(extra.get(2)));
                Log.e("IMAGE", "ID -- " + String.valueOf(extra.get(2)));
                if(extra.get(2) != null)
                    image = openDisplayPhoto(Long.valueOf(extra.get(2)));
                if(image == null)
                    image = openPhotoByID(Long.valueOf(extra.get(2)));

                //photoContainer.setRotation(10);
                if(image != null){
                    Log.e("IMAGE SIZE", String.valueOf(image.getByteCount()));
                    try{
                        photoContainer.setImageBitmap(getResizedBitmap(image,300,300));
//                        photoContainer.setImageBitmap(Bitmap.createScaledBitmap(image,width,width,false));
                        Log.e("IMAGE:", "Image should be loaded!");
                    }catch (NullPointerException e){
                        Log.e("photoContainer", "Can't assign image to photoContainer!! -- " + e.toString());
                    }
                }else{
                    Log.e("IMAGE", " -- image is NULL!!!!");
                }
            }

        }catch (NullPointerException e){
            Log.e("image problem:", "couldn't create image for some reason -- " + e.toString());
        }

        // move into view
//        cNameView.animate().setDuration(750).translationY(0);
//        pNumberView.animate().setDuration(750).translationX(0);
//        photoContainer.animate().setDuration(1100).alpha(1);


    }

    public void onCallClick(View v) {
        try {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:"+phoneNumber));
            startActivity(callIntent);
        } catch (ActivityNotFoundException activityException) {
            Log.e("Calling a Phone Number", "Call failed", activityException);
        }
    }

    public void onTxtClick(View v) {
        try {
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.setData(Uri.parse("sms:" + phoneNumber));
            //smsIntent.putExtra("sms_body","Body of Message");
            startActivity(smsIntent);
        } catch (ActivityNotFoundException activityException) {
            Log.e("Texting a Phone Number", "Text failed", activityException);
        }
    }

    private Bitmap openPhotoByID(Long contactId){

        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
        Uri photoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
        Cursor cursor = getContentResolver().query(photoUri,
                new String[] {ContactsContract.Contacts.Photo.PHOTO}, null, null, null);
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.moveToFirst()) {
                byte[] data = cursor.getBlob(0);
                if (data != null) {
                    return BitmapFactory.decodeByteArray(data,0,data.length);
                }
            }
        } finally {
            cursor.close();
        }
        return null;
    }

    public Bitmap openDisplayPhoto(long contactId) {
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
        Uri displayPhotoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.DISPLAY_PHOTO);
        try {
            AssetFileDescriptor fd =
                    getContentResolver().openAssetFileDescriptor(displayPhotoUri, "r");
            return BitmapFactory.decodeStream(fd.createInputStream());
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void readContact(Long id){

        cr = getContentResolver();
        cur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[] {
                        ContactsContract.CommonDataKinds.Phone._ID,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER,
                        ContactsContract.CommonDataKinds.Photo.PHOTO},
                ContactsContract.Data.CONTACT_ID
                        + "="
                        + id
                        + " AND "
                        + ContactsContract.Data.MIMETYPE
                        + "='"
                        + ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE
                        + "'",
                null,
                null);
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

    public static void setLayoutAnim_slidedownfromtop(ViewGroup panel, Context ctx) {

        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(100);
        set.addAnimation(animation);

        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f
        );
        animation.setDuration(500);
        set.addAnimation(animation);

        LayoutAnimationController controller =
                new LayoutAnimationController(set, 0.25f);
        panel.setLayoutAnimation(controller);

    }
}
