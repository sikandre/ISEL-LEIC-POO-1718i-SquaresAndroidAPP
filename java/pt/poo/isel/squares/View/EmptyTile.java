package pt.poo.isel.squares.View;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import pt.poo.isel.squares.model.square.Square;


class EmptyTile extends SquareView {
    public EmptyTile(Square square) {
        super(square);
    }

    Paint paint = new Paint(Color.BLACK);

    @Override
    public void draw(@NonNull Canvas canvas, int side) {
        RectF r = new RectF(0, 0, side, side);
        canvas.drawRoundRect(r,32,32, paint);
    }
}
