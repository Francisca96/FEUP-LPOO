package com.francisca.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.francisca.game.PiggyCoins;
import com.francisca.game.Player;
import com.francisca.game.sprites.PigType;

/**
 * Created by Francisca on 05/06/16.
 */
public class SettingsState extends State{
    private Texture background;
    private Texture title;

    private Stage stage;
    private SpriteBatch spriteBatch;

    private Button backBtn;
    private Sprite backBtnTexture;

    private Button normalPig;
    private Button bluePig;
    private Button greyPig;
    private Button greenPig;
    private Button purplePig;

    public SettingsState (GameStateManager gsm, PiggyCoins game) {
        super(gsm, game);
        Gdx.input.setCatchBackKey(true);
        background = new Texture("bgStates.png");
        title = new Texture("settings.png");


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
                    gsm.set(new MenuState(gsm, game));
                }
            }
        });

        normalPig = new Button(new SpriteDrawable(new Sprite(new Texture("pigNormalButton.png"))));
        bluePig = new Button(new SpriteDrawable(new Sprite(new Texture("pigBlueButton.png"))));
        greyPig = new Button(new SpriteDrawable(new Sprite(new Texture("pigGreyButton.png"))));
        greenPig = new Button(new SpriteDrawable(new Sprite(new Texture("pigGreenButton.png"))));
        purplePig = new Button(new SpriteDrawable(new Sprite(new Texture("pigPurpleButton.png"))));

        normalPig.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(normalPig.isPressed()) {
                    game.getActualPlayer().setActualPig(PigType.NORMAL);
                    gsm.set(new MenuState(gsm, game));
                }
            }
        });

        bluePig.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(bluePig.isPressed()) {
                    game.getActualPlayer().setActualPig(PigType.BLUE);
                    gsm.set(new MenuState(gsm, game));
                }
            }
        });

        greyPig.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(greyPig.isPressed()) {
                    game.getActualPlayer().setActualPig(PigType.GREY);
                    gsm.set(new MenuState(gsm, game));
                }
            }
        });

        greenPig.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(greenPig.isPressed()) {
                    game.getActualPlayer().setActualPig(PigType.GREEN);
                    gsm.set(new MenuState(gsm, game));
                }
            }
        });

        purplePig.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(purplePig.isPressed()) {
                    game.getActualPlayer().setActualPig(PigType.PURPLE);
                    gsm.set(new MenuState(gsm, game));
                }
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.center();

        table.add(normalPig).expandX();
        table.add(bluePig).expandX();
        table.add(greyPig).expandX();
        table.row();
        table.add(greenPig).expandX();
        table.add(purplePig).expandX();

        stage.addActor(table);

        stage.addActor(backBtn);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK))
        {
            gsm.set(new MenuState(gsm, game));
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
        sb.draw(title, 0, 0, PiggyCoins.WIDTH, PiggyCoins.HEIGHT);
        backBtn.draw(sb, 1);
        stage.draw();
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        System.out.println("Settings State Disposed");
    }
}
