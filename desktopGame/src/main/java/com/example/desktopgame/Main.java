package com.example.desktopgame;

import com.example.logica.Logica;
import com.example.pcengine.PcEngine;

public class Main {
    public static void main(String[] args)
    {
        PCEngine engine = new PcEngine();
        Logica logica = new Logica(engine);
        logica.run();
    }
}