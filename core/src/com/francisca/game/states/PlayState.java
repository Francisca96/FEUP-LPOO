package com.francisca.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.francisca.game.PiggyCoins;
import com.francisca.game.sprites.Pig;

/**
 * Created by Francisca on 15/05/16.
 */
public class PlayState extends State {
    private Pig pig;
    private Texture bg;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        pig = new Pig(50, 300);
        cam.setToOrtho(false, PiggyCoins.WIDTH / 2, PiggyCoins.HEIGHT / 2);
        bg = new Texture("background.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            pig.jump();
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
        sb.draw(bg, cam.position.x - (cam.viewportWidth  / 2), 0);
        sb.draw(pig.getPig(), pig.getPosition().x, pig.getPosition().y, Pig.WIDTH, Pig.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
