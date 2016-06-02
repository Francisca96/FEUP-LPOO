package com.francisca.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Francisca on 18/05/16.
 */
public class Pig {
    public static final int WIDTH = 35;
    public static final int HEIGHT = 35;
    private static final int GRAVITY = -5;
    private Vector2 position;
    private Vector2 velocity;

    private Texture pig;

    public Pig(int x, int y){
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        pig = new Texture("pig.png");
    }

    public Pig()
    {
        position = new Vector2(0, 0);
        velocity = new Vector2(0, 0);
        pig = new Texture("pig.png");
    }

    public void update(float dt){
        if(position.y > 0)
            velocity.add(0, GRAVITY);
        velocity.scl(dt);
        position.add(0, velocity.y);
        if(position.y < 0)
            position.y = 0;

        velocity.scl(1/dt);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getPig() {
        return pig;
    }

    public void jump(){
        velocity.y = 250;
    }
}
