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
    }

    public Font newFont(String filename, float size, boolean isBold)throws Exception {
        com.example.androidengine.Font font = new com.example.androidengine.Font("Fuentes/BungeeHairline-Regular.ttf",size,isBold,context);
        _p.setTypeface(font.f);
        _p.setFakeBoldText(true);
        setColor("black");
        _p.setTextSize(size);
        return font;
    }

    public void clear(String color) {
        setColor(color);
        fillRect(0,0, _c.getWidth(), _c.getHeight());
    }
    public void translate(int x,int y) {
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

    public void setColor(String color) {
        switch (color.toLowerCase()) {
            case "black":
                _p.setColor(Color.BLACK);
                break;
            case "blue":
                _p.setColor(Color.BLUE);
                break;
            case "cyan":
                _p.setColor(Color.CYAN);
                break;
            case "darkgray":
                _p.setColor(Color.DKGRAY);
                break;
            case "gray":
                _p.setColor(Color.GRAY);
                break;
            case "green":
                _p.setColor(Color.GREEN);
                break;
            case "yellow":
                _p.setColor(Color.YELLOW);
                break;
            case "red":
                _p.setColor(Color.RED);
                break;
            case "white":
                _p.setColor(Color.WHITE);
                break;
        }
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
        _c.drawText(text, (int) x, y, _p);
    }

    public float getWidth() {
        return _c.getWidth();
    }
    public float getHeight() {
        return _c.getHeight();
    }

    public int getBaseWidth() { return 640; }
    public int getBaseHeight() { return 480; }

    public float calculateSize() {
        float aux1 = 0;
        float aux2 = 0;

        aux1 =  getWidth()/ (float) getBaseWidth();
        aux2 =  getHeight() / (float) getBaseHeight();

        if(aux1 < aux2)
            return aux1;
        else return aux2;
    }

    public void getContext(Context _context){
        context = _context;
    }

    Canvas _c;
    Context context;
    Paint _p = new Paint();

}
