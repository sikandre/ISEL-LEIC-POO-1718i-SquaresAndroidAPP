package pt.poo.isel.squares.View;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;

import pt.poo.isel.squares.R;
import pt.poo.isel.squares.model.square.Square;

import static pt.poo.isel.squares.SquaresApp.getCtx;

class VerticalTile extends SquareView {
    public VerticalTile(Square square) {
        super(square);
    }


    private Bitmap bitmap = BitmapFactory.decodeResource(getCtx().getResources(), R.drawable.up_down);

    @Override
    public void draw(@NonNull Canvas canvas, int side) {
        Rect rectangle = new Rect(0,0,side,side);
        canvas.drawBitmap(bitmap, null, rectangle, null);
    }


}
