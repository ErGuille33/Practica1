package com.example.logica;

import com.example.engine.Graphics;
import com.example.engine.Input;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.Segment;

import static com.example.logica.Collisions.PerpendicularClockwise;
import static com.example.logica.Collisions.PerpendicularCounterClockwise;
import static com.example.logica.Collisions.segmentsIntersection;
import static com.example.logica.Collisions.sqrDistancePointPoint;

import static com.example.logica.Collisions.sqrDistancePointSegment;


public class Player extends Character {
    public Player(float x, float y, int w, int h, Vector2D vel, ArrayList<Nivel.Paths> path, int difficulty) {
        super(x, y, w, h, vel);
        if(difficulty == 0){
            baseSpeed =250;
        }
        else baseSpeed = 400;
        speed = baseSpeed;
        paths = path;
        actualPath = 0;
        distancePlayer = 0;
        lastCoord = new Coordenada(x,y);
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
        g.setColor("player");
        if (dead) {
            for (Line lin : destroyedSegments
            ) {
                lin.render(g);
            }
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

        speed = 1500;

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
        Coordenada segmentCroos;
        int i = 0;
        int j = 0;
        segmentCroos = null;

        if (distancePlayer > 10) {
            while (!coll && i < lpSegments.size()) {
                while (!coll && j < lpSegments.get(i).segments.size()) {
                    if (lpSegments.get(i).segments.get(j) != actualSegmento){
                        segmentCroos = segmentsIntersection(new Segmento(lastCoord.get_x(), lastCoord.get_y(), logicX, logicY, 0), lpSegments.get(i).segments.get(j));
                }
                    if (segmentCroos != null) {
                        actualPath = i;
                        distancePlayer = 0;
                        dirRegular = !dirRegular;
                        speed = baseSpeed;
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
        if (!dead) {
            if (isJumping) {
                detectCollision();
            }
            if (!isJumping && distancePlayer >= distanceSegment) {
                chooseNewSegmentAndDir();
            }
            collFrames++;
            if(collFrames > 1) {
                lastCoord.set_x(logicX);
                lastCoord.set_y(logicY);
                collFrames = 0;
            }

            logicX += _vel._x * speed * deltaTime;
            logicY += _vel._y * speed * deltaTime;

            distancePlayer += speed * deltaTime;
        }
        if (dead) {
            if (!createdSegments) {
                createdSegments = true;
                createDestroyedSegments();
            }
            for (Line lin : destroyedSegments
            ) {
                lin.update(deltaTime);
            }
        }


    }

    private void createDestroyedSegments() {
        Random r = new Random();
        float rnd1;
        float rnd2;
        int rnd3;
        float randRot;

        for (int i = 0; i < 10; i++) {
            rnd1 = r.nextFloat();
            rnd2 = r.nextFloat();
            rnd3 = r.nextInt(4);
            randRot = r.nextInt(100)- 50;
            switch (rnd3){
                case 0:
                    rnd1 = -rnd1;
                    rnd2 = -rnd2;
                    destroyedSegments.add(new Line(logicX, logicY, 6, 0, new Vector2D(rnd1, rnd2)));
                    break;
                case 1:
                    rnd1 = rnd1;
                    rnd2 = -rnd2;
                    destroyedSegments.add(new Line(logicX, logicY, 0, 6, new Vector2D(rnd1, rnd2)));
                    break;
                case 2:
                    rnd1 = -rnd1;
                    rnd2 = rnd2;
                    destroyedSegments.add(new Line(logicX, logicY, 0, 6, new Vector2D(rnd1, rnd2)));
                    break;
                case 3:
                    destroyedSegments.add(new Line(logicX, logicY, 6, 0, new Vector2D(rnd1, rnd2)));
                    break;
            }
            destroyedSegments.get(i).rotacion = randRot;
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

    public ArrayList<Line> destroyedSegments = new ArrayList<Line>();

    private boolean createdSegments = false;

    private float distancePlayer = 0;
    private float distanceSegment = 0;
    public int speed;
    public int baseSpeed;

    private Segmento actualSegmento;

    private Coordenada auxCoord;

    private int actualPath;
    public Coordenada lastCoord;

    public boolean isJumping = false;
    int collFrames = 0;

    public boolean dead = false;

    //SI recorrera los vertices en sentido ascendente o descendente
    private boolean dirRegular = true;

    class levelPathSegments {
        public ArrayList<Segmento> segments = new ArrayList<Segmento>();
    }
}
