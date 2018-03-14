package genetic.selection;

import genetic.GeneticConfig;
import genetic.board.Board;

import java.util.ArrayList;

public class Selection {

    //survival of the 80 fittest and 20 weakest
    public void doSelection(ArrayList<Board> population){

        ArrayList<Board> temp = new ArrayList<>();
        for (int i = 0; i < GeneticConfig.SIZE_OF_POPULATION -(0.2 * GeneticConfig.SIZE_OF_POPULATION); i++){
            temp.add(population.get(i));
        }

        int index = population.size() - 1;
        for (int i = 0; i < GeneticConfig.SIZE_OF_POPULATION - (0.8 * GeneticConfig.SIZE_OF_POPULATION); i++){
            temp.add(population.get(index));
            index--;
        }

        population.clear();
        population.addAll(temp);
    }
}
