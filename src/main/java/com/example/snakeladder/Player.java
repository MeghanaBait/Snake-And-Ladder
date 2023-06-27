package com.example.snakeladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    private Circle coin;
    private int currentPosition;
    private String name;
    private static Board gameBoard = new Board();
    private static int ladderAndSnakeStart[] = {4, 13, 33, 42, 50, 62, 74, 27, 40,43, 54, 66, 76, 89, 99};
    private static int ladderAndSnakeENd[] = {25, 46, 49, 63, 69, 81, 92, 5, 3, 18, 31, 45, 58, 53, 41};

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
        for(int i = 0; i < ladderAndSnakeStart.length; i++){
            if(currentPosition == ladderAndSnakeStart[i]){
                currentPosition = ladderAndSnakeENd[i];
            }
        }
        int x = gameBoard.getXCoordinate(currentPosition);
        int y = gameBoard.getYCoordinate(currentPosition);
        System.out.println(currentPosition+" x:" + x+" y:"+y);
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
