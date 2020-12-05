package com.example.androidengine;

import android.view.MotionEvent;
import android.view.View;

import com.example.engine.Input;

import java.util.ArrayList;
import java.util.List;

//Input de Android
public class AndroidInput implements Input {

    class TouchListener implements View.OnTouchListener {
        TouchListener() {

        }

        @Override
        //Solo detectaremos la pulsacion standard de la pantalla, ya que para el juego no es necesaria la deteccion de otra distinta
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                Input.TouchEvent aux = new Input.TouchEvent(event.getActionIndex(), Input.TouchEvent.Type.PULSACION, (int) event.getX(), (int) event.getY());
                _touchEvents.add(aux);
            }return true;
        }
    }

    public int getEvents() {
        return _touchEvents.size();
    }

    @Override
    public List<Input.TouchEvent> getTouchEvents() {
        return _touchEvents;
    }

    AndroidInput() {
        _listener = new TouchListener();
        _touchEvents = new ArrayList();
    }

    public TouchListener _listener;
    List<Input.TouchEvent> _touchEvents;
}
