package main.controller;

import java.awt.Graphics;

public class StateContext {
    private State state;

    public StateContext(State state) {
        this.state = state;
        if (state != null) {
            state.enter();
        }
    }

    public void setState(State newState) {
        if (state != null) {
            state.exit();
        }
        state = newState;
        if (state != null) {
            state.enter();
        }
    }

    public void update() {
        if (state != null) {
            state.update();
        }
    }

    public void draw(Graphics g)  {
        if (state != null) {
            state.draw(g);
        }
    }

    public void handleKeyPress(int keyCode) {
        if (state != null) {
            state.handleKeyPress(keyCode);
        }
    }
}