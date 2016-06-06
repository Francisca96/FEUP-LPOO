package com.francisca.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.francisca.game.PiggyCoins;
import com.francisca.game.Player;
import com.francisca.game.sprites.Button;

/**
 * Created by Francisca on 14/05/16.
 */
public class MenuState extends State {
    private Texture background;
    private Texture soundBtn;
    private Stage stage;

    public MenuState(GameStateManager gsm, Player player) {
        super(gsm, player);
        background = new Texture("background.png");
        soundBtn = new Texture("sound.png");
    }

    public void createMenuButtons(){
        final ImageButton playBtn = Button.createButtonWithImage(new TextureRegionDrawable(new TextureRegion(new Texture("playBtn.png"))));
        final ImageButton settingsBtn = Button.createButtonWithImage(new TextureRegionDrawable(new TextureRegion(new Texture("settingsBtn.png"))));
        final ImageButton highscoresBtn = Button.createButtonWithImage(new TextureRegionDrawable(new TextureRegion(new Texture("highscoresBtn.png"))));

        playBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(playBtn.isPressed()) {
                    //bounce.setProgramState(Bounce.ProgramState.LEVEL_SELECTION);
                }
            }
        });

        settingsBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(settingsBtn.isPressed()) {
                    //game.restart();
                    //bounce.setProgramState(Bounce.ProgramState.GAME);
                }
            }
        });

        highscoresBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(highscoresBtn.isPressed()) {
                    //game.start();
                    //bounce.setProgramState(Bounce.ProgramState.GAME);
                }
            }
        });

        stage.addActor(playBtn);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm, player));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, PiggyCoins.WIDTH, PiggyCoins.HEIGHT);
        //sb.draw(playBtn,  (PiggyCoins.WIDTH / 2 - playBtn.getWidth() / 2 ), (PiggyCoins.HEIGHT / 2 - playBtn.getHeight() / 2 ));
        //sb.draw(settingsBtn, (PiggyCoins.WIDTH / 2 - settingsBtn.getWidth() / 2 ), (PiggyCoins.HEIGHT / 2 - settingsBtn.getHeight() / 2 ));
        //sb.draw(highscoresBtn, (PiggyCoins.WIDTH / 2 - highscoresBtn.getWidth() / 2 ), (PiggyCoins.HEIGHT / 2 - highscoresBtn.getHeight() / 2 ));
        sb.draw(soundBtn, 0, 0, PiggyCoins.WIDTH, PiggyCoins.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        //playBtn.dispose();
        //settingsBtn.dispose();
        //highscoresBtn.dispose();
        soundBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}