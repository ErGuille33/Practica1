package com.example.androidengine;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.engine.Engine;
import com.example.engine.Graphics;
import com.example.engine.Input;
import com.example.engine.Logica;

import java.io.IOException;
import java.io.InputStream;

public class AndroidEngine implements Engine, Runnable {

    public AndroidEngine(Logica _logic, SurfaceView sv){

        logica =_logic;
        _sv = sv;

        _holder = sv.getHolder();

    }

    @Override
    public Graphics getGraphics() {
        return _ag;
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
    public void update(double deltaTime) {
        logica.update((float)deltaTime);
    }

    public void handleInput() {
        logica.handleInput(_input.getTouchEvents());
    }

    @Override
    public void render(Graphics g) throws Exception {
        logica.render(g);
    }

    @Override
    public float getGrowthFactor() {
        return 10;
    }

    @Override
    public boolean running() throws Exception {
        // while
        _input = new AndroidInput();
        _ag = new AndroidGraphics();
        _ag.getContext(context);
        logica.init();
        _running = true;
        double lastTime = System.nanoTime();
        _sv.setOnTouchListener(_input._listener);
        while(_running) {
            Canvas canvas = _holder.lockCanvas();
            _ag.setCanvas(canvas);
            double currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime) / 1e9;
            update(deltaTime);
            handleInput();

            while (!_holder.getSurface().isValid())
                ;

            try {
                render(_ag);
            } catch (Exception e) {
                e.printStackTrace();
            }
            _holder.unlockCanvasAndPost(canvas);

            lastTime = currentTime;
        }

        return true;
    }


    public void getContext(Context _context){
        context = _context;
    }


    @Override
    public void run()  {

        if (_renderThread != Thread.currentThread()) {
            throw new RuntimeException("run() should not be called directly");
        }

        while(_running && _sv.getWidth() == 0);

        System.out.println("Runeando aki el android gente");

            try {
                running();
            } catch (Exception e) {
                e.printStackTrace();
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

    public void resume() {
        if (!_running) {
            _running = true;
            _renderThread = new Thread(this);
            _renderThread.start();
        }
    }



    Thread _renderThread;

    boolean _running = false;

    Context context;
    Logica logica;
    AndroidGraphics _ag;
    SurfaceHolder _holder;
    SurfaceView _sv;
    AndroidInput _input;

}
