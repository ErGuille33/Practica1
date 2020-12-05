package com.example.pcengine;

import com.example.engine.Graphics;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;

import javax.swing.JFrame;

public class PCGraphics implements Graphics {

    public PCGraphics(JFrame frame) {
        _frame = frame;
        _font = new Font[25];

    }

    public void setGraphics(java.awt.Graphics graphics) {
        _graphics = graphics;

    }

    //Creamos una nueva fuente si esta no ha sido creada ya y la aplicamos
    public com.example.pcengine.Font newFont(String filename, float size, boolean isBold, int numFont) throws Exception {

        if (_font[numFont] == null)
            _font[numFont] = new com.example.pcengine.Font(filename, size, isBold);

        _graphics.setFont(_font[numFont].f);
        return _font[(numFont)];
    }


    public void clear(String color) {
        setColor(color);
        _graphics.clearRect(0, 0, (int) getWidth(), (int) getHeight());
    }

    public void translate(int x, int y) {

        _graphics.translate(x, y);
    }

    public void scale(float x) {
        ((Graphics2D) _graphics).scale(x, -x);
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

    //Elegimos en un principio introducir un string del color en vez de su valor directamente ya que consideramos
    //que de esta manera nos facilitaria el trabajo a la hora de escoger el color adecuado desde la logica.
    public void setColor(String color) {
        Color c = Color.white;
        switch (color.toLowerCase()) {
            case "enemy":
                c = new Color((float) 255 / 255, (float) 0, (float) 0);
                break;
            case "player":
                c = new Color((float) 0, (float) 136 / 255, (float) 255 / 255);
                break;
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
        _graphics.fillRect(x1, y1, x2 - x1, y2 - y1);
    }

    public void drawText(String text, int x, int y) {

        ((Graphics2D) _graphics).scale(1, -1);
        _graphics.drawString(text, x, y);
        ((Graphics2D) _graphics).scale(1, -1);

    }

    public float getWidth() {
        return _frame.getWidth();
    }

    public float getHeight() {
        return _frame.getHeight();
    }

    public int getBaseWidth() {
        return 640;
    }

    public int getBaseHeight() {
        return 480;
    }

    @Override
    //Metodo util para el correcto reescalado de la pantalla. Devuelve el el ancho o el alto dependiendo de cual es mayor
    public float calculateSize() {
        float aux1 = 0;
        float aux2 = 0;

        aux1 = (float) getWidth() / (float) getBaseWidth();
        aux2 = (float) getHeight() / (float) getBaseHeight();

        if (aux1 < aux2)
            return aux1;
        else return aux2;
    }

    //Array de fuentes creadas
    com.example.pcengine.Font[] _font;
    JFrame _frame;
    java.awt.Graphics _graphics;
    AffineTransform _state;
}
