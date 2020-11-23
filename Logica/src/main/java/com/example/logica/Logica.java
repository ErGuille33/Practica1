package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Graphics;

import java.util.*;

public class Logica implements com.example.engine.Logica {

    public Logica() {

    }

    public void init() throws Exception {
        Nivel nivelActual = new Nivel(13,engine);
        nivelActual.cargaNivel();

        //_engine.run();
        Vector2D cosa = new Vector2D(0,0);

        _objects = new Vector<GameObject>(20 /*aqui habria que poner la cantidad de cosas que tenga el nivel*/);
        Cuadrao cuadrao1 = new Cuadrao(10, 50, 20, 20, cosa, 0);
        _objects.add(cuadrao1);
        Cuadrao cuadrao2 = new Cuadrao(10, 80, 20, 20, cosa, 0);
        _objects.add(cuadrao2);
        Cuadrao cuadrao3 = new Cuadrao(10, 110, 20, 20, cosa, 0);
        _objects.add(cuadrao3);
    }


    @Override
    public void update(float deltaTime) {

    }

    public void render(Graphics g) {
        g.setColor("black");
        g.fillRect(0,0, g.getWidth(), g.getHeight());
        
        for(int i = 0; i < _objects.size(); i++) {
            _objects.elementAt(i).render(g);
        }

    }

    public void getEngine(Engine _engine){
        engine = _engine;
    }

    Engine engine;
    Vector<GameObject> _objects;

}