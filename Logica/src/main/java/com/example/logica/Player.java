package com.example.logica;

import com.example.engine.Graphics;
import com.example.engine.Input;

import java.util.ArrayList;
import java.util.Vector;

import static com.example.logica.Collisions.PerpendicularClockwise;
import static com.example.logica.Collisions.PerpendicularCounterClockwise;
import static com.example.logica.Collisions.sqrDistancePointPoint;
import static com.example.logica.Collisions.segmentsIntersection;
import static com.example.logica.Collisions.sqrDistancePointSegment;
import static com.example.logica.Collisions.getPerpecticularSegment;

public class Player extends Character {
    public Player(float x, float y, int w, int h, Vector2D vel, ArrayList<Nivel.Paths> path) {
        super(x, y, w, h, vel);
        paths = path;
        actualPath = 0;
        distancePlayer = 0;
        contSegmento = 0;
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
        }
    }


    public void render(Graphics g) {
        g.setColor("blue");
        super.render(g);
    }

    private void jump() {
        if(auxJump){
            if (dirRegular)
                auxCoord = PerpendicularClockwise(auxSegmento.getVert1().get_x(), auxSegmento.getVert1().get_y(), auxSegmento.getVert2().get_x(), auxSegmento.getVert2().get_y());

            else
                auxCoord = PerpendicularClockwise(actualSegmento.getVert1().get_x(), actualSegmento.getVert1().get_y(), actualSegmento.getVert2().get_x(), actualSegmento.getVert2().get_y());
        }
        else {
            if (!dirRegular)
                auxCoord = PerpendicularClockwise(auxSegmento.getVert1().get_x(), auxSegmento.getVert1().get_y(), auxSegmento.getVert2().get_x(), auxSegmento.getVert2().get_y());

            else
                auxCoord = PerpendicularClockwise(actualSegmento.getVert1().get_x(), actualSegmento.getVert1().get_y(), actualSegmento.getVert2().get_x(), actualSegmento.getVert2().get_y());
        }

        if (paths.get(actualPath).directions.size() <= 0) {
            _vel._x = auxCoord.get_x();
            _vel._y = auxCoord.get_y();
        } else {
            _vel._x = paths.get(actualPath).directions.get(contSegmento)._pos.get_x();
            _vel._y = paths.get(actualPath).directions.get(contSegmento)._pos.get_y();
        }
        _vel.normalize();
        distanceSegment = sqrDistancePointPoint(actualSegmento.getVert1(), actualSegmento.getVert2());

        logicX += _vel._x * 5;
        logicY += _vel._y * 5;

        distancePlayer = 0;
        isJumping = true;

        auxJump = true;

    }

    private void chooseNewSegmentAndDir() {

        auxSegmento = actualSegmento;
        if (dirRegular == true) {
            if (contSegmento >= lpSegments.get(actualPath).segments.size() - 1) {
                contSegmento = 0;
            } else contSegmento += 1;
            actualSegmento = lpSegments.get(actualPath).segments.get(contSegmento) ;
            _vel._x = actualSegmento.getVert2().get_x() - logicX;
            _vel._y = actualSegmento.getVert2().get_y() - logicY;

        } else {

            if (contSegmento <= 0) {
                contSegmento = lpSegments.get(actualPath).segments.size() - 1;
            } else {
                contSegmento -= 1;
            }
            actualSegmento = lpSegments.get(actualPath).segments.get(contSegmento);

            _vel._x = actualSegmento.getVert2().get_x() - logicX;
            _vel._y = actualSegmento.getVert2().get_y() - logicY;
        }

        _vel.normalize();

        distanceSegment = sqrDistancePointPoint(new Coordenada(logicX, logicY), actualSegmento.getVert2());

        auxJump = false;
        distancePlayer = 0;
    }

    public void detectCollision() {
        int lastPath;
        float auxSeg = 0;
        boolean coll = false;
        int i = 0;
        int j = 0;

        if (distancePlayer > 5) {
            while (!coll && i < lpSegments.size()) {
                while (!coll && j < lpSegments.get(i).segments.size()) {
                    collisionDistance = sqrDistancePointSegment(lpSegments.get(i).segments.get(j), new Coordenada(logicX, logicY));
                    if (collisionDistance <= 4) {
                        lastPath = actualPath;
                        actualPath = i;
                        if(!dirRegular){
                            contSegmento = j - 1;
                        }
                        else  contSegmento = j;
                        distanceSegment = 1;
                        distancePlayer = 0;
                        dirRegular = !dirRegular;

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

    @Override
    public void handleInput(Input.TouchEvent e) {

        if (!isJumping) {
            jump();
        }

    }

    public ArrayList<Nivel.Paths> paths = new ArrayList<Nivel.Paths>();

    public ArrayList<levelPathSegments> lpSegments = new ArrayList<levelPathSegments>();

    private float distancePlayer = 0;
    private float distanceSegment = 0;
    public int speed = 200;

    private Segmento actualSegmento;
    private Segmento auxSegmento;

    private Coordenada auxCoord;

    private int actualPath;
    private int contSegmento = 0;
    private boolean auxJump = false;



    private boolean isJumping = false;

    private float collisionDistance = 0;


    //SI recorrera los vertices en sentido ascendente o descendente
    private boolean dirRegular = false;

    class levelPathSegments {
        public ArrayList<Segmento> segments = new ArrayList<Segmento>();
    }
}
