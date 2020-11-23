package com.example.logica;

import com.example.engine.Graphics;

public class Enemy extends GameObject {
    public Enemy(float x, float y, float length, float angle) {
        super(x, y, 0, 0, new Vector2D(0,0));
        _length = length;
        _angle = angle;
    }

    public void render(Graphics g) {
        g.setColor("red");
        g.drawLine((int)_x, (int)_y, (int)(_x+(_length*Math.cos(_angle))), (int)(_y+(_length*Math.sin(_angle))));
    }

    public void update() {

    }

    float _length;
    float _angle;
}
