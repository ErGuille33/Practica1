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

    @Override
    public void render(Graphics g) {
        g.setColor("yellow");
        g.save();
        g.rotate((int)_rot2);
        g.translate((int)(_x+_w/2), (int)(_y+_h/2));
        g.rotate((int)_rot);
        g.translate((int)(-_x-_w/2), (int)(-_y-_h/2));
        g.drawLine((int)_x, (int)_y, (int)(_x + _w), (int)_y);
        g.drawLine((int)(_x + _w), (int)_y, (int)(_x + _w), (int)(_y + _h));
        g.drawLine((int)(_x + _w), (int)(_y + _h), (int)_x , (int)(_y + _h));
        g.drawLine((int)_x , (int)(_y + _h), (int)_x, (int)_y);
        g.restore();
    }

    public void destroyCoin(){
        destroyingCoin = true;

    }
    public void pickedCoin(){
        picked = true;
    }

    public boolean finallyDestroy(){
        return erase;
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
        if(destroyingCoin){



            if(_w > 30){
                erase = true;
            }
        }
        _rot2+=_speed*deltaTime;
    }
    boolean destroyingCoin = false;
    private boolean erase = false;
    public boolean picked = false;
    float changeInSize = 0;


    float _radius;
    float _speed;
    float _angle;
    float _rot2;
}
