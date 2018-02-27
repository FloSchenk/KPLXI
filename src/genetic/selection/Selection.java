package genetic.selection;

import genetic.GeneticConfig;
import genetic.board.Board;

import java.util.ArrayList;

public class Selection {

    //survival of the fittest
    public void doSelection(ArrayList<Board> population){
        ArrayList<Board> temp = new ArrayList<>();
        for (int i = 0; i < GeneticConfig.SIZE_OF_POPULATION; i++){
            temp.add(population.get(i));
        }
        population.clear();
        population.addAll(temp);
    }
}
