package pt.poo.isel.squares.View;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.LinearLayout;
import android.widget.TextView;

import pt.poo.isel.squares.R;
import pt.poo.isel.squares.model.Squares;
import pt.poo.isel.squares.model.square.Square;
import pt.poo.isel.tile.TileView;

public class GoalView extends LinearLayout {
    TileView goalSquare;
    TextView remainGoal;
    private LinearLayout goals;

    private static int numGoals;
    private Squares square;

    public GoalView(Context context) {
        super(context);
        goals=findViewById(R.id.goals);
    }


    public void setGoal(Squares.Goal goal) {
        numGoals = goal.number;
        goals.addView(remainGoal);

    }




    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        remainGoal.setText(""+numGoals);
    }

    /*goals.setSize(numGoals, 1);
            for (int i = 0; i < numGoals; i++) {
        goals.setTile(i, 0, SquareView.newInstance((model.getGoal(i).square)));*/

}
