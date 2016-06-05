package com.francisca.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.francisca.game.PiggyCoins;
import com.francisca.game.states.PlayState;

/**
 * Created by ZeCarlos on 03/06/2016.
 */
public class Floor extends Element {

    //Measures in pixels
    public static final int WIDTH = 400;
    public static final int HEIGHT = 3;

    public Floor(World world)
    {
        this.world = world;
        position = new Vector2(WIDTH/2,-HEIGHT);
        defineFloor();
    }

    public Floor(World world, float x, float y)
    {
        //Measures in pixels
        this.world = world;
        position = new Vector2(x,y);
        defineFloor();
    }

    public void defineFloor()
    {
        //Measures in METRES
        //defines body
        BodyDef bdef = new BodyDef();

        //bdef.position.set((position.x-PiggyCoins.WIDTH/2)/PiggyCoins.PPM, (position.y-PiggyCoins.HEIGHT/2)/PiggyCoins.PPM); //Alinha o corpo com a sprite
        bdef.position.set((position.x- Gdx.graphics.getWidth()/2)/PiggyCoins.PPM, (position.y-Gdx.graphics.getHeight()/2)/PiggyCoins.PPM); //Alinha o corpo com a sprite

        bdef.type = BodyDef.BodyType.StaticBody;

        //puts body into the world
        b2body = world.createBody(bdef);

        //Define characteristics of body
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((this.WIDTH/2)/PiggyCoins.PPM, (this.HEIGHT/2)/PiggyCoins.PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = PlayState.CATEGORY_LIMIT;
        fdef.filter.maskBits = PlayState.MASK_LIMIT;


        b2body.createFixture(fdef);
        b2body.setAwake(true);

        shape.dispose();
    }
}
