package pt.poo.isel.squares.View;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import pt.poo.isel.squares.model.Squares;
import pt.poo.isel.squares.model.square.Square;
import pt.poo.isel.tile.TileView;

public class GoalView extends LinearLayout {
    public static final int HOLO_BLUE = 0xff33b5e5;
    TileView square;
    TextView remainGoal;
    Square s;
    LinearLayout ln;

    public GoalView(Context context, Squares.Goal g) {
        super(context);
        ln = new LinearLayout(getContext());
        setOrientation(VERTICAL);
        //setDividerPadding(300);


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
        //params=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        addView(remainGoal,params);
    }

    public void setRemainGoal(Squares.Goal goal) {
        remainGoal.setText(""+goal.number);
    }
}
