package com.example.logica;

import com.example.engine.Graphics;
import com.example.engine.Input;

public class Character extends GameObject {

    public Character(float x, float y, float w, float h, Vector2D vel) {
        super(x, y, w, h, vel);
        logicX =_x + _w/2;
        logicY = _y + _h/2;
    }

    public void render(Graphics g) {
        g.save();
        g.translate((int)(_x+_w/2), (int)(_y+_h/2));
        g.rotate((int)_rot);
        g.translate((int)(-_x-_w/2), (int)(-_y-_h/2));
        g.drawLine((int)_x, (int)_y, (int)(_x + _w), (int)_y);
        g.drawLine((int)(_x + _w), (int)_y, (int)(_x + _w), (int)(_y + _h));
        g.drawLine((int)(_x + _w), (int)(_y + _h), (int)_x , (int)(_y + _h));
        g.drawLine((int)_x , (int)(_y + _h), (int)_x, (int)_y);
        g.restore();
    }

    public void update(float deltaTime) {
        _rot += 70f*deltaTime;
        _x = logicX - _w/2;
        _y = logicY - _h/2;
    }

    public void handleInput(Input.TouchEvent e) {


    }

    public float logicX ;
    public float logicY;
}


