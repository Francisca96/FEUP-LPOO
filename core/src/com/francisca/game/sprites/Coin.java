package com.francisca.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Francisca on 01/06/16.
 */
public class Coin {
    private static final int FLUCTUATION = 200;

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Texture coin;
    private Vector2 pos;
    private Random rand;

    public Coin(float x){
        coin = new Texture("coin.png");
        rand = new Random();

        pos = new Vector2(x, rand.nextInt(FLUCTUATION));
    }

    public Texture getCoin() {
        return coin;
    }

    public Vector2 getPos() {
        return pos;
    }
}
