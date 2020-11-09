package com.example.logica;

import java.util.List;

public interface Input {
    class TouchEvent{
        enum tipoPulsacion{
            PUSLACION,
            LIBERACION,
            DESPLAZAMIENTO

        }
        int id;

        int posX;
        int posY;
    }

    List<TouchEvent> getTouchEvents();
}
