package com.edu.t2f.contentprovider;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

public class Typefaces {

    private static final Hashtable cache = new Hashtable();

    public static Typeface get(Context c, String name){
        synchronized(cache){
            if(!cache.containsKey(name)){
                //Log.e("ASSETS: ", c.getAssets().toString());
                Typeface t = Typeface.createFromAsset(
                        c.getAssets(),
                        String.format("fonts/%s.ttf", name)
                );
                cache.put(name, t);
            }
            return (Typeface) cache.get(name);
        }
    }

}
