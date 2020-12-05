package com.example.logica;

public class Vector2D {
//Clase para crear y usar un vector 2D
    public Vector2D(float x, float y) {
        _x = x;
        _y = y;
    }
    //Método de normalizacion
    public void normalize(){
        float mag = (float)Math.sqrt((float)Math.pow( _x,2)+(float)Math.pow( _y,2));
        _x = _x / mag;
        _y = _y / mag;
    }

    public float getX() { return _x; }
    public float getY() { return _y; }
    public void setX(float newx) { _x = newx;}
    public void setY(float newy) { _y = newy; }

    float _x, _y;
}
