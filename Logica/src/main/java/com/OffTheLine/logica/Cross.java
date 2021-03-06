package com.OffTheLine.logica;

import com.OffTheLine.engine.Graphics;
import com.OffTheLine.engine.Input;
//Cruces que indican las vidas perdidas
public class Cross extends GameObject{

    public Cross(float x, float y, float w, float h, Vector2D vel) {
        super(x, y, w, h, vel);
        l1 = new Line(x,y,w,h,vel);
        l2 = new Line(x,y,w,h,vel);
        l2._rot = l1._rot + 90;
    }

    @Override
    public void render(Graphics g) {
        if(visible) {
            g.setColor(0xFFFF0000);
            l1.render(g);
            l2.render(g);
        }
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void handleInput(Input.TouchEvent e) {

    }

    Line l1;
    Line l2;
}
