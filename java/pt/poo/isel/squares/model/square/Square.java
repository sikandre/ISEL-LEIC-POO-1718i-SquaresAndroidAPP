package pt.poo.isel.squares.model.square;


import pt.poo.isel.squares.model.Squares;

public abstract class Square {
    public static final int NO_COLOR = -1;
    public static Squares model;  // The model of game.
    protected boolean selected;
    protected boolean special;
    public static int count = 0;

    public abstract boolean touch(int line, int col);

    public int getColor(){return NO_COLOR;}

    public static Square newInstance(char type) { //depending on type creates new Square
        if(type=='H') return new HorizotalSquare(type);
        if(type=='V') return new VerticalSquare(type);
        if(type=='B') return new BombSquare(type);
        if(type=='X') return new EmptySquare(type);
        if(type=='J') return new jokerSquare(type);
        if(type=='.'||type>='1'&&type <= Squares.MAX_COLORS+'0')
            return new ColorSquare(type);

        return null; //if encounters a different type on file returns null
    }
    public Square getSquare() {return this;}

    //abstract methods to be implemented according to square
    public abstract boolean isMovable();

    public abstract boolean isSelected();

    public abstract void checkAroundSquares(int line, int col);

    public abstract boolean isSpecial();
}
