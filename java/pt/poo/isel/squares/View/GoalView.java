package pt.poo.isel.squares.View;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import pt.poo.isel.squares.R;
import pt.poo.isel.squares.SquaresApp;
import pt.poo.isel.squares.model.Squares;
import pt.poo.isel.squares.model.square.Square;
import pt.poo.isel.tile.Tile;
import pt.poo.isel.tile.TileView;

public class GoalView extends LinearLayout implements Tile {
    public static final int HOLO_BLUE = 0xff33b5e5;
    TileView square;
    TextView remainGoal;
    private Square s;

    public GoalView(Context context, Squares.Goal g) {
        super(context);
/*
        int ori SquaresApp.getGoalOrientation();
*/


        int ori=SquaresApp.getCtx().getResources().getConfiguration().orientation;

        if(ori==VERTICAL)
            setOrientation(VERTICAL);

        s = g.square;
        remainGoal=new TextView(getContext());
        square = new TileView(getContext());
        square.setTile(SquareView.newInstance(s));
        remainGoal.setTextSize(20);
        remainGoal.setBackgroundColor(HOLO_BLUE);
        remainGoal.setText(" "+g.number+" ");
        remainGoal.setGravity(Gravity.TOP|Gravity.CENTER);

        LayoutParams params = new LayoutParams(93,93);
        params.setMargins(0,0,16,0);

        addView(square,params);
        addView(remainGoal,params);
    }

    public void setRemainGoal(Squares.Goal goal) {
        remainGoal.setText(""+goal.number);
    }

    @Override
    public void draw(@NonNull Canvas canvas, int side) {

    }

    @Override
    public boolean setSelect(boolean selected) {
        return false;
    }
}
