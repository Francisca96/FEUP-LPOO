package com.francisca.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.francisca.game.PiggyCoins;
import com.francisca.game.states.PlayState;

/**
 * Created by Francisca on 18/05/16.
 */
public class Pig extends Element{
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
        this.image = chooseTexture(pigType);
        pigAnimation = new Animation(new TextureRegion(this.image), 8, 0.5f);
    }


    public Pig(World world, int x, int y){
        this.world = world;
        position = new Vector2(x,y);
        definePig();
        numLives = 3;
        this.image = chooseTexture(PigType.NORMAL);
        pigAnimation = new Animation(new TextureRegion(this.image), 8, 0.5f);
    }

    public Pig(World world, PigType pigType)
    {
        this.world = world;
        position = new Vector2(0,0);
        definePig();
        numLives = 3;
        this.image = chooseTexture(pigType);
        pigAnimation = new Animation(new TextureRegion(this.image), 8, 0.5f);
    }

    public Pig(World world)
    {
        this.world = world;
        position = new Vector2(0,0);
        definePig();
        numLives = 3;
        this.image = chooseTexture(PigType.NORMAL);
        pigAnimation = new Animation(new TextureRegion(this.image), 8, 0.5f);
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

        b2body.createFixture(fdef);
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

    public void update(float dt){
        pigAnimation.update(dt);
        //Measures in pixels
        /*
        float x = (b2body.getPosition().x)*PiggyCoins.PPM +PiggyCoins.WIDTH/2-WIDTH/2;
        float y = (b2body.getPosition().y)*PiggyCoins.PPM +PiggyCoins.HEIGHT/2-HEIGHT/2+ADJUSTMENT;
        */
        float x = (b2body.getPosition().x)*PiggyCoins.PPM +Gdx.graphics.getWidth()/2-WIDTH/2;
        float y = (b2body.getPosition().y)*PiggyCoins.PPM +Gdx.graphics.getHeight()/2-HEIGHT/2+ADJUSTMENT;
        setPosition(x,y);
        /*
        if(position.y > 0)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(0, velocity.y, 0);
        if(position.y < 0)
            position.y = 0;

        velocity.scl(1/dt);*/
    }

    public TextureRegion getPig() {
        return pigAnimation.getFrame();
    }

    public void jump(){
        Vector2 impulse = new Vector2(0, IMPULSE);
        this.b2body.applyLinearImpulse(impulse, b2body.getWorldCenter(), true);
    }

    public void dispose(){ this.image.dispose(); }
}
