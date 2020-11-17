package com.example.pcengine;

import com.example.engine.Font;
import com.example.engine.Graphics;

import java.awt.Color;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class PCGraphics implements Graphics {

    class MyJFrame extends JFrame {
        public MyJFrame() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setVisible(true);
            setSize(800,600);
        }

        public void render(java.awt.Graphics g) {
            setColor("red");
            fillRect(0,0, getWidth(),getHeight()/3);
            setColor("yellow");
            fillRect(0,getHeight()/3, getWidth(),(getHeight()/3)*2);
            setColor("red");
            fillRect(0,(getHeight()/3)*2, getWidth(), getHeight());
        }
    }

    public PCGraphics() {
        _frame = new MyJFrame();
        _frame.setIgnoreRepaint(true);
        _frame.createBufferStrategy(2);
        _strat = _frame.getBufferStrategy();
    }

    public void render() {
        do {
            do {
                java.awt.Graphics graphics = _strat.getDrawGraphics();
                _graphics = graphics;
                try {
                    _frame.render(_graphics);
                }
                finally {
                    _graphics.dispose();
                }
            } while(_strat.contentsRestored());
            _strat.show();
        } while(_strat.contentsLost());
    }

    public Font newFont(String filename, int size, boolean isBold) {
        return null;
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

    }

    public int getWidth() {
        return _frame.getWidth();
    }
    public int getHeight() {
        return _frame.getHeight();
    }

    MyJFrame _frame;
    BufferStrategy _strat;
    java.awt.Graphics _graphics;

}
