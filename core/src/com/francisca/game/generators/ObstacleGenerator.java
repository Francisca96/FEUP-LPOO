package com.francisca.game.generators;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.physics.box2d.World;
import com.francisca.game.sprites.Obstacle;

/**
 * Created by ZeCarlos on 05/06/2016.
 */
public class ObstacleGenerator {
    public static final float INTERVALS[] = {1, 2, 3, 4};

    private RandomXS128 randomGenerator;
    private World world;
    private float timer;

    public ObstacleGenerator(World world)
    {
        this.randomGenerator = new RandomXS128();
        this.world = world;
        this.timer = 4;
    }

    public Obstacle generateObstacle()
    {
        //Generate position
        float x, y;
        x = Gdx.graphics.getWidth() + Obstacle.WIDTH;
        y = randomGenerator.nextInt(Gdx.graphics.getHeight());

        //Generate size of obstacle
        int size = randomGenerator.nextInt(Obstacle.SIZE.length);

        Obstacle result = new Obstacle(world, x, y, size);

        return result;
    }

    public Obstacle update(float dt)
    {
        timer -= dt;
        Obstacle result;
        if(timer < 0) //timer's up
        {
            result = generateObstacle();
            generateNextTimer();
        }
        else
        {
            result = null;
        }

        return result;
    }

    public void generateNextTimer()
    {
        timer = INTERVALS[randomGenerator.nextInt(INTERVALS.length)];
    }
}
