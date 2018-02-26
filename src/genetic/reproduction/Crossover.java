package genetic.reproduction;

import genetic.board.Board;
import tools.MersenneTwisterFast;

import java.util.ArrayList;

public interface Crossover {
    void doCrossover(MersenneTwisterFast mersenneTwisterFast, ArrayList<Board> population, Board[][] pairedPopulation);
}
