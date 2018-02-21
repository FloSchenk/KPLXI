package genetic.probation;

import genetic.GeneticConfig;
import genetic.board.Board;
import genetic.board.SingleField;

import java.util.ArrayList;

public class FitnessChecker {

    public int getFitness(Board b){
        SingleField [][] board = b.getBoard();
        int actualFitness = GeneticConfig.MAX_FITNESS;

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9 ; j++){
                if (!board[i][j].isStart()){
                    if (checkForError(board, i, j))
                        actualFitness--;
                }
            }
        }


        return actualFitness;
    }

    private ArrayList<SingleField> getNeighbours(SingleField [][] board, int rowIndex, int columnIndex){
        ArrayList<SingleField> neighbours = new ArrayList<>();

        if (rowIndex != 0 && rowIndex != 8){
            if (columnIndex != 0 && columnIndex != 8){
                if (!board[rowIndex + 1][columnIndex].isBlack())
                    neighbours.add(board[rowIndex + 1][columnIndex]);
                if (!board[rowIndex - 1][columnIndex].isBlack())
                    neighbours.add(board[rowIndex - 1][columnIndex]);
                if (!board[rowIndex][columnIndex + 1].isBlack())
                    neighbours.add(board[rowIndex][columnIndex + 1]);
                if (!board[rowIndex][columnIndex - 1].isBlack())
                    neighbours.add(board[rowIndex][columnIndex - 1]);
                return neighbours;
            } else if (columnIndex == 0){
                if (!board[rowIndex + 1][columnIndex].isBlack())
                    neighbours.add(board[rowIndex + 1][columnIndex]);
                if (!board[rowIndex - 1][columnIndex].isBlack())
                    neighbours.add(board[rowIndex - 1][columnIndex]);
                if (!board[rowIndex][columnIndex + 1].isBlack())
                    neighbours.add(board[rowIndex][columnIndex + 1]);
                return neighbours;
            } else if (columnIndex == 8){
                if (!board[rowIndex + 1][columnIndex].isBlack())
                    neighbours.add(board[rowIndex + 1][columnIndex]);
                if (!board[rowIndex - 1][columnIndex].isBlack())
                    neighbours.add(board[rowIndex - 1][columnIndex]);
                if (!board[rowIndex][columnIndex - 1].isBlack())
                    neighbours.add(board[rowIndex][columnIndex - 1]);
                return neighbours;
            }
        } else if (rowIndex == 0){
            if (columnIndex != 0 && columnIndex != 8){
                if (!board[rowIndex + 1][columnIndex].isBlack())
                    neighbours.add(board[rowIndex + 1][columnIndex]);
                if (!board[rowIndex][columnIndex + 1].isBlack())
                    neighbours.add(board[rowIndex][columnIndex + 1]);
                if (!board[rowIndex][columnIndex - 1].isBlack())
                    neighbours.add(board[rowIndex][columnIndex - 1]);
                return neighbours;
            } else if (columnIndex == 0){
                if (!board[rowIndex + 1][columnIndex].isBlack())
                    neighbours.add(board[rowIndex + 1][columnIndex]);
                if (!board[rowIndex][columnIndex + 1].isBlack())
                    neighbours.add(board[rowIndex][columnIndex + 1]);
                return neighbours;
            } else if (columnIndex == 8){
                if (!board[rowIndex + 1][columnIndex].isBlack())
                    neighbours.add(board[rowIndex + 1][columnIndex]);
                if (!board[rowIndex][columnIndex - 1].isBlack())
                    neighbours.add(board[rowIndex][columnIndex - 1]);
                return neighbours;
            }
        } else if (rowIndex == 8){
            if (columnIndex != 0 && columnIndex != 8){
                if (!board[rowIndex - 1][columnIndex].isBlack())
                    neighbours.add(board[rowIndex - 1][columnIndex]);
                if (!board[rowIndex][columnIndex + 1].isBlack())
                    neighbours.add(board[rowIndex][columnIndex + 1]);
                if (!board[rowIndex][columnIndex - 1].isBlack())
                    neighbours.add(board[rowIndex][columnIndex - 1]);
                return neighbours;
            } else if (columnIndex == 0){
                if (!board[rowIndex - 1][columnIndex].isBlack())
                    neighbours.add(board[rowIndex - 1][columnIndex]);
                if (!board[rowIndex][columnIndex + 1].isBlack())
                    neighbours.add(board[rowIndex][columnIndex + 1]);
                return neighbours;
            } else if (columnIndex == 8){
                if (!board[rowIndex - 1][columnIndex].isBlack())
                    neighbours.add(board[rowIndex - 1][columnIndex]);
                if (!board[rowIndex][columnIndex - 1].isBlack())
                    neighbours.add(board[rowIndex][columnIndex - 1]);
                return neighbours;
            }
        }

        return neighbours;
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

    private boolean checkForError(SingleField [][] board, int rowIndex, int columnIndex){
        ArrayList<SingleField> rowAndColumnEntries = getRowAndColumnEntries(board,rowIndex,columnIndex);
        ArrayList<SingleField> neighbours = getNeighbours(board,rowIndex,columnIndex);

        //check if no number is two times in row or column
        for (SingleField s: rowAndColumnEntries) {
            if (s.getValue() == board[rowIndex][columnIndex].getValue())
                return true;
        }

        //check if there is at least one Neighgbour which is entry -1 or entry +1
        int counterForStr8tNeighbours = 0;
        int valueOfField = board[rowIndex][columnIndex].getValue();
        for (SingleField s: neighbours) {
            if (valueOfField + 1 == s.getValue() || valueOfField - 1 == s.getValue())
                counterForStr8tNeighbours++;

        }
        if (counterForStr8tNeighbours == 0){
            return true;
        }

        return false;
    }
}
