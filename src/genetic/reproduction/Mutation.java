package genetic.reproduction;

import genetic.GeneticConfig;
import genetic.board.Board;
import tools.MersenneTwisterFast;

import java.util.ArrayList;

public class Mutation {

    public void doMutation(MersenneTwisterFast mersenneTwisterFast, ArrayList<Board> population){
        for (Board board: population) {
            if (mersenneTwisterFast.nextInt(1, 100) <= GeneticConfig.PROBABILITY_OF_MUTATION_IN_PERCENT){
                int numberOfMutations = GeneticConfig.NUMBER_OF_MUTATIONS;
                    for (int i = 0; i < numberOfMutations; i++){
                        int row = mersenneTwisterFast.nextInt(0,8);
                        int column = mersenneTwisterFast.nextInt(0,8);
                        if(!board.getBoard()[row][column].isStart()){
                            board.getBoard()[row][column].setValue(mersenneTwisterFast.nextInt(1,9));
                        }
                    }
            }
        }
    }
}
