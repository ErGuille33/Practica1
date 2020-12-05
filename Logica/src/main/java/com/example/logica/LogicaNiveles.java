package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Font;
import com.example.engine.Graphics;
import com.example.engine.Input;

import java.util.ArrayList;
import java.util.List;

import static com.example.logica.Collisions.segmentsIntersection;
import static com.example.logica.Collisions.distancePointPoint;

//Esta clase corre la logica de cada nivel individualmente asi como de las transiciones entre niveles conservando variables como
//las vidas o monedas totales recogidas. Tambien gestiona la pantalla de gameOver y vuelta al menu principal, ya que era la forma mas
//conveniente de hacerlo para que se siga renderizando el nivel detras.

public class LogicaNiveles {
    LogicaNiveles(Engine engine, Logica logica, int difficulty) {
        //Inicializamos las variables que seran globales durante el transcurso de toda la partida y los datos de entrada.

        _engine = engine;
        _logica = logica;

        _difficulty = difficulty;

        if (difficulty == 0) {
            _totalLifes = 10;
        } else _totalLifes = 5;
        _lifes = _totalLifes;
        _crosses = new ArrayList<Cross>(10);
        _squares = new ArrayList<Square>(10);
        for (int i = 0; i < _totalLifes; i++) {
            Cross cruz = new Cross((150) + 16 * i, (203), 12, 12, new Vector2D(0, 0));
            cruz.visible = false;
            _crosses.add(cruz);
        }

        for (int i = 0; i < _totalLifes; i++) {
            Square cuadrado = new Square(150 + 16 * i, 203, 12, 12, new Vector2D(0, 0));

            _squares.add(cuadrado);
        }
        gameOver = false;
        totalMonedas = 0;
    }

    //Cargamos el nivel concreto en el que se encuentra el jugador
    public void cargaNivel() throws Exception {

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
                12, 12, new Vector2D(0, 0), nivelActual.paths, _difficulty);

        aux = new Coordenada(0, 0);
        aux1 = new Coordenada(0, 0);
        pastAux = new Coordenada(0, 0);

        _waitSame = false;
        _waitTime = 0;
        _waitNextlvl = false;
        _waitTime = 0;

    }

    //En este metodo comprobaremos las colisiones entre el jugador y las monedas y enemigos. Decidimos hacerlo aqui
    //para facilitar la tarea.
    public void handleCollisions(float deltaTime) {

        aux.set_x(player.logicX);
        aux.set_y(player.logicY);
        for (Coin c : _coins) {
            if (player.isJumping) {
                aux1.set_x(c._x1);
                aux1.set_y(c._y1);
                if (distancePointPoint(aux, aux1) < Math.pow(distCollision, 2) && !c.destroyingCoin) {
                    //Si recogemos la moneda, iniciamos su destrucccion
                    c.destroyCoin();
                }
            }
            if (c.destroyingCoin) {
                //Crecimiento de la moneda
                c.changeInSize += _engine.getGrowthFactor() * deltaTime;
                c.setH(c._h + c.changeInSize);
                c.setW(c._w + c.changeInSize);
            }
            if (c.finallyDestroy()) {
                //Destruccion final de la moneda
                _deleteCoins.add(c);
            }
        }
        //Colision con los enemigos
        if (_enemy != null) {

            for (Enemy e : _enemy) {
                collisionCoord = null;
                auxSegmento.setVert1(e._x1, e._y1);
                auxSegmento.setVert2(e._x2, e._y2);
                collisionCoord = segmentsIntersection(auxSegmento, new Segmento(aux.get_x(), aux.get_y(), player.lastCoord.get_x(), player.lastCoord.get_y(), 0));

                //Esta larga comprobacion nos asegura que el jugador no detectara la colision con el enemigo en caso de que este posea un vertice que se encuentre en el camino.
                if (collisionCoord != null && ((collisionCoord.get_x() != auxSegmento.getVert1().get_x() || collisionCoord.get_y() != auxSegmento.getVert1().get_y())
                        && (collisionCoord.get_x() != auxSegmento.getVert2().get_x() || collisionCoord.get_y() != auxSegmento.getVert2().get_y()))) {
                    deadByEnemy = true;
                    player.dead = true;
                    if (!playerDead) {
                        substractLife();
                        playerDead = true;
                    }

                }
            }
        }
    }

    //Quitamos una vida al jugador y cambiamos el estado del marcador
    void substractLife() {
        _lifes--;
        totalMonedas -= monedasRecogidas;
        monedasRecogidas = 0;
        _crosses.get(_lifes).visible = true;
        _squares.get(_lifes).visible = false;
    }

    //Destruimos finalmente las monedas. No lo realizamos directamente al comprobar la colision ya que surgen excepciones no deseadas al
    //ya que es posible que se traten de modificar parametros de las monedas ya destruidas
    void destroyItems() {
        for (Coin c : _deleteCoins) {
            if (!c.picked) {
                if (!playerDead) {
                    monedasRecogidas += 1;
                    totalMonedas += 1;
                    c.pickedCoin();
                }
            }
            _coins.remove(c);
        }
    }

    //Comprobaremos si el jugado recogió todas las monedas sin estar muerto
    boolean compruebaVictoria() {
        if (monedasRecogidas >= nMonedas && !playerDead) {
            return true;
        } else return false;
    }

    public void update(float deltaTime) {
        //Si no ha acabado la partida
        if (!gameOver) {

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

            //Si el jugador sale de los limites de la pantalla
            if (player._x > _engine.getGraphics().getWidth() / 2 || player._x < -_engine.getGraphics().getWidth() / 2 ||
                    player._y > _engine.getGraphics().getHeight() / 2 || player._y < -_engine.getGraphics().getHeight() / 2) {
                playerDead = true;
                substractLife();
                pasaNivel(true);
            }
            //destruimos las monedas necesarias
            destroyItems();

            if (compruebaVictoria()) {
                _waitNextlvl = true;
            }
            if (deadByEnemy) {
                _waitSame = true;
            }
            //Esperaremos 2.2 segundos si el jugador ha sido destruido y comenzaremos el mismo nivel
            if (_waitSame) {
                _waitTime += deltaTime;
                if (_waitTime >= 2.2) {
                    pasaNivel(true);

                }
                //Esperaremos 1 segundo si el jugador ha completado el nivel.
                //Si el jugador recoge todas las monedas y seguidamente muere antes de haber pasado este segundo, tendra que reiniciar el nivel.
            } else if (_waitNextlvl) {
                _waitTime += deltaTime;
                if (_waitTime >= 1) {

                    pasaNivel(false);

                }
            }
        }
    }

    public void render(Graphics g) throws Exception {

        g.setColor("black");
        g.fillRect(0, 0, (int) g.getWidth(), (int) g.getHeight());

        g.translate((int) g.getWidth() / 2, (int) g.getHeight() / 2);

        g.scale(g.calculateSize());

        g.newFont("BungeeHairline-Regular.ttf", 15, true, 0);
        g.setColor("white");
        g.drawText("Level " + (_level + 1) + " - " + nivelActual._name, (int) (-300), (int) (-203));

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
        //Renderizar solo si hemos perdido el juego
        if (gameOver) {

            g.setColor("darkGray");
            g.fillRect(-500, 50, 500, 180);

            g.newFont("Bungee-Regular.ttf", 50, true, 1);
            g.setColor("red");
            g.drawText("Game Over", (int) (-150), (int) (-130));

            g.newFont("Bungee-Regular.ttf", 20, true, 2);
            g.setColor("white");
            if (_difficulty == 0) {
                g.drawText("EASY MODE", (int) (-50), (int) (-90));
            } else {
                g.drawText("HARD MODE", (int) (-50), (int) (-90));
            }
            g.setColor("white");

            g.drawText("SCORE: " + (totalMonedas), (int) (-38), (int) (-60));
        }
    }

    public void handleInput(List<Input.TouchEvent> te) {
        if (!gameOver) {
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
        else {
            for (int j = 0; j < te.size(); j++) {
                _logica.startMenu();
                te.clear();
            }
        }

    }

    //Metodo para pasar de nivel, ya sea repitiendo el mismo o continuando al siguiente
    public void pasaNivel(boolean same) {

        nMonedas = 0;
        monedasRecogidas = 0;
        playerDead = false;
        deadByEnemy = false;

        //Si pasamos de nivel
        if (!same) {
            _level++;
            if (_level >= 20)
                _logica.startMenu();
        }

        //Si nos quedamos sin vidas
        if (_lifes <= 0) {

            gameOver = true;

        } else {
            //Comienzo del nivel
            try {
                _enemy.clear();
                _coins.clear();
                _paths.clear();
                _deleteCoins.clear();
                cargaNivel();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    Engine _engine;
    Logica _logica;

    //Elementos del nivel
    ArrayList<Enemy> _enemy;
    ArrayList<Coin> _coins;
    ArrayList<Path> _paths;
    ArrayList<Coin> _deleteCoins; //Esta lista sirve para poder eliminar las monedas traspasandolas

    //Vidas
    ArrayList<Cross> _crosses;
    ArrayList<Square> _squares;

    Player player;
    //Cordenaedas y segmentos auxiliares utiles para realizar ciertas operaciones de colisiones
    Coordenada aux;
    Coordenada aux1;
    Coordenada pastAux;
    Coordenada collisionCoord = null;
    Segmento auxSegmento;

    //Datos del nivel actual
    Nivel nivelActual;


    float distCollision = 20;    //DIstancia de colision con las monedas
    float _waitTime = 0;    //Timer

    //Booleanos usados en la logica
    boolean deadByEnemy = false;
    boolean playerDead = false;
    boolean _waitNextlvl = false;
    boolean _waitSame = false;
    boolean gameOver = false;

    //Variables necesarias para la logica
    int _difficulty;
    int _level = 0;
    int _lifes;
    int _totalLifes;
    int monedasRecogidas = 0;
    int nMonedas;
    int totalMonedas = 0;


}