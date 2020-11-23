package com.example.logica;

import com.example.engine.Graphics;

public class Cuadrao extends GameObject {

    public Cuadrao(int x, int y, int w, int h, Vector2D vel, double rot) {
        super(x, y, w, h, vel, rot);
    }

    public void render(Graphics g) {
        g.setColor("green");
        g.drawLine(_x, _y, _x + _w, _y);
        g.drawLine(_x + _w, _y, _x + _w, _y + _h);
        g.drawLine(_x + _w, _y + _h, _x , _y + _h);
        g.drawLine(_x , _y + _h, _x, _y);

    }

    public void update() {

    }

}
