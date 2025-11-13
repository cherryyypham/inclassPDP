package main.controller;

import java.awt.Graphics;

public interface State {
    void update();
    void draw(Graphics g);
    void handleKeyPress(int keyCode);
    void enter();
    void exit();
}