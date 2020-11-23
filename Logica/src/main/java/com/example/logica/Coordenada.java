package com.example.logica;

public class Coordenada {
    Coordenada(double x, double y){
        _x = x;
        _y = y;
    }

    public double get_x(){
        return _x;
    }

    public double get_y(){
        return _y;
    }

    public void set_y(double y){
        _y = y;
    }

    public void set_x(double x){
        _x = x;
    }

    private double _x;
    private double _y;
}
