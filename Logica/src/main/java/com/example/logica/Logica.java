package com.example.logica;

import com.example.engine.Engine;
import com.github.cliftonlabs.json_simple.JsonException;

import java.io.IOException;

public class Logica {

    public Logica(Engine engine) {
        _engine = engine;
    }

    public void run() throws Exception {

        Nivel nivelActual = new Nivel(7);
        nivelActual.cargaNivel();
        _engine.run();
    }

    public void update() {
        _engine.update();
    }

    Engine _engine;


}