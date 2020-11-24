package com.example.androidengine;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.engine.Engine;
import com.example.engine.Graphics;
import com.example.engine.Input;
import com.example.engine.Logica;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class AndroidEngine implements Engine {

    public AndroidEngine(Logica _logic, SurfaceHolder holder){
        logica =_logic;
        _holder = holder;
    }

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
    public void update(double deltaTime) {
        logica.update((float)deltaTime);
    }


    @Override
    public void render(Graphics g) throws Exception {
        logica.render(g);
    }


    public void getContext(Context _context){
        context = _context;
    }

    @Override
    public boolean run() throws Exception {
        System.out.println("Runeando aki el android gente");
        logica.init();
        boolean acabar = false;
        _ag = new AndroidGraphics();
        _ag.getContext(context);
        double lastTime = System.nanoTime();
        while(!acabar) {
            double currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime) / 1e9;
            System.out.println(deltaTime);
            update(deltaTime);

            while (!_holder.getSurface().isValid())
                ;
            Canvas canvas = _holder.lockCanvas();
            _ag.setCanvas(canvas);
            render(_ag);
            _holder.unlockCanvasAndPost(canvas);
            lastTime = currentTime;


        }

        return false;
    }
    Context context;
    Logica logica;
    AndroidGraphics _ag;
    SurfaceHolder _holder;
    SurfaceView sv;

}
