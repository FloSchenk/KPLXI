package genetic;

import board.Board;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Solver {

    private GridPane gridPane;
    private Button button;
    private ArrayList<Board> population;

    public Solver(GridPane gridPane, Button button){
        this.button = button;
        this.gridPane = gridPane;
        population = new ArrayList<>();
    }

    public void solve(){

    }
}
