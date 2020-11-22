package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Graphics;

import java.awt.Canvas;

public class Logica implements com.example.engine.Logica {

    public Logica() {

    }

    public void init() throws Exception {
        Nivel nivelActual = new Nivel(7);
        nivelActual.cargaNivel();

        //_engine.run();
        Vector2D cosa = new Vector2D(0,0);
        _cuadrao = new Cuadrao(10, 10, 20, 20, cosa, 0);
    }


    @Override
    public void update(float deltaTime) {

    }

    public void render(Graphics g) {
        g.setColor("yellow");
        g.fillRect(0,0, g.getWidth(), g.getHeight());
        _cuadrao.render(g);
    }



    Cuadrao _cuadrao;

}