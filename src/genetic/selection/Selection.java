package genetic.selection;

import genetic.board.Board;

import java.util.ArrayList;

public class Selection {

    public void doSelection(ArrayList<Board> population){
        ArrayList<Board> temp = new ArrayList<>();
        for (int i = 0; i < population.size() / 2; i++){
            temp.add(population.get(i));
        }
        population.clear();
        population.addAll(temp);
    }
}
