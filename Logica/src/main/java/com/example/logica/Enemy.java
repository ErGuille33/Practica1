package com.example.logica;

import com.example.engine.Graphics;

public class Enemy extends GameObject {
    public Enemy(float x, float y, float fx, float fy, float speed, float length, float angle, float time) {
        super(x, y, 0, 0, new Vector2D(0,0));
        _inix = x;
        _iniy = y;
        _length = length;
        _angle = angle;
        _time = time+1;
        _fx = _x-(fx+1);
        _fy = _y-(fy+1);
        _speed = speed+1;
        _vel.setX(_fx - _x);
        _vel.setY(_fy - _y);
        if(_vel.getX()!=0 && _vel.getY()!=0) _vel.normalize();
    }

    public void render(Graphics g) {
        g.setColor("red");
        g.save();
        g.translate((int)_x, (int)_y);
        g.rotate((int)_angle + (int)_rot);
        g.drawLine((int)-_length/2, 0, (int)_length/2, 0);
        g.restore();
    }

    public void update(float deltaTime) {
        _rot+=(_speed)*deltaTime;

        _x -= _vel.getX()*deltaTime*(Math.abs((_inix - _fx)) * _time) * _dir;
        _y -= _vel.getY()*deltaTime*(Math.abs((_iniy - _fy)) * _time) * _dir;

        if(_x == _fx && _dir == 1)
            _dir*=-1;
        if(_x == _inix && _dir == -1)
            _dir*=-1;
        
    }

    float _inix;
    float _iniy;
    float _length;
    float _angle;
    float _fx, _fy;
    float _time;
    float _dist;
    float _speed;
    int _dir = 1;
}
