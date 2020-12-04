package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Font;
import com.example.engine.Graphics;
import com.example.engine.Input;

import java.util.List;

public class LogicaMenu {

    LogicaMenu(Engine engine, Logica logica) {
        _engine = engine;
        _logica = logica;

    }

    public void init() throws Exception {

    }

    public void render(Graphics g) throws Exception {
        g.setColor("black");
        g.fillRect(0, 0, (int) g.getWidth(), (int) g.getHeight());

        g.translate((int) g.getWidth() / 2, (int) g.getHeight() / 2);

        g.scale(g.calculateSize());

        g.newFont("Bungee-Regular.ttf", 10, true, 0);
        g.setColor("blue");
        g.drawText("A GAME COPIED TO BRYAN PERFETTO", (int) (-300), (int) (-100));

        g.newFont("Bungee-Regular.ttf", 30, true, 1);
        g.setColor("blue");
        g.drawText("OFF THE LINE", (int) (-300), (int) (-175));




    }

    public void handleInput(List<Input.TouchEvent> te) {

    }

    public void update(float deltaTime) {

    }

    Font fuente1;
    Font fuente2;
    Logica _logica;
    Engine _engine;
}
