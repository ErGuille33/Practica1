package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Graphics;
import com.example.engine.Input;

import java.util.List;

//Clase anterior a la logica concreta de los niveles y del menu. Consiste simplemente en decidir en cual de los dos estados estamos, y crearlos.
public class Logica implements com.example.engine.Logica {

    public Logica() {

    }

    public void init() throws Exception {
        _engine.getGraphics().setBaseWidth(640);
        _engine.getGraphics().setBaseHeight(480);
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

    public void handleInput(List<Input.TouchEvent> te) throws Exception {
        if (levelState)
            logicaNiveles.handleInput(te);
        else if (menuState) {
            logicaMenu.handleInput(te);
        }
    }

    public void getEngine(Engine engine) {
        _engine = engine;
    }

    //Creamos e iniciamos el estado del juego principal
    public void startLevelState(int dif) throws Exception {
        levelState = true;
        menuState = false;
        logicaNiveles = new LogicaNiveles(_engine, this, dif);
        try {
            initGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Creamos e iniciamos el estado del menu
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

    //Para servir de transicion entre el menu y el comienzo del juego
    public void processButton(String action) throws Exception {
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