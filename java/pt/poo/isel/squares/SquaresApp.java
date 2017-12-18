package pt.poo.isel.squares;

import android.app.Activity;
import android.os.Bundle;

import pt.poo.isel.squares.model.Squares;
import pt.poo.isel.tile.TilePanel;

public class SquaresApp extends Activity {

    private TilePanel grid;
    private Squares model = new Squares(5);//todo moves???

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squares);

        grid = findViewById(R.id.grid);


    }

}
