package genetic.reproduction;

import genetic.board.Board;
import tools.MersenneTwisterFast;

import java.util.ArrayList;

public class ModuloCrossover implements Crossover{
    //Just invented by me...
    @Override
    public void doCrossover(MersenneTwisterFast mersenneTwisterFast, ArrayList<Board> population, Board[][] pairedPopulation) {

        for (int x = 0; x < pairedPopulation.length; x++){
            Board childrenOne,childrenTwo;

            Board parentOne = pairedPopulation[x][0];
            Board parentTwo = pairedPopulation[x][1];

            //With new Board childrens already get filled with Start Values and Dummys
            childrenOne = new Board();
            childrenTwo = new Board();

            int crossoverIndex = mersenneTwisterFast.nextInt(3,6);

            for (int i = 0; i <= 80; i++) {
                if (i % crossoverIndex == 0){
                    if (!parentOne.getBoard()[i / 9][i % 9].isStart()) {
                        childrenOne.getBoard()[i / 9][i % 9].setValue(parentTwo.getBoard()[i / 9][i % 9].getValue());
                        childrenTwo.getBoard()[i / 9][i % 9].setValue(parentOne.getBoard()[i / 9][i % 9].getValue());
                }
                }else{
                    if (!parentOne.getBoard()[i / 9][i % 9].isStart()) {
                        childrenOne.getBoard()[i / 9][i % 9].setValue(parentOne.getBoard()[i / 9][i % 9].getValue());
                        childrenTwo.getBoard()[i / 9][i % 9].setValue(parentTwo.getBoard()[i / 9][i % 9].getValue());
                    }
                }
            }


            population.add(childrenOne);
            population.add(childrenTwo);
        }
    }

}
