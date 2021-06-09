package com.example.androidengine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.engine.Font;
import com.example.engine.Graphics;

public class AndroidGraphics implements Graphics {

    public void setCanvas(Canvas canvas) {
        _c = canvas;
        _font = new com.example.androidengine.Font[25];
        //grosor de lineas
        _p.setStrokeWidth((float) 1.5);
    }

    @Override
    //Creamos una nueva fuente, si esta no ha sido creada ya y la aplicamos
    public Font newFont(String filename, float size, boolean isBold, int numFont) throws Exception {
        if (_font[numFont] == null)
            _font[numFont] = new com.example.androidengine.Font("Fuentes/" + filename, size, isBold, context);
        _p.setTypeface(_font[numFont].f);
        _p.setFakeBoldText(true);
        _p.setTextSize(size);
        return _font[numFont];
    }

    public void clear(int color) {
        setColor(color);
        fillRect(0, 0, _c.getWidth(), _c.getHeight());
    }

    public void translate(int x, int y) {
        _c.translate(x, y);
    }

    @Override
    public void scale(float x) {
        _c.scale(x, -x);
    }

    public void rotate(int angle) {
        _c.rotate(angle);
    }

    public void save() {
        _c.save();
    }

    public void restore() {
        _c.restore();
    }

    //Elegimos en un principio introducir un string del color en vez de su valor RGBA directamente ya que consideramos
    //que de esta manera nos facilitaria el trabajo a la hora de escoger el color adecuado desde la logica.
    public void setColor(int color) {
        _p.setColor(color);
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        _c.drawLine(x1, y1, x2, y2, _p);

    }

    public void fillRect(int x1, int y1, int x2, int y2) {
        Rect r = new Rect(x1, y1, x2, y2);
        _c.drawRect(r, _p);
    }

    @Override
    public void drawText(String text, int x, int y) {
        _c.scale(1, -1);
        _c.drawText(text, (int) x, y, _p);
        _c.scale(1, -1);
    }

    public float getWidth() {
        int aux = _c.getWidth();
        return _c.getWidth();
    }

    public float getHeight() {
        int aux = _c.getHeight();
        return _c.getHeight();
    }

    public int getBaseWidth() {
        return 640;
    }

    public int getBaseHeight() {
        return 480;
    }

    //Metodo util para el correcto reescalado de la pantalla. Devuelve el el ancho o el alto dependiendo de cual es mayor
    public float calculateSize() {
        float aux1 = 0;
        float aux2 = 0;

        aux1 = getWidth() / (float) getBaseWidth();
        aux2 = getHeight() / (float) getBaseHeight();

        if (aux1 < aux2)
            return aux1;
        else return aux2;
    }

    public void getContext(Context _context) {
        context = _context;
    }

    //Array de fuentes creadas
    com.example.androidengine.Font[] _font;
    Canvas _c;
    Context context;
    Paint _p = new Paint();

}
