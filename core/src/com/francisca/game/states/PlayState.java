package com.francisca.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.francisca.game.generators.ObstacleGenerator;
import com.francisca.game.sprites.Cloud;
import com.francisca.game.sprites.Coin;
import com.francisca.game.sprites.Floor;
import com.francisca.game.sprites.Obstacle;
import com.francisca.game.sprites.Pig;
import com.francisca.game.tools.WorldContactListener;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Francisca on 15/05/16.
 */
public class PlayState extends State {
    //Forces and Impulses
    public static final float GRAVITY = -9.8f;
    public static final float OBSTACLE_VELOCITY = -1f;

    //Categories for collisions
    public static final short CATEGORY_PIG = 0x0001;
    public static final short CATEGORY_LIMIT = 0x0002;
    public static final short CATEGORY_OBSTACLE = 0x0004;
    public static final short CATEGORY_COLLECTIBLE = 0x0008;

    //Masks for collisions
    public static final short MASK_PIG = CATEGORY_LIMIT | CATEGORY_OBSTACLE;
    public static final short MASK_LIMIT = CATEGORY_PIG;
    public static final short MASK_OBSTACLE = CATEGORY_PIG | CATEGORY_COLLECTIBLE;
    public static final short MASK_COLLECTIBLE = CATEGORY_OBSTACLE;


    private Texture bg;
    private Player player;
    private World world;
    private Box2DDebugRenderer b2dr;

    //World's objects
    private Pig pig;
    private Floor floor, ceiling;
    //private Obstacle obstacle;
    private ArrayList<Obstacle> obstacles;

    private Coin coin;
    private Cloud cloud;

    //Generators
    private ObstacleGenerator obstacleGenerator;

    public PlayState(GameStateManager gsm, Player player) {
        super(gsm, player);
        bg = new Texture("bg.png");
        coin = new Coin(100);
        cloud = new Cloud(200);

        //Set world
        Vector2 gravity = new Vector2(0, GRAVITY);
        world = new World(gravity, true);
        b2dr = new Box2DDebugRenderer();

        //Initializing world objects
        this.pig = new Pig(world, Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/2,player.getActualPig());
        this.floor = new Floor(world);
        this.ceiling = new Floor(world, Obstacle.WIDTH/2, Gdx.graphics.getHeight());
        Obstacle obstacle = new Obstacle(world);
        //this.obstacle = new Obstacle(world);
        this.obstacles = new ArrayList<Obstacle>();
        this.obstacles.add(obstacle);

        //Initializing generators
        this.obstacleGenerator = new ObstacleGenerator(this.world);

        world.setContactListener(new WorldContactListener());
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            pig.jump();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            pig.move();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        world.step(1/60f, 6, 2);
        pig.update(dt);

        //Generating new obstacles
        Obstacle newObstacle = obstacleGenerator.update(dt);
        if(newObstacle != null)
        {
            obstacles.add(newObstacle);
        }

        //Updating/Removing existing obstacles
        for(Iterator<Obstacle> iterator = obstacles.iterator(); iterator.hasNext();)
        {
            Obstacle nextObstacle = iterator.next();
            if(!nextObstacle.update(dt)) //If body is out of screen
            {
                nextObstacle.delete();
                iterator.remove();
            }
        }
        /*
        System.out.print(pig.getBodyPosition().x);
        System.out.print(" ");
        System.out.print(pig.getBodyPosition().y);
        System.out.print("\n");
        */
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0, 0, 0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //sb.setProjectionMatrix(cam.combined);

        sb.begin();
       // sb.draw(bg, 0, 0, PiggyCoins.WIDTH, PiggyCoins.HEIGHT);
        sb.draw(pig.getImage(), pig.getPosition().x, pig.getPosition().y, Pig.WIDTH, Pig.HEIGHT);
        for(Obstacle obstacle : obstacles) {
            sb.draw(obstacle.getImage(), obstacle.getPosition().x, obstacle.getPosition().y, Obstacle.WIDTH, obstacle.getSize());
        }
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
