package pt.poo.isel.squares.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import pt.poo.isel.squares.R;
import pt.poo.isel.squares.model.square.Square;
import static pt.poo.isel.squares.SquaresApp.getCtx;


class BombTile extends SquareView {
    Bitmap bitmap;
    public BombTile(Square square) {
        super(square);
        bitmap = BitmapFactory.decodeResource(getCtx().getResources(), R.drawable.bomb);
    }

    @Override
    public void draw(@NonNull Canvas canvas, int side) {
        Rect rectangle = new Rect(0,0,side,side);
        canvas.drawBitmap(bitmap, null, rectangle, null);
    }

}
