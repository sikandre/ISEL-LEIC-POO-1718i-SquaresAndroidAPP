package pt.poo.isel.squares.View;

import android.graphics.Canvas;


import pt.poo.isel.squares.model.square.BombSquare;
import pt.poo.isel.squares.model.square.ColorSquare;
import pt.poo.isel.squares.model.square.EmptySquare;
import pt.poo.isel.squares.model.square.HorizotalSquare;
import pt.poo.isel.squares.model.square.Square;
import pt.poo.isel.squares.model.square.VerticalSquare;
import pt.poo.isel.squares.model.square.jokerSquare;
import pt.poo.isel.tile.Tile;

import android.graphics.Color;
import android.support.annotation.NonNull;


public abstract class SquareView implements Tile {
    protected final Square square; // Reference to the model square

    static final int[] COLORS = {
            Color.RED, Color.GREEN, Color.BLUE,
            Color.YELLOW, Color.MAGENTA, Color.CYAN
    };

    SquareView(Square square) {
        this.square = square;
    }


    public static SquareView newInstance(Square square) {

        if (square instanceof jokerSquare) return new jokerTile(square);
        if (square instanceof ColorSquare) return new ColorTile(square);
        if (square instanceof HorizotalSquare) return new HorizontalTile(square);
        if (square instanceof EmptySquare) return new EmptyTile(square);
        if (square instanceof ColorSquare) return new ColorTile(square);
        if (square instanceof BombSquare) return new BombTile(square);
        if (square instanceof VerticalSquare) return new VerticalTile(square);
        return null;
    }

    int getColor() {
        int color = square.getColor();
        return color==Square.NO_COLOR ? Color.BLACK : COLORS[ color ];
    }

    @Override
    public abstract void draw(@NonNull Canvas canvas, int side);

    @Override
    public boolean setSelect(boolean selected) {
        return false;
    }
}
