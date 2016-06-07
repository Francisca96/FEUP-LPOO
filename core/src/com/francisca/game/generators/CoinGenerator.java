package com.francisca.game.generators;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.physics.box2d.World;
import com.francisca.game.PiggyCoins;
import com.francisca.game.sprites.CoinPattern;
import com.francisca.game.sprites.CoinsMatrix;
import com.francisca.game.sprites.elements.Coin;
import com.francisca.game.states.PlayState;

import java.util.ArrayList;

/**
 * Created by ZeCarlos on 06/06/2016.
 */
public class CoinGenerator {
    public static final float INTERVALS[] = {4, 7, 10, 13};
    public static final float ADJUSTMENT = 0.2f;
    //Time taken until the previous coin has passed the "coin generating space"
    public static final float GENERATION_INTERVAL = Math.abs((2*Coin.RADIUS)/(PiggyCoins.PPM*PlayState.WORLD_VELOCITY));

    private PlayState playState;
    private RandomXS128 randomGenerator;
    private ArrayList<CoinsMatrix> matrices;
    private static final int MATRIX_ARROW = 0;
    private static final int MATRIX_BLOCK = 1;
    private static final int MATRIX_LINE = 2;
    private static final int MATRIX_PLUS = 3;

    private float timer;
    private float timerColumn;
    private boolean generating;
    private int columnCounter;
    private int columns;
    private int yGenerated;
    private CoinsMatrix actualMatrix;


    public CoinGenerator(PlayState playState)
    {
        this.playState = playState;
        this.randomGenerator = new RandomXS128();
        this.matrices = new ArrayList<CoinsMatrix>();
        CoinsMatrix matrixArrow = new CoinsMatrix();
        CoinsMatrix matrixBlock = new CoinsMatrix();
        CoinsMatrix matrixLine = new CoinsMatrix();
        CoinsMatrix matrixPlus = new CoinsMatrix();
        matrixArrow.fillArrow();
        matrixBlock.fillBlock();
        matrixLine.fillLine();
        matrixPlus.fillPlus();
        matrices.add(matrixArrow);
        matrices.add(matrixBlock);
        matrices.add(matrixLine);
        matrices.add(matrixPlus);
        this.timer = 4;
        this.timerColumn = GENERATION_INTERVAL;
        this.generating = false;
        this.columnCounter = 0;
    }

    public Coin generateCoin(float x, float y)
    {
        Coin result = new Coin(playState.getWorld(), x, y);
        playState.getCoins().add(result);
        return result;
    }
/*
    public void generateGroupCoins(CoinPattern pattern, float x, float y)
    {
        if(pattern == CoinPattern.ARROW)
        {
            generateMatrix(matrixArrow, x, y);
        }
        else if(pattern == CoinPattern.BLOCK)
        {
            generateMatrix(matrixBlock, x, y);
        }
        else if(pattern == CoinPattern.LINE)
        {
            generateMatrix(matrixLine, x, y);
        }
        else if(pattern == CoinPattern.PLUS)
        {
            generateMatrix(matrixPlus, x, y);
        }
    }

    public void generateMatrix(CoinsMatrix matrix, float x, float y)
    {

        for(int i = 0; i < matrix.getMatrix().size(); i++)
        {
            ArrayList<Boolean> arrayList = matrix.getMatrix().get(i);
            for(int j = 0; j < arrayList.size(); j++)
            {
                if(arrayList.get(j)) {
                    generateCoin(x + i * Coin.RADIUS * 2 + ADJUSTMENT, y + j * Coin.RADIUS * 2 + ADJUSTMENT);
                }
            }
        }


    }
*/
    public void generateColumnMatrix(CoinsMatrix matrix, float x, float y, int numColumn)
    {
        ArrayList<Boolean> column = matrix.getMatrix().get(numColumn);

        for(int i = 0; i < column.size(); i++)
        {
            if(column.get(i))
            {
                generateCoin(x, y + i * Coin.RADIUS * 2 + ADJUSTMENT);
            }
        }

    }

    public CoinsMatrix generateRandomColumnMatrix()
    {
        int typeMatrix = randomGenerator.nextInt(matrices.size());
        actualMatrix = matrices.get(typeMatrix);
        columns = actualMatrix.getMatrix().size();
        yGenerated = randomGenerator.nextInt(Gdx.graphics.getHeight()-2*Coin.RADIUS) + Coin.RADIUS/2;

        return actualMatrix;
    }

    public void update(float dt)
    {
        if(generating)
        {
            timerColumn -= dt;
            if(timerColumn < 0)
            {
                generateColumnMatrix(actualMatrix, Gdx.graphics.getWidth(), yGenerated, columnCounter);
                columnCounter++;
                timerColumn = GENERATION_INTERVAL;
                if(columnCounter == columns)
                {
                    columnCounter = 0;
                    timer = INTERVALS[randomGenerator.nextInt(3)];
                    generating = false;
                }
            }
        }
        else
        {
            timer -= dt;
            if (timer < 0) {
                generating = true;
                columnCounter = 0;
                generateRandomColumnMatrix();
                return;
            }
        }
    }
}
