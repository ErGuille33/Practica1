package com.OffTheLine.desktopgame;

import com.OffTheLine.engine.Engine;
import com.OffTheLine.logica.Logica;
import com.OffTheLine.pcengine.PCGraphics;
import com.OffTheLine.pcengine.PcEngine;

import java.awt.image.BufferStrategy;

import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) throws Exception {
        //Creamos los 3 elementos necesarios : Motor, graficos y logica
        PcEngine engine;
        PCGraphics _graphics;
        Logica _logic;
        boolean _exit = false;

        //Creamos y colocamos la ventana
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.setSize(1080, 720);
        frame.setIgnoreRepaint(true);
        frame.createBufferStrategy(2);
        BufferStrategy strat = frame.getBufferStrategy();


        //Inicializamos la logica
        _logic = new Logica();
        engine = new PcEngine(_logic, frame);
        _logic.getEngine(engine);

        //Comenzamos el bucle principal
        try {
            engine.running();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (!_exit) {
        }

    }


}