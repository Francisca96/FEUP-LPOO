package com.francisca.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.francisca.game.PiggyCoins;

/**
 * Created by Francisca on 05/06/16.
 */
public class SettingsState extends State{
    private Texture background;

    public SettingsState (GameStateManager gsm) {
        super(gsm);
        background = new Texture("bgStates.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, PiggyCoins.WIDTH, PiggyCoins.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        System.out.println("Settings State Disposed");
    }
}
