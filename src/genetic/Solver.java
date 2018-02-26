package genetic;

import genetic.board.Board;
import genetic.probation.FitnessChecker;
import genetic.reproduction.Crossover;
import genetic.reproduction.ModuloCrossover;
import genetic.reproduction.OrderedCrossover;
import genetic.reproduction.Mutation;
import genetic.selection.Pairing;
import genetic.selection.Selection;
import gui.controller.ApplicationController;
import tools.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solver implements Runnable{

    private MersenneTwisterFast mersenneTwisterFast;
    private ApplicationController applicationController;
    private ArrayList<Board> population;
    private FitnessChecker fitnessChecker;
    private Selection selection;
    private Pairing pairing;
    private Crossover crossover;
    private Mutation mutation;
    private int bestActualFitnes = 0;

    public Solver(ApplicationController applicationController){
        mersenneTwisterFast = new MersenneTwisterFast();
        this.applicationController = applicationController;
        population = new ArrayList<>();
        fitnessChecker = new FitnessChecker();
        selection = new Selection();
        pairing = new Pairing();
        if (genetic.GeneticConfig.TYPE_OF_CROSSOVER.equals("ordered")){
            System.out.println("Type of Crossover: " + genetic.GeneticConfig.TYPE_OF_CROSSOVER);
            crossover = new OrderedCrossover();
        } else if (genetic.GeneticConfig.TYPE_OF_CROSSOVER.equals("modulo")){
            System.out.println("Type of Crossover: " + genetic.GeneticConfig.TYPE_OF_CROSSOVER);
            crossover = new ModuloCrossover();
        }
        mutation = new Mutation();
    }

    public void solve(){
        initialize(population);
        sortPopulationForFitness(population);

        for (int iteration = 0; iteration < genetic.GeneticConfig.MAX_ITERATIONS; iteration++) {
            if (bestActualFitnes == genetic.GeneticConfig.MAX_FITNESS) {
                applicationController.setStatsForActualize(population.get(0), iteration, true);
                break;
            } else {
                selection.doSelection(population);
                doReproduction(population);
                sortPopulationForFitness(population);
                if (iteration % 100 == 0)
                    applicationController.setStatsForActualize(population.get(0), iteration,false);
            }
            System.out.println("Actual Cyclus: " + iteration + " - Actual Fitness: " + bestActualFitnes);
        }
        if (bestActualFitnes != genetic.GeneticConfig.MAX_FITNESS){
            applicationController.setStatsForActualize(population.get(0), genetic.GeneticConfig.MAX_ITERATIONS, false);
            System.out.println(bestActualFitnes);//TODO delete this
        }
    }

    private void initialize(ArrayList<Board> population){
        for (int i = 0; i < genetic.GeneticConfig.SIZE_OF_POPULATION; i++){
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

    @Override
    public void run() {
        solve();
    }
}
