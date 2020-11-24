package com.example.logica;

import com.example.engine.Graphics;

public class Character extends GameObject {

    public Character(float x, float y, float w, float h, Vector2D vel) {
        super(x, y, w, h, vel);
    }

    public void render(Graphics g) {
        g.save();

        float _rx = (g.getWidth() * _x) / 640;
        float _ry = (g.getWidth() * _y) / 480;

        g.translate((int)_rx, (int)_ry);
        g.rotate((int)_rot);
        g.drawLine((int)_rx, (int)_ry, (int)(_rx + _w), (int)_ry);
        g.drawLine((int)(_rx + _w), (int)_ry, (int)(_rx + _w), (int)(_ry + _h));
        g.drawLine((int)(_rx + _w), (int)(_ry + _h), (int)_rx , (int)(_ry + _h));
        g.drawLine((int)_rx , (int)(_ry + _h), (int)_rx, (int)_ry);
        g.restore();
    }

    public void update(float deltaTime) {
        //_rot += 50f*deltaTime;
    }

}


