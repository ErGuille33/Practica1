package com.example.logica;

public class Segmento {
    public Segmento(float x1, float y1,float x2, float y2){
            vert1 = new Coordenada(x1,y1);
            vert2 = new Coordenada(x2,y2);
    }

    public void setVert1(float x, float y){
        vert1.set_x(x);
        vert1.set_y(y);
    }

    public void setVert2(float x, float y){
        vert2.set_x(x);
        vert2.set_y(y);
    }

    public Coordenada getVert1(){
        return vert1;

    }

    public Coordenada getVert2(){
        return vert2;
    }
    private Coordenada vert1;
    private Coordenada vert2;
}
