package com.example.engine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public interface Engine {
    Graphics getGraphics();
    Input getInput();
    InputStream openInputStream(String filename) throws IOException;
    FileReader openFileReader(String filename) throws IOException;
    void update();
    boolean run();

}
