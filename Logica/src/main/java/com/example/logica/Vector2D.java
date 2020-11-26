package com.example.logica;

public class Vector2D {

    public Vector2D(float x, float y) {
        _x = x;
        _y = y;
    }
    public void normalize(){
        double mag = Math.sqrt((float)Math.pow( _x,2)+(float)Math.pow( _y,2));
        _x = _x / (float) mag;
        _y = _y / (float) mag;
    }
    float _x, _y;
}
