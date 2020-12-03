package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Font;
import com.example.engine.Graphics;
import com.example.engine.Input;

import java.util.ArrayList;
import java.util.List;

import static com.example.logica.Collisions.segmentsIntersection;
import static com.example.logica.Collisions.sqrDistancePointPoint;

public class LogicaNiveles {
    LogicaNiveles(Engine engine, Logica logica){
        _engine = engine;
        _logica = logica;

        _crosses = new ArrayList<Cross>(10);
        _squares = new ArrayList<Square>(10);
        for (int i = 0; i < 10; i++) {
            Cross cruz = new Cross((150) + 16 * i, (203), 12, 12, new Vector2D(0, 0));
            cruz.visible = false;
            _crosses.add(cruz);
        }

        for (int i = 0; i < 10; i++) {
            Square cuadrado = new Square(150 + 16 * i, 203, 12, 12, new Vector2D(0, 0));

            _squares.add(cuadrado);
        }
    }
    public void cargaNivel() throws Exception {

        System.out.println("Vidas: " + _lifes);
        nivelActual = new Nivel(_level, _engine);
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
        pastAux = new Coordenada(0, 0);

    }

    public void handleCollisions(float deltaTime) {

        aux.set_x(player.logicX);
        aux.set_y(player.logicY);
        for (Coin c : _coins) {
            if (player.isJumping) {
                aux1.set_x(c._x1);
                aux1.set_y(c._y1);
                if (sqrDistancePointPoint(aux, aux1) < distCollision && !c.destroyingCoin) {
                    c.destroyCoin();
                }
            }
            if (c.destroyingCoin) {
                c.changeInSize += _engine.getGrowthFactor() * deltaTime;
                c.setH(c._h + c.changeInSize);
                c.setW(c._w + c.changeInSize);
            }
            if (c.finallyDestroy()) {
                _deleteCoins.add(c);
            }
        }
        if (_enemy != null) {

            for (Enemy e : _enemy) {
                collisionCoord = null;
                auxSegmento.setVert1(e._x1, e._y1);
                auxSegmento.setVert2(e._x2, e._y2);
                collisionCoord = segmentsIntersection(auxSegmento, new Segmento(aux.get_x(), aux.get_y(), player.lastCoord.get_x(), player.lastCoord.get_y(), 0));
                if (collisionCoord != null && ((collisionCoord.get_x() != auxSegmento.getVert1().get_x() || collisionCoord.get_y() != auxSegmento.getVert1().get_y())
                        && (collisionCoord.get_x() != auxSegmento.getVert2().get_x() || collisionCoord.get_y() != auxSegmento.getVert2().get_y()))) {
                    deadByEnemy = true;
                    player.dead = true;
                    if(!playerDead) {
                        substractLife();
                        playerDead = true;
                    }

                }
            }
        }
    }

    void substractLife() {
        _lifes--;
        _crosses.get(_lifes).visible = true;
        _squares.get(_lifes).visible = false;
    }

    void destroyItems() {
        for (Coin c : _deleteCoins) {
            if (!c.picked) {
                monedasRecogidas += 1;
                c.pickedCoin();
            }
            _coins.remove(c);
        }
    }

    boolean compruebaVictoria() {
        if (monedasRecogidas >= nMonedas && !playerDead) {
            return true;
        } else return false;
    }

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
        handleCollisions(deltaTime);

        player.update(deltaTime);
        if (player._x > _engine.getGraphics().getWidth() / 2 || player._x < -_engine.getGraphics().getWidth() / 2 ||
                player._y > _engine.getGraphics().getHeight() / 2 || player._y < -_engine.getGraphics().getHeight() / 2) {
            substractLife();
            pasaNivel(true);
        }

        destroyItems();

        if (compruebaVictoria()) {
            _waitNextlvl = true;
        }
        if (deadByEnemy) {
            _waitSame = true;
        }
        if (_waitNextlvl) {
            _waitTime += deltaTime;
            if (_waitTime >= 1) {
                pasaNivel(false);
                _waitNextlvl = false;
                _waitTime = 0;
            }
        } else if (_waitSame) {
            _waitTime += deltaTime;
            if (_waitTime >= 2.2) {
                pasaNivel(true);
                _waitSame = false;
                _waitTime = 0;
            }
        }
    }

    public void render(Graphics g) throws Exception {

        g.setColor("black");
        g.fillRect(0, 0, (int) g.getWidth(), (int) g.getHeight());

        g.translate((int) g.getWidth() / 2, (int) g.getHeight() / 2);

        g.scale(g.calculateSize());

        if (fuente == null) {
            fuente = g.newFont("BungeeHairline-Regular.ttf", 15, true);
        }

        _engine.getGraphics().drawText("Level " + (_level + 1) + " - " + nivelActual._name, (int) (-300), (int) (-203));

        for (int i = 0; i < _coins.size(); i++) {
            _coins.get(i).render(g);
        }
        for (int i = 0; i < _enemy.size(); i++) {
            _enemy.get(i).render(g);
        }
        for (int i = 0; i < _paths.size(); i++) {
            _paths.get(i).render(g);
        }

        for (int i = 0; i < _crosses.size(); i++) {
            _crosses.get(i).render(g);
        }
        for (int i = 0; i < _squares.size(); i++) {
            _squares.get(i).render(g);
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
        playerDead = false;
        deadByEnemy = false;
        if (!same) {
            _level++;
            if (_level >= 20)
            _logica.startMenu();

        }
        if (_lifes <= 0){
            _logica.startGameOverState();

        }
        else {
            try {
                cargaNivel();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    Engine _engine;
    Logica _logica;
    ArrayList<Enemy> _enemy;
    ArrayList<Coin> _coins;
    ArrayList<Path> _paths;
    ArrayList<Coin> _deleteCoins;

    ArrayList<Cross> _crosses;
    ArrayList<Square> _squares;

    Player player;

    Coordenada aux;
    Coordenada aux1;
    Coordenada pastAux;
    Segmento auxSegmento;
    Nivel nivelActual;

    float distCollision = 20;
    int monedasRecogidas = 0;
    int nMonedas;
    boolean deadByEnemy = false;
    boolean playerDead = false;
    Coordenada collisionCoord = null;
    Font fuente;

    int _level = 2;
    int _lifes = 10;

    boolean _waitNextlvl = false;
    boolean _waitSame = false;

    float _waitTime = 0;
}