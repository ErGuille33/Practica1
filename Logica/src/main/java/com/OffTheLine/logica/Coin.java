package com.OffTheLine.logica;

import com.OffTheLine.engine.Graphics;

//Monedas que recoger
public class Coin extends Character {
    public Coin(float x, float y, int w, int h, float radius, float speed, float angle) {
        super(x + radius * (float) Math.cos(Math.toRadians(angle)), y + radius * (float) Math.sin(Math.toRadians(angle)), w, h, new Vector2D(0, 0));
        _inix = x;
        _iniy = y;
        _radius = radius + 1;
        _speed = speed + 1;
        _angle = angle + 1;
        _rot2 = 0;
        _x1 = x + radius * (float) Math.cos(Math.toRadians(angle));
        _y1 = y + radius * (float) Math.sin(Math.toRadians(angle));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(0xFFFFFF00);
        g.save();
        g.rotate((int) _rot2);
        g.translate((int) (_x + _w / 2), (int) (_y + _h / 2));
        g.rotate((int) _rot);
        g.translate((int) (-_x - _w / 2), (int) (-_y - _h / 2));
        g.drawLine((int) _x, (int) _y, (int) (_x + _w), (int) _y);
        g.drawLine((int) (_x + _w), (int) _y, (int) (_x + _w), (int) (_y + _h));
        g.drawLine((int) (_x + _w), (int) (_y + _h), (int) _x, (int) (_y + _h));
        g.drawLine((int) _x, (int) (_y + _h), (int) _x, (int) _y);
        g.restore();

    }

    public void destroyCoin() {
        destroyingCoin = true;

    }

    //Indica si el jugador colisiono con la moneda
    public void pickedCoin() {
        picked = true;
    }

    //Indica que la moneda debe finalmente destruirse
    public boolean finallyDestroy() {
        return erase;
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
        _x1 = _inix + _radius * (float) Math.cos(Math.toRadians(_rot2 + _angle));
        _y1 = _iniy + _radius * (float) Math.sin(Math.toRadians(_rot2 + _angle));
        if (destroyingCoin) {
            //Si la moneda está en fase de crecimiento, la destruiremos cuando supere el tamaño indicado
            if (_w > 30) {
                erase = true;
            }
        }
        _rot2 += _speed * deltaTime;
    }

    boolean destroyingCoin = false;
    private boolean erase = false;
    public boolean picked = false;

    float changeInSize = 0;
    float _x1, _y1;
    float _inix, _iniy;
    float _radius;
    float _speed;
    float _angle;
    float _rot2;
}
