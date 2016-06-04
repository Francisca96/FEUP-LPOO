package com.francisca.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.francisca.game.PiggyCoins;
import com.francisca.game.Player;
import com.francisca.game.sprites.Cloud;
import com.francisca.game.sprites.Coin;
import com.francisca.game.sprites.Floor;
import com.francisca.game.sprites.Pig;

/**
 * Created by Francisca on 15/05/16.
 */
public class PlayState extends State {
    public static final float GRAVITY = -0.1f;

    private Pig pig;
    private Floor floor, ceiling;
    private Texture bg;
    private Player player;
    private World world;
    private Box2DDebugRenderer b2dr;

    private Coin coin;
    private Cloud cloud;

    public PlayState(GameStateManager gsm, Player player) {
        super(gsm, player);
        bg = new Texture("bg.png");
        coin = new Coin(100);
        cloud = new Cloud(200);

        Vector2 gravity = new Vector2(0, GRAVITY*PiggyCoins.PPM);

        world = new World(gravity, true);
        b2dr = new Box2DDebugRenderer();

        this.pig = new Pig(world, 50, 400,player.getActualPig());
        this.floor = new Floor(world);
        this.ceiling = new Floor(world, 0, PiggyCoins.HEIGHT);
       // this.pig.setPosition(50/PiggyCoins.PPM, 10/PiggyCoins.PPM);
        //this.pig.setPosition(0/PiggyCoins.PPM, 0/PiggyCoins.PPM);

        //world.setContactListener(new WorldContactListener());
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            pig.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        world.step(1/60f, 6, 2); /*TODO Investigar o que o 6 e o 2 fazem*/
        pig.update(dt);
        System.out.println(pig.getBodyPosition().y);
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0, 0, 0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        sb.draw(bg, 0, 0, PiggyCoins.WIDTH, PiggyCoins.HEIGHT);
        sb.draw(pig.getPig(), pig.getPosition().x, pig.getPosition().y, Pig.WIDTH, Pig.HEIGHT);
        sb.draw(coin.getCoin(), coin.getPos().x, coin.getPos().y, coin.WIDTH, coin.HEIGHT);
        sb.draw(cloud.getCloud(), cloud.getPos().x, cloud.getPos().y, cloud.getWidth(), cloud.getHeight());
        sb.end();

        b2dr.render(world, cam.combined);
    }


    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
        pig.dispose();
        bg.dispose();
    }
}
