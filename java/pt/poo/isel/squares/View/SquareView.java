package pt.poo.isel.squares.View;

import android.graphics.Canvas;

import pt.poo.isel.squares.model.square.Square;
import pt.poo.isel.tile.Tile;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;


public class SquareView implements Tile {
    protected final Square square; // Reference to the model square
    private static final Paint paint = new Paint();
    private int color;


    static final int[] COLORS = {
            Color.RED, Color.GREEN, Color.BLUE,
            Color.YELLOW, Color.MAGENTA, Color.WHITE
    };

    public SquareView(Square square) {
        this.square = square;
        color = COLORS[square.getColor()];
    }


    @Override
    public void draw(@NonNull Canvas canvas, int side) {
        paint.setColor(color);
        canvas.drawRect(0,0,side,side,paint);
        //canvas.drawCircle(side/2,side/2,side*3/8,paint);
    }

    @Override
    public boolean setSelect(boolean selected) {
        return false;
    }
}
