package main.view;

import main.model.*;
import main.controller.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener {
    int boardWidth;
    int boardHeight;
    int tileSize = 25;

    //snake
    Snake snake;

    //food
    Food food;
    Random rand;

    //game logic
    int velocityX;
    int velocityY;
    Timer gameLoop;
    boolean gameOver = false;
    private HighScoreManager highScoreManager;

    //game states
    private StateContext stateContext;
    private MenuState menuState;
    private PlayingState playingState;
    private PauseState pauseState;
    private GameOverState gameOverState;

    public SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        setFocusable(true);

        rand = new Random();
        highScoreManager = new HighScoreManager();

        snake = new Snake(5, 5);
        food = new Food(10, 10);
        velocityX = 0;
        velocityY = 0;

        //states
        menuState = new MenuState(this);
        playingState = new PlayingState(this);
        pauseState = new PauseState(this);
        gameOverState = new GameOverState(this);

        stateContext = new StateContext(menuState);

        addKeyListener(new KeyInputs(this));

		//game timer
		gameLoop = new Timer(100, this); //how long it takes to start timer, milliseconds gone between frames
        gameLoop.start();
	}

    public void startGame() {
        snake = new Snake(5, 5);
        food = new Food(10, 10);
        placeFood();
        velocityX = 1;
        velocityY = 0;
        gameOver = false;
        changeToPlayingState();
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		stateContext.draw(g);
	}

    public void placeFood(){
        food.setX(rand.nextInt(boardWidth/tileSize));
		food.setY(rand.nextInt(boardHeight/tileSize));
	}

    public void move() {
        //eat food
        if (collision(snake.getTile(), food.getTile())) {
            snake.addBody(food.getX(), food.getY());
            placeFood();
        }

        //move snake body
        for (int i = snake.getBodySize()-1; i >= 0; i--) {
            Tile snakePart = snake.getBody().get(i);
            if (i == 0) { //right before the head
                snakePart.x = snake.getX();
                snakePart.y = snake.getY();
            }
            else {
                Tile prevSnakePart = snake.getBody().get(i-1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }
        //move snake head
        snake.setX(snake.getX() + velocityX);
        snake.setY(snake.getY() + velocityY);

        //game over conditions
        for (int i = 0; i < snake.getBodySize(); i++) {
            Tile snakePart = snake.getBody().get(i);

            //collide with snake head
            if (collision(snake.getTile(), snakePart)) {
                gameOver = true;
            }
        }

        if (snake.getX()*tileSize < 0 || snake.getX()*tileSize > boardWidth || //passed left border or right border
            snake.getY()*tileSize < 0 || snake.getY()*tileSize > boardHeight ) { //passed top border or bottom border
            gameOver = true;
        }
    }

    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) { //called every x milliseconds by gameLoop timer
        stateContext.update();
        repaint();
    }

    public void handleKeyPress(int keyCode) {
        stateContext.handleKeyPress(keyCode);
    }

    public void changeToPlayingState() {
        stateContext.setState(playingState);
    }

    public void changeToPauseState() {
        stateContext.setState(pauseState);
    }

    public void changeToGameOverState() {
        stateContext.setState(gameOverState);
    }

    public void changeToMenuState() {
        stateContext.setState(menuState);
    }

    public int getWidth() {
        return boardWidth;
    }

    public int getHeight() {
        return boardHeight;
    }

    public int getTileSize() {
        return tileSize;
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getHighScore() {
        return highScoreManager.getHighScore();
    }

    public void setVelocity(int x, int y) {
        velocityX = x;
        velocityY = y;
    }

    public boolean updateHighScore(int score) {
        return highScoreManager.updateHighScore(score);
    }
}

