package main.controller;

import main.view.SnakeGame;
import main.model.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverState implements State {
    private SnakeGame game;
    private int selectedOption = 0; // 0 = Restart, 1 = Menu
    private int finalScore;

    public GameOverState(SnakeGame game) {
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

        // Game Over overlay
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(80, 120, game.getWidth() - 160, 360);

        // Game Over text
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 56));
        String gameOverText = "GAME OVER";
        FontMetrics fm = g.getFontMetrics();
        int gameOverX = (game.getWidth() - fm.stringWidth(gameOverText)) / 2;
        g.drawString(gameOverText, gameOverX, 200);


        // Score
        g.setColor(Color.GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String scoreText = "Score: " + finalScore;
        int scoreX = (game.getWidth() - g.getFontMetrics().stringWidth(scoreText)) / 2;
        g.drawString(scoreText, scoreX, 260);

        // High Score
        g.setColor(Color.GRAY);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        String highScoreText = "High Score: " + game.getHighScore();
        int hsX = (game.getWidth() - g.getFontMetrics().stringWidth(highScoreText)) / 2;
        g.drawString(highScoreText, hsX, 290);

        // Menu options
        g.setFont(new Font("Arial", Font.BOLD, 24));

        if (selectedOption == 0) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.WHITE);
        }
        String restartText = "RESTART (R)";
        int restartX = (game.getWidth() - g.getFontMetrics().stringWidth(restartText)) / 2;
        g.drawString(restartText, restartX, 385);

        if (selectedOption == 1) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.WHITE);
        }
        String menuText = "MAIN MENU (M)";
        int menuX = (game.getWidth() - g.getFontMetrics().stringWidth(menuText)) / 2;
        g.drawString(menuText, menuX, 435);
    }

    @Override
    public void handleKeyPress(int keyCode) {
        if (keyCode == KeyEvent.VK_R || (keyCode == KeyEvent.VK_ENTER && selectedOption == 0)) {
            game.startGame();
        } else if (keyCode == KeyEvent.VK_M || (keyCode == KeyEvent.VK_ENTER && selectedOption == 1)) {
            game.changeToMenuState();
        } else if (keyCode == KeyEvent.VK_UP) {
            selectedOption = 0;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            selectedOption = 1;
        }
    }

    @Override
    public void enter() {
        selectedOption = 0;
        finalScore = game.getSnake().getBodySize();
    }

    @Override
    public void exit() {
    }
}