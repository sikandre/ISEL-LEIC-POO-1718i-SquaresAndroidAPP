package pt.poo.isel.squares.View;

import android.graphics.Canvas;


import pt.poo.isel.squares.model.square.ColorSquare;
import pt.poo.isel.squares.model.square.EmptySquare;
import pt.poo.isel.squares.model.square.HorizotalSquare;
import pt.poo.isel.squares.model.square.Square;
import pt.poo.isel.tile.Tile;

import android.graphics.Color;
import android.support.annotation.NonNull;


public abstract class SquareView implements Tile {
    protected final Square square; // Reference to the model square

    static final int[] COLORS = {
            Color.RED, Color.GREEN, Color.BLUE,
            Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.alpha(1)
    };


    public SquareView(Square square) {
        this.square = square;
    }
    public static SquareView newInstance(Square square) {
        //creation of all types of squares
        /*
        if (square instanceof VerticalSquare) return new LineTile(square);
        if (square instanceof BombSquare) return new BombTile(square);
        if (square instanceof ColorSquare) return new ColorTile(square);

        if (square instanceof jokerSquare) return new jokerTile(square);*/
        if (square instanceof ColorSquare) return new ColorTile(square);
        //if (square instanceof HorizotalSquare) return new HorizontalTile(square);
        if (square instanceof EmptySquare) return new EmptyTile(square);

        return null;

    }


    @Override
    public void draw(@NonNull Canvas canvas, int side) {
        /*color = COLORS[square.getColor()];
        paint.setColor(color);
        RectF r = new RectF(0, 0, side, side);
        canvas.drawRoundRect(r,32,32, paint);*/
    }

    @Override
    public boolean setSelect(boolean selected) {
        return false;
    }
}
