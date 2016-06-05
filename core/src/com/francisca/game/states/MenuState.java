package com.francisca.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.francisca.game.PiggyCoins;

/**
 * Created by Francisca on 14/05/16.
 */
public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    private Texture settingsBtn;
    private Texture highscoresBtn;
    private Texture soundBtn;



    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.png");
        playBtn = new Texture("playBtn.png");
        settingsBtn = new Texture("settingsBtn.png");
        highscoresBtn = new Texture("highscoresBtn.png");
        soundBtn = new Texture("sound.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
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
        sb.draw(playBtn,  (PiggyCoins.WIDTH / 2 - playBtn.getWidth() / 2 ), (PiggyCoins.HEIGHT / 2 - playBtn.getHeight() / 2 ));
        sb.draw(settingsBtn, (PiggyCoins.WIDTH / 2 - settingsBtn.getWidth() / 2 ), (PiggyCoins.HEIGHT / 2 - settingsBtn.getHeight() / 2 ));
        sb.draw(highscoresBtn, (PiggyCoins.WIDTH / 2 - highscoresBtn.getWidth() / 2 ), (PiggyCoins.HEIGHT / 2 - highscoresBtn.getHeight() / 2 ));
        sb.draw(soundBtn, 0, 0, PiggyCoins.WIDTH, PiggyCoins.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        settingsBtn.dispose();
        highscoresBtn.dispose();
        soundBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}