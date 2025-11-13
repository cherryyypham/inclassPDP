package main.controller;

import main.view.SnakeGame;
import main.model.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayingState implements State {
    private SnakeGame game;

    public PlayingState(SnakeGame game) {
        this.game = game;
    }

    @Override
    public void update() {
        game.move();

        if (game.isGameOver()) {
            game.changeToGameOverState();
        }
    }

    @Override
    public void draw(Graphics g) {
        //Grid Lines
        g.setColor(new Color(30, 30, 30));
        for(int i = 0; i < game.getWidth()/game.getTileSize(); i++) {
            //(x1, y1, x2, y2)
            g.drawLine(i*game.getTileSize(), 0, i*game.getTileSize(), game.getHeight());
            g.drawLine(0, i*game.getTileSize(), game.getWidth(), i*game.getTileSize());
        }

        // Food
        Food food = game.getFood();
        g.setColor(Color.red);
        // g.fillRect(food.getX()*tileSize, food.getY()*tileSize, tileSize, tileSize);
        g.fill3DRect(food.getX() * game.getTileSize(), food.getY() * game.getTileSize(),
                game.getTileSize(), game.getTileSize(), true);

        // Snake Head
        Snake snake = game.getSnake();
        g.setColor(Color.green);
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

        // Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Score: " + snake.getBodySize(), game.getTileSize() - 16, game.getTileSize());

        // Pause hint
        g.setColor(Color.GRAY);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Press P to Pause", game.getWidth() - 140, game.getTileSize());
    }

    @Override
    public void handleKeyPress(int keyCode) {
        if (keyCode == KeyEvent.VK_UP && game.getVelocityY() != 1) {
            game.setVelocity(0, -1);
        } else if (keyCode == KeyEvent.VK_DOWN && game.getVelocityY() != -1) {
            game.setVelocity(0, 1);
        } else if (keyCode == KeyEvent.VK_LEFT && game.getVelocityX() != 1) {
            game.setVelocity(-1, 0);
        } else if (keyCode == KeyEvent.VK_RIGHT && game.getVelocityX() != -1) {
            game.setVelocity(1, 0);
        } else if (keyCode == KeyEvent.VK_P) {
            game.changeToPauseState();
        }
    }

    @Override
    public void enter() {
    }

    @Override
    public void exit() {
    }
}