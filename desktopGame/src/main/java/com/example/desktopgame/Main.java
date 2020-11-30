package com.example.desktopgame;

import com.example.engine.Engine;
import com.example.logica.Logica;
import com.example.pcengine.PCGraphics;
import com.example.pcengine.PcEngine;

import java.awt.image.BufferStrategy;

import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) throws Exception
    {
        Engine engine;
        PCGraphics _graphics;
        Logica _logic;
        boolean _exit = false;

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.setSize(1080,720);
        frame.setIgnoreRepaint(true);
        frame.createBufferStrategy(2);
        BufferStrategy strat = frame.getBufferStrategy();



       _logic = new Logica();
        engine = new PcEngine(_logic,frame);
        _logic.getEngine(engine);

        try {
            engine.running();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(!_exit) {
        }

    }




}