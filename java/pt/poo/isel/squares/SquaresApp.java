package pt.poo.isel.squares;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import pt.poo.isel.squares.View.SquareView;
import pt.poo.isel.squares.model.Loader;
import pt.poo.isel.squares.model.Squares;
import pt.poo.isel.tile.TilePanel;

public class SquaresApp extends Activity {

    public static final String LEVELS_FILE = "Levels.txt";
    private TilePanel grid;
    private TextView moves;
    private TilePanel goals;
    private Squares model;
    private Squares.Listener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squares);

        grid = findViewById(R.id.grid);
        moves = findViewById(R.id.moves);
        goals = findViewById(R.id.goals);

        initBoard();

    }

    private void initBoard() {
        int height = grid.getHeightInTiles();
        int width = grid.getWidthInTiles();

        //Tile [][] t = model.grid //todo
        //grid.setAllTiles();
        int lvl = 0;
        loadLevel(++lvl);
        updateMoves();
        setGoals();
        for (int line = 0; line < height; line++) {
            for (int col = 0; col < width; col++) {
                grid.setTile(line,col,new SquareView(model.getSquare(line,col)));
            }
            
        }

    }

    private void setGoals() {
        int numGoals=model.getNumGoals();
        goals.setSize(numGoals,1);
        for (int i = 0; i < numGoals; i++) {
            goals.setTile(i,0,new SquareView(model.getGoal(i).square));

        }
    }

    private void updateMoves() {
        moves.setText(""+model.getTotalMoves());

    }


    private boolean loadLevel(int n) {
        Scanner in = null;
        AssetManager am = getAssets();
        try {
            in = new Scanner(am.open(LEVELS_FILE)); // Scanner to read the file
            model = new Loader(in).load(n);                     // Load level from scanner
            model.setListener(listener);                      // Set the listener of modifications
            return true;
        } catch (FileNotFoundException | InputMismatchException e) {
            System.out.println("Error loading file \"" + LEVELS_FILE + "\":\n" + e.getMessage());
            return false;
        } catch (Loader.LevelFormatException e) {
            System.out.println(e.getMessage() + " in file \"" + LEVELS_FILE + "\"");
            System.out.println(" " + e.getLineNumber() + ": " + e.getLine());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (in != null) in.close();   // Close the file
        }
    }

}
