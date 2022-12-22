package test;


import java.util.Arrays;
import java.util.Objects;

public class Word {
    Tile[] tiles;
    int row;
    int col;
    boolean vertical;


    public Word(Tile[] tiles, int row, int col, boolean vertical) {
        this.tiles = tiles;
        this.row = row;
        this.col = col;
        this.vertical = vertical;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isVertical() {
        return vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return getRow() == word.getRow() && getCol() == word.getCol() && isVertical() == word.isVertical() && Arrays.equals(getTiles(), word.getTiles());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getRow(), getCol(), isVertical());
        result = 31 * result + Arrays.hashCode(getTiles());
        return result;
}

}