package com.example.logica;

import com.example.engine.Graphics;

import java.util.ArrayList;
import java.util.Vector;

import static com.example.logica.Collisions.sqrDistancePointPoint;
import static com.example.logica.Collisions.segmentsIntersection;
import static com.example.logica.Collisions.sqrDistancePointSegment;
import static com.example.logica.Collisions.getPerpecticularSegment;

public class Player extends Character {
    public Player(float x, float y, int w, int h, Vector2D vel, ArrayList<Nivel.Paths> path) {
        super(x, y, w, h, vel);
        paths = path;
        vert1 = 0;
        vert2 = 0;
        actualPath = 0;
        setAllSegments();
        actualSegmento =  segments.get(0);
        chooseNewSegmentAndDir();
    }

    private void setAllSegments(){
        for(int i = 0; i < paths.size(); i++){
            for(int j = 0; j < paths.get(i).vertices.size(); j++){
                segments.add(new Segmento(paths.get(i).vertices.get(j)._pos.get_x(),paths.get(i).vertices.get(j)._pos.get_y(),
                        paths.get(i).vertices.get(j)._pos.get_x(), paths.get(i).vertices.get(j)._pos.get_y()));
            }
        }
    }

    public void render(Graphics g) {
        g.setColor("blue");
        super.render(g);
    }



    private void jump(){

    }

    private void chooseNewSegmentAndDir(){
        if(dirRegular == true) {
            vert1 = vert2;
            if(vert2 >= paths.get(actualPath).vertices.size() -1 ) {
                vert2 = 0;
            }
            else vert2+=1;
            actualSegmento.setVert1(paths.get(actualPath).vertices.get(vert1)._pos.get_x(),paths.get(actualPath).vertices.get(vert1)._pos.get_y());
            actualSegmento.setVert2(paths.get(actualPath).vertices.get(vert2)._pos.get_x(),paths.get(actualPath).vertices.get(vert2)._pos.get_y());
            _vel._x = actualSegmento.getVert2().get_x() - logicX ;
            _vel._y =  actualSegmento.getVert2().get_y() - logicY;
        } else {
            vert2 = vert1;
            if(vert1 == 0 ) {
                vert1 = paths.get(actualPath).vertices.size() -1;
            }
            else vert1-=1;
            actualSegmento.setVert1(paths.get(actualPath).vertices.get(vert1)._pos.get_x(),paths.get(actualPath).vertices.get(vert1)._pos.get_y());
            actualSegmento.setVert2(paths.get(actualPath).vertices.get(vert2)._pos.get_x(),paths.get(actualPath).vertices.get(vert2)._pos.get_y());
            _vel._x = actualSegmento.getVert1().get_x() - logicX;
            _vel._y =  actualSegmento.getVert1().get_y() - logicY;

        }

        _vel.normalize();

        distanceSegment = sqrDistancePointPoint(actualSegmento.getVert1(),actualSegmento.getVert2());
        distancePlayer = 0;
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
        if(distancePlayer >= distanceSegment){
                chooseNewSegmentAndDir();
        }
        if(distancePlayer >= distanceSegment/2){
            actualSegmento = getPerpecticularSegment(actualSegmento, logicX,logicY);
            _vel._x = actualSegmento.getVert2().get_x() - logicX;
            _vel._y =  actualSegmento.getVert2().get_y() - logicY;
            _vel.normalize();
        }


        logicX += _vel._x * speed *  deltaTime;
        logicY += _vel._y * speed *  deltaTime;

        distancePlayer += speed * deltaTime;


    }

    public ArrayList<Nivel.Paths> paths = new ArrayList<Nivel.Paths>();

    public ArrayList<Segmento> segments = new ArrayList<Segmento>();

    private float distancePlayer = 0;
    private float distanceSegment= 0;
    public int speed = 250;

    private Segmento actualSegmento;
    private int actualPath;

    private Nivel.Vertice nextVertice;

    private int vert1,vert2;



    //SI recorrera los vertices en sentido ascendente o descendente
    private boolean dirRegular = true;
}
