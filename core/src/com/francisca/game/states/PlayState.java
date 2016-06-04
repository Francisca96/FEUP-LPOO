package com.francisca.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.francisca.game.PiggyCoins;
import com.francisca.game.sprites.Cloud;
import com.francisca.game.sprites.Coin;
import com.francisca.game.sprites.Stick;
import com.francisca.game.sprites.Pig;

/**
 * Created by Francisca on 15/05/16.
 */
public class PlayState extends State {
    private Pig pig;
    private Texture bg;
    private Coin coin;
    private Cloud cloud;
    private Stick stick;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        pig = new Pig(50, 300);
        cam.setToOrtho(false, PiggyCoins.WIDTH / 2, PiggyCoins.HEIGHT / 2);
        bg = new Texture("bg.png");
        coin = new Coin(100);
        cloud = new Cloud(200);
        stick = new Stick(150);
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
        sb.draw(bg, 0, 0, PiggyCoins.WIDTH / 2, PiggyCoins.HEIGHT / 2);
        sb.draw(pig.getPig(), pig.getPosition().x, pig.getPosition().y, Pig.WIDTH, Pig.HEIGHT);
        //sb.draw(coin.getCoin(), coin.getPos().x, coin.getPos().y, coin.getWIDTH(), coin.getHEIGHT());
        sb.draw(cloud.getCloud(), cloud.getPos().x, cloud.getPos().y, cloud.getWidth(), cloud.getHeight());
        //sb.draw(stick.getTopStick(), stick.getPosTopStick().x, stick.getPosTopStick().y, stick.getWidth(), stick.getHeightTopStick());
        //sb.draw(stick.getBottomStick(), stick.getPosBottomStick().x, stick.getPosBottomStick().y, stick.getWidth(), stick.getHeightBottomStick());
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
