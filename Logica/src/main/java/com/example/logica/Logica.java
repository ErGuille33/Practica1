package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Graphics;

import java.awt.Canvas;

public class Logica {

    public Logica(Engine engine) {
        _engine = engine;
    }

    public void run() throws Exception {
        //Nivel nivelActual = new Nivel(7);
        //nivelActual.cargaNivel();

        _engine.run();
        Vector2D cosa = new Vector2D(0,0);
        _cuadrao = new Cuadrao(10, 10, 20, 20, cosa, 0);
    }

    public void update() {
        _engine.update();
    }

    public void render(Graphics g) {
        g.setColor("yellow");
        g.fillRect(0,0, g.getWidth(), g.getHeight());
        _cuadrao.render(g);
    }

    Engine _engine;

    Cuadrao _cuadrao;

}