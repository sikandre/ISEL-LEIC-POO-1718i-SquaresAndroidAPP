package pt.poo.isel.squares.model;


import java.util.NoSuchElementException;
import java.util.Scanner;

import pt.poo.isel.squares.model.square.Square;

/**
 * Loads a game level from the file read with the scanner indicated in the constructor.<br/>
 * The file contains several levels.<br/>
 * Each level has a number from 1 to N.<br/><br/>
 * The first line of description for a level must conform to the format:<br/>
 * <code>#NNN MOVES OBJECTIVES</code><br/>
 * Where: <code>NNN</code> is the level number.<br/>
 * <code>MOVES</code> is the number of moves.<br/>
 * <code>OBJECTIVES</code> is the objective list, where each element is in format: <CODE>S-NNN</CODE><br/>
 * The following <code>10</code> lines describe the cells of the level.<br/><br/>
 *
 */
public class Loader {
    private final Scanner in;   // Scanner used to read the file
    private int lineNumber;     // Current line number
    private String line;        // Text of current line

    private Squares model;      // The loaded model
    private int moves;          // Number of moves

    /**
     * Build the loader to read it from the file through the scanner
     * @param in The scanner to use
     */
    public Loader(Scanner in) {
        this.in = in;
    }

    /**
     * Reads the level identified by the number.<br/>
     * This is the only public method of the class.<br/>
     * @param level The level number
     * @return The model for the loaded level
     * @throws LevelFormatException If an error is found in the file
     */
    public Squares load(int level) throws LevelFormatException {
        findHeader(level);                  // Find the header line
        model = new Squares(moves);         // Build the model
        loadGoals();                        // Load goals information
        loadGrid();                         // Load cells information
        return model;
    }

    private void loadGoals() throws LevelFormatException {
        try {
            int idx = 0;
            while ((idx = line.indexOf('-', idx)) > 0) {
                char type = line.charAt(idx - 1);
                Square s = createSquare(type);
                int from = idx + 1;
                idx = line.indexOf(' ', idx+1);
                if (idx < 0) idx = line.length();
                int num = Integer.parseInt(line.substring(from, idx));
                if (!model.addGoal( new Squares.Goal(s,num) ))
                    error("Invalid Goal ("+type+"-"+num+")");
            }
            if (model.getNumGoals()==0) error("Missing Goals");
        } catch (NumberFormatException e) {
            error("Invalid goal number");
        }
    }

    /**
     * Read the square grid and instantiate each square according to its description.<br/>
     * The square descriptions of each row are separated by one or more separators.
     * @throws LevelFormatException If an error is found in square descriptions
     */
    private void loadGrid() throws LevelFormatException {
        for(int l=0; l<Squares.HEIGHT ; ++lineNumber,++l) {
            line = in.nextLine();                       // Read a line of cells
            String[] cells = line.split("\\s+");        // Split by separators
            if (cells.length!=Squares.WIDTH)            // Verify number of cells in line
                error("Wrong number of cells in line");
            int c = 0;
            for(String word : cells) {                  // For each description
                char type = word.charAt(0);
                Square cell = createSquare(type);
                model.putSquare(cell, l,c++);              // Add square to the model
            }
        }
    }

    private Square createSquare(char type) throws LevelFormatException {
        Square cell = Square.newInstance(type);     // Create a square identified by first char
        if (cell==null)
            error("Unknown square type ("+type+")");
        return cell;
    }

    /**
     * Find the header line for the level<br/>
     * Stores the dimensions of the level in <code>height</code> and <code>width</code> fields.
     * @param level The level number
     * @throws LevelFormatException If an errors is found in the file or level not found.
     */
    private void findHeader(int level) throws LevelFormatException {
        try {
            int idx;
            for (lineNumber = 1; ; ++lineNumber) {
                line = in.nextLine();
                if (line.length() == 0 || line.charAt(0) != '#') continue;
                if ((idx = line.indexOf(' ')) <= 1) error("Invalid header line");
                if (Integer.parseInt(line.substring(1, idx)) == level) break;
            }
            int idxGoals = line.indexOf(' ',idx+1);
            if (idxGoals<=0) error("Missing goals of level "+level);
            moves = Integer.parseInt(line.substring(idx+1,idxGoals));
            line = line.substring(idxGoals+1).trim();
        } catch (NumberFormatException e) {
            error("Invalid number");
        } catch (NoSuchElementException e) {
            error("Level " + level + " not found");
        }
    }

    /**
     * Helper method to launch a LevelFormatException in internal methods.
     * @param msg The exception message
     * @throws LevelFormatException
     */
    private void error(String msg) throws LevelFormatException {
        throw new LevelFormatException(msg);
    }

    /**
     * Launched when a level loading error is detected.
     * The message describes the type of error.
     * Has the line number and the line where the error was detected.
     */
    public class LevelFormatException extends Exception {
        public LevelFormatException(String msg) {
            super(msg);
        }
        public int getLineNumber() { return lineNumber; }
        public String getLine() { return line; }
    }
}
