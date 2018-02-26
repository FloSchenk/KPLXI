package genetic.board;

import tools.MersenneTwisterFast;

public class Board {

    private SingleField[][] board = new SingleField[9][9];

    public Board(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9 ; j++){
                board[i][j] = new SingleField();
            }
        }
        setStartState(board);
    }

    private void setStartState(SingleField[][] board) {
        board[0][0].setBlack(true);
        board[0][0].setStart(true);

        board[0][2].setStart(true);
        board[0][2].setValue(4);

        board[0][3].setBlack(true);
        board[0][3].setStart(true);

        board[0][4].setBlack(true);
        board[0][4].setStart(true);
        board[0][4].setValue(1);

        board[0][8].setBlack(true);
        board[0][8].setStart(true);

        board[1][0].setBlack(true);
        board[1][0].setStart(true);

        board[1][3].setBlack(true);
        board[1][3].setStart(true);
        board[1][3].setValue(4);

        board[1][7].setStart(true);
        board[1][7].setValue(5);

        board[1][8].setBlack(true);
        board[1][8].setStart(true);

        board[2][0].setBlack(true);
        board[2][0].setStart(true);

        board[2][5].setBlack(true);
        board[2][5].setStart(true);

        board[3][1].setStart(true);
        board[3][1].setValue(4);

        board[3][3].setStart(true);
        board[3][3].setValue(3);

        board[3][5].setBlack(true);
        board[3][5].setStart(true);

        board[3][6].setBlack(true);
        board[3][6].setStart(true);
        board[3][6].setValue(9);

        board[4][2].setBlack(true);
        board[4][2].setStart(true);

        board[4][6].setBlack(true);
        board[4][6].setStart(true);

        board[5][0].setStart(true);
        board[5][0].setValue(7);

        board[5][2].setBlack(true);
        board[5][2].setStart(true);

        board[5][3].setBlack(true);
        board[5][3].setStart(true);

        board[6][2].setStart(true);
        board[6][2].setValue(8);

        board[6][3].setBlack(true);
        board[6][3].setStart(true);

        board[6][5].setStart(true);
        board[6][5].setValue(1);

        board[6][6].setStart(true);
        board[6][6].setValue(3);

        board[6][8].setBlack(true);
        board[6][8].setStart(true);

        board[7][0].setBlack(true);
        board[7][0].setStart(true);

        board[7][3].setStart(true);
        board[7][3].setValue(7);

        board[7][5].setBlack(true);
        board[7][5].setStart(true);
        board[7][5].setValue(5);

        board[7][8].setBlack(true);
        board[7][8].setStart(true);
        board[7][8].setValue(2);

        board[8][0].setBlack(true);
        board[8][0].setStart(true);
        board[8][0].setValue(3);

        board[8][1].setStart(true);
        board[8][1].setValue(8);

        board[8][4].setBlack(true);
        board[8][4].setStart(true);

        board[8][5].setBlack(true);
        board[8][5].setStart(true);

        board[8][7].setStart(true);
        board[8][7].setValue(1);

        board[8][8].setBlack(true);
        board[8][8].setStart(true);
    }

    public SingleField[][] getBoard() {
        return board;
    }

    public void fillBoardWithRandomNumbers(){
        MersenneTwisterFast randomGenerator = new MersenneTwisterFast();

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9 ; j++){
                if (!board[i][j].isStart()){
                    board[i][j].setValue(randomGenerator.nextInt(1,9));
                }
            }
        }
    }

}
