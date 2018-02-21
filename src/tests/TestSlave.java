package tests;

import genetic.board.Board;
import genetic.board.SingleField;
import genetic.probation.FitnessChecker;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSlave {

    public static Board board;

    //TODO Write Tests

    @BeforeClass
    public static void init(){
        board = new Board();
    }

    @Test
    public void testFitnesChecker(){
        FitnessChecker fitnessChecker = new FitnessChecker();
        board.fillBoardWithRandomNumbers();
        int fitness = fitnessChecker.getFitness(board);
        System.out.println(fitness);
    }

}
