package com.example.pcengine;

import com.example.engine.Graphics;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;

import javax.swing.JFrame;

public class PCGraphics implements Graphics {

    public PCGraphics(JFrame frame) {
        _frame = frame;
    }

    public void setGraphics(java.awt.Graphics graphics) {
        _graphics = graphics;
    }

    public com.example.pcengine.Font newFont(String filename, float size, boolean isBold) throws Exception {
        com.example.pcengine.Font _font = new com.example.pcengine.Font(filename,size,isBold);
        _graphics.setFont(_font.f);
        return _font;
    }

    public void clear(String color) {
        setColor(color);
        _graphics.clearRect(0,0, (int)getWidth(), (int)getHeight());
    }

    public void translate(int x,int y) {

        _graphics.translate(x, y);
    }

    public void scale(float x) {
        ((Graphics2D) _graphics).scale(-x, x);
    }
    public void rotate(int angle) {
        ((Graphics2D) _graphics).rotate((Math.toRadians(angle)));
    }
    public void save() {
        _state = ((Graphics2D) _graphics).getTransform();
    }
    public void restore() {
        ((Graphics2D) _graphics).setTransform(_state);
    }

    public void setColor(String color) {
        Color c = Color.white;
        switch (color.toLowerCase()) {
            case "black":
                c = Color.BLACK;
                break;
            case "blue":
                c = Color.BLUE;
                break;
            case "cyan":
                c = Color.CYAN;
                break;
            case "darkgray":
                c = Color.DARK_GRAY;
                break;
            case "gray":
                c = Color.GRAY;
                break;
            case "green":
                c = Color.GREEN;
                break;
            case "yellow":
                c = Color.YELLOW;
                break;
            case "red":
                c = Color.RED;
                break;
            case "white":
                c = Color.WHITE;
                break;
        }
        _graphics.setColor(c);
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        _graphics.drawLine(x1, y1, x2, y2);
    }

    public void fillRect(int x1, int y1, int x2, int y2) {
        _graphics.fillRect(x1, y1, x2-x1, y2-y1);
    }

    public void drawText(String text, int x, int y) {
        try {
            setColor("blue");

        } catch (Exception e) {
            e.printStackTrace();
        }

        _graphics.drawString(text, x, y);

    }

    public float getWidth() {
        return _frame.getWidth();
    }
    public float getHeight() {
        return _frame.getHeight();
    }

    public int getBaseWidth() { return 640; }
    public int getBaseHeight() { return 480; }

    @Override
    public float calculateSize() {
        float aux1 = 0;
        float aux2 = 0;

        aux1 = (float) getWidth()/ (float) getBaseWidth();
        aux2 = (float) getHeight() / (float) getBaseHeight();

        if(aux1 < aux2)
            return aux1;
        else return aux2;
    }

    JFrame _frame;
    java.awt.Graphics _graphics;
    AffineTransform _state;
}
