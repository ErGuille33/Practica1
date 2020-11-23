package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Graphics;

import java.awt.Canvas;

public class Logica implements com.example.engine.Logica {

    public Logica() {

    }

    public void init() throws Exception {
        Nivel nivelActual = new Nivel(13,_engine);
        nivelActual.cargaNivel();

        Vector2D cosa = new Vector2D(0,0);
        _cuadrao = new Cuadrao(10, 10, 20, 20, cosa, 0);

    }

    public void update() {

    }

    @Override
    public void update(float deltaTime) {
    }

    public void render(Graphics g) throws Exception {
        g.setColor("yellow");
        g.fillRect(0,0, g.getWidth(), g.getHeight());
        g.newFont("BungeeHairline-Regular.ttf",40,true);
        g.drawText("Viva el vino", 100,100);
        _cuadrao.render(g);
    }

    public void getEngine(Engine engine){
        _engine = engine;
    }


    Engine _engine;
    Cuadrao _cuadrao;

}