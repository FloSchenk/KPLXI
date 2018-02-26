
package gui.controller;

import backtracking.BacktrackingSolver;
import genetic.GeneticConfig;
import genetic.Solver;
import genetic.board.Board;
import gui.StraitsConfig;
import javafx.collections.ObservableList;
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
    private int iterations = 0;
    private Boolean isSolutionFound = false;

    @FXML
    Button button;
    @FXML
    GridPane gridPane;

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
    }

    @FXML
    public void solveStraits() {
        button.setText("Algorithm is solving...");
        button.setDisable(true);
        if (StraitsConfig.TYPE_OF_SOLVER.equals("genetic")) {
            Thread solver = new Thread(new Solver(this));
            solver.start();
        } else if(StraitsConfig.TYPE_OF_SOLVER.equals("backtracking")){
            Thread solver = new Thread(new BacktrackingSolver(this));
            solver.start();
        }


        // TODO wie regelmäßig aktualisieren?
        //while (!isSolutionFound) {
            try {
                Thread.sleep(500);
                updateGrid(startBoard, iterations, isSolutionFound);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        //}
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

    private void updateGrid(Board board, int iterations, boolean isSolutionFound){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9 ; j++){
                if (!board.getBoard()[i][j].isStart()){
                    Label label = (Label) getNodeByRowColumnIndex(i,j,gridPane);
                    label.setText(Integer.toString(board.getBoard()[i][j].getValue()));
                }
            }
        }
        if (iterations == GeneticConfig.MAX_ITERATIONS) {
            button.setText("After " + GeneticConfig.MAX_ITERATIONS + " iterations NO solution was found. Best Result shown below: ");
        }else {
            if(isSolutionFound){
                button.setText("After " + iterations + " iterations the solution was found. Solution is shown below:");
            } else {
                button.setText("Still searching for Solution. After " + iterations + " iterations actual best solution is shown below:");
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

    public void setStatsForActualize(Board board, int iterations, boolean isSolutionFound){
       this.startBoard = board;
       this.iterations = iterations;
       this.isSolutionFound = isSolutionFound;
    }
}
