package com.example.engine;

public interface Graphics {
    public Font newFont(String filename, float size, boolean isBold,int numFont)throws Exception;

    public void clear(int color);
    public void translate(int x,int y);
    public void scale(float x);
    public void rotate(int angle);
    public void save();
    public void restore();


    public void setColor(int color);
    public void drawLine(int x1, int y1, int x2, int y2);
    public void fillRect(int x1, int y1, int x2, int y2);

    public void drawText(String text, int x, int y);

    public float getWidth();
    public float getHeight();

    public float getBaseWidth();
    public float getBaseHeight();

    public void setBaseWidth(float w);
    public void setBaseHeight(float h);

    public float calculateSize();


}
