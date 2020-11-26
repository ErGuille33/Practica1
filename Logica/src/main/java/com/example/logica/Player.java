package com.example.logica;

import com.example.engine.Graphics;

import java.util.ArrayList;

public class Player extends Character {
    public Player(float x, float y, int w, int h, Vector2D vel, ArrayList<Nivel.Paths> path) {
        super(x, y, w, h, vel);
        paths = path;
        vert1 = 0;
        vert2 = 1;
        actualSegmento =  new Segmento(paths.get(0).vertices.get(vert1)._pos.get_x(),paths.get(vert1).vertices.get(0)._pos.get_y(), paths.get(0).vertices.get(vert2)._pos.get_x(), paths.get(0).vertices.get(vert2)._pos.get_y());
        nextVertice = paths.get(0).vertices.get(1);
        _vel = new Vector2D(actualSegmento.getVert2().get_x() - _x, actualSegmento.getVert2().get_y() - _y);
        _vel.normalize();
    }

    public void render(Graphics g) {
        g.setColor("blue");
        super.render(g);
    }

    public static float sqrDistancePointPoint(Coordenada p1, Coordenada p2){
        float ret = 0.0f;
        ret = (float) Math.sqrt(Math.pow(p1.get_x() - p2.get_x(), 2) + Math.pow(p1.get_y() - p2.get_y(), 2));
        return ret;
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
        if(sqrDistancePointPoint(new Coordenada(_x,_y), nextVertice._pos) < 10){

            if(dirRegular == true) {
                vert1 = vert2;
                if(vert2 >= paths.get(0).vertices.size() -1 ) {
                  vert2 = 0;
                }
                else vert2+=1;
                actualSegmento.setVert1(paths.get(0).vertices.get(vert1)._pos.get_x(),paths.get(0).vertices.get(vert1)._pos.get_y());
                actualSegmento.setVert2(paths.get(0).vertices.get(vert2)._pos.get_x(),paths.get(0).vertices.get(vert2)._pos.get_y());
                _vel._x = actualSegmento.getVert2().get_x() - _x;
                _vel._y =  actualSegmento.getVert2().get_y() - _y;
            } else {
                vert2 = vert1;
                if(vert1 == 0 ) {
                    vert1 = paths.get(0).vertices.size() -1;
                }
                else vert1-=1;
                actualSegmento.setVert1(paths.get(0).vertices.get(vert1)._pos.get_x(),paths.get(0).vertices.get(vert1)._pos.get_y());
                actualSegmento.setVert2(paths.get(0).vertices.get(vert2)._pos.get_x(),paths.get(0).vertices.get(vert2)._pos.get_y());
                _vel._x = actualSegmento.getVert1().get_x() - _x;
                _vel._y =  actualSegmento.getVert1().get_y() - _y;

            }

            _vel.normalize();
        }


        _x += _vel._x * speed *  deltaTime;
        _y += _vel._y * speed *  deltaTime;



    }

    public ArrayList<Nivel.Paths> paths = new ArrayList<Nivel.Paths>();
    public int speed = 250;

    private Segmento actualSegmento;
    private Segmento nextSegmento;

    private Nivel.Vertice nextVertice;

    private int vert1,vert2;




    //SI recorrera los vertices en sentido ascendente o descendente
    private boolean dirRegular = true;
}
