package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.androidengine.AndroidEngine;
import com.example.androidengine.AndroidGraphics;
import com.example.engine.Graphics;
import com.example.logica.Logica;

public class MySurfaceView extends SurfaceView implements Runnable {

    public MySurfaceView(Context context) {
        super(context);
        _holder = getHolder();

        _logic = new Logica();
         engine = new AndroidEngine(_logic, _holder);
        _logic.getEngine(engine);
        engine.getContext(context);



    }

    public void resume() {
        if (!_running) {
            _running = true;
            _renderThread = new Thread(this);
            _renderThread.start();
        }
    }

    public void pause() {
        if (_running) {
            _running = false;
            while (true) {
                try {
                    _renderThread.join();
                    _renderThread = null;
                    break;
                } catch (InterruptedException ie) {
                    // Esto no debería ocurrir nunca...
                }
            }
        }
    }

    @Override
    public void run() {
        if (_renderThread != Thread.currentThread()) {
            throw new RuntimeException("run() should not be called directly");
        }

        while(_running && getWidth() == 0);

        try {
            engine.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        _running = true;
        while(_running) {


        } // while
    }

    Logica _logic;
    AndroidGraphics _ag;
    Thread _renderThread;
    SurfaceHolder _holder;
    AndroidEngine engine;
    boolean _running = false;

}
