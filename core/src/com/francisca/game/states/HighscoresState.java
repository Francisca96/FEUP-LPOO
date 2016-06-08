package com.francisca.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.francisca.game.PiggyCoins;
import com.francisca.game.Player;

/**
 * Created by Francisca on 05/06/16.
 */
public class HighscoresState extends State{
    public static final int WIDTH_NAME_HIGHSCORE = 20;
    private Texture background;
    private Texture title;

    private Stage stage;
    private SpriteBatch spriteBatch;

    private Button backBtn;
    private Sprite backBtnTexture;

    private Label highscore1;
    private Label highscore2;
    private Label highscore3;
    private Label highscore4;
    private Label highscore5;
    private Label highscore6;
    private Label highscore7;
    private Label highscore8;
    private Label highscore9;
    private Label highscore10;
    private Label namehighscore1;
    private Label namehighscore2;
    private Label namehighscore3;
    private Label namehighscore4;
    private Label namehighscore5;
    private Label namehighscore6;
    private Label namehighscore7;
    private Label namehighscore8;
    private Label namehighscore9;
    private Label namehighscore10;

    public HighscoresState (GameStateManager gsm, PiggyCoins game) {
        super(gsm, game);
        Gdx.input.setCatchBackKey(true);
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
                    gsm.set(new MenuState(gsm, game));
                }
            }
        });

        highscore1 = new Label(String.format("%06d", game.getHighscore().get(0)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        highscore2 = new Label(String.format("%06d", game.getHighscore().get(1)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        highscore3 = new Label(String.format("%06d", game.getHighscore().get(2)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        highscore4 = new Label(String.format("%06d", game.getHighscore().get(3)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        highscore5 = new Label(String.format("%06d", game.getHighscore().get(4)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        highscore6 = new Label(String.format("%06d", game.getHighscore().get(5)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        highscore7 = new Label(String.format("%06d", game.getHighscore().get(6)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        highscore8 = new Label(String.format("%06d", game.getHighscore().get(7)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        highscore9 = new Label(String.format("%06d", game.getHighscore().get(8)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        highscore10 = new Label(String.format("%06d", game.getHighscore().get(9)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        namehighscore1 = new Label("1st", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        namehighscore2 = new Label("2nd", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        namehighscore3 = new Label("3rd", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        namehighscore4 = new Label("4th", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        namehighscore5 = new Label("5th", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        namehighscore6 = new Label("6th", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        namehighscore7 = new Label("7th", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        namehighscore8 = new Label("8th", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        namehighscore9 = new Label("9th", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        namehighscore10 = new Label("10th", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        highscore1.setFontScale(1.8f);
        highscore2.setFontScale(1.8f);
        highscore3.setFontScale(1.8f);
        highscore4.setFontScale(1.8f);
        highscore5.setFontScale(1.8f);
        highscore6.setFontScale(1.8f);
        highscore7.setFontScale(1.8f);
        highscore8.setFontScale(1.8f);
        highscore9.setFontScale(1.8f);
        highscore10.setFontScale(1.8f);
        namehighscore1.setFontScale(1.8f);
        namehighscore2.setFontScale(1.8f);
        namehighscore3.setFontScale(1.8f);
        namehighscore4.setFontScale(1.8f);
        namehighscore5.setFontScale(1.8f);
        namehighscore6.setFontScale(1.8f);
        namehighscore7.setFontScale(1.8f);
        namehighscore8.setFontScale(1.8f);
        namehighscore9.setFontScale(1.8f);
        namehighscore10.setFontScale(1.8f);


        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.padTop(20);
        table.add(namehighscore1);
        table.add(highscore1);
        table.row();
        table.add(namehighscore2);
        table.add(highscore2);
        table.row();
        table.add(namehighscore3);
        table.add(highscore3);
        table.row();
        table.add(namehighscore4);
        table.add(highscore4);
        table.row();
        table.add(namehighscore5);
        table.add(highscore5);
        table.row();
        table.add(namehighscore6);
        table.add(highscore6);
        table.row();
        table.add(namehighscore7);
        table.add(highscore7);
        table.row();
        table.add(namehighscore8);
        table.add(highscore8);
        table.row();
        table.add(namehighscore9);
        table.add(highscore9);
        table.row();
        table.add(namehighscore10);
        table.add(highscore10);
        table.row();

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
        System.out.println("Highscores State Disposed");
    }
}

