package com.OffTheLine.logica;

import com.OffTheLine.engine.Graphics;
import com.OffTheLine.engine.Input;

import java.util.ArrayList;

public class Path extends GameObject {
    //Clase consistente unicamente en los paths y su renderizado
    public Path(ArrayList<Nivel.Vertice> vertex) {
        super(0,0,0,0, new Vector2D(0,0));
        _vertex = vertex;
    }

    public void render(Graphics g) {
        g.setColor(0xFFFFFFFF);
        for(int i = 0; i < _vertex.size(); i++) {
            if(i != _vertex.size()-1)
                g.drawLine((int)_vertex.get(i)._pos.get_x(), (int)_vertex.get(i)._pos.get_y(),
                        (int)_vertex.get(i+1)._pos.get_x(), (int)_vertex.get(i+1)._pos.get_y());
            else
                g.drawLine((int)_vertex.get(i)._pos.get_x(), (int)_vertex.get(i)._pos.get_y(),
                        (int)_vertex.get(0)._pos.get_x(), (int)_vertex.get(0)._pos.get_y());
        }
    }

    public void update(float deltaTime) {

    }

    public void handleInput(Input.TouchEvent e) {

    }

    ArrayList<Nivel.Vertice> _vertex;
}
