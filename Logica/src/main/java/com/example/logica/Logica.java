package com.example.logica;

import com.example.engine.Engine;
import com.github.cliftonlabs.json_simple.JsonException;

import java.io.IOException;

public class Logica {

    public Logica(Engine engine) {
        _engine = engine;
    }

    public void run() throws Exception {

        jsonHandler = new JsonHandler(_engine);
        jsonHandler.JsonParseFile();
        _engine.run();
    }

    public void update() {
        _engine.update();
    }

    Engine _engine;
    JsonHandler jsonHandler;

}