package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Graphics;
import com.example.engine.Input;

import java.util.List;

public class Logica implements com.example.engine.Logica {

    public Logica() {

    }

    public void init() throws Exception {
        startMenu();
    }

    public void initGame() throws Exception {
        logicaNiveles.cargaNivel();
    }

    @Override
    public void update(float deltaTime) {
        if (levelState)
            logicaNiveles.update(deltaTime);
        else if (menuState)
           logicaMenu.update(deltaTime);
        }


    public void render(Graphics g) throws Exception {
        if (levelState)
            logicaNiveles.render(g);
         else if (menuState) {
            logicaMenu.render(g);
        }

    }

    public void handleInput(List<Input.TouchEvent> te) {
        if (levelState)
            logicaNiveles.handleInput(te);
        else if (menuState) {
            logicaMenu.handleInput(te);
        }
    }

    public void getEngine(Engine engine) {
        _engine = engine;
    }

    public void startLevelState(int dif) {
        levelState = true;
        menuState = false;
        logicaNiveles = new LogicaNiveles(_engine, this, dif);
        try {
            initGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void startMenu() {
        menuState = true;
        levelState = false;
        logicaMenu = new LogicaMenu(_engine, this);
        try {
            logicaMenu.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processButton(String action) {
        switch (action) {
            case "menu":
                startMenu();
                break;
            case "game":
                startLevelState(0);
                break;
            case "diffgame":
                startLevelState(1);
                break;
        }
    }

    Engine _engine;

    LogicaNiveles logicaNiveles;

    LogicaMenu logicaMenu;

    boolean levelState = false;

    boolean menuState = false;


}