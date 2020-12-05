package com.example.logica;

import com.example.engine.Graphics;
import com.example.engine.Input;
//Clase Gameobject. Cuenta unicamente con posición, dimensiones, velocidad y rotacion
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
    public void setH(float h) {
        _h = h;
    }
    public void setW(float w) {
        _w= w;
    }

    public abstract void render(Graphics g);
    public abstract void update(float deltaTime);
    public abstract void handleInput(Input.TouchEvent e);


    float _x, _y;
    float _w, _h;
    float _rot;
    Vector2D _vel;
    boolean visible = true;

}
