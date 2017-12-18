package pt.poo.isel.squares.model.square;


import pt.poo.isel.squares.model.Squares;
import pt.poo.isel.squares.model.square.Square;

public class BombSquare extends Square {
    protected char type;
    protected BombSquare(char type) {
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
        this.selected=true;
        checkAroundSquares(line,col);
        return true;
    }

    public void checkAroundSquares(int l, int c){
        int rowLimit = Squares.HEIGHT -1;
        int columnLimit = Squares.WIDTH -1;
        for(int line = Math.max(0, l-1); line <= Math.min(l+1, rowLimit); line++) { //l-1 except if l-1 is -1
            for(int col = Math.max(0, c-1); col <= Math.min(c+1, columnLimit); col++) {
                Square s = model.getSquare(line,col);
                if (s != null && s.isMovable()) {
                    s.selected = true;
                }
            }
        }
    }

    @Override
    public int getColor() {
        return NO_COLOR;
    }

    @Override
    public boolean isSpecial(){ return special=true; }
}
