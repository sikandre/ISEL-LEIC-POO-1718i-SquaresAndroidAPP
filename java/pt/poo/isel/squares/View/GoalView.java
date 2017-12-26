package pt.poo.isel.squares.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.widget.LinearLayout;
import android.widget.TextView;

import pt.poo.isel.squares.R;
import pt.poo.isel.squares.model.Squares;
import pt.poo.isel.squares.model.square.Square;
import pt.poo.isel.tile.Tile;
import pt.poo.isel.tile.TileView;

public class GoalView extends LinearLayout implements Tile {
    TileView square;
    TextView remainGoal;
    Paint paint= new Paint();
    Square s;

    public GoalView(Context context, Squares.Goal g) {
        super(context);
        s=g.square;
        remainGoal=new TextView(getContext());
        TileView square = new TileView(getContext());
        square.setTile(SquareView.newInstance(s));
        //square=SquareView.newInstance(g.square);
        remainGoal.setTextSize(20);
        remainGoal.setBackgroundColor(Color.BLUE);
        remainGoal.setText(" "+g.number+" ");
        addView(remainGoal);
        addView(square);
        //SquareView.newInstance(g.square);
    }

    @Override
    public void draw(@NonNull Canvas canvas, int side) {
        int color = s.getColor();
        paint.setColor(color);
        RectF r = new RectF(0, 0, side, side);
        canvas.drawRoundRect(r,32,32, paint);
    }

    @Override
    public boolean setSelect(boolean selected) {
        return false;
    }

/*
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int color = square.getColor();
        paint.setColor(color);
        //RectF r = new RectF(0, 0, , side);
        //canvas.drawRoundRect(r,32,32, paint);
    }*/
}
