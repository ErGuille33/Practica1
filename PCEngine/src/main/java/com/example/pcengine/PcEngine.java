package com.example.pcengine;

import com.example.engine.Engine;
import com.example.engine.Graphics;
import com.example.engine.Input;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;

public class PcEngine implements Engine {

    public Graphics getGraphics() {
        return _graphics;
    }

    public Input getInput() {
        return _input;
    }

    public InputStream openInputStream(String filename) {
        try (InputStream is = new FileInputStream(filename)) {
            return is;
        }
        catch (Exception e) {
            System.err.println("Error cargando el archivo: " + e);
            return null;
        }
    }

    @Override
    public void update() {

    }

    @Override
    public boolean run() {

        _graphics = new PCGraphics();

        while(!_exit) {
            update();
            _graphics.render();

        }

        return true;
    }

    protected PCGraphics _graphics;
    protected Input _input;
    protected JFrame _frame;
    boolean _exit = false;

}