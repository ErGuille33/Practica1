package com.example.logica;

import com.example.engine.Graphics;
import com.example.engine.Input;

public class Square extends GameObject {
    public Square(float x, float y, float w, float h, Vector2D vel) {
        super(x, y, w, h, vel);
    }

    @Override
    public void render(Graphics g) {
        if(visible) {
            g.save();
            g.setColor("cyan");
            g.drawLine((int) _x, (int) _y, (int) (_x + _w), (int) _y);
            g.drawLine((int) (_x + _w), (int) _y, (int) (_x + _w), (int) (_y + _h));
            g.drawLine((int) (_x + _w), (int) (_y + _h), (int) _x, (int) (_y + _h));
            g.drawLine((int) _x, (int) (_y + _h), (int) _x, (int) _y);
            g.restore();
        }
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void handleInput(Input.TouchEvent e) {

    }
}
