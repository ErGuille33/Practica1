package com.example.desktopgame;

import com.example.engine.Engine;
import com.example.engine.Graphics;
import com.example.logica.Logica;
import com.example.pcengine.PCGraphics;
import com.example.pcengine.PcEngine;

import javax.swing.JFrame;

class MyJFrame extends JFrame {
    public MyJFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        setSize(640,480);

        _logic = new Logica();
        engine = new PcEngine(_logic,this);
        _logic.getEngine(engine);

    }

    public boolean run() {
        try {
            engine.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(!_exit) {
        }
        return true;
    }


    Engine engine;
    PCGraphics _graphics;
    Logica _logic;
    boolean _exit = false;
}
