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

public class GoalView extends LinearLayout {
    SquareView square;
    TextView remainGoal;
    Paint paint= new Paint();

    public GoalView(Context context, Squares.Goal g) {
        super(context);
        remainGoal=new TextView(getContext());
        //square=SquareView.newInstance(g.square);
        remainGoal.setTextSize(20);
        remainGoal.setBackgroundColor(Color.BLUE);
        remainGoal.setText(" "+g.number+" ");
        addView(remainGoal);
        //SquareView.newInstance(g.square);
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
