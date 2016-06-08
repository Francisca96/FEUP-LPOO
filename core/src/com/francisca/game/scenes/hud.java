package com.francisca.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.francisca.game.PiggyCoins;

/**
 * Created by ZeCarlos on 06/06/2016.
 */
public class Hud {

    public static final String[] LIVES_IMAGES = {"NoLives.png", "1Live.png", "2Lives.png", "3Lives.png"};
    public Stage stage;

    private Viewport viewport;

    private Integer score;
    private Integer lives;

    Label scoreLabel;
    Label scoreName;
    Image livesImage;
    Image gameOverImage;

    public Hud(SpriteBatch sb, int lives)
    {
        this.score = 0;
        this.lives = lives;

        this.viewport = new FitViewport(PiggyCoins.WIDTH, PiggyCoins.HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        this.scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        this.scoreLabel.setFontScale(2);
        this.scoreName = new Label("Score :", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        this.scoreName.setFontScale(2);
        this.livesImage = new Image(new Texture("3Lives.png"));
        this.gameOverImage = new Image(new Texture("gameover.png"));
        this.livesImage.setScaling(Scaling.fit);
        Label empty = new Label(" ", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.add(livesImage).left().padTop(10);
        table.add(empty).expandX();
        table.add(scoreName).right().padTop(10);
        table.add(scoreLabel).right().padTop(10);

        table.row();
        this.gameOverImage.setVisible(false);
        table.add(empty).width(20);
        table.add(gameOverImage).expand().right();

        stage.addActor(table);
    }

    public void updateLives()
    {
        Texture texture = new Texture(LIVES_IMAGES[lives]);
        livesImage.setDrawable(new SpriteDrawable(new Sprite(texture)));
    }

    public void gotHit()
    {
        lives--;
        if(lives <= 0) {
            lives = 0;
            gameOverImage.setVisible(true);
        }
        updateLives();
    }

    public int getLives()
    {
        return lives;
    }
    public Viewport getViewport() {
        return viewport;
    }

    public Integer getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
        scoreLabel.setText(String.format("%06d", score));
    }

    public void dispose(){
        stage.dispose();
    }
}
