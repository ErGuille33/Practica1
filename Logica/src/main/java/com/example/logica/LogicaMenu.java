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
        g.newFont("Bungee-Regular.ttf", 50, true, 1);
        g.setColor("player");
        g.drawText("OFF THE LINE", (int) (-310), (int) (-150));

        g.newFont("Bungee-Regular.ttf", 20, false, 0);
        g.setColor("player");
        g.drawText("A GAME COPIED TO BRYAN PERFETTO", (int) (-305), (int) (-120));

        g.newFont("Bungee-Regular.ttf", 30, false, 0);
        g.setColor("white");
        g.drawText("EASY MODE", (int) (-305), (int) (90));

        g.newFont("Bungee-Regular.ttf", 30, false, 0);
        g.setColor("white");
        g.drawText("HARD MODE", (int) (-305), (int) (150));

        g.newFont("Bungee-Regular.ttf", 15, false, 0);
        g.setColor("gray");
        g.drawText("(SLOW SPEED, 10 LIVES)", (int) (-115), (int) (90));

        g.newFont("Bungee-Regular.ttf", 15, false, 0);
        g.setColor("gray");
        g.drawText("(FAST SPEED, 5 LIVES)", (int) (-115), (int) (150));


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
