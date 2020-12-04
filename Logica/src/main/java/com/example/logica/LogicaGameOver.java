package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Font;
import com.example.engine.Graphics;
import com.example.engine.Input;

import java.util.List;

public class LogicaGameOver {
    LogicaGameOver(Engine engine,Logica logica){
        _engine = engine;
        _logica = logica;
    }
    public void init() throws Exception {
        if (fuente == null) {
            fuente = _engine.getGraphics().newFont("Bungee-Regular.ttf", 30, true,0);
        }
    }
    public void render(Graphics g) throws Exception {
        g.setColor("black");
        g.fillRect(0, 0, (int) g.getWidth(), (int) g.getHeight());

        g.translate((int) g.getWidth() / 2, (int) g.getHeight() / 2);

        g.scale(g.calculateSize());



        _engine.getGraphics().drawText("Has perdido, subnormal", (int) (-300), (int) (-0));
    }
    public void handleInput(List<Input.TouchEvent> te) {

    }
    public void update(float deltaTime) {
    }

    Font fuente;
    Logica _logica;
    Engine _engine;
}
