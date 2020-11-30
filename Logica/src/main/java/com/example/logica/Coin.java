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

    public void destroyCoin(){
        destroyingCoin = true;
    }

    public boolean finallyDestroy(){
        return erase;
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
        if(destroyingCoin){
            System.out.println(changeInSize);
            changeInSize += 0.05 * deltaTime;
            _w += changeInSize;
            _h += changeInSize;

            if(changeInSize > 0.025){
                erase = true;
            }
        }

    }
    boolean destroyingCoin = false;
    private boolean erase = false;
    float changeInSize = 0;
}
