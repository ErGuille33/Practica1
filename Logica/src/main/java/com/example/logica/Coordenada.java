package com.example.logica;

public class Coordenada {
    Coordenada(float x, float y){
        _x = x;
        _y = y;
    }

    public float get_x(){
        return _x;
    }

    public float get_y(){
        return _y;
    }

    public void set_y(float y){
        _y = y;
    }

    public void set_x(float x){
        _x = x;
    }

    private float _x;
    private float _y;
}
