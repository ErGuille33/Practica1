package com.example.androidengine;
import android.graphics.Typeface;
import android.content.Context;

//Crea una fuente de Typeface y se guarda en f
public class Font implements com.example.engine.Font  {
    public Font(String filename,float size, boolean isBold, Context context) throws Exception{
       f = Typeface.createFromAsset(context.getAssets(), filename);
       _size = size;
       _isBold = isBold;

    }
    public Typeface f;
    public float _size;
    public boolean _isBold;
}
