package com.example.logica;

import com.example.engine.Graphics;

public class Cuadrao extends GameObject {

    public Cuadrao(int x, int y, int w, int h, Vector2D vel) {
        super(x, y, w, h, vel);
    }

    public void render(Graphics g) {
        g.setColor("green");
        g.drawLine((int)_x, (int)_y, (int)(_x + _w), (int)_y);
        g.drawLine((int)(_x + _w), (int)_y, (int)(_x + _w), (int)(_y + _h));
        g.drawLine((int)(_x + _w), (int)(_y + _h), (int)_x , (int)(_y + _h));
        g.drawLine((int)_x , (int)(_y + _h), (int)_x, (int)_y);
    }

    public void update() {

    }

}
