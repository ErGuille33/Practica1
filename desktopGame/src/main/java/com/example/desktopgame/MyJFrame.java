package com.example.desktopgame;

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
            _logic.run();
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
                    _logic.render(_graphics);
                    _graphics.drawText("De Don Benito al cielo", 200,300);
                }
                finally {
                    graphics.dispose();
                }
            } while(getBufferStrategy().contentsRestored());
            getBufferStrategy().show();
        } while(getBufferStrategy().contentsLost());
    }

    PCGraphics _graphics;
    Logica _logic;
    boolean _exit = false;
}
