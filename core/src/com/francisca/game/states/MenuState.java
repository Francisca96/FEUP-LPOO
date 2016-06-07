package com.francisca.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
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

    private Button playBtn;
    private Button settingsBtn;
    private Button highscoresBtn;
    private Button soundBtn;
    private Button musicBtn;

    private Sprite playBtnTexture;
    private Sprite settingsBtnTexture;
    private Sprite highscoresBtnTexture;
    private Sprite soundBtnTexture;
    private Sprite musicBtnTexture;
    public MenuState(GameStateManager gsm, PiggyCoins game) {
        super(gsm, game);
        background = new Texture("background.png");

        FitViewport viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        spriteBatch = new SpriteBatch();
        stage = new Stage(viewport, spriteBatch);

        Gdx.input.setInputProcessor(stage);
        createMenuButtons();
    }

    public void createMenuButtons(){
        playBtnTexture = new Sprite(new Texture("playBtn.png"));
        playBtn = new Button(new SpriteDrawable(playBtnTexture));

        settingsBtnTexture = new Sprite(new Texture("settingsBtn.png"));
        settingsBtn = new Button(new SpriteDrawable(settingsBtnTexture));

        highscoresBtnTexture = new Sprite(new Texture("highscoresBtn.png"));
        highscoresBtn = new Button(new SpriteDrawable(highscoresBtnTexture));

        soundBtnTexture = new Sprite(new Texture("soundBtn.png"));
        soundBtn = new Button(new SpriteDrawable(soundBtnTexture));

        musicBtnTexture = new Sprite(new Texture("musicBtn.png"));
        musicBtn = new Button(new SpriteDrawable(musicBtnTexture));

        playBtn.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(playBtn.isPressed()) {
                    gsm.set(new PlayState(gsm, game));
                }
            }
        });

        settingsBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(settingsBtn.isPressed()) {

                }
            }
        });

        highscoresBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(highscoresBtn.isPressed()) {

                }
            }
        });

        soundBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(soundBtn.isPressed()) {

                }
            }
        });

        musicBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(musicBtn.isPressed()) {

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
            gsm.set(new PlayState(gsm, game));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        stage.draw();
        sb.begin();
        sb.draw(background, 0, 0, PiggyCoins.WIDTH, PiggyCoins.HEIGHT);
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