package test;

import java.util.Objects;
import java.util.Random;

public class Tile {

    public final char letter ;

    public final int score;

    private Tile(char letter , int score)
    {
        this.letter = letter;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public char getLetter() {
        return letter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && score == tile.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }

    public static class Bag{
        private static Bag b = null;
        Tile[] tile;
        int totalTiles = 98;
        int[] arrCountLetter = new int[]{9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
        int[] arrOriginalAmount = new int[]{9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
        char[] arrL = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        int[] arrValue = new int[]{1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10}; //ערך האות

        private Bag() {
            tile = new Tile[26];
            for(int i=0;i<26;i++)
            {
                tile[i]=new Tile(arrL[i],arrValue[i]);
            }
        }

        public Tile getRand() {

            int rand = (int)(Math.random()*26);
            while (arrCountLetter[rand] == 0) {
                rand = (int)(Math.random()*26);
            }

            arrCountLetter[rand]--;
            totalTiles--;
            return tile[rand];

        }


        public Tile getTile(char a){
            for(int i = 0; i<26 ; i++){
                if(tile[i].getLetter() == a){
                    if(arrCountLetter[a-'A']!= 0)
                        return tile[i];
                }
            }
            return null;
        }

        public void put(Tile t){
            int index=t.getLetter()-'A';
            if(arrCountLetter[index]<arrOriginalAmount[index])
            {
                arrCountLetter[index]++;
                totalTiles++;
            }

        }

        public int size(){
            return totalTiles;
        }

        public int[] getQuantities(){
            int [] copy = new int[26];
            System.arraycopy(arrCountLetter, 0 , copy , 0,arrCountLetter.length );
            return copy;
        }

        public static Bag getBag() {
            if (b == null) {
                b = new Bag();
            }
            return b;
 }
}

}