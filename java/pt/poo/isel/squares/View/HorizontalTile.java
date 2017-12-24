package pt.poo.isel.squares.View;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import pt.poo.isel.squares.R;
import pt.poo.isel.squares.SquaresApp;
import pt.poo.isel.squares.model.square.Square;
import pt.poo.isel.tile.TilePanel;
import pt.poo.isel.tile.TileView;

import static java.security.AccessController.*;


class HorizontalTile extends SquareView {
    public HorizontalTile(Square square) {
        super(square);
    }

    Paint p = new Paint();



    //Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.up_down);


    @Override
    public void draw(@NonNull Canvas canvas, int side) {
        p.setColor(Color.BLACK);
        //canvas.drawBitmap(bmp,0,0,p);
    }
}
