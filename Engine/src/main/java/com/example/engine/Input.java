package com.example.engine;

import java.awt.event.MouseListener;
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

        int id;
        Type type;
        int posX;
        int posY;
    }

    List<TouchEvent> getTouchEvents();
}
