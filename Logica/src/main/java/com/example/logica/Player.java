package com.example.logica;

import com.example.engine.Graphics;
import com.example.engine.Input;

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
        actualSegmento = segments.get(0);
        chooseNewSegmentAndDir();
    }

    private void setAllSegments() {

        for (int i = 0; i < paths.size(); i++) {
            for (int j = 0; j < paths.get(i).vertices.size(); j++) {
                int auxNum = j +1 ;
                if (j >= paths.get(i).vertices.size() - 1) {
                    auxNum = 0;
                }
                segments.add(new Segmento(paths.get(i).vertices.get(j)._pos.get_x(), paths.get(i).vertices.get(j)._pos.get_y(),
                        paths.get(i).vertices.get(auxNum)._pos.get_x(), paths.get(i).vertices.get(auxNum)._pos.get_y(),i));
            }
        }
    }


    public void render(Graphics g) {
        g.setColor("blue");
        super.render(g);
    }

    private void jump() {

        if (distancePlayer >= distanceSegment / 2 && aux) {
            auxSegmento = getPerpecticularSegment(actualSegmento, logicX, logicY);
            if (paths.get(actualPath).directions.size() <= 0) {

                if ((int) auxSegmento.getVert1().get_x() == (int) auxSegmento.getVert2().get_x()) {
                    if (Math.abs((int) auxSegmento.getVert1().get_y()) < Math.abs((int) auxSegmento.getVert2().get_y())) {
                        _vel._x = auxSegmento.getVert1().get_x() - logicX;
                        _vel._y = auxSegmento.getVert1().get_y() - logicY;
                    } else {
                        _vel._x = auxSegmento.getVert2().get_x() - logicX;
                        _vel._y = auxSegmento.getVert2().get_y() - logicY;
                    }
                } else {
                    if (Math.abs((int) auxSegmento.getVert1().get_x()) < Math.abs((int) auxSegmento.getVert2().get_x())) {
                        _vel._x = auxSegmento.getVert1().get_x() - logicX;
                        _vel._y = auxSegmento.getVert1().get_y() - logicY;
                    } else {
                        _vel._x = auxSegmento.getVert2().get_x() - logicX;
                        _vel._y = auxSegmento.getVert2().get_y() - logicY;
                    }
                }
            } else {
                _vel._x = paths.get(actualPath).directions.get(vert1)._pos.get_x();
                _vel._y = paths.get(actualPath).directions.get(vert1)._pos.get_y();
            }
            _vel.normalize();
            distanceSegment = sqrDistancePointPoint(actualSegmento.getVert1(), actualSegmento.getVert2());

            distancePlayer = 0;
            collWithSegment = false;
            aux = false;
            isJumping = true;
        }
    }

    private void chooseNewSegmentAndDir() {

        if (dirRegular == true) {
            vert1 = vert2;
            if (vert2 >= paths.get(actualPath).vertices.size() - 1) {
                vert2 = 0;
            } else vert2 += 1;
            actualSegmento.setVert1(paths.get(actualPath).vertices.get(vert1)._pos.get_x(), paths.get(actualPath).vertices.get(vert1)._pos.get_y());
            actualSegmento.setVert2(paths.get(actualPath).vertices.get(vert2)._pos.get_x(), paths.get(actualPath).vertices.get(vert2)._pos.get_y());
            _vel._x = actualSegmento.getVert2().get_x() - logicX;
            _vel._y = actualSegmento.getVert2().get_y() - logicY;
        } else {
            vert2 = vert1;
            if (vert1 == 0) {
                vert1 = paths.get(actualPath).vertices.size() - 1;
            } else vert1 -= 1;
            actualSegmento.setVert1(paths.get(actualPath).vertices.get(vert1)._pos.get_x(), paths.get(actualPath).vertices.get(vert1)._pos.get_y());
            actualSegmento.setVert2(paths.get(actualPath).vertices.get(vert2)._pos.get_x(), paths.get(actualPath).vertices.get(vert2)._pos.get_y());
            _vel._x = actualSegmento.getVert2().get_x() - logicX;
            _vel._y = actualSegmento.getVert2().get_y() - logicY;

        }

        _vel.normalize();

        distanceSegment = sqrDistancePointPoint(new Coordenada(logicX,logicY), actualSegmento.getVert2());
        distancePlayer = 0;
    }

    public void detectCollision(){
        boolean coll = false;
        int i = 0;
        if(sqrDistancePointSegment(actualSegmento,new Coordenada(logicX, logicY)) > 20) {
            while (!coll && i < segments.size()) {
                collisionDistance = sqrDistancePointSegment(segments.get(i), new Coordenada(logicX, logicY));
                if (collisionDistance <= 2) {
                    coll = true;
                    lastPath = actualPath;
                    actualPath = segments.get(i).getPath();
                    actualSegmento = segments.get(i);
                    collWithSegment = true;
                    isJumping = false;
                    dirRegular = !dirRegular;
                    distancePlayer = 0;
                    distanceSegment = 0;

                    if (dirRegular == true) {
                        vert2 = i  - (actualPath  * (paths.get(lastPath).vertices.size()) );
                    } else vert1= i  - (actualPath * (paths.get(lastPath).vertices.size()) );

                }
                i++;
            }
        }


    }

    public void update(float deltaTime) {
        super.update(deltaTime);
        if (collWithSegment && distancePlayer >= distanceSegment ) {
            chooseNewSegmentAndDir();

        }
        if (!isJumping) {
            jump();
        }
        else {
            detectCollision();
        }


        logicX += _vel._x * speed * deltaTime;
        logicY += _vel._y * speed * deltaTime;
        if(!isJumping) {
            distancePlayer += speed * deltaTime;
        }

    }

    @Override
    public void handleInput(Input.TouchEvent e) {


    }

    public ArrayList<Nivel.Paths> paths = new ArrayList<Nivel.Paths>();

    public ArrayList<Segmento> segments = new ArrayList<Segmento>();

    private float distancePlayer = 0;
    private float distanceSegment = 0;
    public int speed = 250;

    private Segmento actualSegmento;
    private Segmento auxSegmento;

    private int lastPath;
    private int actualPath;

    private int vert1, vert2;

    private boolean isJumping = false;

    private float collisionDistance = 0;
    private boolean collWithSegment = true;

    //SI recorrera los vertices en sentido ascendente o descendente
    private boolean dirRegular = true;
    private boolean aux = true;
}
