package com.example.logica;

import com.example.engine.Graphics;

public abstract class GameObject {

    public GameObject(float x, float y, int w, int h, Vector2D vel) {
        _x = x;
        _y = y;
        _w = w;
        _h = h;
        _vel = vel;
        _rot = 0;
    }

    public float getX() {return _x;}
    public float getY() {return _y;}
    public int getW() {return _w;}
    public int getH() {return _h;}
    public int getRot() {return _rot;}
    public Vector2D getVel() {return _vel;}

    public void setPos(float x, float y) {
        _x = x;
        _y = y;
    }

    public void setVel(Vector2D vel) {
        _vel = vel;
    }

    public void setRot(int rot) {
        _rot = rot;
    }

    public abstract void render(Graphics g);
    public abstract void update();

    float _x, _y;
    int _w, _h;
    int _rot;
    Vector2D _vel;

}
