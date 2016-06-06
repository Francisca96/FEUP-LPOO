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
import com.francisca.game.sprites.PigType;
import com.francisca.game.states.PlayState;

/**
 * Created by Francisca on 18/05/16.
 */
public class Pig extends com.francisca.game.sprites.elements.Element {
    //Measures in pixels
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    public static final float RADIUS = 20.5f;
    public static final float ADJUSTMENT = 4f; //To adjust the body to the sprite

    public static final float IMPULSE = 0.4f;


    private Animation pigAnimation;
    private int numLives;

    public Pig(World world, int x, int y, PigType pigType){

        this.world = world;
        position = new Vector2(x,y);
        definePig();
        numLives = 3;
        this.image = new TextureRegion();
        this.image.setTexture(chooseTexture(pigType));
        pigAnimation = new Animation(new TextureRegion(this.image.getTexture()), 8, 0.5f);

        //align the sprite
        float xalign = (b2body.getPosition().x)*PiggyCoins.PPM +Gdx.graphics.getWidth()/2-WIDTH/2;
        float yalign = (b2body.getPosition().y)*PiggyCoins.PPM +Gdx.graphics.getHeight()/2-HEIGHT/2+ADJUSTMENT;
        setPosition(xalign,yalign);
    }


    public Pig(World world, int x, int y){
        this.world = world;
        position = new Vector2(x,y);
        definePig();
        numLives = 3;
        this.image = new TextureRegion();
        this.image.setTexture(chooseTexture(PigType.NORMAL));
        pigAnimation = new Animation(new TextureRegion(this.image.getTexture()), 8, 0.5f);

        //align the sprite
        float xalign = (b2body.getPosition().x)*PiggyCoins.PPM +Gdx.graphics.getWidth()/2-WIDTH/2;
        float yalign = (b2body.getPosition().y)*PiggyCoins.PPM +Gdx.graphics.getHeight()/2-HEIGHT/2+ADJUSTMENT;
        setPosition(xalign,yalign);
    }

    public Pig(World world, PigType pigType)
    {
        this.world = world;
        position = new Vector2(0,0);
        definePig();
        numLives = 3;
        this.image = new TextureRegion();
        this.image.setTexture(chooseTexture(pigType));
        pigAnimation = new Animation(new TextureRegion(this.image.getTexture()), 8, 0.5f);

        //align the sprite
        float xalign = (b2body.getPosition().x)*PiggyCoins.PPM +Gdx.graphics.getWidth()/2-WIDTH/2;
        float yalign = (b2body.getPosition().y)*PiggyCoins.PPM +Gdx.graphics.getHeight()/2-HEIGHT/2+ADJUSTMENT;
        setPosition(xalign,yalign);
    }

    public Pig(World world)
    {
        this.world = world;
        position = new Vector2(0,0);
        definePig();
        numLives = 3;
        this.image = new TextureRegion();
        this.image.setTexture(chooseTexture(PigType.NORMAL));
        pigAnimation = new Animation(new TextureRegion(this.image.getTexture()), 8, 0.5f);

        //align the sprite
        float xalign = (b2body.getPosition().x)*PiggyCoins.PPM +Gdx.graphics.getWidth()/2-WIDTH/2;
        float yalign = (b2body.getPosition().y)*PiggyCoins.PPM +Gdx.graphics.getHeight()/2-HEIGHT/2+ADJUSTMENT;
        setPosition(xalign,yalign);
    }

    public void definePig()
    {
        //Measures in METRES
        //defines body
        BodyDef bdef = new BodyDef();
        //bdef.position.set((position.x-PiggyCoins.WIDTH/2)/PiggyCoins.PPM, (position.y-PiggyCoins.HEIGHT/2)/PiggyCoins.PPM);
        bdef.position.set((position.x- Gdx.graphics.getWidth()/2)/PiggyCoins.PPM, (position.y-Gdx.graphics.getHeight()/2)/PiggyCoins.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        //puts body into the world
        b2body = world.createBody(bdef);

        //Define characteristics of body
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(RADIUS/PiggyCoins.PPM);
        fdef.shape = shape;
        fdef.density = 1;
        fdef.filter.categoryBits = PlayState.CATEGORY_PIG;
        fdef.filter.maskBits = PlayState.MASK_PIG;

        b2body.createFixture(fdef).setUserData("pig");
        shape.dispose();
    }

    public Texture chooseTexture(PigType pigType)
    {
        Texture texture;
        if(pigType == PigType.NORMAL)
        {
            texture = new Texture("pigSprite.png");
        }
        else
        {
            texture = new Texture("pigSprite.png");
        }

        return texture;
    }

    public boolean update(float dt){
        pigAnimation.update(dt);
        //Measures in pixels

        float x = (b2body.getPosition().x)*PiggyCoins.PPM +Gdx.graphics.getWidth()/2-WIDTH/2;
        float y = (b2body.getPosition().y)*PiggyCoins.PPM +Gdx.graphics.getHeight()/2-HEIGHT/2+ADJUSTMENT;
        setPosition(x,y);

        //Stops Velocity in the x axis
        b2body.setLinearVelocity(0, b2body.getLinearVelocity().y);
        b2body.setAngularVelocity(0);

        return true;
    }

    @Override
    public TextureRegion getImage() {
        return pigAnimation.getFrame();
    }

    public void jump(){
        Vector2 impulse = new Vector2(0, IMPULSE);
        this.b2body.applyLinearImpulse(impulse, b2body.getWorldCenter(), true);
    }

    public void move()
    {
        this.b2body.applyLinearImpulse(new Vector2(0.1f, 0), b2body.getWorldCenter(), true);
    }

    /**
     * Just for debugging purposes
     */
    public void descend(){
        this.b2body.applyLinearImpulse(new Vector2(0, -0.1f), b2body.getWorldCenter(), true);
    }
    public void dispose(){ }
}
