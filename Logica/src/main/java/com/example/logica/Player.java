package com.example.logica;

import com.example.engine.Graphics;
import com.example.engine.Input;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.Segment;

import static com.example.logica.Collisions.PerpendicularClockwise;
import static com.example.logica.Collisions.PerpendicularCounterClockwise;
import static com.example.logica.Collisions.sqrDistancePointPoint;

import static com.example.logica.Collisions.sqrDistancePointSegment;


public class Player extends Character {
    public Player(float x, float y, int w, int h, Vector2D vel, ArrayList<Nivel.Paths> path) {
        super(x, y, w, h, vel);
        paths = path;
        actualPath = 0;
        distancePlayer = 0;
        setAllSegments();
        chooseNewSegmentAndDir();
    }

    private void setAllSegments() {

        for (int i = 0; i < paths.size(); i++) {
            lpSegments.add(new levelPathSegments());
            for (int j = 0; j < paths.get(i).vertices.size(); j++) {
                int auxNum = j + 1;
                if (j >= paths.get(i).vertices.size() - 1) {
                    auxNum = 0;
                }
                lpSegments.get(i).segments.add(new Segmento(paths.get(i).vertices.get(j)._pos.get_x(), paths.get(i).vertices.get(j)._pos.get_y(),
                        paths.get(i).vertices.get(auxNum)._pos.get_x(), paths.get(i).vertices.get(auxNum)._pos.get_y(), i));


            }
            for (int j = 0; j < paths.get(i).vertices.size(); j++) {
                if (j == 0) {
                    lpSegments.get(i).segments.get(j).setNextSegmento(lpSegments.get(i).segments.get(j + 1));
                    lpSegments.get(i).segments.get(j).setPreSegmento(lpSegments.get(i).segments.get(paths.get(i).vertices.size() - 1));

                } else if (j == paths.get(i).vertices.size() - 1) {
                    lpSegments.get(i).segments.get(j).setNextSegmento(lpSegments.get(i).segments.get(0));
                    lpSegments.get(i).segments.get(j).setPreSegmento(lpSegments.get(i).segments.get(j - 1));
                } else {
                    lpSegments.get(i).segments.get(j).setNextSegmento(lpSegments.get(i).segments.get(j + 1));
                    lpSegments.get(i).segments.get(j).setPreSegmento(lpSegments.get(i).segments.get(j - 1));
                }
                if (paths.get(i).directions.size() > 0) {
                    lpSegments.get(i).segments.get(j).setDir(paths.get(i).directions.get(j)._pos);
                }
                lpSegments.get(i).segments.get(j).setInverted();
            }
        }

        actualSegmento = lpSegments.get(actualPath).segments.get(lpSegments.get(0).segments.size() - 1);

    }


    public void render(Graphics g) {
        g.setColor("blue");
        if (dead) {
            //10 segmentos de 6 pixeles

        } else {
            super.render(g);
        }
    }

    private void jump() {

        if (paths.get(actualPath).directions.size() <= 0) {
            auxCoord = PerpendicularClockwise(actualSegmento.getVert1().get_x(), actualSegmento.getVert1().get_y(), actualSegmento.getVert2().get_x(), actualSegmento.getVert2().get_y());
        } else {
            auxCoord = actualSegmento.getDir();
        }

        setNewDir(auxCoord);

        speed = speed * 2;

        distancePlayer = 0;
        isJumping = true;

    }

    private void chooseNewSegmentAndDir() {

        if (dirRegular == true) {
            actualSegmento = actualSegmento.getNextSegmento();

        } else {
            actualSegmento = actualSegmento.getPreSegmento();
        }
        setNewDir(actualSegmento);

    }

    private void setNewDir(Coordenada coor) {
        _vel._x = auxCoord.get_x();
        _vel._y = auxCoord.get_y();

        _vel.normalize();
        distanceSegment = sqrDistancePointPoint(actualSegmento.getVert1(), actualSegmento.getVert2());
        distancePlayer = 0;
    }

    private void setNewDir(Segmento seg) {
        if (dirRegular) {
            _vel._x = actualSegmento.getVert2().get_x() - logicX;
            _vel._y = actualSegmento.getVert2().get_y() - logicY;
            distanceSegment = sqrDistancePointPoint(new Coordenada(logicX, logicY), actualSegmento.getVert2());
        } else {
            _vel._x = actualSegmento.getInvertedSegmento().getVert2().get_x() - logicX;
            _vel._y = actualSegmento.getInvertedSegmento().getVert2().get_y() - logicY;
            distanceSegment = sqrDistancePointPoint(new Coordenada(logicX, logicY), actualSegmento.getInvertedSegmento().getVert2());
        }

        _vel.normalize();

        distancePlayer = 0;
    }

    public void detectCollision() {

        boolean coll = false;
        int i = 0;
        int j = 0;

        if (distancePlayer > 5) {
            while (!coll && i < lpSegments.size()) {
                while (!coll && j < lpSegments.get(i).segments.size()) {
                    collisionDistance = sqrDistancePointSegment(lpSegments.get(i).segments.get(j), new Coordenada(logicX, logicY));
                    if (collisionDistance <= 1) {
                        actualPath = i;
                        speed = speed / 2;
                        distanceSegment = 1;
                        distancePlayer = 0;
                        dirRegular = !dirRegular;
                        actualSegmento = lpSegments.get(i).segments.get(j);
                        setNewDir(actualSegmento);
                        isJumping = false;
                        coll = true;
                    }
                    j++;
                }
                j = 0;
                i++;
            }
        }
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
        if(!dead) {
            if (isJumping) {
                detectCollision();
            }
            if (!isJumping && distancePlayer >= distanceSegment) {
                chooseNewSegmentAndDir();
            }
            logicX += _vel._x * speed * deltaTime;
            logicY += _vel._y * speed * deltaTime;

            distancePlayer += speed * deltaTime;
        }
        if (dead && !createdSegments) {
            createdSegments = true;
            createDestroyedSegments();
        }
    }

    private void createDestroyedSegments() {
        Random r = new Random();
        float rnd1;

        for (int i = 0; i < 10; i++) {
            rnd1 = r.nextFloat();
            if(rnd1 < 0.5)
                destroyedSegments.add(new Segmento( logicX , logicY, logicX , logicY + 6 ,0));

            else
                destroyedSegments.add(new Segmento( logicX , logicY, logicX+6 , logicY ,0));
            randDir.add( new Coordenada(r.nextFloat(),r.nextFloat()));
        }
    }

    @Override
    public void handleInput(Input.TouchEvent e) {
        if (!isJumping) {
            jump();
        }
    }

    public ArrayList<Nivel.Paths> paths = new ArrayList<Nivel.Paths>();

    public ArrayList<levelPathSegments> lpSegments = new ArrayList<levelPathSegments>();

    public ArrayList<Segmento> destroyedSegments = new ArrayList<Segmento>();
    public ArrayList<Coordenada> randDir = new ArrayList<Coordenada>();

    private boolean createdSegments = false;

    private float distancePlayer = 0;
    private float distanceSegment = 0;
    public int speed = 250;

    private Segmento actualSegmento;

    private Coordenada auxCoord;

    private int actualPath;

    public boolean isJumping = false;

    public boolean dead = false;

    private float collisionDistance = 0;

    //SI recorrera los vertices en sentido ascendente o descendente
    private boolean dirRegular = true;

    class levelPathSegments {
        public ArrayList<Segmento> segments = new ArrayList<Segmento>();
    }
}
