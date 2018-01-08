package pt.poo.isel.squares.View;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import pt.poo.isel.squares.model.square.Square;

class ColorTile extends SquareView {
    private int color;
    private static final Paint paint = new Paint();

    public ColorTile(Square square) {
        super(square);
    }
    int getColor() {
        return color = square.getColor();
    }

    @Override
    public void draw(@NonNull Canvas canvas, int side) {
        if(square.getColor()==-1)
            System.out.println(color);
        color = COLORS[square.getColor()];
        paint.setColor(color);
        RectF r = new RectF(0, 0, side, side);
        canvas.drawRoundRect(r,32,32, paint);
    }
}
