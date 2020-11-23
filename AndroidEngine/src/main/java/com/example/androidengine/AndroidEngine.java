package com.example.androidengine;


import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;

import com.example.engine.Engine;
import com.example.engine.Graphics;
import com.example.engine.Input;
import com.example.engine.Logica;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class AndroidEngine implements Engine {

    public AndroidEngine(Logica _logic){logica =_logic;}

    @Override
    public Graphics getGraphics() {
        return null;
    }

    @Override
    public Input getInput() {
        return null;
    }

    @Override
    public InputStream openInputStream(String filename) throws IOException {

        return context.getAssets().open(filename);
    }

    @Override
    public FileReader openFileReader(String filename) throws IOException {
        AssetFileDescriptor descriptor = context.getAssets().openFd(filename);
        FileReader reader = new FileReader(descriptor.getFileDescriptor());

        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }

    public void getContext(Context _context){
        context = _context;
    }

    @Override
    public boolean run() throws Exception {
        System.out.println("Runeando aki el android gente");
        return false;
    }
    Context context;
    Logica logica;

}
