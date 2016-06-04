package com.francisca.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.francisca.game.PiggyCoins;

import java.util.Random;

/**
 * Created by Francisca on 01/06/16.
 */

public class Stick {
    public static final int STICK_GAP = 100;

    public static final int MAXSIZE = 160;
    public static final int MINSIZE = 80;

    private Texture topStick, bottomStick;
    private Vector2 posTopStick, posBottomStick;
    private Random rand;

    private int width = 0;

    private int heightTopStick = 0, heightBottomStick = 0;

    public Stick(float x) {
        topStick = new Texture("topStick.png");
        bottomStick = new Texture("bottomStick.png");
        rand = new Random();

        heightTopStick = rand.nextInt(MAXSIZE-MINSIZE) + MINSIZE;
        heightBottomStick = rand.nextInt(MAXSIZE-MINSIZE) + MINSIZE;
        width = 20;

        posTopStick = new Vector2(x, PiggyCoins.HEIGHT/2 - heightTopStick);
        posBottomStick = new Vector2(x + STICK_GAP, 0);
    }

    public Vector2 getPosBottomStick() {
        return posBottomStick;
    }

    public Vector2 getPosTopStick() {
        return posTopStick;
    }

    public Texture getBottomStick() {
        return bottomStick;
    }

    public Texture getTopStick() {
        return topStick;
    }

    public int getWidth() {
        return width;
    }

    public int getHeightTopStick() {
        return heightTopStick;
    }

    public int getHeightBottomStick() {
        return heightBottomStick;
    }
}
