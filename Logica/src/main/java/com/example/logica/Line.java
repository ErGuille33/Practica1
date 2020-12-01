package com.example.logica;

import com.example.engine.Graphics;
import com.example.engine.Input;

public class Line extends GameObject{
    public Line(float x, float y, float w, float h, Vector2D vel) {
        super(x, y, w, h, vel);
        logicX =_x + _w/2;
        logicY = _y + _h/2;
    }

    public void render(Graphics g) {
        g.save();
        g.translate((int)(_x+_w/2), (int)(_y+_h/2));
        g.rotate((int)_rot);
        g.translate((int)(-_x-_w/2), (int)(-_y-_h/2));
        g.drawLine((int)_x, (int)_y, (int)(_x + _w), (int)(_y+_h));
        g.restore();
    }

    public void update(float deltaTime) {
        _rot += rotacion*deltaTime;

        _x += _vel._x * 100 * deltaTime;
        _y += _vel._y * 100 * deltaTime;

    }

    public void handleInput(Input.TouchEvent e) {
    }
    public float rotacion;
    public float logicX ;
    public float logicY;
}
