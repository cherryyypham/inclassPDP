package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
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

    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snake = new Snake(5, 5);

        food = new Food(10,10);
        rand = new Random();
        placeFood();

        velocityX = 1;
        velocityY = 0;
        
		//game timer
		gameLoop = new Timer(100, this); //how long it takes to start timer, milliseconds gone between frames 
        gameLoop.start();
	}	
    
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {
        //Grid Lines
        for(int i = 0; i < boardWidth/tileSize; i++) {
            //(x1, y1, x2, y2)
            g.drawLine(i*tileSize, 0, i*tileSize, boardHeight);
            g.drawLine(0, i*tileSize, boardWidth, i*tileSize); 
        }

        //Food
        g.setColor(Color.red);
        // g.fillRect(food.getX()*tileSize, food.getY()*tileSize, tileSize, tileSize);
        g.fill3DRect(food.getX()*tileSize, food.getY()*tileSize, tileSize, tileSize, true);

        //Snake Head
        g.setColor(Color.green);
        // g.fillRect(snake.getX(), snake.getY(), tileSize, tileSize);
        // g.fillRect(snake.getX()*tileSize, snake.getY()*tileSize, tileSize, tileSize);
        g.fill3DRect(snake.getX()*tileSize, snake.getY()*tileSize, tileSize, tileSize, true);
        
        //Snake Body
        for (int i = 0; i < snake.getBodySize(); i++) {
            Tile snakePart = snake.getBody().get(i);
            // g.fillRect(snakePart.x*tileSize, snakePart.y*tileSize, tileSize, tileSize);
            g.fill3DRect(snakePart.x*tileSize, snakePart.y*tileSize, tileSize, tileSize, true);
		}

        //Score
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if (gameOver) {
            g.setColor(Color.red);
            g.drawString("Game Over: " + String.valueOf(snake.getBodySize()), tileSize - 16, tileSize);
        }
        else {
            g.drawString("Score: " + String.valueOf(snake.getBodySize()), tileSize - 16, tileSize);
        }
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
        move();
        repaint();
        if (gameOver) {
            gameLoop.stop();
        }
    }  

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("KeyEvent: " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    //not needed
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
