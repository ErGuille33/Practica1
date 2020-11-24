package com.example.pcengine;

import com.example.engine.Engine;
import com.example.engine.Graphics;
import com.example.engine.Input;
import com.example.engine.Logica;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;


public class PcEngine implements Engine {

    public PcEngine(Logica _logic, JFrame frame){
        logica=_logic;
        _frame = frame;
    }

    public Graphics getGraphics() {
        return _graphics;
    }

    public Input getInput() {
        return _input;
    }

    public InputStream openInputStream(String filename) {
        InputStream is = null;
        try {
             is = new FileInputStream("assets/" +filename);
        }
        catch (Exception e) {
            System.err.println("Error cargando el archivo: " + e);
            return null;
        }
        return is;
    }

    @Override
    public FileReader openFileReader(String filename) throws FileNotFoundException {

        return new FileReader("assets/" + filename);
    }

    @Override
    public void update(double deltaTime) {
        logica.update((float)deltaTime);
    }


    @Override
    public void render(Graphics g) throws Exception {
        logica.render(g);
    }


    @Override
    public boolean run() throws Exception {
        logica.init();
        _graphics = new PCGraphics(_frame);

        boolean _running = true;

        long lastTime = System.nanoTime();
        while(_running){
            long currentTime = System.nanoTime();
            double nanoElapsedTime = currentTime - lastTime;
            double deltaTime = nanoElapsedTime / 1e9;
            lastTime = currentTime;

            update(deltaTime);

            do {
                do {
                    graphicsAwt = _frame.getBufferStrategy().getDrawGraphics();
                    _graphics.setGraphics(graphicsAwt);
                    try {
                        render(_graphics);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        graphicsAwt.dispose();
                    }
                } while(_frame.getBufferStrategy().contentsRestored());
                _frame.getBufferStrategy().show();
            } while(_frame.getBufferStrategy().contentsLost());

        }


        return true;
    }

    protected PCGraphics _graphics;
    Logica logica;
    protected Input _input;
    boolean _exit = false;
    java.awt.Graphics graphicsAwt;

    JFrame _frame;
}