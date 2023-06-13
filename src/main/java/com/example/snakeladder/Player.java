package com.example.snakeladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    private Circle coin;
    private int currentPosition;
    private String name;
    private static Board gameBoard = new Board();

    public Player(int tileSize, Color coinColor, String playerName){
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        currentPosition = 0;
        movePlayer(1);
        name = playerName;
    }

    public void movePlayer(int diceValue){
        if(currentPosition + diceValue <= 100)
            currentPosition += diceValue;
        int x = gameBoard.getXCoordinate(currentPosition);
        int y = gameBoard.getYCoordinate(currentPosition);
        coin.setTranslateX(x);
        coin.setTranslateY(y);
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}
