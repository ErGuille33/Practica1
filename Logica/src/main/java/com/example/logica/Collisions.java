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
        t = ( s2_x * (s1.getVert1().get_y() - s2.getVert1().get_y()) - s2_y * (s1.getVert1().get_y() - s2.getVert1().get_y())) / (-s2_x * s1_y + s1_x * s2_y);

        if (s >= 0 && s <= 1 && t >= 0 && t <= 1)
        {
            // Collision detected
            p.set_x(s1.getVert1().get_x() + (t * s1_x));
            p.set_y(s1.getVert1().get_y() + (t * s1_y));
        }
        return p;
    }

    public static float sqrDistancePointSegment(Segmento seg, Coordenada p){
        float A = p.get_x() -  seg.getVert1().get_x(); // position of point rel one end of line
        float B = p.get_y() - seg.getVert1().get_y();
        float C = seg.getVert2().get_x() - seg.getVert1().get_x(); // vector along line
        float D = seg.getVert2().get_y() - seg.getVert1().get_y();
        float E = -D; // orthogonal vector
        float F = C;

        float dot = A * E + B * F;
        float len_sq = E * E + F * F;

        return (float) (Math.abs(dot) / Math.sqrt(len_sq));
    }

    public static Segmento getPerpecticularSegment(Segmento seg, float x, float y) {
        Segmento retSegment = new Segmento(y-(seg.getVert2().get_y() - seg.getVert1().get_y()), x+(seg.getVert2().get_x() - seg.getVert1().get_x()),
                  y+(seg.getVert2().get_y() - seg.getVert1().get_y()),x -(seg.getVert2().get_x() - seg.getVert1().get_x()));
        return retSegment;
    }

    public static float angle (float x1, float y1, float x2, float y2) {
        float xdiff = x1 - x2;
        float ydiff = y1 - y2;
        //double tan = xdiff / ydiff;
        float atan = (float) Math.atan2(ydiff, xdiff);
        return atan;
    }
}
