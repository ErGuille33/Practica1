package com.example.logica;

//CLase que usamos para los segmentos de los distintos paths, así como para hacer operaciones entre segmentos
public class Segmento {
    public Segmento(float x1, float y1, float x2, float y2, int _path) {
        vert1 = new Coordenada(x1, y1);
        vert2 = new Coordenada(x2, y2);
        path = _path;
        dir = new Coordenada(0, 0);

    }

    public void setVert1(float x, float y) {
        vert1.set_x(x);
        vert1.set_y(y);
    }

    public void setVert2(float x, float y) {
        vert2.set_x(x);
        vert2.set_y(y);
    }

    public Coordenada getVert1() {
        return vert1;

    }

    //Guardamos el siguiente segmento en orden del mismo path
    public void setNextSegmento(Segmento seg) {
        nextSegmento = seg;
    }

    //Guardamos el anterior segmento en orden del mismo path
    public void setPreSegmento(Segmento seg) {
        preSegmento = seg;
    }

    public Segmento getNextSegmento() {
        return nextSegmento;
    }

    //Guardamos tambien la direccion del segmento si es que la tiene
    public void setDir(Coordenada _dir) {
        this.dir = _dir;
    }

    public Coordenada getDir() {
        return dir;
    }

    public Segmento getPreSegmento() {
        return preSegmento;
    }

    public int getPath() {
        return path;
    }

    public Coordenada getVert2() {
        return vert2;
    }

    //Invertimos el segmento para cuando el jugador vaya en dirección invertida
    public void setInverted() {
        invertedSegmento = new Segmento(vert2.get_x(), vert2.get_y(), vert1.get_x(), vert1.get_y(), path);
    }

    public Segmento getInvertedSegmento() {
        return invertedSegmento;
    }

    private Coordenada vert1;
    private Coordenada vert2;

    private Segmento invertedSegmento;
    private Segmento nextSegmento;
    private Segmento preSegmento;
    private Coordenada dir;
    private int path;
}
