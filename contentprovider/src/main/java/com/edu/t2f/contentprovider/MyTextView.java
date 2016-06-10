package com.edu.t2f.contentprovider;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by tuan on 06/07/2016.
 */
public class MyTextView extends TextView {

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        if(!this.getRootView().isInEditMode()){
            Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/cfmidnight.ttf");
            setTypeface(typeface,1);
        }
    }
}
