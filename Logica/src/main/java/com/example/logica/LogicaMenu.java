package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Font;
import com.example.engine.Graphics;
import com.example.engine.Input;

import java.util.ArrayList;
import java.util.List;

public class LogicaMenu {

    LogicaMenu(Engine engine, Logica logica) {
        _engine = engine;
        _logica = logica;
        _buttons = new ArrayList();
    }

    //Iniciamos los botones
    public void init() throws Exception {
        Boton easyGame = new Boton(-305, 90, 305, 50, "game", _logica);
        _buttons.add(easyGame);
        Boton diffGame = new Boton(-305, 150, 305, 50, "diffgame", _logica);
        _buttons.add(diffGame);
    }

    //Renderizamos todo el texto
    public void render(Graphics g) throws Exception {
        g.setColor("black");
        g.fillRect(0, 0, (int) g.getWidth(), (int) g.getHeight());

        g.translate((int) g.getWidth() / 2, (int) g.getHeight() / 2);

        g.scale(g.calculateSize());

        g.newFont("Bungee-Regular.ttf", 50, true, 3);
        g.setColor("player");
        g.drawText("OFF THE LINE", (int) (-310), (int) (-150));

        g.newFont("Bungee-Regular.ttf", 20, false, 4);
        g.setColor("player");
        g.drawText("A GAME COPIED TO BRYAN PERFETTO", (int) (-305), (int) (-120));

        g.newFont("Bungee-Regular.ttf", 30, false, 5);
        g.setColor("white");
        g.drawText("EASY MODE", (int) (-305), (int) (90));

        g.newFont("Bungee-Regular.ttf", 30, false, 6);
        g.setColor("white");
        g.drawText("HARD MODE", (int) (-305), (int) (150));

        g.newFont("Bungee-Regular.ttf", 15, false, 7);
        g.setColor("gray");
        g.drawText("(SLOW SPEED, 10 LIVES)", (int) (-115), (int) (90));

        g.newFont("Bungee-Regular.ttf", 15, false, 8);
        g.setColor("gray");
        g.drawText("(FAST SPEED, 5 LIVES)", (int) (-115), (int) (150));

    }

    //Input de los botones
    public void handleInput(List<Input.TouchEvent> te) {
        for (int i = 0; i < _buttons.size(); i++) {
            for (int j = 0; j < te.size(); j++) {
                _buttons.get(i).handleInput(te.get(j));
            }
        }
        te.clear();
    }

    public void update(float deltaTime) {
    }

    List<Boton> _buttons;

    Logica _logica;
    Engine _engine;
}
