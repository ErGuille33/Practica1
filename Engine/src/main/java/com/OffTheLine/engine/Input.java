package com.OffTheLine.engine;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public interface Input {
    class TouchEvent {
        public enum Type{
            PULSACION,
            LIBERACION,
            DESPLAZAMIENTO
        }

        public TouchEvent(int i, Type t, int x, int y) {
            id = i;
            type = t;
            posX = x;
            posY = y;
        }

        public int getPosX(){return posX;}
        public int getPosY(){return posY;}

        public Type getType(){return type;}

        public int getId(){return id;}

        int id;
        Type type;
        int posX;
        int posY;
    }

    ArrayList<TouchEvent> getTouchEvents();
}
