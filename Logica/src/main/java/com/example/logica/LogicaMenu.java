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
        Boton easyGame = new Boton(-305, 90, 400, 20, "game", _logica);
        _buttons.add(easyGame);
        Boton diffGame = new Boton(-305, 150, 400, 20, "diffgame", _logica);
        _buttons.add(diffGame);

        _fonts[0] = _engine.getGraphics().newFont("Fuentes/Bungee-Regular.ttf", 50, true);
        _fonts[1] = _engine.getGraphics().newFont("Fuentes/Bungee-Regular.ttf", 30, false);
        _fonts[2] = _engine.getGraphics().newFont("Fuentes/Bungee-Regular.ttf", 20, false);
        _fonts[3] = _engine.getGraphics().newFont("Fuentes/Bungee-Regular.ttf", 15, false);
    }

    //Renderizamos todo el texto
    public void render(Graphics g) throws Exception {
        g.setColor(0xFF000000);
        g.fillRect((int)-g.getWidth()/2, (int)-g.getHeight()/2, (int) g.getWidth(), (int) g.getHeight());

        g.save();

        g.setFont(_fonts[0]);
        g.setColor("player");
        g.drawText("OFF THE LINE", (int) (-310), (int) (-150));

        g.setFont(_fonts[2]);
        g.setColor("player");
        g.drawText("A GAME COPIED TO BRYAN PERFETTO", (int) (-305), (int) (-120));

        g.setFont(_fonts[1]);
        g.setColor("white");
        g.drawText("EASY MODE", (int) (-305), (int) (90));

        g.setFont(_fonts[1]);
        g.setColor("white");
        g.drawText("HARD MODE", (int) (-305), (int) (150));

        g.setFont(_fonts[3]);
        g.setColor("gray");
        g.drawText("(SLOW SPEED, 10 LIVES)", (int) (-115), (int) (90));

        g.setFont(_fonts[3]);
        g.setColor("gray");
        g.drawText("(FAST SPEED, 5 LIVES)", (int) (-115), (int) (150)); 
        g.restore();


        for(int i = 0; i < _buttons.size(); i++) {
            _buttons.get(i).render(g);
        }
    }

    //Input de los botones
    public void handleInput(List<Input.TouchEvent> te) throws Exception {
        for (int i = 0; i < _buttons.size(); i++) {
            for (int j = 0; j < te.size(); j++) {
                _buttons.get(i).handleInput(te.get(j));
            }
        }
        te.clear();
    }

    public void update(float deltaTime) {
        for(int i = 0; i < _buttons.size(); i++) {
            _buttons.get(i).update(deltaTime);
        }
    }

    List<Boton> _buttons;

    Logica _logica;
    Engine _engine;
    Font _fonts[] = new Font[6];
}
