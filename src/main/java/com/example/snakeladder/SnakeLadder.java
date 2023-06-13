package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize = 40, width = 10, height = 10;
    public static final int buttonLine = height*tileSize + 50, infoline = buttonLine - 30;
    private static Dice dice = new Dice();

    private boolean gameStarted = false, playerOneTurn  = false, playerTwoTurn = false;

    private Player playerOne, playerTwo;
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize, height*tileSize + 100);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j * tileSize);
                tile.setTranslateY(i * tileSize);
                root.getChildren().add(tile);
            }
        }

        Image img = new Image("C:\\Users\\Meghana\\IdeaProjects\\SnakeLadder\\src\\main\\img.png");

        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        //buttons
        Button player1Btn = new Button("Player One");
        Button player2Btn = new Button("Player Two");
        Button startButton = new Button("Start");

        player1Btn.setTranslateY(buttonLine);
        player1Btn.setTranslateX(20);
        player2Btn.setTranslateY(buttonLine);
        player2Btn.setTranslateX(300);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(170);

        //Info Display
        Label player1Label = new Label("Your Turn! P1");
        Label player2Label = new Label("Your Turn! P2");
        Label diceLabel = new Label("Start the Game");

        player1Label.setTranslateY(infoline);
        player1Label.setTranslateX(20);
        player2Label.setTranslateY(infoline);
        player2Label.setTranslateX(300);
        diceLabel.setTranslateY(infoline);
        diceLabel.setTranslateX(160);

        playerOne = new Player(tileSize, Color.BLACK, "Megha");
        playerTwo = new Player(tileSize-5, Color.WHITE, "Anu");

        //Player Action
        player1Btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneTurn){
                        //playing
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " + diceValue);
                        playerOne.movePlayer(diceValue);

                        //disabling the current player
                        playerOneTurn = false;
                        player1Btn.setDisable(true);
                        player1Label.setText("");

                        //Enable other player
                        playerTwoTurn = true;
                        player2Btn.setDisable(false);
                        player2Label.setText("Your Turn " + playerTwo.getName());
                    }
                }
            }
        });

        player2Btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                        //playing
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " + diceValue);
                        playerTwo.movePlayer(diceValue);

                        //disabling the current player
                        playerOneTurn = true;
                        player1Btn.setDisable(false);
                        player1Label.setText("Your Turn" + playerOne.getName());

                        //Enable other player
                        playerTwoTurn = true;
                        player2Btn.setDisable(false);
                        player2Label.setText("");
                    }
                }
            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);
                playerOneTurn = true;
                player1Label.setText("Your Turn " + playerOne.getName());
                playerTwoTurn = false;
                player2Label.setText("");
                player2Btn.setDisable(true);
            }
        });

        root.getChildren().addAll(board,
                player1Btn, player2Btn, startButton,
                player1Label, player2Label, diceLabel,
                playerOne.getCoin(), playerTwo.getCoin());
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}