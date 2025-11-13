package main.controller;

import main.view.SnakeGame;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState implements State {
    private SnakeGame game;
    private int menuOption = 0; // 0 = start game; 1 = quit

    public MenuState(SnakeGame game) {
        this.game = game;
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());

        // Grid Lines
        g.setColor(new Color(30, 30, 30));
        for(int i = 0; i < game.getWidth()/game.getTileSize(); i++) {
            //(x1, y1, x2, y2)
            g.drawLine(i*game.getTileSize(), 0, i*game.getTileSize(), game.getHeight());
            g.drawLine(0, i*game.getTileSize(), game.getWidth(), i*game.getTileSize());
        }

        // Menu overlay
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(80, 120, game.getWidth() - 160, 360);

        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.setColor(Color.GREEN);
        String title = "SNAKE GAME";
        FontMetrics fm = g.getFontMetrics();
        int titleX = (game.getWidth() - fm.stringWidth(title)) / 2;
        g.drawString(title, titleX, 220);

        // High Score
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        String highScore = "High Score: " + game.getHighScore();
        int hsX = (game.getWidth() - g.getFontMetrics().stringWidth(highScore)) / 2;
        g.setColor(Color.GRAY);
        g.drawString(highScore, hsX, 260);

        // Menu Options
        g.setFont(new Font("Arial", Font.BOLD, 30));

        if (menuOption == 0) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.WHITE);
        }
        String startText = "START GAME";
        int startX = (game.getWidth() - g.getFontMetrics().stringWidth(startText)) / 2;
        g.drawString(startText, startX, 330);

        // Quit
        if (menuOption == 1) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.WHITE);
        }
        String quitText = "QUIT";
        int quitX = (game.getWidth() - g.getFontMetrics().stringWidth(quitText)) / 2;
        g.drawString(quitText, quitX, 390);

        // Instructions
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.setColor(Color.GRAY);
        String instructions = "Use UP/DOWN arrows to navigate, ENTER to select";
        int instX = (game.getWidth() - g.getFontMetrics().stringWidth(instructions)) / 2;
        g.drawString(instructions, instX, 450);
    }

    @Override
    public void handleKeyPress(int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            menuOption = 0;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            menuOption = 1;
        } else if (keyCode == KeyEvent.VK_ENTER) {
            if (menuOption == 0) {
                game.startGame();
            } else if (menuOption == 1) {
                System.exit(0);
            }
        }
    }

    @Override
    public void enter() {
        menuOption = 0;
    }

    @Override
    public void exit() {
    }
}