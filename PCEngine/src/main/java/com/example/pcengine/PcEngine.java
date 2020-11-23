package com.example.pcengine;

import com.example.engine.Engine;
import com.example.engine.Graphics;
import com.example.engine.Input;
import com.example.engine.Logica;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;

import sun.rmi.runtime.Log;

public class PcEngine implements Engine {

    public PcEngine(Logica _logic){logica=_logic;}

    public Graphics getGraphics() {
        return _graphics;
    }

    public Input getInput() {
        return _input;
    }

    public InputStream openInputStream(String filename) {
        try (InputStream is = new FileInputStream("assets/" +filename)) {
            return is;
        }
        catch (Exception e) {
            System.err.println("Error cargando el archivo: " + e);
            return null;
        }
    }

    @Override
    public FileReader openFileReader(String filename) throws FileNotFoundException {
        FileReader fileReader = new FileReader("assets/" + filename);
        return fileReader;
    }

    @Override
    public void update(/*Delta time*/) {
        //Llamamos a la logica del update
    }



    @Override
    public boolean run() {
        return true;
    }

    protected PCGraphics _graphics;
    Logica logica;
    protected Input _input;
    boolean _exit = false;

}