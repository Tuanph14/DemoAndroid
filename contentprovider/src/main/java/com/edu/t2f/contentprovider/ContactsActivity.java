package com.edu.t2f.contentprovider;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ContactsActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.edu.t2f.contentprovider.MESSAGE";

    Cursor cur;
    ContentResolver cr;
    public EditText mFilterTextView;
    public ListView mListView;
    public TextView mTimeToLoadView;
    public Button mClearFilterButton;
    public ArrayAdapter aa = null;
    public Map<String,ArrayList<String>> hashContactTable = new HashMap<>();
    public ArrayList<String> namesList;

    private ViewGroup viewGroup;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_contacts);

        LayoutTransition l = new LayoutTransition();
        l.enableTransitionType(LayoutTransition.CHANGING);
        viewGroup = (ViewGroup) findViewById(R.id.ContainerView);
        viewGroup.setLayoutTransition(l);

        setLayoutAnim_slidedownfromtop(viewGroup, this);

        // Setting your own typeface - still doesn't work.
        Typeface type = Typefaces.get(this, "ayuma2yk");
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/ayuma2yk.ttf");
        try{
            mFilterTextView.setTypeface(tf);
        }catch (NullPointerException e){
            Log.e("loadingFont: ", "The font seems to be null or something, " +
                    "maybe it didn't like the file: " + e.toString());
        }

        // Get all of my contacts into the contactTable by way of the queryPhonesTable() method below.
        hashContactTable = queryPhonesTable();
        namesList = new ArrayList<String>(hashContactTable.size());
        namesList.addAll(hashContactTable.keySet());
        Collections.sort(namesList);


        // Assign my listView, textView and button from the layout to an object.
        mListView = (ListView) findViewById(R.id.listview);
        mTimeToLoadView = (TextView) findViewById(R.id.textView2);
        mClearFilterButton = (Button) findViewById(R.id.clearButton);

        // Create my adapter using the contactTable I filled earlier.
        aa = new ArrayAdapter(this, R.layout.row, R.id.listitem, namesList);

        // Setup the Filter box
        mFilterTextView = (EditText) findViewById(R.id.editText);
        mFilterTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s,
                                      int start, int before, int count)
            {
                mListView.setTextFilterEnabled(true);
                mListView.setFilterText(s.toString());
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) { }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0){
                    mListView.clearTextFilter();
                }
            }
        });

//        For adding new items - need to add another EditText box and an Add button for this to run.
        mFilterTextView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        //listItems.add(listItems.size(), mFilterTextView.getText().toString());
                        //mFilterTextView.setText("");

                        return true;
                    }
                return false;
            }
        });

        // Set my adapter to the mListView object.
        mListView.setAdapter(aa);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.e("CLICKED ITEM: ", aa.getItem(position).toString());
                // Open a new view that shows the details of the List Item
                openView(aa.getItem(position).toString(), findViewById(R.id.ContainerView));

            }
        });
//        Log.d("mListView.setOnItemClickListener: ", "Success!");
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, final View view, int position, long id){
                if(!aa.isEmpty()){
//                    animView(aa.getView(position, view, mListView));
                    Toast.makeText(getApplicationContext(),
                            "Removed ListItem : " + aa.getItem(position).toString(),
                            Toast.LENGTH_SHORT).show();
                    final String item = (String) adapterView.getItemAtPosition(position);
                    view.animate().setDuration(100).translationX(adapterView.getMeasuredWidth()).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            namesList.remove(item);
                            aa.remove(view);
                            aa.notifyDataSetChanged();
                            view.setTranslationX(0);
                        }
                    });
                }
                return true;
            }
        });

        mTimeToLoadView.setText("Contact List");


    }

    public void clearFilterText(View target){
        mFilterTextView.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private HashMap<String, ArrayList<String>> queryPhonesTable() {
        HashMap<String,ArrayList<String>> contactList = new HashMap<String, ArrayList<String>>();
        ArrayList<String> contact;

        cr = getContentResolver();
        cur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[] {
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER},
                null,
                null,
                null);

        if (cur.getCount() > 0) {

            while(cur.moveToNext()){
                contact = new ArrayList<String>();
                //String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String pnumber = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String contactID = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));

                contact.add(name);
                contact.add(pnumber);
                contact.add(contactID);
                contactList.put(name,contact);

            }
            cur.close();
        }

        Log.e("contactList", contactList.toString());
        return contactList;
    }

//      // For multi message transferring to some activity
//    public void openView(String message, Long id){
//        Intent intent = new Intent(this, DetailActivity.class);
//        Bundle extras = new Bundle();
//        extras.putLong("ID",id);
//        extras.putStringArrayList(EXTRA_MESSAGE, hashContactTable.get(message));
//        intent.putExtras(extras);
//        startActivity(intent);
//    }

    // For single message transferring
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void openView(String message, View view){
        Intent intent = new Intent(this, DetailActivity.class);
        ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view, 0,
                0, 0, view.getHeight());
        intent.putExtra(EXTRA_MESSAGE, hashContactTable.get(message));

//        view.animate().setDuration(500).translationX(-view.getWidth());
//        view.setVisibility(View.GONE);
//        view.setTranslationX(0);
        startActivity(intent);
    }


    public static void setLayoutAnim_slidedownfromtop(ViewGroup panel, Context ctx) {

        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(100);
        set.addAnimation(animation);

        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f
        );
        animation.setDuration(500);
        set.addAnimation(animation);

        LayoutAnimationController controller =
                new LayoutAnimationController(set, 0.25f);
        panel.setLayoutAnimation(controller);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
