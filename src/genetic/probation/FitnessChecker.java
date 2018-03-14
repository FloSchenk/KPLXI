package genetic.probation;

import genetic.GeneticConfig;
import genetic.board.Board;
import genetic.board.SingleField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FitnessChecker {

    //Nach Mail vom 04.03.2018 der aktuelle und korrekt implementierte Algorithmus.

    public int getFitness(Board b){
        SingleField [][] board = b.getBoard();
        int actualFitness = GeneticConfig.MAX_FITNESS;

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9 ; j++){
                if (!board[i][j].isStart()){
                        actualFitness = actualFitness - checkForError(board, i, j);
                }
            }
        }


        return actualFitness;
    }

    private ArrayList<SingleField> getRowCompartment(SingleField [][] board, int rowIndex, int columnIndex){

        ArrayList<SingleField> rowCompartment = new ArrayList<>();
        rowCompartment.clear();

        if (columnIndex == 0){
            for (int i = columnIndex; i <= 8; i++){
                if (board[rowIndex][i].isBlack())
                    break;
                rowCompartment.add(board[rowIndex][i]);
            }
        } else if (columnIndex == 8){
            for (int i = columnIndex; i >= 0; i--){
                if (board[rowIndex][i].isBlack())
                    break;
                rowCompartment.add(board[rowIndex][i]);
            }
        } else {
            for (int i = columnIndex; i <= 8; i++){
                if (board[rowIndex][i].isBlack())
                    break;
                rowCompartment.add(board[rowIndex][i]);
            }
            for (int i = columnIndex - 1; i >= 0; i--){
                if (board[rowIndex][i].isBlack())
                    break;
                rowCompartment.add(board[rowIndex][i]);
            }
        }

        return rowCompartment;
    }

    private ArrayList<SingleField> getColumnCompartment(SingleField [][] board, int rowIndex, int columnIndex){

        ArrayList<SingleField> columnCompartment = new ArrayList<>();
        columnCompartment.clear();

        if (rowIndex == 0){
            for (int i = rowIndex; i <= 8; i++){
                if (board[i][columnIndex].isBlack())
                    break;
                columnCompartment.add(board[i][columnIndex]);
            }
        } else if (rowIndex == 8){
            for (int i = rowIndex; i >= 0; i--){
                if (board[i][columnIndex].isBlack())
                    break;
                columnCompartment.add(board[i][columnIndex]);
            }
        } else {
            for (int i = rowIndex; i <= 8; i++){
                if (board[i][columnIndex].isBlack())
                    break;
                columnCompartment.add(board[i][columnIndex]);
            }
            for (int i = rowIndex - 1; i >= 0; i--){
                if (board[i][columnIndex].isBlack())
                    break;
                columnCompartment.add(board[i][columnIndex]);
            }
        }

        return columnCompartment;
    }

     public boolean checkForStraitError(ArrayList<SingleField> compartment){
        Collections.sort(compartment, new Comparator<SingleField>() {
            @Override
            public int compare(SingleField s1, SingleField s2) {
                return s1.getValue() - s2.getValue(); //sort ascending
            }
        });

        for (int i = 0; i < compartment.size() - 1; i ++){
                if ((compartment.get(i + 1).getValue() - compartment.get(i).getValue()) != 1){
                    return true;
                }
        }

        return false;
    }

    private ArrayList<SingleField> getRowAndColumnEntries(SingleField [][] board, int rowIndex, int columnIndex){
        ArrayList<SingleField> rowAndColumnEntries = new ArrayList<>();

        for (int i = 0; i < 9; i++){
            if (i == columnIndex)
                continue;
            if (board[rowIndex][i].getValue() == -5){
                continue;
            }
            rowAndColumnEntries.add(board[rowIndex][i]);
        }

        for (int i = 0; i < 9; i++){
            if (i == rowIndex)
                continue;
            if (board[i][columnIndex].getValue() == -5){
                continue;
            }
            rowAndColumnEntries.add(board[i][columnIndex]);
        }

        return rowAndColumnEntries;
    }

    private int checkForError(SingleField [][] board, int rowIndex, int columnIndex){

        int numberOfErrors = 0;

        //check if no number is two times in row or column
       ArrayList<SingleField> rowAndColumnEntries = getRowAndColumnEntries(board,rowIndex,columnIndex);
        for (SingleField s: rowAndColumnEntries) {
            if (s.getValue() == board[rowIndex][columnIndex].getValue()) {
                numberOfErrors++;
                break;
            }
        }

        //check for Straits
        ArrayList<SingleField> rowCompartment = getRowCompartment(board, rowIndex, columnIndex);
        if (checkForStraitError(rowCompartment)){
            numberOfErrors++;
        }

        ArrayList<SingleField> columnCompartment = getColumnCompartment(board, rowIndex, columnIndex);
        if (checkForStraitError(columnCompartment)){
            numberOfErrors++;
        }

        return numberOfErrors;
    }
}
