package com.example.logica;

public interface Graphics {
    Font newFont(String filename, int size, boolean isBold);

    void clear(String color);
    void translate(int x,int y, boolean logicMetric);
    void scale(int x, int y, boolean logicMetric);
    void rotate(int angle, boolean logicMetric);
    void save();
    void restore();

    void setColor(String color);
    void drawLine(int x1, int y1, int x2, int y2, boolean logicMetric);
    void fillRect(int x1, int y1, int x2, int y2, boolean logicMetric);

    void drawText(String text, int x, int y, boolean logicMetric);

    int getWidth(boolean logicMetric);
    int getHeight(boolean logicMetric);

}
