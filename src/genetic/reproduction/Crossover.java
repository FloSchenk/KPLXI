package genetic.reproduction;

import genetic.board.Board;
import tools.MersenneTwisterFast;

import java.util.ArrayList;

public class Crossover {

    public void doCrossover(MersenneTwisterFast mersenneTwisterFast, ArrayList<Board> population, Board[][] pairedPopulation){
        for (int x = 0; x < pairedPopulation.length; x++){
            Board childrenOne,childrenTwo;

            Board parentOne = pairedPopulation[x][0];
            Board parentTwo = pairedPopulation[x][1];

            //With new Board childrens already get filled with Start Values and Dummys
            childrenOne = new Board();
            childrenTwo = new Board();

            int startIndex = mersenneTwisterFast.nextInt(0,80);
            int stopIndex = mersenneTwisterFast.nextInt(0,80);

            if (startIndex > stopIndex){
                int temp = startIndex;
                startIndex = stopIndex;
                stopIndex = temp;
            }

            for (int i = startIndex; i <= stopIndex; i++){
                if (!parentOne.getBoard()[i / 9][i % 9].isStart()){
                    childrenOne.getBoard()[i / 9][i % 9].setValue(parentTwo.getBoard()[i / 9][i % 9].getValue());
                    childrenTwo.getBoard()[i / 9][i % 9].setValue(parentOne.getBoard()[i / 9][i % 9].getValue());
                }
            }

            for (int i = 0;  i < startIndex; i++){
                if (!parentOne.getBoard()[i / 9][i % 9].isStart()){
                    childrenOne.getBoard()[i / 9][i % 9].setValue(parentTwo.getBoard()[i / 9][i % 9].getValue());
                    childrenTwo.getBoard()[i / 9][i % 9].setValue(parentOne.getBoard()[i / 9][i % 9].getValue());
                }
            }

            for (int i = stopIndex + 1; i < 81; i++){
                if (!parentOne.getBoard()[i / 9][i % 9].isStart()){
                    childrenOne.getBoard()[i / 9][i % 9].setValue(parentTwo.getBoard()[i / 9][i % 9].getValue());
                    childrenTwo.getBoard()[i / 9][i % 9].setValue(parentOne.getBoard()[i / 9][i % 9].getValue());
                }
            }

            population.add(childrenOne);
            population.add(childrenTwo);
        }
    }
}
