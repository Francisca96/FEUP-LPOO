package com.francisca.game.sprites.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.francisca.game.PiggyCoins;
import com.francisca.game.sprites.Animation;
import com.francisca.game.states.PlayState;

/**
 * Created by ZeCarlos on 03/06/2016.
 */
public abstract class Element extends Sprite{

    protected Vector2 position; //Measures in pixels
    protected Vector3 velocity;
    public World world;
    public Body b2body; //Measure in METRES
    protected TextureRegion image;
    protected Animation animation;

    public Element()
    {

    }

    public Element(World world)
    {
        this.world = world;
        defineElement();
        position = new Vector2(0, 0);
        velocity = new Vector3(0, 0, 0);
        image = new TextureRegion();
    }

    public Element(World world, int x, int y)
    {
        this.world = world;
        position = new Vector2(x, y);
        defineElement(x, y);
        velocity = new Vector3(0,0,0);
        image = new TextureRegion();
    }

    public void defineElement()
    {
        //defines body
        BodyDef bdef = new BodyDef();
        bdef.position.set(0, 0);
        bdef.type = BodyDef.BodyType.StaticBody;

        //puts body into the world
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/ PiggyCoins.PPM);
        fdef.shape = shape;

        b2body.createFixture(fdef);
    }

    public void defineElement(int x, int y)
    {
        //defines body
        BodyDef bdef = new BodyDef();
        bdef.position.set(x/PiggyCoins.PPM, y/PiggyCoins.PPM);
        bdef.type = BodyDef.BodyType.StaticBody;

        //puts body into the world
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/PiggyCoins.PPM);
        fdef.shape = shape;

        b2body.createFixture(fdef).setUserData(this);
    }

    public abstract boolean update(float dt);

    public void applyVelocity()
    {
        this.b2body.setLinearVelocity(PlayState.WORLD_VELOCITY, 0);
    }

    public void delete()
    {
        for(Fixture fixture : b2body.getFixtureList()) {
            b2body.destroyFixture(fixture);
        }
        world.destroyBody(b2body);
    }

    public void setBodyPosition(float x, float y)
    {
        Vector2 v = new Vector2(x/PiggyCoins.PPM, y/PiggyCoins.PPM);
        b2body.setTransform(v, 0);
        return;
    }

    public void setPosition(float x, float y)
    {
        position.set(x, y);
        return;
    }

    public Vector2 getPosition() { return position;}
    public Vector2 getBodyPosition() {
        return b2body.getPosition();
    }

    public void setImage(Texture texture)
    {
        image.setTexture(texture);
    }

    public void setImage(String filename)
    {
        image.setTexture(new Texture(filename));
    }

    public TextureRegion getImage()
    {
        return animation.getFrame();
    }
}
