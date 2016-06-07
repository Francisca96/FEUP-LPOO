package com.francisca.game.sprites;

import java.util.ArrayList;

/**
 * Created by Francisca on 04/06/16.
 */
public class CoinsMatrix {

    public static final int ARROW_WIDTH = 6;
    public static final int ARROW_HEIGHT = 5;
    public static final int BLOCK_WIDTH = 4;
    public static final int BLOCK_HEIGHT = 4;
    public static final int LINE_WIDTH = 5;
    public static final int PLUS_WIDTH = 3;


    private ArrayList<ArrayList<Boolean>> matrix;

    public CoinsMatrix(ArrayList<ArrayList<Boolean>> matrix)
    {
        this.matrix = matrix;
    }

    public CoinsMatrix(Boolean bool, int x, int y)
    {
        this.matrix = new ArrayList<ArrayList<Boolean>>();
       for(int i = 0; i < x; i++)
       {
           this.matrix.add(new ArrayList<Boolean>());
           for(int j = 0; j < y; j++)
           {
               this.matrix.get(i).add(bool);
           }
       }
    }

    public CoinsMatrix(CoinsMatrix matrix)
    {
        this.matrix = matrix.getMatrix();
    }

    public CoinsMatrix()
    {

    }

    public void fillArrow()
    {
        CoinsMatrix m = new CoinsMatrix( new Boolean(true), ARROW_WIDTH, ARROW_HEIGHT);
        this.matrix = m.getMatrix();

        setBoolean(new Boolean(false), 0, 0);
        setBoolean(new Boolean(false), 1, 0);
        setBoolean(new Boolean(false), 2, 0);

        setBoolean(new Boolean(false), 0, ARROW_HEIGHT-1);
        setBoolean(new Boolean(false), 1, ARROW_HEIGHT-1);
        setBoolean(new Boolean(false), 2, ARROW_HEIGHT-1);

        setBoolean(new Boolean(false), 4, 0);
        setBoolean(new Boolean(false), 5, 0);
        setBoolean(new Boolean(false), 5, 1);
        setBoolean(new Boolean(false), 4, ARROW_HEIGHT-1);
        setBoolean(new Boolean(false), 5, ARROW_HEIGHT-1);
        setBoolean(new Boolean(false), 5, ARROW_HEIGHT-2);

    }

    public void fillBlock()
    {
        CoinsMatrix m = new CoinsMatrix( new Boolean(true), BLOCK_WIDTH, BLOCK_HEIGHT);
        this.matrix = m.getMatrix();
    }

    public void fillLine()
    {
        CoinsMatrix m = new CoinsMatrix(new Boolean(true), LINE_WIDTH, 1);
        this.matrix = m.getMatrix();
    }

    public void fillPlus()
    {
        CoinsMatrix m = new CoinsMatrix(new Boolean(false), PLUS_WIDTH, PLUS_WIDTH);
        this.matrix = m.getMatrix();

        setBoolean(new Boolean(true), 1, 0);
        setBoolean(new Boolean(true), 1, 1);
        setBoolean(new Boolean(true), 1, 2);
        setBoolean(new Boolean(true), 0, 1);
        setBoolean(new Boolean(true), 2, 1);

    }

    public void setBoolean(Boolean bool, int x, int y)
    {
        matrix.get(x).set(y, bool);
    }

    public Boolean getBoolean(int x, int y)
    {
        return matrix.get(x).get(y);
    }

    public ArrayList<ArrayList<Boolean>> getMatrix() {
        return matrix;
    }

}
