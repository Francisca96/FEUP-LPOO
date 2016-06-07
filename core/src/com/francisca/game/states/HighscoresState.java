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
 * Created by Francisca on 05/06/16.
 */
public class HighscoresState extends State{
    private Texture background;
    private Texture title;

    private Stage stage;
    private SpriteBatch spriteBatch;

    private Button backBtn;
    private Sprite backBtnTexture;

    public HighscoresState (GameStateManager gsm, PiggyCoins game) {
        super(gsm, game);
        background = new Texture("bgStates.png");
        title = new Texture("highscores.png");

        FitViewport viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        spriteBatch = new SpriteBatch();
        stage = new Stage(viewport, spriteBatch);

        Gdx.input.setInputProcessor(stage);
        createHighscoresButtons();
    }

    public void createHighscoresButtons(){
        backBtnTexture = new Sprite(new Texture("backBtn.png"));
        backBtn = new Button(new SpriteDrawable(backBtnTexture));

        backBtn.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(backBtn.isPressed()) {
                }
            }
        });

        stage.addActor(backBtn);
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
        sb.draw(title, 0, 0, PiggyCoins.WIDTH, PiggyCoins.HEIGHT);
        backBtn.draw(sb, 1);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        System.out.println("Highscores State Disposed");
    }
}

