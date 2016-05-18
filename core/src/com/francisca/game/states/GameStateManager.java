package com.francisca.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.francisca.game.states.*;

import java.util.Stack;

/**
 * Created by Francisca on 14/05/16.
 */
public class GameStateManager {

    private Stack<com.francisca.game.states.State> states;

    public GameStateManager(){
        states =  new Stack<com.francisca.game.states.State>();
    }

    public void push(com.francisca.game.states.State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(com.francisca.game.states.State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
