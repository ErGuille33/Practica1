package com.example.logica;

import com.example.engine.Graphics;
import com.example.engine.Input;

import java.awt.Graphics2D;

public class Boton extends GameObject {

    public Boton(float x, float y, float w, float h, String action, Logica logic) {
        super(x, y, w, h, new Vector2D(0,0));
        _action = action;
        _logic = logic;

        _scale = calculateSize();
    }

    public void render(Graphics g) {

    }

    public void update(float deltaTime) {
        _scale = calculateSize();
    }

    public void handleInput(Input.TouchEvent te) {
        float _ratonx = (te.getPosX() - _logic._engine.getGraphics().getWidth()/2)*(1.0f/_scale);
        float _ratony = (_logic._engine.getGraphics().getHeight()/2 - te.getPosY())*(1.0f/_scale);
        float _dx = _x + (_w*_scale);
        float _dy = _y - (_h*_scale);

        if(_ratonx > _x && _ratonx < _dx && _ratony > - _y && _ratony < -_dy) {
            _logic.processButton(_action);
        }
    }

    float calculateSize() {
        float aux1 = 0;
        float aux2 = 0;

        aux1 = (float) _logic._engine.getGraphics().getWidth() / (float) _logic._engine.getGraphics().getBaseWidth();
        aux2 = (float) _logic._engine.getGraphics().getHeight() / (float) _logic._engine.getGraphics().getBaseHeight();

        if (aux1 < aux2)
            return aux1;
        else return aux2;
    }

    float _scale;
    Logica _logic;
    public String _action;

}
