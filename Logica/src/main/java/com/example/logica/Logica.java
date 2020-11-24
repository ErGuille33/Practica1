package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Graphics;

import java.awt.Canvas;
import java.util.Vector;

public class Logica implements com.example.engine.Logica {

    public Logica() {

    }

    public void init() throws Exception {
        Nivel nivelActual = new Nivel(13,_engine);
        nivelActual.cargaNivel();

        _objects = new Vector<GameObject>(nivelActual.items.size()+nivelActual.enemies.size()+nivelActual.paths.size()+1);
        for(int i = 0; i < nivelActual.items.size(); i++) {
            Coin newcoin = new Coin(nivelActual.items.get(i)._pos.get_x(),
                    nivelActual.items.get(i)._pos.get_y(),
                    20, 20);
            _objects.add(newcoin);
        }

        for(int i = 0; i < nivelActual.paths.size(); i++) {
            Path newpath = new Path(nivelActual.paths.get(i).vertices);
            _objects.add(newpath);
        }

        for(int i = 0; i < nivelActual.enemies.size(); i++) {
            Enemy newenemy = new Enemy(nivelActual.enemies.get(i)._pos.get_x(),
                    nivelActual.enemies.get(i)._pos.get_y(),
                    nivelActual.enemies.get(i)._length,
                    nivelActual.enemies.get(i)._angle);
            _objects.add(newenemy);
        }

        Player player = new Player(nivelActual.paths.get(0).vertices.get(0)._pos.get_x(), nivelActual.paths.get(0).vertices.get(0)._pos.get_y(),
                20, 20, new Vector2D(0,0));
        _objects.add(player);

    }

    public void update() {

    }

    @Override
    public void update(float deltaTime) {
        for(int i = 0; i < _objects.size(); i++) {
            _objects.elementAt(i).update();
        }
    }

    public void render(Graphics g) throws Exception {
        g.setColor("black");
        g.fillRect(0,0, g.getWidth(), g.getHeight());
        g.translate(g.getWidth()/2, g.getHeight()/2);


        for(int i = 0; i < _objects.size(); i++) {
            _objects.elementAt(i).render(g);
        }

        g.newFont("BungeeHairline-Regular.ttf",40,true);
        g.drawText("Viva el vino", 100,100);

    }

    public void getEngine(Engine engine){
        _engine = engine;
    }


    Engine _engine;
    Vector<GameObject> _objects;

}