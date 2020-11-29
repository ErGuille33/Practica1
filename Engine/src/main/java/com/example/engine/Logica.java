package com.example.engine;

import java.util.List;

public interface Logica {
    public void update(float deltaTime);
    public void render(Graphics g) throws Exception;
    public void handleInput(List<Input.TouchEvent> te);
    public void init() throws Exception;
    public void getEngine(Engine engine);
}
