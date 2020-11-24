package com.example.logica;

import com.example.engine.Graphics;

public class Coin extends Character {
    public Coin(float x, float y, int w, int h) {
        super(x, y, w, h, new Vector2D(0,0));
    }

    public void render(Graphics g) {
        g.setColor("yellow");
        super.render(g);
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
