package com.example.androidengine;


import android.content.res.AssetManager;

import com.example.engine.Engine;
import com.example.engine.Graphics;
import com.example.engine.Input;
import com.example.engine.Logica;

import java.io.InputStream;

public class AndroidEngine implements Engine {

    public AndroidEngine(Logica _logic){logica = _logic;}
    @Override
    public Graphics getGraphics() {
        return null;
    }

    @Override
    public Input getInput() {
        return null;
    }

    @Override
    public InputStream openInputStream(String filename) {

        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }

    @Override
    public boolean run() throws Exception {
        System.out.println("Runeando aki el android gente");
        logica.init();
        return false;
    }
Logica logica;

}
