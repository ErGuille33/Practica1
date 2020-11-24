package com.example.logica;

import com.example.engine.Graphics;

public class Player extends Character {
    public Player(float x, float y, int w, int h, Vector2D vel) {
        super(x, y, w, h, vel);
    }

    public void render(Graphics g) {
        g.setColor("blue");
        super.render(g);
    }
}
