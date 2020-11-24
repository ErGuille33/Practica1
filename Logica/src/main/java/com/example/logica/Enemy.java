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
        g.save();
        g.translate((int)-_x, (int)-_y);
        g.rotate((int)_angle);
        g.drawLine((int)-_length/2, 0, (int)_length/2, 0);
        g.restore();
    }

    public void update(float deltaTime) {

    }

    float _length;
    float _angle;
}
