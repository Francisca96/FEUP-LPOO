package com.francisca.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Francisca on 18/05/16.
 */
public class Pig {
    public static final int WIDTH = 35;
    public static final int HEIGHT = 35;
    private static final int GRAVITY = -5;
    private Vector3 position;
    private Vector3 velocity;

    private Texture pig;

    public Pig(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        pig = new Texture("pig.png");
    }

    public void update(float dt){
        if(position.y > 0)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(0, velocity.y, 0);
        if(position.y < 0)
            position.y = 0;

        velocity.scl(1/dt);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getPig() {
        return pig;
    }

    public void jump(){
        velocity.y = 250;
    }
}
