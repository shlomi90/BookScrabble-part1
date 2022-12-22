package test;


import java.util.ArrayList;

public class Board {
    private static Board b = null;
    Tile[][] t = new Tile[15][15];
    int [][] score = new int [15][15];
    public Board() {
        for (int i = 0; i < 15; i++) //initialize the array
            for (int j = 0; j < 15; j++) {

                t[i][j] = null;
                score[i][j] = 0;
            }

        score[7][7] = 5;
//Setting Red BoardSquare x3 word
        score[0][0] = (4);
        score[0][7] = (4);
        score[0][14] = (4);
        score[7][0] = (4);
        score[7][14] = (4);
        score[14][0] = (4);
        score[14][7] = (4);
        score[14][14] = (4);

        //Setting Yellow BoardSquare x2 word
        score[1][1] = (3);
        score[2][2] = (3);
        score[3][3] = (3);
        score[4][4] = (3);
        score[1][13] = (3);
        score[2][12] = (3);
        score[3][11] = (3);
        score[4][10] = (3);
        score[13][13] = (3);
        score[12][12] = (3);
        score[11][11] = (3);
        score[10][10] = (3);
        score[10][4] = (3);
        score[11][3] = (3);
        score[12][2] = (3);
        score[13][1] = (3);

        //Setting Blue BoardSquare x3 letter
        score[1][5] = (2);
        score[1][9] = (2);
        score[5][1] = (2);
        score[5][5] = (2);
        score[5][9] = (2);
        score[5][13] = (2);
        score[9][1] = (2);
        score[9][5] = (2);
        score[9][9] = (2);
        score[9][13] = (2);
        score[13][5] = (2);
        score[13][9] = (2);

        //Setting Cyan BoardSquare x2
        score[0][3] = (1);
        score[0][11] = (1);
        score[2][6] = (1);
        score[2][8] = (1);
        score[3][0] = (1);
        score[3][7] = (1);
        score[3][14] = (1);
        score[6][2] = (1);
        score[6][6] = (1);
        score[6][8] = (1);
        score[6][12] = (1);
        score[7][3] = (1);
        score[7][11] = (1);
        score[8][2] = (1);
        score[8][6] = (1);
        score[8][8] = (1);
        score[8][12] = (1);
        score[11][0] = (1);
        score[11][7] = (1);
        score[11][14] = (1);
        score[12][6] = (1);
        score[12][8] = (1);
        score[14][3] = (1);
        score[14][11] = (1);

    }

    public static Board getBoard() {
        if (b == null) {
            b = new Board();
        }
        return b;
    }

    public Tile[][] getTiles(){
        Tile[][] copy = new Tile[15][15];
        for(int i=0; i<15; i++){
            System.arraycopy(t,0,copy[i],0,15); // 0 represents the colum
        }
        return copy;
    }

    boolean inBoard(Word w){
        if (w.isVertical()==true)
        {
            if(15 - w.getRow() > w.tiles.length && w.getRow()>=0 && w.getRow() <15  && w.getCol()>=0 && w.getCol()<15){  //the word isn't in the limits of the board
                return true;
            }
        }
        else if(15 - w.getCol() > w.tiles.length && w.getCol()>=0 && w.getCol()<15 && w.getRow()>=0 && w.getRow() <15){  //for horizontal word
            return true;
        }
        return false;
    }

    Tile leanOnTile(Word w) {  // didn't check for cases if the word as at the end of the board.. could go look for a tile that doesn't exist.
        int j =0;
        if (w.isVertical() == true) {
            for (int i = 0; i < w.tiles.length; i++) {
                if (t[w.getRow() + i][w.getCol()] != null &&  w.tiles[i]==null)
                    return t[w.getRow() + i][w.getCol()];
                if (w.getCol() -1 >= 0 && t[w.getRow() + i][w.getCol() - 1] != null) {
                    return t[w.getRow() + i][w.getCol() - 1];
                }
                if (w.getCol() +1 <= 14 && t[w.getRow() + i][w.getCol() + 1] != null) {
                    return t[w.getRow() + i][w.getCol() + 1];
                }
            }
            return null;
        }
        else  //word is horizontal
            for (int i = 0; i < w.tiles.length; i++) {
                if (t[w.getRow()][w.getCol() + i] != null && w.tiles[i]==null)
                    return t[w.getRow()][w.getCol() + i];
                if (w.getRow() - 1 >= 0 && t[w.getRow() - 1][w.getCol() + i] != null) {
                    return t[w.getRow() - 1][w.getCol() + i];
                }
                if (w.getRow() + 1 <= 14 && t[w.getRow() + 1][w.getCol() + i] != null) {
                    return t[w.getRow() + 1][w.getCol() + i];
                }
            }
        return null;
    }

    boolean tileNotChange(Word w){
        if (w.isVertical() == true) {
            for (int i = 0; i < w.tiles.length; i++) {
                if (w.tiles[i] == null &&  t[w.getRow()+i][w.getCol()] == null) {
                    return false;
                }
            }
        }else  for (int i = 0; i < w.tiles.length; i++) {
            if  (w.tiles[i] == null && t[w.getRow()][w.getCol()+i] == null) {
                return false;
            }
        }
        return true;
    }

    boolean emptyBoard(){
        if(t[7][7] == null)
            return true;
        return false;
    }

    public boolean firstWordPlace(Word w) {
        if (emptyBoard()) {
            if (w.isVertical()) {
                for (int i = 0; i < w.tiles.length; i++) {
                    if (w.getCol() == 7) {
                        if (w.getRow() + i == 7)
                            return true;
                    }
                }

            } else for (int i = 0; i < w.tiles.length; i++) {
                if (w.getRow() == 7)
                    if (w.getCol() + i == 7)
                        return true;
            }
        }
        return false;
    }
    boolean boardLegal(Word w) {
        if (emptyBoard()) {
            if (firstWordPlace(w)) {
                if (inBoard(w))
                    return true;
            }
            return false;
        }
        if (inBoard(w))
            if (leanOnTile(w)!=null)
                if(tileNotChange(w))
                    return true;
        return false;
    }

    boolean dictionaryLegal(Word w){
        return true;
    }

    ArrayList<Word> getWords(Word w) {
        ArrayList<Word> a = new ArrayList<>();
        a.add(w);
        int leftUpCounter = 0, rightDownCounter = 0;
        int i = 0, j = 1 , length =0, newCol = w.col, newRow = w.row;
        Tile m;
        if (w.isVertical()) {
            while (i<w.tiles.length) {
                if (w.tiles[i] == null)
                    i++;
                leftUpCounter = 0;
                rightDownCounter = 0;
                j = 1;
                length = 0;
                while (t[w.getRow() + i][w.getCol() - j] != null && w.getCol() - j >=0) {
                    j++;
                    leftUpCounter++;
                }
                j = 1;
                while (t[w.getRow() + i][w.getCol() + j] != null && w.getCol() + j <=14) {
                    j++;
                    rightDownCounter++;
                }
                m = w.tiles[i];
                if(leftUpCounter >0 )
                    a.add(copyWordFromBoard(w.getRow() + i , w.getCol() - leftUpCounter , length, false, m, newCol ));
                if(rightDownCounter>0 && leftUpCounter == 0)
                    a.add(copyWordFromBoard(w.getRow() + i , w.getCol() , length, false, m, newCol));
                i++;
            }



            // call function to copy the word and the insert it to the vector
        }
        else

            while (i < w.tiles.length) {
                if (w.tiles[i] == null)
                    i++;
                leftUpCounter = 0;
                rightDownCounter = 0;
                j = 1;
                length = 0;

                while (w.getRow() -j >= 0 && t[w.getRow() - j][w.getCol() + i] != null) {
                    j++;
                    leftUpCounter++;
                }
                j = 1;
                while (w.getRow() +j <= 14 && t[w.getRow() + j][w.getCol() + i] != null) {
                    j++;
                    rightDownCounter++;
                }
                m = w.tiles[i];
                length = rightDownCounter + leftUpCounter + 1;
                if(leftUpCounter >0 )
                    a.add(copyWordFromBoard(w.getRow() - leftUpCounter  , w.getCol() + i , length, true, m, newRow));
                if(rightDownCounter>0 && leftUpCounter == 0)
                    a.add(copyWordFromBoard(w.getRow() , w.getCol() + i , length, true, m, newRow));
                i++;

            }
        return a;
    }

    Word copyWordFromBoard(int row, int col, int length, boolean vertical , Tile m,int newRowCol) {
        Tile[] word = new Tile[length];
        if (vertical == true) {
            for (int i =0; i < length ; i++) {
                if(t[row + i][col] == null) {
                    word[i] = m;
                    i++;
                }
                else word[i] = t[row + i][col];
            }
            Word newWord = new Word(word, row, col, vertical);
            return newWord;
        } else
            for (int i =0; i < length; i++) {
                if(t[row][col + i] == null){
                    word[i] = m;
                    i++;
                }
                word[i] = t[row][col + i];
            }
        Word newWord = new Word(word, row, col, vertical);
        return newWord;

    }

    public int getScore(Word w) {
        int sum = 0;
        int temp = 1;

        int currentRow = w.getRow(), currentCol = w.getCol();
        int tileScore;

        for (int i = 0; i < w.tiles.length; i++) {

            if (w.tiles[i] == null && t[currentRow][currentCol] != null) {
                tileScore = t[currentRow][currentCol].getScore();
            } else {
                tileScore = w.tiles[i].getScore();
            }

            if (score[currentRow][currentCol] == 5) { //first word.
                if (t[currentRow][currentCol] == null) {
                    temp *= 2;
                }
                sum += tileScore;
            } else if (score[currentRow][currentCol] == 4) {
                temp *= 3;
                sum += tileScore;
            } else if (score[currentRow][currentCol] == 3) {
                temp *= 2;
                sum += tileScore;
            } else if (score[currentRow][currentCol] == 2)
                sum += (3 * tileScore);
            else if (score[currentRow][currentCol] == 1)
                sum += (2 * tileScore);
            else
                sum += tileScore;

            if (w.vertical)
                currentRow++;
            else
                currentCol++;
        }

        sum = sum * temp;
        return sum;
    }
    int tryPlaceWord (Word w) {
        int sum = 0;
        if (dictionaryLegal(w)) {
            if (boardLegal(w)) {
                for(int i = 0; i<getWords(w).size(); i++){
                    sum += getScore(getWords(w).get(i));
                }
                for (int i = 0; i < w.tiles.length; i++) {
                    if (w.isVertical()) {
                        if (w.tiles[i] == null && t[w.row + i][w.col] != null)
                            i++;
                        t[w.getRow() + i][w.getCol()] = w.tiles[i];
                    }
                    else
                    if (w.tiles[i] == null && t[w.row][w.col + i] != null)
                        i++;
                    t[w.getRow()][w.getCol() + i] = w.tiles[i];


                }
            }
        }
        return sum;
}
}