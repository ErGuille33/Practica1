package com.example.logica;

import com.example.engine.Graphics;
import com.example.engine.Input;
//Clase que dibuja y administra la logica de las lineas enemigas
public class Enemy extends GameObject {
    public Enemy(float x, float y, float fx, float fy, float speed, float length, float angle, float time, float time2) {
        super(x, y, 0, 0, new Vector2D(0,0));
        _inix = x;
        _iniy = y;
        _length = length;
        _angle = angle;
        _time = time;
        _time2 = time2;
        _fx = _x-(fx+1);
        _fy = _y-(fy+1);
        _speed = speed+1;
        _vel.setX(_fx - _x);
        _vel.setY(_fy - _y);
        if(_vel.getX()!=0 && _vel.getY()!=0) _vel.normalize();

        _x1 = _x + (_length/2)*(float)Math.cos(Math.toRadians(_angle));
        _y1 = _y + (_length/2)*(float)Math.sin(Math.toRadians(_angle));
        _x2 = _x - (_length/2)*(float)Math.cos(Math.toRadians(_angle));
        _y2 = _y - (_length/2)*(float)Math.sin(Math.toRadians(_angle));

    }

    public void render(Graphics g) {
        g.setColor("enemy");
        g.drawLine((int)_x1, (int)_y1, (int)_x2, (int)_y2);
        g.save();
        g.translate((int)_x, (int)_y);
        g.rotate((int)_angle + (int)_rot);
        g.restore();
    }

    public void update(float deltaTime) {
        _rot+=(_speed)*deltaTime;

        _x1 = _x + (_length / 2) * (float) Math.cos(Math.toRadians(_rot + _angle));
        _y1 = _y + (_length / 2) * (float) Math.sin(Math.toRadians(_rot + _angle));
        _x2 = _x - (_length / 2) * (float) Math.cos(Math.toRadians(_rot + _angle));
        _y2 = _y - (_length / 2) * (float) Math.sin(Math.toRadians(_rot + _angle));

        if(!_stop) {
            _x -= _vel.getX() * deltaTime * (Math.abs(_inix - _fx) / _time) * _dir;
            _y -= _vel.getY() * deltaTime * (Math.abs(_iniy - _fy) / _time) * _dir;

            _timer += deltaTime;
            if (_timer > _time) {
                _dir *= -1;
                _timer = 0;
                _stop = true;
            }
        }
        else {
            _stopTimer+=deltaTime;
            if(_stopTimer > _time2) {
                _stop = false;
                _stopTimer = 0;
            }
        }
    }

    public void handleInput(Input.TouchEvent e) {

    }

    float _inix;
    float _iniy;
    float _length;
    float _angle;
    float _fx, _fy;
    float _time;
    float _time2;
    float _timer = 0;
    float _stopTimer = 0;
    float _speed;
    boolean _stop = false;
    int _dir = 1;

    float _x1, _x2, _y1, _y2;
}
