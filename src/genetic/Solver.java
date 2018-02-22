package genetic;

import genetic.board.Board;
import genetic.probation.FitnessChecker;
import genetic.reproduction.Crossover;
import genetic.reproduction.Mutation;
import genetic.selection.Pairing;
import genetic.selection.Selection;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import tools.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solver {

    private MersenneTwisterFast mersenneTwisterFast;
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
        mersenneTwisterFast = new MersenneTwisterFast();
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

        for (int iteration = 0; iteration < GeneticConfig.MAX_ITERATIONS; iteration++) {
            if (bestActualFitnes == GeneticConfig.MAX_FITNESS) {
                population.get(0).updateGrid(gridPane);
                button.setText("After " + iteration + " iterations the solution was found. Solution is shown below:");
                break;
            } else {
                selection.doSelection(population);
                doReproduction(population);
                sortPopulationForFitness(population);
                //if (iteration % 1000 == 0)
                  //  population.get(0).updateGrid(gridPane); // TODO fix this. To Update the Gridpane
            }
        }
        if (bestActualFitnes != GeneticConfig.MAX_FITNESS){
            population.get(0).updateGrid(gridPane);
            System.out.println(bestActualFitnes);//TODO delete this
            button.setText("After " + GeneticConfig.MAX_ITERATIONS + " iterations NO solution was found. Best Result shown below: ");
        }
    }

    private void initialize(ArrayList<Board> population){
        for (int i = 0; i < GeneticConfig.SIZE_OF_POPULATION; i++){
            Board b = new Board();
            b.fillBoardWithRandomNumbers();
            population.add(b);
        }
    }

    private void sortPopulationForFitness(ArrayList<Board> population){
        Collections.sort(population, new Comparator<Board>() {
                    @Override
                    public int compare(Board b1, Board b2) {
                        return fitnessChecker.getFitness(b2) - fitnessChecker.getFitness(b1);
                    }
                });
        bestActualFitnes = fitnessChecker.getFitness(population.get(0));
    }


    private void doReproduction(ArrayList<Board> population){
        Board[][] pairedPopulation = pairing.doPairing(mersenneTwisterFast, population);
        crossover.doCrossover(mersenneTwisterFast, population, pairedPopulation);
        mutation.doMutation(mersenneTwisterFast, population);
    }

}
