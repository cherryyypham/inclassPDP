package main.controller;

import main.view.SnakeGame;
import main.model.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PauseState implements State {
    private SnakeGame game;
    private int selectedOption = 0; // 0 = Resume, 1 = Menu

    public PauseState(SnakeGame game) {
        this.game = game;
    }

    @Override
    public void update() {}


    @Override
    //	public void draw(Graphics g) {
//        //Grid Lines
//        for(int i = 0; i < boardWidth/tileSize; i++) {
//            //(x1, y1, x2, y2)
//            g.drawLine(i*tileSize, 0, i*tileSize, boardHeight);
//            g.drawLine(0, i*tileSize, boardWidth, i*tileSize);
//        }
//
//        //Food
//        g.setColor(Color.red);
//        // g.fillRect(food.getX()*tileSize, food.getY()*tileSize, tileSize, tileSize);
//        g.fill3DRect(food.getX()*tileSize, food.getY()*tileSize, tileSize, tileSize, true);
//
//        //Snake Head
//        g.setColor(Color.green);
//        // g.fillRect(snake.getX(), snake.getY(), tileSize, tileSize);
//        // g.fillRect(snake.getX()*tileSize, snake.getY()*tileSize, tileSize, tileSize);
//        g.fill3DRect(snake.getX()*tileSize, snake.getY()*tileSize, tileSize, tileSize, true);
//
//        //Snake Body
//        for (int i = 0; i < snake.getBodySize(); i++) {
//            Tile snakePart = snake.getBody().get(i);
//            // g.fillRect(snakePart.x*tileSize, snakePart.y*tileSize, tileSize, tileSize);
//            g.fill3DRect(snakePart.x*tileSize, snakePart.y*tileSize, tileSize, tileSize, true);
//		}
//
//        //Score
//        g.setFont(new Font("Arial", Font.PLAIN, 16));
//        if (gameOver) {
//            g.setColor(Color.red);
//            g.drawString("Game Over: " + String.valueOf(snake.getBodySize()), tileSize - 16, tileSize);
//        }
//        else {
//            g.drawString("Score: " + String.valueOf(snake.getBodySize()), tileSize - 16, tileSize);
//        }
//	}
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());

        g.setColor(new Color(30, 30, 30));
        //Grid Lines
        for(int i = 0; i < game.getWidth()/game.getTileSize(); i++) {
            //(x1, y1, x2, y2)
            g.drawLine(i*game.getTileSize(), 0, i*game.getTileSize(), game.getHeight());
            g.drawLine(0, i*game.getTileSize(), game.getWidth(), i*game.getTileSize());
        }

        // Food
        Food food = game.getFood();
        g.setColor(new Color(30, 0, 0));
        // g.fillRect(food.getX()*tileSize, food.getY()*tileSize, tileSize, tileSize);
        g.fill3DRect(food.getX() * game.getTileSize(), food.getY() * game.getTileSize(),
                game.getTileSize(), game.getTileSize(), true);

        // Snake Head
        Snake snake = game.getSnake();
        g.setColor(new Color(0, 0, 0));
        // g.fillRect(snake.getX(), snake.getY(), tileSize, tileSize);
        // g.fillRect(snake.getX()*tileSize, snake.getY()*tileSize, tileSize, tileSize);
        g.fill3DRect(snake.getX() * game.getTileSize(), snake.getY() * game.getTileSize(),
                game.getTileSize(), game.getTileSize(), true);

        // Snake Body
        for (int i = 0; i < snake.getBodySize(); i++) {
            Tile snakePart = snake.getBody().get(i);
            // g.fillRect(snakePart.x*tileSize, snakePart.y*tileSize, tileSize, tileSize);
            g.fill3DRect(snakePart.x * game.getTileSize(), snakePart.y * game.getTileSize(),
                    game.getTileSize(), game.getTileSize(), true);
        }

        // Pause overlay
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(100, 150, game.getWidth() - 200, 300);

        // Pause text
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        String pauseText = "PAUSED";
        FontMetrics fm = g.getFontMetrics();
        int pauseX = (game.getWidth() - fm.stringWidth(pauseText)) / 2;
        g.drawString(pauseText, pauseX, 230);

        // Menu options
        g.setFont(new Font("Arial", Font.BOLD, 24));
        if (selectedOption == 0) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.WHITE);
        }
        String resumeText = "RESUME (P)";
        int resumeX = (game.getWidth() - g.getFontMetrics().stringWidth(resumeText)) / 2;
        g.drawString(resumeText, resumeX, 310);

        // Back to Menu
        if (selectedOption == 1) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.WHITE);
        }
        String menuText = "BACK TO MENU";
        int menuX = (game.getWidth() - g.getFontMetrics().stringWidth(menuText)) / 2;
        g.drawString(menuText, menuX, 360);
    }

    @Override
    public void handleKeyPress(int keyCode) {
        if (keyCode == KeyEvent.VK_P) {
            game.changeToPlayingState();
        } else if (keyCode == KeyEvent.VK_UP) {
            selectedOption = 0;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            selectedOption = 1;
        } else if (keyCode == KeyEvent.VK_ENTER) {
            if (selectedOption == 0) {
                game.changeToPlayingState();
            } else if (selectedOption == 1) {
                game.changeToMenuState();
            }
        }
    }

    @Override
    public void enter() {
        selectedOption = 0;
    }

    @Override
    public void exit() {
    }
}