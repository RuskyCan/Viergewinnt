package com.example.viergewinnt;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class View {

    private static final int FIELD_SIZE = 100;
    private static final int BORDER_SIZE = FIELD_SIZE / 10;

    private Stage stage;
    private Model model;
    private GridPane gridPane;
    private Button[] buttons;
    private Circle[][] canvasses;
    private Label messageLabel;

    public View(Model model, Stage stage) {
        this.model = model;
        this.stage = stage;
        this.gridPane = new GridPane();
        this.canvasses = new Circle[model.getNumberOfRows()][model.getNumberOfColumns()];
        this.buttons = new Button[model.getNumberOfColumns()];
        initializeStage();
    }

    private void initializeStage() {
        BorderPane root = new BorderPane();
        gridPane.setStyle("-fx-background-color: darkblue; -fx-padding: 10;");

        for (int r = 0; r < model.getNumberOfRows(); r++) {
            for (int c = 0; c < model.getNumberOfColumns(); c++) {
                Circle circle = new Circle(FIELD_SIZE / 2 - BORDER_SIZE);
                circle.setFill(Color.WHITE);
                canvasses[r][c] = circle;
                gridPane.add(circle, c, r + 1);
            }
        }

        GridPane topBar = new GridPane();
        for (int c = 0; c < model.getNumberOfColumns(); c++) {
            Button button = new Button("Drop " + (c + 1));
            button.setPrefWidth(FIELD_SIZE);
            buttons[c] = button;
            topBar.add(button, c, 0);
        }

        this.messageLabel = new Label("Spieler " + model.getActingPlayer() + " ist am Zug.");
        root.setTop(topBar);
        root.setCenter(gridPane);
        root.setBottom(messageLabel);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Vier Gewinnt (MVC)");
        stage.show();
    }

    public void update() {
        for (int r = 0; r < model.getNumberOfRows(); r++) {
            for (int c = 0; c < model.getNumberOfColumns(); c++) {
                String colorName = model.getFieldColor(r, c);
                Color color = Color.valueOf(colorName);
                canvasses[r][c].setFill(color);
            }
        }

        String status = model.Winner();
        if (status != null) {
            messageLabel.setText(status.equals("Unentschieden") ?
                    "!!! UNENTSCHIEDEN! Spielfeld voll. !!!" :
                    "!!! " + status + " hat GEWONNEN! !!!");
            for (Button b : buttons) {
                b.setDisable(true);
            }
        } else {
            messageLabel.setText("Aktueller Spieler: " + model.getActingPlayer());
            for (int c = 0; c < model.getNumberOfColumns(); c++) {
                if(model.getFieldContent(0, c) == 0) {
                    buttons[c].setDisable(false);
                } else {
                    buttons[c].setDisable(true);
                }
            }
        }
    }

    public Stage getStage() {
        return stage;
    }

    public Button getButton(int iColumn) {
        return buttons[iColumn];
    }
}