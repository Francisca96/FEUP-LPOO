package com.francisca.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Francisca on 01/06/16.
 */

public class Obstacles {
    public static final int FLUCTUATION = 130;
    public static final int STICK_GAP = 100;
    public static final int LOWEST_PEAK = 120;

    private Texture topStick, bottomStick;
    private Vector2 posTopStick, posBottomStick;
    private Random rand;



    public Obstacles(float x) {
        topStick = new Texture("topStick.png");
        bottomStick = new Texture("bottomStick.png");
        rand = new Random();

        posTopStick = new Vector2(x, rand.nextInt(FLUCTUATION) + STICK_GAP + LOWEST_PEAK);
        posBottomStick = new Vector2(x, posTopStick.y - STICK_GAP - bottomStick.getHeight());
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
}
