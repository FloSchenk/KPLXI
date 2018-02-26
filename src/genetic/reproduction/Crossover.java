package genetic.reproduction;

import genetic.board.Board;
import tools.MersenneTwisterFast;

import java.util.ArrayList;

public interface Crossover {
    public void doCrossover(MersenneTwisterFast mersenneTwisterFast, ArrayList<Board> population, Board[][] pairedPopulation);
}
