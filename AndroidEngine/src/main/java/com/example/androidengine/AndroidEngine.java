package com.example.androidengine;

import com.example.engine.Engine;
import com.example.engine.Graphics;
import com.example.engine.Input;

import java.io.InputStream;

public class AndroidEngine implements Engine {

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
    public boolean run() {
        System.out.println("Runeando aki el android gente");
        return false;
    }
}
