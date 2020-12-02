package com.example.logica;

public class  Collisions {
    public static float sqrDistancePointPoint(Coordenada p1, Coordenada p2){
        float ret = 0.0f;
        ret = (float) Math.sqrt(Math.pow(p1.get_x() - p2.get_x(), 2) + Math.pow(p1.get_y() - p2.get_y(), 2));
        return ret;
    }

    public static Coordenada segmentsIntersection(Segmento s1, Segmento s2){
        Coordenada p = null;
        float s1_x, s1_y, s2_x, s2_y;
        s1_x = s1.getVert2().get_x() - s1.getVert1().get_x();     s1_y = s1.getVert2().get_y() - s1.getVert1().get_y();
        s2_x = s2.getVert2().get_x() - s2.getVert1().get_x();     s2_y = s2.getVert2().get_y() - s2.getVert1().get_y();

        float s, t;
        s = (-s1_y * (s1.getVert1().get_x() - s2.getVert1().get_x()) + s1_x * (s1.getVert1().get_y() - s2.getVert1().get_y())) / (-s2_x * s1_y + s1_x * s2_y);
        t = ( s2_x * (s1.getVert1().get_y() - s2.getVert1().get_y()) - s2_y * (s1.getVert1().get_x() - s2.getVert1().get_x())) / (-s2_x * s1_y + s1_x * s2_y);

        if (s >= 0 && s <= 1 && t >= 0 && t <= 1)
        {
            p = new Coordenada(s1.getVert1().get_x() + (t * s1_x),s1.getVert1().get_y() + (t * s1_y));
            // Collision detected
        }
        return p;
    }

    public static float sqrDistancePointSegment(Segmento seg, Coordenada p){

        float A = p.get_x() - seg.getVert1().get_x();
        float B = p.get_y() - seg.getVert1().get_y();
        float C = seg.getVert2().get_x() - seg.getVert1().get_x();
        float D = seg.getVert2().get_y() - seg.getVert1().get_y();
        float E = Math.abs( p.get_x() - seg.getVert2().get_x());
        float F = Math.abs(p.get_y() - seg.getVert2().get_y());
        float G = Math.abs( p.get_x() - seg.getVert1().get_x());
        float H = Math.abs(p.get_y() - seg.getVert1().get_y());

        float dot = A * C + B * D;
        float len_sq = C * C + D * D;
        float param = -1;
        if (len_sq != 0) //in case of 0 length line
            param = dot / len_sq;

        float xx, yy;

        if (param < 0) {
            xx = seg.getVert1().get_x();
            yy = seg.getVert1().get_y();
        }
        else if (param > 1) {
            xx = seg.getVert2().get_x();
            yy = seg.getVert2().get_y();
        }
        else {
            xx = seg.getVert1().get_x() + param * C;
            yy = seg.getVert1().get_y() + param * D;
        }

        float dx = p.get_x() - xx;
        float dy = p.get_y() - yy;
        //Para que no detecte segmentos en la misma linea
        if((G <= 1.2 && H <= 1.2) || (E <= 1.2 && F <= 1.2)){
            return 1000;
        }
        return (float) Math.sqrt(dx * dx + dy * dy);
    }


    public static Coordenada PerpendicularClockwise(float x1, float y1 , float x2, float y2)
    {
        return new Coordenada(y2 - y1, -(x2-x1));
    }

    public static Coordenada PerpendicularCounterClockwise(float x1, float y1 , float x2, float y2)
    {
        return new Coordenada(-(y2 - y1), (x2-x1));
    }


}
