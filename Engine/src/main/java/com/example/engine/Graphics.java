package com.example.engine;

public interface Graphics {
    public Font newFont(String filename, float size, boolean isBold)throws Exception;
    public void setFont(Font font);

    public void clear(String color);
    public void translate(int x,int y);
    public void scale(float x);
    public void rotate(int angle);
    public void save();
    public void restore();


    public void setColor(String color);
    public void drawLine(int x1, int y1, int x2, int y2);
    public void fillRect(int x1, int y1, int x2, int y2);

    public void drawText(String text, int x, int y);

    public float getWidth();
    public float getHeight();
    public int getBaseWidth();
    public int getBaseHeight();
    public float calculateSize();


}
