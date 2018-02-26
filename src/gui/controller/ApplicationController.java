
package gui.controller;

import genetic.Solver;
import genetic.board.Board;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ApplicationController {

    private Board startBoard;

    @FXML
    Button button;
    @FXML
    GridPane gridPane;
    @FXML
    Label label;

    @FXML
    public void initialize() {
        startBoard = new Board();
        //Methode in Board actualizeGrid und fill startGrid
        gridPane.setGridLinesVisible(true);
        gridPane.setMaxSize(450, 450);
        gridPane.setMinSize(450, 450);
        gridPane.setPrefSize(450, 450);

        Label[] labels = new Label[81];
        for (int i = 0; i < labels.length; i++){
            labels[i] = new Label();
            labels[i].setMinSize(50,50);
            labels[i].setMaxSize(50,50);
            labels[i].setPrefSize(50,50);
            labels[i].setFont(new Font("Arial", 30));
            labels[i].setAlignment(Pos.CENTER);
            gridPane.add(labels[i], i%9, i / 9);
        }
        fillGridForStartState(startBoard);
        button.setFont(new Font("Arial", 12));
        button.setOnAction(event -> solveStraits());
        label.setText("Status will be shown here.");
    }

    @FXML
    public void solveStraits() {
        button.setDisable(true);
            Task<Board> task= new Solver();

            label.textProperty().bind(task.messageProperty());
            task.valueProperty().addListener(new ChangeListener<Board>() {
                @Override
                public void changed(ObservableValue<? extends Board> observable, Board oldValue, Board newValue) {
                    if (newValue != null){
                        updateGrid(gridPane, newValue);
                    }
                }
            });

            Thread solver = new Thread(task);
            solver.setDaemon(true);
            solver.start();

    }

    private void fillGridForStartState(Board board){

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9 ; j++){

                if (board.getBoard()[i][j].isStart()){
                    if(board.getBoard()[i][j].isBlack()){
                        Label label = (Label) getNodeByRowColumnIndex(i,j,gridPane);
                        label.setStyle("-fx-background-color: Black");
                        if (board.getBoard()[i][j].getValue() != -5){
                            label.setTextFill(Color.WHITE);
                            label.setText(Integer.toString(board.getBoard()[i][j].getValue()));
                        }
                    } else {
                        Label label = (Label) getNodeByRowColumnIndex(i,j,gridPane);
                        label.setText(Integer.toString(board.getBoard()[i][j].getValue()));
                    }
                }
            }
        }
    }

    public void updateGrid(GridPane gridPane, Board board){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9 ; j++){
                if (!board.getBoard()[i][j].isStart()){
                    Label label = (Label) getNodeByRowColumnIndex(i,j,gridPane);
                    label.setText(Integer.toString(board.getBoard()[i][j].getValue()));
                }
            }
        }
    }

    private Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (node instanceof Label){
                if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                    result = node;
                    break;
                }
            }
        }
        return result;
    }

}
