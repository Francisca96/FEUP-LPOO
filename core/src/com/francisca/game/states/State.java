package com.francisca.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.francisca.game.Player;

/**
 * Created by Francisca on 14/05/16.
 */

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;
    protected Player player;

    protected State (GameStateManager gsm, Player player){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
        this.player = player;
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
