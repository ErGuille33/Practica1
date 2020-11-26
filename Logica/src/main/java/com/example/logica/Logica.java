package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Graphics;

import java.awt.Canvas;
import java.util.Vector;

public class Logica implements com.example.engine.Logica {

    public Logica() {

    }

    public void init() throws Exception {
        Nivel nivelActual = new Nivel(15,_engine);
        nivelActual.cargaNivel();

        _objects = new Vector<GameObject>(nivelActual.items.size()+nivelActual.enemies.size()+nivelActual.paths.size()+1);
        for(int i = 0; i < nivelActual.items.size(); i++) {
            Coin newcoin = new Coin(nivelActual.items.get(i)._pos.get_x()-4,
                    nivelActual.items.get(i)._pos.get_y()-4,
                    8, 8);
            _objects.add(newcoin);
        }

        for(int i = 0; i < nivelActual.paths.size(); i++) {
            Path newpath = new Path(nivelActual.paths.get(i).vertices);
            _objects.add(newpath);
        }

        for(int i = 0; i < nivelActual.enemies.size(); i++) {
            Enemy newenemy = new Enemy(nivelActual.enemies.get(i)._pos.get_x(),
                    nivelActual.enemies.get(i)._pos.get_y(),
                    nivelActual.enemies.get(i)._offset.get_x(),
                    nivelActual.enemies.get(i)._offset.get_y(),
                    nivelActual.enemies.get(i)._speed,
                    nivelActual.enemies.get(i)._length,
                    nivelActual.enemies.get(i)._angle,
                    nivelActual.enemies.get(i)._time1);
            _objects.add(newenemy);
        }

        Player player = new Player(nivelActual.paths.get(0).vertices.get(0)._pos.get_x()-4, nivelActual.paths.get(0).vertices.get(0)._pos.get_y()-4,
                8, 8, new Vector2D(0,0), nivelActual.paths);
        _objects.add(player);

    }

    @Override
    public void update(float deltaTime) {
        for(int i = 0; i < _objects.size(); i++) {
            _objects.elementAt(i).update(deltaTime);
        }
    }

    public void render(Graphics g) throws Exception {

        g.setColor("black");
        g.fillRect(0,0, (int) g.getWidth(),(int)  g.getHeight());
        g.translate((int) g.getWidth()/2, (int) g.getHeight()/2);

        g.scale(g.calculateSize());


        for(int i = 0; i < _objects.size(); i++) {
            _objects.elementAt(i).render(g);
        }

    }

    public void getEngine(Engine engine){
        _engine = engine;
    }

    Engine _engine;
    Vector<GameObject> _objects;

}