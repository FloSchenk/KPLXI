package tests;

import genetic.Solver;
import genetic.board.Board;
import genetic.board.SingleField;
import genetic.probation.FitnessChecker;
import genetic.reproduction.ModuloCrossover;
import genetic.reproduction.Mutation;
import genetic.reproduction.OrderedCrossover;
import genetic.selection.Pairing;
import genetic.selection.Selection;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestSlave {

    public static SingleField singleField;
    public static Board board;
    public static FitnessChecker fitnessChecker;
    public static ModuloCrossover moduloCrossover;
    public static OrderedCrossover orderedCrossover;
    public static Mutation mutation;
    public static Pairing pairing;
    public static Selection selection;
    public static Solver solver;

    @BeforeClass
    public static void init(){
        board = new Board();
        board.fillBoardWithRandomNumbers();
        singleField = new SingleField();
        fitnessChecker = new FitnessChecker();
        moduloCrossover = new ModuloCrossover();
        orderedCrossover = new OrderedCrossover();
        mutation = new Mutation();
        pairing = new Pairing();
        selection = new Selection();
        solver = new Solver();
    }

    @Test
    public void testSingleField(){
        Assert.assertNotNull(singleField);
        assertEquals(false, singleField.isStart());
        assertEquals(false, singleField.isBlack());
        assertEquals(-5, singleField.getValue());
    }

    @Test
    public void testBoard(){
        Assert.assertNotNull(board);
        for (int i = 0; i <= 80; i++) {
            Assert.assertNotNull(board.getBoard()[i / 9][i % 9]);
        }
        assertEquals(true,board.getBoard()[0][0].isBlack());
        assertEquals(true,board.getBoard()[0][0].isStart());
        assertEquals(true,board.getBoard()[0][4].isBlack());
        assertEquals(1 ,board.getBoard()[0][4].getValue());
        assertEquals(true ,board.getBoard()[8][7].isStart());
        assertEquals(1 ,board.getBoard()[8][7].getValue());

    }

    @Test
    public void testFitnessChecker(){
        Assert.assertNotNull(fitnessChecker);
        SingleField singleField1 = new SingleField();
        SingleField singleField2 = new SingleField();
        SingleField singleField3 = new SingleField();
        singleField1.setValue(1);
        singleField2.setValue(3);
        singleField3.setValue(2);
        ArrayList<SingleField> arrayList = new ArrayList<>();
        arrayList.add(singleField1);
        arrayList.add(singleField2);
        arrayList.add(singleField3);
        assertEquals(false,fitnessChecker.checkForStraitError(arrayList));
    }

    @Test
    public void testModuloCrossover(){
        Assert.assertNotNull(moduloCrossover);
    }

    @Test
    public void testOrderedCrossover(){
        Assert.assertNotNull(orderedCrossover);
    }

    @Test
    public void testMutation(){
        Assert.assertNotNull(mutation);
    }

    @Test
    public void testPairing(){
        Assert.assertNotNull(pairing);
    }

    @Test
    public void testSelection(){
        Assert.assertNotNull(selection);
    }

    @Test
    public void testSolver(){
        Assert.assertNotNull(solver);
    }
}
