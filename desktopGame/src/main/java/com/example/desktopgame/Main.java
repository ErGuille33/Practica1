package com.example.desktopgame;

import com.example.logica.Logica;
import com.example.pcengine.PcEngine;

import java.awt.image.BufferStrategy;


public class Main {
    public static void main(String[] args) throws Exception
    {
        MyJFrame frame = new MyJFrame();
        frame.setIgnoreRepaint(true);
        frame.createBufferStrategy(2);
        BufferStrategy strat = frame.getBufferStrategy();

        frame.run();
    }

}