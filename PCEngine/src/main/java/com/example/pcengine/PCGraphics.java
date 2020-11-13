package com.example.pcengine;

import com.example.engine.Font;
import com.example.engine.Graphics;

import java.awt.Color;

import javax.swing.JFrame;

public class PCGraphics implements Graphics {

    class MyJFrame extends JFrame {
        public MyJFrame() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setVisible(true);
            setSize(1920,1080);
        }

        public void paint(java.awt.Graphics g) {
            super.paint(g);

            fillRect(100,100, 600,400);

            g.setColor(Color.yellow);
            fillRect(100, 500,600, 1000);
        }
    }

    public PCGraphics() {
        _frame = new MyJFrame();
    }

    public Font newFont(String filename, int size, boolean isBold) {
        return null;
    }

    public void clear(String color) {
        setColor(color);
        _frame.getGraphics().clearRect(0,0, getWidth(), getHeight());
    }

    public void translate(int x,int y) {
        _frame.getGraphics().translate(x, y);
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
        System.out.println(color);
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
        }
        _frame.getGraphics().setColor(c);
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        _frame.getGraphics().drawLine(x1, y1, x2, y2);
    }

    public void fillRect(int x1, int y1, int x2, int y2) {
        _frame.getGraphics().fillRect(x1, y1, x2-x1, y2-y1);
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

}
