package com.example.logica;

import com.example.engine.Graphics;

public class Character extends GameObject {

    public Character(float x, float y, float w, float h, Vector2D vel) {
        super(x, y, w, h, vel);
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
        _rot += 20f*deltaTime;
    }

}


