package genetic.selection;

import genetic.board.Board;
import tools.MersenneTwisterFast;

import java.util.ArrayList;

public class Pairing {

    public Board[][] doPairing(MersenneTwisterFast mersenneTwisterFast, ArrayList<Board> population){
        ArrayList<Board> populationToPair = (ArrayList<Board>) population.clone();
        Board[][] pairedParents = new Board[populationToPair.size() / 2][2];
        int counter = 0;
        while (!populationToPair.isEmpty()){
            int parentOne, parentTwo;
            do {
                parentOne = mersenneTwisterFast.nextInt(0, populationToPair.size() - 1);
                parentTwo = mersenneTwisterFast.nextInt(0 ,populationToPair.size() - 1);
            } while (parentOne == parentTwo);
            pairedParents[counter][0] = populationToPair.get(parentOne);
            pairedParents[counter][1] = populationToPair.get(parentTwo);
            if (parentOne < parentTwo){
                populationToPair.remove(parentTwo);
                populationToPair.remove(parentOne);
            } else{
                populationToPair.remove(parentOne);
                populationToPair.remove(parentTwo);
            }
            counter++;
        }
        return pairedParents;
    }
}
