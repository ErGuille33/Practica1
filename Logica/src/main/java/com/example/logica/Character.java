package com.example.logica;

import com.example.engine.Graphics;

public class Character extends GameObject {

    public Character(float x, float y, int w, int h, Vector2D vel) {
        super(x, y, w, h, vel);
    }

    public void render(Graphics g) {
        g.save();
        g.translate((int)_x, (int)_y);
        g.rotate(_rot);
        g.drawLine((int)_x, (int)_y, (int)_x + _w, (int)_y);
        g.drawLine((int)_x + _w, (int)_y, (int)_x + _w, (int)_y + _h);
        g.drawLine((int)_x + _w, (int)_y + _h, (int)_x , (int)_y + _h);
        g.drawLine((int)_x , (int)_y + _h, (int)_x, (int)_y);
        g.restore();
    }

    public void update() {
        _rot += 1;
    }

}


