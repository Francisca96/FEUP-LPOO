package com.francisca.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.francisca.game.PiggyCoins;
import com.francisca.game.states.PlayState;

/**
 * Created by Francisca on 01/06/16.
 */
public class Obstacle extends Element{

    public static final int WIDTH = 10;
    public static final int HEIGHT = 200;
    public static final String IMAGE = "bottomStick.png";
    public static final float[] SIZE = {HEIGHT/4, HEIGHT/2, HEIGHT, HEIGHT*1.5f};

    private float size;

    /**
     * @param world
     * @return Obstacle in the middle of the world
     */
    public Obstacle(World world)
    {
        this.world = world;
        this.size = SIZE[2];
        this.position = new Vector2(Gdx.graphics.getWidth(), HEIGHT/2);
        this.image = new TextureRegion(new Texture(IMAGE));
        defineObstacle();
        this.animation = new Animation(new TextureRegion(this.image.getTexture()), 1, 0.5f);
    }

    public Obstacle(World world, float x, float y)
    {
        this.world = world;
        this.size = SIZE[2];
        this.position = new Vector2(x, y);
        this.image = new TextureRegion(new Texture(IMAGE));
        defineObstacle();
        this.animation = new Animation(new TextureRegion(this.image.getTexture()), 1, 0.5f);
    }

    public Obstacle(World world, float x, float y, int size)
    {
        this.world = world;
        this.size = SIZE[size];
        this.position = new Vector2(x, y);
        this.image = new TextureRegion(new Texture(IMAGE));
        defineObstacle();
        this.animation = new Animation(new TextureRegion(this.image.getTexture()), 1, 0.5f);
    }

    public void defineObstacle()
    {
        //Measures in METRES
        //defines body
        BodyDef bdef = new BodyDef();
        bdef.position.set((position.x- Gdx.graphics.getWidth()/2)/ PiggyCoins.PPM, (position.y-Gdx.graphics.getHeight()/2)/PiggyCoins.PPM);
        bdef.type = BodyDef.BodyType.KinematicBody;

        //puts body into the world
        b2body = world.createBody(bdef);

        //Define characteristics of body
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((this.WIDTH/2)/PiggyCoins.PPM, (size/2)/PiggyCoins.PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = PlayState.CATEGORY_OBSTACLE;
        fdef.filter.maskBits = PlayState.MASK_OBSTACLE;

        //Adds the characteristics to the body
        b2body.createFixture(fdef);
        b2body.setAwake(true);

        //Nullifies the force of gravity
        b2body.setGravityScale(0);
        applyVelocity();

        shape.dispose();
    }

    public boolean update(float dt)
    {
        animation.update(dt);
        float x = (b2body.getPosition().x)*PiggyCoins.PPM +Gdx.graphics.getWidth()/2-WIDTH/2;
        float y = (b2body.getPosition().y)*PiggyCoins.PPM +Gdx.graphics.getHeight()/2-size/2;
        setPosition(x,y);

        if(x < -Obstacle.WIDTH) //No need for the body to exist
        {
            return false;
        }

        return true;
    }

    public void applyVelocity()
    {
        this.b2body.setLinearVelocity(PlayState.OBSTACLE_VELOCITY, 0);
    }

    public float getSize()
    {
        return size;
    }

    public void delete()
    {
        for(Fixture fixture : b2body.getFixtureList()) {
            b2body.destroyFixture(fixture);
        }
        world.destroyBody(b2body);
    }
}
