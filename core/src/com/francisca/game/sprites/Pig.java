package com.francisca.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Francisca on 18/05/16.
 */
public class Pig {
    public static final int WIDTH = 35;
    public static final int HEIGHT = 35;
    private static final int GRAVITY = -15;


    private Vector3 position;
    private Vector3 velocity;
    private Animation pigAnimation;
    private int numLifes;

    private Texture pig;

    public Pig(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        numLifes = 3;
        //pig = new Texture("pig.png");
        pig = new Texture("pigSprite.png");
        pigAnimation = new Animation(new TextureRegion(pig), 8, 0.5f);
    }

    public Pig()
    {
        position = new Vector3(0, 0, 0);
        velocity = new Vector3(0, 0, 0);
        numLifes = 3;
        pig = new Texture("pigSprite.png");
        pigAnimation = new Animation(new TextureRegion(pig), 8, 0.5f);
    }

    public void update(float dt){
        pigAnimation.update(dt);
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

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void setPosition(int x, int y)
    {
        this.position.x = x;
        this.position.y = y;
    }

    public TextureRegion getPig() {
        return pigAnimation.getFrame();
    }

    public void jump(){
        velocity.y = 250;
    }

    public void dispose(){ pig.dispose(); }
}
