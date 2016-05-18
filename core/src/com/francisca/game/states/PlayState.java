package com.francisca.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.francisca.game.PiggyCoins;
import com.francisca.game.sprites.Pig;

/**
 * Created by Francisca on 15/05/16.
 */
public class PlayState extends State {
    private Pig pig;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        pig = new Pig(50, 300);
        cam.setToOrtho(false, PiggyCoins.WIDTH / 2, PiggyCoins.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        pig.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(pig.getPig(), pig.getPosition().x, pig.getPosition().y, Pig.WIDTH, Pig.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
