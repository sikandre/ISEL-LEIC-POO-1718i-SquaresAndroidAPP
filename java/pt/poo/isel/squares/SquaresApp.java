package pt.poo.isel.squares;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import pt.poo.isel.squares.View.GoalView;
import pt.poo.isel.squares.View.SquareView;
import pt.poo.isel.squares.model.Loader;
import pt.poo.isel.squares.model.Squares;
import pt.poo.isel.squares.model.square.Square;
import pt.poo.isel.tile.Animator;
import pt.poo.isel.tile.OnTileTouchListener;
import pt.poo.isel.tile.TilePanel;


public class SquaresApp extends Activity {

    public static final String LEVELS_FILE = "Levels.txt";
    private static int level=0;
    private TilePanel grid;
    private TextView moves;
    private LinearLayout goals;
    private Squares model;
    private Animator anim;

    private GoalView[] g;

    private static Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squares);

        ctx=this;

        grid = findViewById(R.id.grid);
        moves = findViewById(R.id.moves);
        goals = findViewById(R.id.goals);
        anim = grid.getAnimator();

        initBoard();
    }

    private class ModelListener implements Squares.Listener {
        @Override
        public void notifyDelete(Square s, int l, int c) {
            anim.shrinkTile(c,l,500,null);
        }
        @Override
        public void notifyMove(Square s, int lFrom, int c, int lTo) {
            anim.floatTile(c, lFrom, c, lTo, 700);
        }
        @Override
        public void notifyNew(Square s, int l, int c) {
            anim.expandTile(c, l, 1000, null, SquareView.newInstance(s));
        }
        @Override
        public void notifyPut(Square s, int l, int c) {
            grid.setTile(c, l, SquareView.newInstance(s));
        }
    }
    private ModelListener listener = new ModelListener();

    private Runnable action = new Runnable() {
        @Override
        public void run() {
            grid.postDelayed(action, 1500);
        }
    };

    private void play() {
        grid.setListener(new OnTileTouchListener() {
            @Override
            public boolean onClick(int xTile, int yTile) {
                if (model.touch(yTile, xTile)) {
                    updateMoves(model.getTotalMoves());
                    updateGoals();
                    if (model.isOver()) {
                        if(!winGame()){
                            gameOver("You lost");
                        }
                        else
                            continueGame();
                    }
                }
                return true;
            }


            @Override
            public boolean onDrag(int xFrom, int yFrom, int xTo, int yTo) {
                return false;
            }

            @Override
            public void onDragEnd(int x, int y) {
            }

            @Override
            public void onDragCancel() {
            }
        });
    }

    private void gameOver(String txt) {
        new AlertDialog.Builder(this,2)
                .setCancelable(false)
                .setTitle("Game Over")
                .setMessage(txt)
                .setPositiveButton("Ok", (DialogInterface,i)->{
                    finish();
                })
                .show();
    }

    private void continueGame() {
        //final EditText contGame = new EditText(this);
        new AlertDialog.Builder(this,2)
                .setTitle("Continue Game")
                .setCancelable(false)
                .setPositiveButton("Yes",(DialogInterface,i)->{
                    grid.postDelayed(action, 1500);
                    initBoard();
                })
                .setNegativeButton("No",(DialogInterface,i)->{
                    message("Bye");
                    finish();
                })
                .show();
    }


    private void updateGoals () {
        for (int i = 0; i < model.getNumGoals(); i++)
            g[i].setRemainGoal(model.getGoal(i));
    }

    private void initBoard () {
        int height = grid.getHeightInTiles();
        int width = grid.getWidthInTiles();

        if(!loadLevel(++level)) {
            gameOver("no more levels");
        }
        updateMoves(model.getTotalMoves());
        setGoals();

        //init visual board
        for (int line = 0; line < height; line++)
            for (int col = 0; col < width; col++)
                grid.setTile(col, line, SquareView.newInstance(model.getSquare(line, col)));
        play();
    }

    private void setGoals () {
        goals.removeAllViews();
        int numGoals = model.getNumGoals();
        g = new GoalView[numGoals];
        for (int i = 0; i < numGoals; i++) {
            g[i] = new GoalView(this, model.getGoal(i));
            goals.addView(g[i]);
        }
    }

    private void updateMoves ( int totalMoves){
        moves.setText("" + totalMoves);
    }

    private boolean loadLevel ( int n){
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

    private boolean winGame () {
        if (model.isWinner()) {
            message("Winner");
            return true;
        }
        return false;
    }
    private void message (String txt){
        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
    }

    public static Context getCtx () {
        return ctx;
    }
}