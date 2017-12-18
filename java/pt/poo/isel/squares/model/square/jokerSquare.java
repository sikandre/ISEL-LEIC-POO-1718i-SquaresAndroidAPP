package pt.poo.isel.squares.model.square;


import pt.poo.isel.squares.model.Squares;

public class jokerSquare extends Square {
    protected char type;

    public jokerSquare(char type) {
        this.type = type;
    }


    @Override
    public boolean touch(int line, int col) {
        return false;
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void checkAroundSquares(int l, int c) {
    }

    @Override
    public int getColor() {
        for (int l = 0; l < Squares.HEIGHT; l++) {
            for (int c = 0; c < Squares.WIDTH; c++) {
                Square s = Square.model.getSquare(l,c);
                if(s.selected && s!=this)
                    return s.getColor();
            }
        }
        return NO_COLOR;
        }

    @Override
    public boolean isSpecial() {
        return special=true;
    }
}
