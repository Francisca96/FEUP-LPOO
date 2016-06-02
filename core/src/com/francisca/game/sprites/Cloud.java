package com.francisca.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by Francisca on 02/06/16.
 */
public class Cloud {
    private static final int FLUCTUATION = 200;

    public static final int MAXSIZE = 90;
    public static final int MINSIZE = 50;

    public static final Texture cloud1 = new Texture("cloud1.png");
    public static final  Texture cloud2 = new Texture("cloud2.png");
    public static final  Texture cloud3 = new Texture("cloud3.png");
    private Array <Texture> clouds;
    private Vector2 pos;
    private Random rand;

    private int width = 0;
    private int height = 0;

    private Texture currTexture;

    public Cloud(float x){

        clouds = new Array<Texture>();
        clouds.add(cloud1);
        clouds.add(cloud2);
        clouds.add(cloud3);

        rand = new Random();

        pos = new Vector2(x, rand.nextInt(FLUCTUATION));

        width = rand.nextInt(MAXSIZE-MINSIZE) + MINSIZE;
        height = width;

        currTexture = clouds.get(rand.nextInt(3));
    }

    public Texture getCloud() {
     return currTexture;
    }

    public Vector2 getPos() {
        return pos;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}