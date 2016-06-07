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
    private Texture soundBtn;

    private Stage stage;
    private SpriteBatch spriteBatch;

    private Button playBtn;
    private Button settingsBtn;
    private Button highscoresBtn;

    private Sprite playBtnTexture;
    private Sprite settingsBtnTexture;
    private Sprite highscoresBtnTexture;

    public MenuState(GameStateManager gsm, Player player) {
        super(gsm, player);
        background = new Texture("background.png");
        soundBtn = new Texture("sound.png");

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

        playBtn.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(playBtn.isPressed()) {
                    gsm.set(new PlayState(gsm, player));
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

        stage.addActor(playBtn);
        stage.addActor(settingsBtn);
        stage.addActor(highscoresBtn);
    }

    @Override
    public void handleInput() {
        /*if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm, player));
            dispose();
        }*/
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
        //sb.draw(playBtn,  (PiggyCoins.WIDTH / 2 - playBtn.getWidth() / 2 ), (PiggyCoins.HEIGHT / 2 - playBtn.getHeight() / 2 ));
        //sb.draw(settingsBtn, (PiggyCoins.WIDTH / 2 - settingsBtn.getWidth() / 2 ), (PiggyCoins.HEIGHT / 2 - settingsBtn.getHeight() / 2 ));
        //sb.draw(highscoresBtn, (PiggyCoins.WIDTH / 2 - highscoresBtn.getWidth() / 2 ), (PiggyCoins.HEIGHT / 2 - highscoresBtn.getHeight() / 2 ));
        playBtn.draw(sb, 1);
        settingsBtn.draw(sb, 1);
        highscoresBtn.draw(sb, 1);
        sb.draw(soundBtn, 0, 0, PiggyCoins.WIDTH, PiggyCoins.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        //playBtn.dispose();
        //settingsBtn.dispose();
        //highscoresBtn.dispose();
        //soundBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}