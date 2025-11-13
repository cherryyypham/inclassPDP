package main.model;

import java.io.*;

public class HighScoreManager {
    private static final String FILE_NAME = "highscore.txt";
    private int highScore;

    public HighScoreManager() {
        loadHighScore();
    }

    private void loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line != null) {
                highScore = Integer.parseInt(line.trim());
            } else {
                highScore = 0;
            }
        } catch (FileNotFoundException e) {
            highScore = 0;
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading high score: " + e.getMessage());
            highScore = 0;
        }
    }

    private void saveHighScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(String.valueOf(highScore));
        } catch (IOException e) {
            System.err.println("Error saving high score: " + e.getMessage());
        }
    }

    public int getHighScore() {
        return highScore;
    }

    public boolean updateHighScore(int newScore) {
        if (newScore > highScore) {
            highScore = newScore;
            saveHighScore();
            return true;
        }
        return false;
    }
}