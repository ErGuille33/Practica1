package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Graphics;
import com.example.engine.Input;

import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Vector;

import static com.example.logica.Collisions.sqrDistancePointPoint;
import static com.example.logica.Collisions.sqrDistancePointSegment;

public class Logica implements com.example.engine.Logica {

    public Logica() {

    }

    public void init() throws Exception {
        Nivel nivelActual = new Nivel(0, _engine);
        nivelActual.cargaNivel();


        _enemy = new ArrayList<Enemy>(nivelActual.enemies.size());
        nMonedas = nivelActual.items.size();
        _coins = new ArrayList<Coin>(nivelActual.items.size());
        _paths = new ArrayList<Path>(nivelActual.paths.size());
        _deleteCoins = new ArrayList<Coin>(nivelActual.items.size());

        for (int i = 0; i < nivelActual.items.size(); i++) {
            Coin newcoin = new Coin(nivelActual.items.get(i)._pos.get_x() - 4,
                    nivelActual.items.get(i)._pos.get_y() - 4,
                    8, 8, nivelActual.items.get(i)._radius,nivelActual.items.get(i)._speed,nivelActual.items.get(i)._angle);
            _coins.add(newcoin);
        }

        for (int i = 0; i < nivelActual.paths.size(); i++) {
            Path newpath = new Path(nivelActual.paths.get(i).vertices);
            _paths.add(newpath);
        }

        for (int i = 0; i < nivelActual.enemies.size(); i++) {
            Enemy newenemy = new Enemy(nivelActual.enemies.get(i)._pos.get_x(),
                    nivelActual.enemies.get(i)._pos.get_y(),
                    nivelActual.enemies.get(i)._offset.get_x(),
                    nivelActual.enemies.get(i)._offset.get_y(),
                    nivelActual.enemies.get(i)._speed,
                    nivelActual.enemies.get(i)._length,
                    nivelActual.enemies.get(i)._angle,
                    nivelActual.enemies.get(i)._time1,
                    nivelActual.enemies.get(i)._time2);
            _enemy.add(newenemy);
        }

        player = new Player(nivelActual.paths.get(0).vertices.get(0)._pos.get_x() - 4, nivelActual.paths.get(0).vertices.get(0)._pos.get_y() - 4,
                8, 8, new Vector2D(0, 0), nivelActual.paths);

        aux = new Coordenada(0, 0);
        aux1 = new Coordenada(0, 0);

    }

    public void handleCollisions() {
        for (Coin c : _coins
        ) {
            aux.set_x(player.logicX);
            aux.set_y(player.logicY);
            aux1.set_x(c.logicX);
            aux1.set_y(c.logicY);
            if (sqrDistancePointPoint(aux, aux1) < distCollision && player.isJumping) {
                c.destroyCoin();

            }
            if (c.finallyDestroy()) {
                _deleteCoins.add(c);
                monedasRecogidas += 1;
            }
        }
    }

    void destroyItems(){
        for (Coin c: _deleteCoins){
            _coins.remove(c);
        }
    }

    void compruebaVictoria(){
        if(monedasRecogidas >= nMonedas){
            System.out.println("Has ganau");
        }
    }


    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < _coins.size(); i++) {
            _coins.get(i).update(deltaTime);
        }
        for (int i = 0; i < _enemy.size(); i++) {
            _enemy.get(i).update(deltaTime);
        }
        for (int i = 0; i < _paths.size(); i++) {
            _paths.get(i).update(deltaTime);
        }
        handleCollisions();
        player.update(deltaTime);
        destroyItems();
        compruebaVictoria();


        //Aqui destruimos los vectores si terminamos el nivel
    }

    public void render(Graphics g) throws Exception {

        g.setColor("black");
        g.fillRect(0, 0, (int) g.getWidth(), (int) g.getHeight());
        g.translate((int) g.getWidth() / 2, (int) g.getHeight() / 2);

        g.scale(g.calculateSize());

        for (int i = 0; i < _coins.size(); i++) {
            _coins.get(i).render(g);
        }
        for (int i = 0; i < _enemy.size(); i++) {
            _enemy.get(i).render(g);
        }
        for (int i = 0; i < _paths.size(); i++) {
            _paths.get(i).render(g);
        }
        player.render(g);

    }

    public void handleInput(List<Input.TouchEvent> te) {
        for (int i = 0; i < _coins.size(); i++) {
            for (int j = 0; j < te.size(); j++) {
                Input.TouchEvent e = te.get(j);
                _coins.get(i).handleInput(e);
            }
        }
        for (int i = 0; i < _enemy.size(); i++) {
            for (int j = 0; j < te.size(); j++) {
                Input.TouchEvent e = te.get(j);
                _enemy.get(i).handleInput(e);
            }
        }
        for (int i = 0; i < _paths.size(); i++) {
            for (int j = 0; j < te.size(); j++) {
                Input.TouchEvent e = te.get(j);
                _paths.get(i).handleInput(e);
            }
        }
        for (int j = 0; j < te.size(); j++) {
            Input.TouchEvent e = te.get(j);
            player.handleInput(e);
        }

        te.clear();
    }

    public void getEngine(Engine engine) {
        _engine = engine;
    }

    Engine _engine;
    ArrayList<Enemy> _enemy;
    ArrayList<Coin> _coins;
    ArrayList<Path> _paths;
    ArrayList<Coin> _deleteCoins;

    Player player;
    int itemsSize = 0;
    Coordenada aux;
    Coordenada aux1;
    float distCollision = 20;
    int monedasRecogidas = 0;
    int nMonedas ;

}