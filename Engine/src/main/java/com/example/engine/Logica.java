package com.example.engine;

public interface Logica {
    public void update(float deltaTime);
    public void render(Graphics g);
    public void init() throws Exception;
    public void getEngine(Engine engine);
}
