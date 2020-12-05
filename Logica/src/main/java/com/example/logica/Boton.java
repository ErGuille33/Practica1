package com.example.logica;

import com.example.engine.Graphics;
import com.example.engine.Input;

import java.awt.Graphics2D;

public class Boton extends GameObject {

    public Boton(float x, float y, float w, float h, String action, Logica logic) {
        super(x, y, w, h, new Vector2D(0,0));
        _action = action;
        _logic = logic;
        _rx = _x + _logic._engine.getGraphics().getWidth()/2;
        _ry = _y + _logic._engine.getGraphics().getHeight()/2;
    }

    public void render(Graphics g) {

    }

    public void update(float deltaTime) {

    }

    public void handleInput(Input.TouchEvent te) {
        if(te.getPosX() > _rx && te.getPosX() < _rx+_w && te.getPosY() > _ry && te.getPosX() < _ry+_h) {
            _logic.processButton(_action);
            System.out.println(_action);
        }
    }

    public float _rx, _ry;
    Logica _logic;
    public String _action;

}
