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
    public void update(double deltaTime) {
        logica.update((float)deltaTime);
    }


    @Override
    public void render(Graphics g) throws Exception {

        logica.render(g);
    }

    @Override
    //Método que devuelve el factor de cambio de tamaño a multiplicar por deltatime, que resulta no ser el mismo en android y Pc
    public float getGrowthFactor() {
        return (float) 0.1;
    }

    public void handleInput() {
        logica.handleInput(_input.getTouchEvents());
    }


    @Override
    public boolean running() throws Exception {
        logica.init();
        _graphics = new PCGraphics(_frame);
        _input = new PcInput();
        _frame.addMouseListener(_input._handler);
        boolean _running = true;

        long lastTime = System.nanoTime();
        while(_running){
            long currentTime = System.nanoTime();
            double nanoElapsedTime = currentTime - lastTime;
            double deltaTime = nanoElapsedTime / 1e9;
            lastTime = currentTime;

            update(deltaTime);
            handleInput();

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
    protected PcInput _input;
    boolean _exit = false;
    java.awt.Graphics graphicsAwt;

    JFrame _frame;
}