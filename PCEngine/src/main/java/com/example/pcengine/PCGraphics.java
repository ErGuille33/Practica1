package com.example.pcengine;

import  com.example.pcengine.Font;
import com.example.engine.Graphics;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;

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
        return _font;
    }

    public void clear(String color) {
        setColor(color);
        _graphics.clearRect(0,0, getWidth(), getHeight());
    }

    public void translate(int x,int y) {
        _graphics.translate(x, y);
    }
    public void scale(int x, int y) {

    }
    public void rotate(int angle) {

    }
    public void save() {

    }
    public void restore() {

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
            Font _font =  newFont("assets/Fuentes/BungeeHairline-Regular.ttf", 24, true);
            _graphics.setFont(_font.f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        _graphics.drawString(text, x, y);

    }

    public int getWidth() {
        return _frame.getWidth();
    }
    public int getHeight() {
        return _frame.getHeight();
    }

    JFrame _frame;
    java.awt.Graphics _graphics;

}
