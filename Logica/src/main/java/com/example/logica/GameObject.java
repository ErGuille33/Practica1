package com.example.logica;

import com.example.engine.Graphics;

public abstract class GameObject {

    public GameObject(int x, int y, int w, int h, Vector2D vel, double rot) {
        _x = x;
        _y = y;
        _w = w;
        _h = h;
        _vel = vel;
        _rot = rot;
    }

    public int getX() {return _x;}
    public int getY() {return _y;}
    public int getW() {return _w;}
    public int getH() {return _h;}
    public double getRot() {return _rot;}
    public Vector2D getVel() {return _vel;}

    public void setPos(int x, int y) {
        _x = x;
        _y = y;
    }

    public void setVel(Vector2D vel) {
        _vel = vel;
    }

    public void setRot(double rot) {
        _rot = rot;
    }

    public abstract void render(Graphics g);
    public abstract void update();

    int _x, _y;
    int _w, _h;
    double _rot;
    Vector2D _vel;

}
