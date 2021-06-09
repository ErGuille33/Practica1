package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Graphics;
import com.example.engine.Input;

import java.util.List;

//Clase anterior a la logica concreta de los niveles y del menu. Consiste simplemente en decidir en cual de los dos estados estamos, y crearlos.
public class Logica implements com.example.engine.Logica {

    enum GameState {MENU, LEVEL};

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
        switch (_state) {
            case MENU:
                logicaMenu.update(deltaTime);
                break;
            case LEVEL:
                logicaNiveles.update(deltaTime);
                break;
        }
    }


    public void render(Graphics g) throws Exception {
        switch (_state) {
            case MENU:
                logicaMenu.render(g);
                break;
            case LEVEL:
                logicaNiveles.render(g);
                break;
        }
    }

    public void handleInput(List<Input.TouchEvent> te) {
        switch (_state) {
            case MENU:
                logicaMenu.handleInput(te);
                break;
            case LEVEL:
                logicaNiveles.handleInput(te);
                break;
        }
    }

    public void getEngine(Engine engine) {
        _engine = engine;
    }

    //Creamos e iniciamos el estado del juego principal
    public void startLevelState(int dif) {
        _state = GameState.LEVEL;
        logicaNiveles = new LogicaNiveles(_engine, this, dif);
        try {
            initGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Creamos e iniciamos el estado del menu
    public void startMenu() {
        _state = GameState.MENU;
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

    GameState _state;


}