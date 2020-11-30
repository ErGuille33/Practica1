package com.example.logica;

import com.example.engine.Graphics;

public class Coin extends Character {
    public Coin(float x, float y, int w, int h, float radius, float speed, float angle) {
        super(x + radius*(float)Math.cos(Math.toRadians(angle)), y + radius*(float)Math.sin(Math.toRadians(angle)), w, h, new Vector2D(0,0));
        _radius = radius+1;
        _speed = speed+1;
        _angle = angle+1;
        _rot2 = 0;
    }

    public void render(Graphics g) {
        g.setColor("yellow");
        super.render(g);
        g.save();
        g.rotate((int)_rot2);
        g.restore();
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
            changeInSize += 0.05 * deltaTime;
            _w += changeInSize;
            _h += changeInSize;

            if(changeInSize > 0.025){
                erase = true;
            }
        }
        _rot2+=_angle*deltaTime;

    }
    boolean destroyingCoin = false;
    private boolean erase = false;
    float changeInSize = 0;
    float _radius;
    float _speed;
    float _angle;
    float _rot2;
}
