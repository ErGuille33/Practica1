package com.example.logica;

import com.example.engine.Graphics;

public class Enemy extends GameObject {
    public Enemy(float x, float y, float fx, float fy, float speed, float length, float angle, float time) {
        super(x, y, 0, 0, new Vector2D(0,0));
        _inix = x;
        _iniy = y;
        _length = length;
        _angle = angle;
        _time = time;
        _fx = _x-fx;
        _fy = _y-fy;
    }

    public void render(Graphics g) {
        g.setColor("red");
        g.save();
        g.translate((int)-_x, (int)-_y);
        g.rotate((int)_angle);
        g.drawLine((int)-_length/2, 0, (int)_length/2, 0);
        g.restore();
    }

    public void update(float deltaTime) {
        //if(_x == _fx) _dir*=-1;
        //else if(_x == _inix) _dir*=-1;

        //_x += (_fx - _x) * 20 * deltaTime * _dir;
        //_y += (_fy - _y) * 20 * deltaTime * _dir;
        
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
