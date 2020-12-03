package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Graphics;
import com.example.engine.Input;

import java.util.List;

public class Logica implements com.example.engine.Logica {

    public Logica() {

    }

    public void init() throws Exception {
        startLevelState();
    }

    public void initGame() throws Exception {
        logicaNiveles.cargaNivel();
    }

    @Override
    public void update(float deltaTime) {
        if (levelState)
            logicaNiveles.update(deltaTime);
        else if (gameOverState) {
            logicaGameOver.update(deltaTime);
        } else if (menuState) {
            logicaMenu.update(deltaTime);
        }
    }

    public void render(Graphics g) throws Exception {
        if (levelState)
            logicaNiveles.render(g);
        else if (gameOverState) {
            logicaGameOver.render(g);
        } else if (menuState) {
            logicaMenu.render(g);
        }

    }

    public void handleInput(List<Input.TouchEvent> te) {
        if (levelState)
            logicaNiveles.handleInput(te);
        else if (gameOverState) {
            logicaGameOver.handleInput(te);
        } else if (menuState) {
            logicaMenu.handleInput(te);
        }
    }

    public void getEngine(Engine engine) {
        _engine = engine;
    }

    public void startLevelState() {
        levelState = true;
        gameOverState = false;
        menuState = false;
        logicaNiveles = new LogicaNiveles(_engine, this,1);
        try {
            initGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startGameOverState() {
        gameOverState = true;
        levelState = false;
        menuState = false;
        logicaGameOver = new LogicaGameOver(_engine, this);
    }

    public void startMenu() {
        menuState = true;
        levelState = false;
        gameOverState = false;
        logicaMenu = new LogicaMenu(_engine, this);
    }

    Engine _engine;

    LogicaNiveles logicaNiveles;
    LogicaGameOver logicaGameOver;
    LogicaMenu logicaMenu;

    boolean levelState = false;
    boolean gameOverState = false;
    boolean menuState = false;


}