package com.francisca.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.francisca.game.PiggyCoins;
import com.francisca.game.Player;

/**
 * Created by Francisca on 14/05/16.
 */

public abstract class State {
    protected OrthographicCamera cam;
    protected Viewport gamePort;
    protected Vector3 mouse;
    protected GameStateManager gsm;
    protected Player player;


    protected State (GameStateManager gsm, Player player){
        this.gsm = gsm;
        cam = new OrthographicCamera(Gdx.graphics.getWidth()/PiggyCoins.PPM, Gdx.graphics.getHeight()/PiggyCoins.PPM);
        //cam = new OrthographicCamera();
        //cam.update();
        gamePort = new StretchViewport(Gdx.graphics.getWidth()/PiggyCoins.PPM, Gdx.graphics.getHeight()/PiggyCoins.PPM, cam);
        //cam.position.set(cam.viewportWidth, cam.viewportHeight);
        //gamePort = new ScreenViewport(cam);
        mouse = new Vector3();
        this.player = player;
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
