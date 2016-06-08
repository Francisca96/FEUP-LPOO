package com.francisca.game.sprites.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
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
    public static final float INVULNERABILITY = 3;

    public static final float IMPULSE = 0.4f;


    private Animation pigAnimation;
    public boolean hit;
    public int numCoins;
    private float invulnerabilityTimer;
    private PigType pigType;
    Fixture pigFixture;

    public Pig(World world, int x, int y, PigType pigType){

        this.world = world;
        position = new Vector2(x,y);
        definePig();
        hit = false;
        this.pigType = pigType;
        this.image = new TextureRegion();
        this.image.setTexture(chooseTexture(pigType));
        pigAnimation = new Animation(new TextureRegion(this.image.getTexture()), 8, 0.5f);
        this.numCoins = 0;
        this.invulnerabilityTimer = 0;

        //align the sprite
        float xalign = (b2body.getPosition().x)*PiggyCoins.PPM +Gdx.graphics.getWidth()/2-(WIDTH/2);
        float yalign = (b2body.getPosition().y)*PiggyCoins.PPM +Gdx.graphics.getHeight()/2-(HEIGHT/2)+ADJUSTMENT;
        setPosition(xalign,yalign);
    }


    public Pig(World world, int x, int y){
        this.world = world;
        position = new Vector2(x,y);
        definePig();
        hit = false;
        this.pigType = PigType.NORMAL;
        this.image = new TextureRegion();
        this.image.setTexture(chooseTexture(PigType.NORMAL));
        pigAnimation = new Animation(new TextureRegion(this.image.getTexture()), 8, 0.5f);
        this.numCoins = 0;
        this.invulnerabilityTimer = 0;


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
        this.pigType = pigType;
        hit = false;
        this.image = new TextureRegion();
        this.image.setTexture(chooseTexture(pigType));
        pigAnimation = new Animation(new TextureRegion(this.image.getTexture()), 8, 0.5f);
        this.numCoins = 0;
        this.invulnerabilityTimer = 0;


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
        hit = false;
        this.pigType = PigType.NORMAL;
        this.image = new TextureRegion();
        this.image.setTexture(chooseTexture(PigType.NORMAL));
        pigAnimation = new Animation(new TextureRegion(this.image.getTexture()), 8, 0.5f);
        this.numCoins = 0;
        this.invulnerabilityTimer = 0;


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

        b2body.createFixture(fdef).setUserData(this);
        shape.dispose();
    }

    public Texture chooseTexture(PigType pigType)
    {
        Texture texture;
        if(pigType == PigType.NORMAL)
        {
            texture = new Texture("pigSprite.png");
        }
        else if(pigType == PigType.BLUE)
        {
            texture = new Texture("bluePigSprite.png");
        }
        else if(pigType == PigType.GREEN)
        {
            texture = new Texture("greenPigSprite.png");
        }
        else if(pigType == PigType.GREY)
        {
            texture = new Texture("greyPigSprite.png");
        }
        else if(pigType == PigType.PURPLE)
        {
            texture = new Texture("purplePigSprite.png");
        }
        else if(pigType == PigType.INVULNERABLE)
        {
            if(this.pigType == PigType.NORMAL)
            {
                texture = new Texture("pigSpriteInvulnerable.png");
            }
            else if(this.pigType == PigType.BLUE)
            {
                texture = new Texture("bluePigSpriteInvulnerable.png");
            }
            else if(this.pigType == PigType.GREEN)
            {
                texture = new Texture("greenPigSpriteInvulnerable.png");
            }
            else if(this.pigType == PigType.GREY)
            {
                texture = new Texture("greyPigSpriteInvulnerable.png");
            }
            else if(this.pigType == PigType.PURPLE)
            {
                texture = new Texture("purplePigSpriteInvulnerable.png");
            }
            else
            {
                texture = new Texture("pigSpriteInvulnerable.png");
            }
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

        float x = ((b2body.getPosition().x)*PiggyCoins.PPM +Gdx.graphics.getWidth()/2-(WIDTH/2));
        float y = ((b2body.getPosition().y)*PiggyCoins.PPM +Gdx.graphics.getHeight()/2-(HEIGHT/2)+ADJUSTMENT);
        setPosition(x,y);

        //Stops Velocity in the x axis
        b2body.setLinearVelocity(0, b2body.getLinearVelocity().y);
        b2body.setAngularVelocity(0);

        //Invulnerability
        if(invulnerabilityTimer > 0) {
            invulnerabilityTimer -= dt;
            if(invulnerabilityTimer <= 0)
            {
                this.image.setTexture(chooseTexture(pigType));
                pigAnimation = new Animation(new TextureRegion(this.image.getTexture()), 8, 0.5f);

                for(Fixture fixture : b2body.getFixtureList()) {
                    Filter filter = new Filter();
                    filter.categoryBits = PlayState.CATEGORY_PIG;
                    filter.maskBits = PlayState.MASK_PIG;
                    fixture.setFilterData(filter);
                }
            }
        }

        return true;
    }

    @Override
    public TextureRegion getImage() {
        return pigAnimation.getFrame();
    }

    public float getInvulnerabilityTimer() {
        return invulnerabilityTimer;
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

    public void catchCoin()
    {
        numCoins++;
    }

    public void resetCoins()
    {
        numCoins = 0;
    }

    public boolean isHit()
    {
        return hit;
    }

    public void afterHit()
    {
        hit = false;
        invulnerabilityTimer = INVULNERABILITY;
        this.image.setTexture(chooseTexture(PigType.INVULNERABLE));
        pigAnimation = new Animation(new TextureRegion(this.image.getTexture()), 8, 0.5f);
        for(Fixture fixture : b2body.getFixtureList())
        {
            Filter filter = new Filter();
            filter.categoryBits = PlayState.CATEGORY_INVULNERABLE;
            filter.maskBits = PlayState.MASK_INVULNERABLE;
            fixture.setFilterData(filter);
        }
    }


    public void dispose(){ }
}
