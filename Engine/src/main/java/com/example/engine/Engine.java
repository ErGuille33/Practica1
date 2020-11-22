package com.example.engine;

import java.io.InputStream;

public interface Engine {
    Graphics getGraphics();
    Input getInput();
    InputStream openInputStream(String filename);
    void update();
    void render();
    boolean run() throws Exception;

}
