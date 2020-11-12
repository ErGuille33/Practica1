package com.example.engine;

public interface Graphics {
    public Font newFont(String filename, int size, boolean isBold);

    public void clear(String color);
    public void translate(int x,int y, boolean logicMetric);
    public void scale(int x, int y, boolean logicMetric);
    public void rotate(int angle, boolean logicMetric);
    public void save();
    public void restore();

    public void setColor(String color);
    public void drawLine(int x1, int y1, int x2, int y2, boolean logicMetric);
    public void fillRect(int x1, int y1, int x2, int y2, boolean logicMetric);

    public void drawText(String text, int x, int y, boolean logicMetric);

    public int getWidth(boolean logicMetric);
    public int getHeight(boolean logicMetric);

}
