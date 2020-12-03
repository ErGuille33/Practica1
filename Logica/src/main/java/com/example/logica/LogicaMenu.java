package com.example.logica;

import com.example.engine.Engine;
import com.example.engine.Font;
import com.example.engine.Graphics;
import com.example.engine.Input;

import java.util.List;

public class LogicaMenu {

        LogicaMenu(Engine engine, Logica logica){
            _engine = engine;
            _logica = logica;
        }
        public void init(){

        }
        public void render(Graphics g) throws Exception {
            g.setColor("black");
            g.fillRect(0, 0, (int) g.getWidth(), (int) g.getHeight());

            g.translate((int) g.getWidth() / 2, (int) g.getHeight() / 2);

            g.scale(g.calculateSize());

            if (fuente == null) {
                fuente = g.newFont("Bungee-Regular.ttf", 30, true);
            }

            _engine.getGraphics().drawText("Esto es un menu", (int) (-300), (int) (-0));
        }
        public void handleInput(List<Input.TouchEvent> te) {

        }
        public void update(float deltaTime) {
        }

        Font fuente;
        Logica _logica;
        Engine _engine;
}
