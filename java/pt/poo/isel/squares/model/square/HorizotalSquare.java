package pt.poo.isel.squares.model.square;


import pt.poo.isel.squares.model.Squares;

public class HorizotalSquare extends Square{

    protected char type;

    protected HorizotalSquare(char type) {
        this.type = type;
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
    public boolean touch(int line, int col) {
        checkAroundSquares(line, col);
        return true;
    }

    public void checkAroundSquares(int line, int col){
        for (int c = 0; c < Squares.WIDTH; c++) {
            Square s = model.getSquare(line, c);
            if(s != null && s.isMovable())
                s.selected = true;
        }
    }

    @Override
    public int getColor() {
        return NO_COLOR;
    }

    @Override
    public boolean isSpecial(){ return special=true; }

}
