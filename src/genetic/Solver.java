package genetic;

import genetic.board.Board;
import genetic.probation.FitnessChecker;
import genetic.reproduction.Crossover;
import genetic.reproduction.Mutation;
import genetic.selection.Pairing;
import genetic.selection.Selection;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Solver {

    private GridPane gridPane;
    private Button button;
    private ArrayList<Board> population;
    private FitnessChecker fitnessChecker;
    private Selection selection;
    private Pairing pairing;
    private Crossover crossover;
    private Mutation mutation;
    private int bestActualFitnes = 0;

    public Solver(GridPane gridPane, Button button){
        this.button = button;
        this.gridPane = gridPane;
        population = new ArrayList<>();
        fitnessChecker = new FitnessChecker();
        selection = new Selection();
        pairing = new Pairing();
        crossover = new Crossover();
        mutation = new Mutation();
    }

    public void solve(){
        initialize(population);
        sortPopulationForFitness(population);

        for (int iteration = 0; iteration < GeneticConfig.maxIterations; iteration++) {
            if (bestActualFitnes == GeneticConfig.maxFitness) {
                population.get(0).updateGrid(gridPane);
                button.setText("After " + iteration + " iterations the solution was found. Solution is shown below:");
                break;
            } else {
                //TODO
                //1. Selection
                //2. do Reproduktion
                //3. SortPopulation
            }
        }
        if (bestActualFitnes != GeneticConfig.maxFitness){
            button.setText("After " + GeneticConfig.maxIterations + " iterations NO solution was found. Best Result shown below: ");
        }
    }

    private void initialize(ArrayList<Board> population){
        for (int i = 0; i < GeneticConfig.sizeOfPopulation; i++){
            Board b = new Board();
            b.fillBoardWithRandomNumbers();
            population.add(b);
        }
    }

    private void sortPopulationForFitness(ArrayList<Board> population){
        //sortieren nach Fitness und actual Fitness setzen.
    }

    private void doSelection(ArrayList<Board> population){
        //schwächste töten mit Pest
    }

    private void doReproduction(ArrayList<Board> population){
        //doPairing(population); Array aus Lsiten mit Size 2 zurückgeben
        //doCrossover(population); für jeden Array Eintrag do Crossover aufrufen. Kinder zur Ursprünglichen population hinzufügen.
        // mit bedingter Wahrscheinlichkeit für jeden population Eintrag
        //doMutation(population);
    }

    private void doPairing(ArrayList<Board> population){
        //population paaren. Mit Jedem Paar Do Crossover aufrufen.
    }

    private void doCrossover(ArrayList<Board> population){
        //Liste mit 2 Parents übergeben
        //Liste mit 4 individuen zurückgeben
    }

    private void doMutation(Board board){
        // mutieren
    }
}
