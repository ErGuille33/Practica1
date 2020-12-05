package com.example.pcengine;

import com.example.engine.Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class PcInput implements Input {

    class MouseEventHandler implements MouseListener {
        public MouseEventHandler() {
        }

        public void mousePressed(MouseEvent e) {
        }

        //Solo detectaremos la pulsacion standard del raton, ya que para el juego no es necesaria la deteccion de otra distinta
        public void mouseClicked(MouseEvent e) {
            TouchEvent aux = new TouchEvent(e.getButton(), TouchEvent.Type.PULSACION, e.getX(), e.getY());
            _touchEvents.add(aux);
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

    }

    public PcInput() {
        _handler = new MouseEventHandler();
        _touchEvents = new ArrayList();
    }

    public int getEvents() {
        return _touchEvents.size();
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        return _touchEvents;
    }

    MouseEventHandler _handler;
    List<TouchEvent> _touchEvents;
}
