package com.francisca.game.sprites.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.francisca.game.PiggyCoins;
import com.francisca.game.sprites.Animation;
import com.francisca.game.states.PlayState;

/**
 * Created by Francisca on 01/06/16.
 */
public class Coin extends Element {
    private static final int FLUCTUATION = 200;

    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int RADIUS = 10;
    public static final String IMAGE = "coin.png";

    private boolean markedForDelete;

    public Coin(World world, float x, float y){

        this.world = world;
        this.position = new Vector2(x, y);
        this.image = new TextureRegion(new Texture(IMAGE));
        defineCoin();
        this.animation = new Animation(new TextureRegion(this.image.getTexture()), 1, 0.5f);
        this.markedForDelete = false;

        //Aligns the sprite
        float xalign = (b2body.getPosition().x)*PiggyCoins.PPM +Gdx.graphics.getWidth()/2-WIDTH/2;
        float yalign = (b2body.getPosition().y)*PiggyCoins.PPM +Gdx.graphics.getHeight()/2-HEIGHT/2;
        setPosition(xalign,yalign);

    }

    public void defineCoin()
    {
        //Measures in METRES

        //Defines body
        BodyDef bdef = new BodyDef();
        bdef.position.set((position.x- Gdx.graphics.getWidth()/2)/ PiggyCoins.PPM, (position.y-Gdx.graphics.getHeight()/2)/PiggyCoins.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        //puts body into world
        b2body = world.createBody(bdef);

        //Defines characteristics of body
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(RADIUS/PiggyCoins.PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = PlayState.CATEGORY_COLLECTIBLE;
        fdef.filter.maskBits = PlayState.MASK_COLLECTIBLE;
        fdef.isSensor = true;

        //Adds the characteristics to the body
        b2body.createFixture(fdef).setUserData(this);
        b2body.setAwake(true);

        //Nullifies the force of gravity
        b2body.setGravityScale(0);
        applyVelocity();


        shape.dispose();
    }

    public boolean update(float dt)
    {
        animation.update(dt);
        /*
        float x = (b2body.getPosition().x)*PiggyCoins.PPM +Gdx.graphics.getWidth()/2-WIDTH/2;
        float y = (b2body.getPosition().y)*PiggyCoins.PPM +Gdx.graphics.getHeight()/2-HEIGHT/2;
        */
        float x = (b2body.getPosition().x)*PiggyCoins.PPM +Gdx.graphics.getWidth()/2-WIDTH/2;
        float y = (b2body.getPosition().y)*PiggyCoins.PPM +Gdx.graphics.getHeight()/2-HEIGHT/2;
        setPosition(x,y);

        if(x < -Obstacle.WIDTH) //No need for the body to exist
        {
            return false;
        }

        return true;
    }

    public void onCreatingCollision()
    {
       markedForDelete = true;
    }

    public boolean isMarkedForDelete() {
        return markedForDelete;
    }
}
