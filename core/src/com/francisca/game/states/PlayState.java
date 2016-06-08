package com.francisca.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.francisca.game.PiggyCoins;
import com.francisca.game.Player;
import com.francisca.game.generators.CoinGenerator;
import com.francisca.game.generators.ObstacleGenerator;
import com.francisca.game.scenes.Hud;
import com.francisca.game.sprites.Cloud;
import com.francisca.game.sprites.CoinPattern;
import com.francisca.game.sprites.elements.Coin;
import com.francisca.game.sprites.elements.Floor;
import com.francisca.game.sprites.elements.Obstacle;
import com.francisca.game.sprites.elements.Pig;
import com.francisca.game.tools.WorldContactListener;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Francisca on 15/05/16.
 */
public class PlayState extends State {
    //Forces and Impulses
    public static final float GRAVITY = -9.8f;
    //public static final float GRAVITY = 0;
    public static float WORLD_VELOCITY = -1f;

    //Categories for collisions
    public static final short CATEGORY_PIG = 0x0001;
    public static final short CATEGORY_LIMIT = 0x0002;
    public static final short CATEGORY_OBSTACLE = 0x0004;
    public static final short CATEGORY_COLLECTIBLE = 0x0008;
    public static final short CATEGORY_INVULNERABLE = 0x0010;

    //Masks for collisions
    public static final short MASK_PIG = CATEGORY_LIMIT | CATEGORY_OBSTACLE | CATEGORY_COLLECTIBLE;
    public static final short MASK_INVULNERABLE = CATEGORY_LIMIT;
    public static final short MASK_LIMIT = CATEGORY_PIG | CATEGORY_INVULNERABLE;
    public static final short MASK_OBSTACLE = CATEGORY_PIG | CATEGORY_COLLECTIBLE;
    public static final short MASK_COLLECTIBLE = CATEGORY_PIG | CATEGORY_OBSTACLE;
    //ATENCAO, QUE A PROPRIA CLASSE NAO COLIDE ENTRE AS SUAS INSTANCIAS

    //Game settings
    public static final int NUM_LIVES = 3;

    private boolean gameover;
    private float endGamecounter;

    private Texture bg;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Hud hud;

    private Button soundBtn;
    private Button musicBtn;

    private Sprite soundBtnTexture;
    private Sprite musicBtnTexture;

    //World's objects
    private Pig pig;
    private Floor floor, ceiling;
    //private Obstacle obstacle;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Coin> coins;

    private Cloud cloud;

    private Stage stage;
    private SpriteBatch spriteBatch;
    private Music playSong;
    private Music pigJumpingSound;


    //Generators
    private ObstacleGenerator obstacleGenerator;
    private CoinGenerator coinGenerator;

    public PlayState(GameStateManager gsm, PiggyCoins game) {
        super(gsm, game);
        Gdx.input.setCatchBackKey(true);
        bg = new Texture("bg.png");
        cloud = new Cloud(200);
        gameover = false;
        endGamecounter = 5;

        //Set world
        Vector2 gravity = new Vector2(0, GRAVITY);
        world = new World(gravity, true);
        b2dr = new Box2DDebugRenderer();
        hud = new Hud(game.batch, NUM_LIVES);

        //Initializing world objects
        this.pig = new Pig(world, Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/2,game.getActualPlayer().getActualPig());
        this.floor = new Floor(world);
        this.ceiling = new Floor(world, Obstacle.WIDTH/2, Gdx.graphics.getHeight());
        Obstacle obstacle = new Obstacle(world);
        this.obstacles = new ArrayList<Obstacle>();
        this.obstacles.add(obstacle);
        this.coins = new ArrayList<Coin>();

        //Initializing generators
        this.obstacleGenerator = new ObstacleGenerator(this.world);
        this.coinGenerator = new CoinGenerator(this);

        world.setContactListener(new WorldContactListener());

        FitViewport viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        spriteBatch = new SpriteBatch();
        stage = new Stage(viewport, spriteBatch);
        this.playSong = Gdx.audio.newMusic(Gdx.files.internal("playmusic.mp3"));
        this.playSong.setLooping(true);
        this.playSong.play();

        this.pigJumpingSound = Gdx.audio.newMusic(Gdx.files.internal("oinc.mp3"));
        this.pigJumpingSound.setVolume(0.1f);

        Gdx.input.setInputProcessor(stage);
        createMenuButtons();
    }

    public void createMenuButtons(){
        soundBtnTexture = new Sprite(new Texture("soundBtn.png"));
        soundBtn = new Button(new SpriteDrawable(soundBtnTexture));

        musicBtnTexture = new Sprite(new Texture("musicBtn.png"));
        musicBtn = new Button(new SpriteDrawable(musicBtnTexture));
        musicBtn.setPosition(soundBtn.getWidth(), 0);

        soundBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(soundBtn.isPressed()) {
                    if(pigJumpingSound.getVolume() == 0)
                        pigJumpingSound.setVolume(0.1f);
                    else
                        pigJumpingSound.setVolume(0);
                }
            }
        });

        musicBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(musicBtn.isPressed()) {
                    if(playSong.isPlaying())
                        playSong.stop();
                    else
                        playSong.play();
                }
            }
        });

        stage.addActor(soundBtn);
        stage.addActor(musicBtn);
    }

    @Override
    protected void handleInput() {

        if(Gdx.input.isKeyPressed(Input.Keys.BACK))
        {
            gsm.set(new MenuState(gsm, game));
            return;
        }
        if(Gdx.input.justTouched()) {
            pigJumpingSound.stop();
            pig.jump();
            pigJumpingSound.play();
        }
    }

    @Override
        public void update(float dt) {
        //If gameOver
        if(hud.getLives() <= 0)
        {
            gameover = true;
            endGamecounter -= dt;
            if(endGamecounter < 0)
            {
                if(game.isHighscore(hud.getScore()))
                    game.addToHighscores(hud.getScore());
                gsm.set(new MenuState(gsm, game));
                return;
            }
            return;
        }

        handleInput();
        world.step(1/60f, 6, 2);
        pig.update(dt);

        //Collect coins
        hud.setScore(pig.numCoins * 10);

        //Check lives
        if(pig.isHit())
        {
            hud.gotHit();
            pig.afterHit();
        }


        //Generating new obstacles
        Obstacle newObstacle = obstacleGenerator.update(dt);
        if(newObstacle != null)
        {
            obstacles.add(newObstacle);
        }

        /* Generate new Coins*/
        coinGenerator.update(dt);
        /* Update/Remove Coins*/
        for(Iterator<Coin> iterator = coins.iterator(); iterator.hasNext();)
        {
            Coin nextCoin = iterator.next();
            if(!nextCoin.update(dt) || nextCoin.isMarkedForDelete())
            {
                nextCoin.delete();
                iterator.remove();
            }
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
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0, 0, 0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /*
        this.gamePort.apply();
        sb.setProjectionMatrix(cam.combined);
*/
        sb.begin();
        sb.draw(bg, 0, 0, PiggyCoins.WIDTH, PiggyCoins.HEIGHT);
        sb.draw(cloud.getCloud(), cloud.getPos().x, cloud.getPos().y, cloud.getWidth(), cloud.getHeight());
        sb.draw(pig.getImage(), pig.getPosition().x, pig.getPosition().y, Pig.WIDTH, Pig.HEIGHT);
        for(Obstacle obstacle : obstacles) {
            sb.draw(obstacle.getImage(), obstacle.getPosition().x, obstacle.getPosition().y, Obstacle.WIDTH, obstacle.getSize());
        }
        for(Coin coin: coins)
        {
            sb.draw(coin.getImage(), coin.getPosition().x, coin.getPosition().y, coin.WIDTH, coin.HEIGHT);
        }
        //sb.draw(stick.getTopStick(), stick.getPosTopStick().x, stick.getPosTopStick().y, stick.getWidth(), stick.getHeightTopStick());
        //sb.draw(stick.getBottomStick(), stick.getPosBottomStick().x, stick.getPosBottomStick().y, stick.getWidth(), stick.getHeightBottomStick());
        soundBtn.draw(sb, 1);
        musicBtn.draw(sb, 1);
        sb.end();

        //hud.getViewport().apply();
        sb.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        //b2dr.render(world, cam.combined);
    }


    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
        pig.dispose();
        bg.dispose();
        cloud.dispose();
        playSong.stop();
        playSong.dispose();
        pigJumpingSound.stop();
        pigJumpingSound.dispose();
    }

    public World getWorld() {
        return world;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }
}
