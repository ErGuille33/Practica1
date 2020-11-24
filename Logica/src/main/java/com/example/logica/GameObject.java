package com.example.logica;

import com.example.engine.Graphics;

public abstract class GameObject {

    public GameObject(float x, float y, float w, float h, Vector2D vel) {
        _x = x;
        _y = y;
        _w = w;
        _h = h;
        _vel = vel;
        _rot = 0;
    }

    public float getX() {return _x;}
    public float getY() {return _y;}
    public float getW() {return _w;}
    public float getH() {return _h;}
    public float getRot() {return _rot;}
    public Vector2D getVel() {return _vel;}

    public void setPos(int x, int y) {
        _x = x;
        _y = y;
    }

    public void setVel(Vector2D vel) {
        _vel = vel;
    }

    public void setRot(float rot) {
        _rot = rot;
    }

    public abstract void render(Graphics g);
    public abstract void update(float deltaTime);

    float _x, _y;
    float _w, _h;
    float _rot;
    Vector2D _vel;

}
