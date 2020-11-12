package com.example.logica;

import com.example.engine.Engine;

public class Logica {

    public Logica(Engine engine) {
        _engine = engine;
    }

    void run() {
        _engine.run();
    }

    void update() {
        _engine.update();
    }

    Engine _engine;

}