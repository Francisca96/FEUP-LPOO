package com.francisca.game.sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.francisca.game.PiggyCoins;

/**
 * Created by ZeCarlos on 03/06/2016.
 */
public class Floor extends Element {

    public static final int WIDTH = 200;
    public static final int HEIGHT = 30;

    public Floor(World world)
    {
        this.world = world;
        position = new Vector2(0,0);
        defineFloor();
    }

    public void defineFloor()
    {
        //defines body
        BodyDef bdef = new BodyDef();
        bdef.position.set(position.x-PiggyCoins.WIDTH/2, position.y-PiggyCoins.HEIGHT/2); //Alinha o corpo com a sprite
        bdef.type = BodyDef.BodyType.StaticBody;

        //puts body into the world
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.WIDTH/2, this.HEIGHT/2);
        fdef.shape = shape;

        b2body.createFixture(fdef);
        b2body.setAwake(true);

        shape.dispose();
    }
}
