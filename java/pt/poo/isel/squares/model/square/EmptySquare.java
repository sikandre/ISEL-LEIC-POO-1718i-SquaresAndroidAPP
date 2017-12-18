package pt.poo.isel.squares.model.square;

public class EmptySquare extends Square {

    protected char type;
    private final boolean movable = false;

    protected EmptySquare(char type) {this.type = type;} //black square

    @Override
    public boolean touch(int line, int col) {return false;}

    @Override
    public boolean isMovable() {return movable;}

    @Override
    public boolean isSelected() {return selected;}

    @Override
    public void checkAroundSquares(int line, int col){}

    @Override
    public boolean isSpecial() {return special=true;}


}
