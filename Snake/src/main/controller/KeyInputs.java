package main.controller;

import main.view.SnakeGame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs implements KeyListener {
    private SnakeGame game;

    public KeyInputs(SnakeGame game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        game.handleKeyPress(e.getKeyCode());
    }

    //not needed
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}

