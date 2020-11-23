package com.example.androidengine;

import android.graphics.Typeface;

import java.io.FileInputStream;
import java.io.InputStream;
import android.content.Context;

public class Font implements com.example.engine.Font  {
    public Font(String filename,float _size, boolean _isBold, Context context) throws Exception{
       f = Typeface.createFromAsset(context.getAssets(), filename);

    }
    public Typeface f;
}
