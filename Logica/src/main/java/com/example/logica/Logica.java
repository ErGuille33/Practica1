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
        System.out.println("Vidas: " + _lifes);
        Nivel nivelActual = new Nivel(_level, _engine);
        nivelActual.cargaNivel();

        _enemy = new ArrayList<Enemy>(nivelActual.enemies.size());
        nMonedas = nivelActual.items.size();
        _coins = new ArrayList<Coin>(nivelActual.items.size());
        _paths = new ArrayList<Path>(nivelActual.paths.size());
        _deleteCoins = new ArrayList<Coin>(nivelActual.items.size());

        auxSegmento = new Segmento(0, 0, 0, 0, 0);

        for (int i = 0; i < nivelActual.items.size(); i++) {
            Coin newcoin = new Coin(nivelActual.items.get(i)._pos.get_x() - 4,
                    nivelActual.items.get(i)._pos.get_y() - 4,
                    8, 8, nivelActual.items.get(i)._radius, nivelActual.items.get(i)._speed, nivelActual.items.get(i)._angle);
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

        aux.set_x(player.logicX);
        aux.set_y(player.logicY);
        for (Coin c : _coins) {
            if (player.isJumping) {
                aux1.set_x(c.logicX);
                aux1.set_y(c.logicY);
                if (sqrDistancePointPoint(aux, aux1) < distCollision) {
                    c.destroyCoin();

                }
            }
            if (c.finallyDestroy()) {
                _deleteCoins.add(c);
                monedasRecogidas += 1;
            }
        }
        if (player.isJumping) {
            for (Enemy e : _enemy) {
                auxSegmento.setVert1(e._x, e._y);
                auxSegmento.setVert2(e._fx, e._fy);
                if (sqrDistancePointSegment(auxSegmento, aux) < distEnemyCollision) {
                    _lifes--;
                    if(_lifes > 0) {
                        pasaNivel(true);
                        System.out.println("Vidas: " + _lifes);
                    }
                    else {
                        System.out.println("Fin de la partida, vuelta al menu inicial supongo");
                    }
                }
            }
        }
    }

    void destroyItems() {
        for (Coin c : _deleteCoins) {
            _coins.remove(c);
        }
    }

    boolean compruebaVictoria() {
        if (monedasRecogidas >= nMonedas) {
            return true;
        } else return false;
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
        if(player._x > _engine.getGraphics().getWidth()/2 || player._x < -_engine.getGraphics().getWidth()/2 ||
            player._y > _engine.getGraphics().getHeight()/2 || player._y < -_engine.getGraphics().getHeight()/2)
            pasaNivel(true);
        destroyItems();
        if(compruebaVictoria()) {
            pasaNivel(false);
        }
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

    public void pasaNivel(boolean same) {
        _enemy.clear();
        _coins.clear();
        _paths.clear();
        _deleteCoins.clear();
        nMonedas = 0;
        monedasRecogidas = 0;

        if(!same) {
            _level++;
            if (_level > 20) System.out.println("Fin de la partida, vuelta al menu supongo");
        }
        try {
            init();
        } catch (Exception e){
            System.out.println(e);
        }
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
    Segmento auxSegmento;
    float distCollision = 20;
    float distEnemyCollision = 15;
    int monedasRecogidas = 0;
    int nMonedas;

    int _level = 0;
    int _lifes = 10;

}