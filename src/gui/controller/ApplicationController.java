
package gui.controller;

import genetic.board.Board;
import genetic.Solver;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class ApplicationController {

    Board startBoard;

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
        startBoard.fillGridForStartState(gridPane);
        button.setFont(new Font("Arial", 12));
    }

    @FXML
    public void solveStraits() {
        button.setText("Algorithm is solving...");
        button.setDisable(true);
        Solver solver = new Solver(gridPane, button);
        solver.solve();
        //hier Genetischen Algorithmus aufrufen und dann grid und button mitgeben um Inhalt anzupassen.
        //button.setText("Algorithm is solving... - Best Solution after cycle number: "  + cycleIndex);
        //Button verschwinden lassen Label einblenden mit "is solving with genetic algorithm, best solution is shown below:"
        //Am ende Label ersetzen und schreiben "final Solution:"

    }
}
