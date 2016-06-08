package com.francisca.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.francisca.game.PiggyCoins;
import com.francisca.game.Player;

/**
 * Created by Francisca on 14/05/16.
 */
public class MenuState extends State {
    private Texture background;

    private Stage stage;
    private SpriteBatch spriteBatch;

    private ImageButton playBtn;
    private ImageButton settingsBtn;
    private ImageButton highscoresBtn;
    private ImageButton soundBtn;
    private ImageButton musicBtn;

    private Sprite playBtnTexture;
    private Sprite settingsBtnTexture;
    private Sprite highscoresBtnTexture;
    private Sprite soundBtnTexture;
    private Sprite musicBtnTexture;

    public MenuState(GameStateManager gsm, PiggyCoins game) {
        super(gsm, game);
        Gdx.input.setCatchBackKey(false);
        background = new Texture("background.png");

        //Playing the awesome song
        if(!game.getMenuSong().isPlaying()) {
            game.getMenuSong().play();
        }

        FitViewport viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        spriteBatch = new SpriteBatch();
        stage = new Stage(viewport, spriteBatch);

        Gdx.input.setInputProcessor(stage);
        createMenuButtons();
    }

    public void createMenuButtons(){

        playBtnTexture = new Sprite(new Texture("playBtn.png"));
        playBtn = new ImageButton(new SpriteDrawable(playBtnTexture));
        playBtn.setPosition(stage.getWidth()/2-playBtn.getWidth()/2, stage.getHeight()/2-playBtn.getHeight()/2);

        settingsBtnTexture = new Sprite(new Texture("settingsBtn.png"));
        settingsBtn = new ImageButton(new SpriteDrawable(settingsBtnTexture));
        settingsBtn.setPosition(stage.getWidth()/2-settingsBtn.getWidth()/2, stage.getHeight()/2-settingsBtn.getHeight()/2-playBtn.getHeight()-20);

        highscoresBtnTexture = new Sprite(new Texture("highscoresBtn.png"));
        highscoresBtn = new ImageButton(new SpriteDrawable(highscoresBtnTexture));
        highscoresBtn.setPosition(stage.getWidth()/2-highscoresBtn.getWidth()/2, stage.getHeight()/2-highscoresBtn.getHeight()/2-settingsBtn.getHeight()-3*playBtn.getHeight()/2-20);


        soundBtnTexture = new Sprite(new Texture("soundBtn.png"));
        soundBtn = new ImageButton(new SpriteDrawable(soundBtnTexture));
        soundBtn.setPosition(0, 0);

        musicBtnTexture = new Sprite(new Texture("musicBtn.png"));
        musicBtn = new ImageButton(new SpriteDrawable(musicBtnTexture));
        musicBtn.setPosition(soundBtn.getWidth() + 5, 0);

        playBtn.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(playBtn.isPressed()) {
                    game.getMenuSong().stop();
                    gsm.set(new PlayState(gsm, game));
                }
            }
        });

        settingsBtn.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(settingsBtn.isPressed()) {
                    gsm.set(new SettingsState(gsm, game));
                }
            }
        });

        highscoresBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(highscoresBtn.isPressed()) {
                    gsm.set(new HighscoresState(gsm, game));
                }
            }
        });

        soundBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(soundBtn.isPressed()) {
                    System.out.println("Pressed sound");
                }
            }
        });

        musicBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(musicBtn.isPressed()) {
                    if(game.getMenuSong().isPlaying())
                        game.getMenuSong().stop();
                    else
                        game.getMenuSong().play();
                }
            }
        });


        stage.addActor(playBtn);
        stage.addActor(settingsBtn);
        stage.addActor(highscoresBtn);
        stage.addActor(soundBtn);
        stage.addActor(musicBtn);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            //gsm.set(new PlayState(gsm, game));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        //stage.draw();
        sb.begin();
        sb.draw(background, 0, 0, PiggyCoins.WIDTH, PiggyCoins.HEIGHT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

        playBtn.draw(sb, 1);
        settingsBtn.draw(sb, 1);
        highscoresBtn.draw(sb, 1);
        soundBtn.draw(sb, 1);
        musicBtn.draw(sb, 1);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        System.out.println("Menu State Disposed");
    }
}