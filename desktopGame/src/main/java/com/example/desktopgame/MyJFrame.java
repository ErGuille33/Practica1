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
        engine = new PcEngine(_logic);
        _logic.getEngine(engine);

    }

    public boolean run() {
        _graphics = new PCGraphics(this);

        try {
            engine.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(!_exit) {
            //update();
            render();
        }

        return true;
    }

    public void render() {
        do {
            do {
                java.awt.Graphics graphics = getBufferStrategy().getDrawGraphics();
                _graphics.setGraphics(graphics);
                try {
                    engine.render(_graphics);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    graphics.dispose();
                }
            } while(getBufferStrategy().contentsRestored());
            getBufferStrategy().show();
        } while(getBufferStrategy().contentsLost());
    }

    Engine engine;
    PCGraphics _graphics;
    Logica _logic;
    boolean _exit = false;
}
