package com.francisca.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.francisca.game.states.GameStateManager;
import com.francisca.game.states.MenuState;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;

public class PiggyCoins extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final String TITLE = "PiggyCoins";
	public static final int HIGHSCORE_SIZE = 10;
	public static final float PPM = 100f;

	public static float RATIO_WIDTH = WIDTH;
	public static float RATIO_HEIGHT = HEIGHT;

	private GameStateManager gsm;
	public SpriteBatch batch;
	private Player actualPlayer; /*TODO criar um state em que se cria um player FUTURAS PATCHES*/
	private Array<Integer> highscore;

	private Preferences highscoresFile;

	Music menuSong;

	@Override
	public void create () {
		Box2D.init();
		batch = new SpriteBatch();
		gsm = new GameStateManager();

		menuSong = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		menuSong.setVolume(0.2f);
		menuSong.setLooping(true);
		menuSong.play();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		actualPlayer = new Player("PlayerDefault");

		highscore = new Array<Integer>(10);

		highscoresFile = Gdx.app.getPreferences("Highscores");
		String name = highscoresFile.getString("ScoreTable", "empty");
		if(name.equals("empty"))
		{
			fillHighscoreFile();
		}
		else
		{
			readHighscoreFile();
		}

		gsm.push(new MenuState(gsm, this));
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		gsm.resize(width, height);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		RATIO_WIDTH = ((float)WIDTH)/((float)Gdx.graphics.getWidth());
		RATIO_HEIGHT = ((float)HEIGHT)/((float)Gdx.graphics.getHeight());
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose() {
		menuSong.stop();
		menuSong.dispose();
		writeHighscoreFile();
		super.dispose();
	}

	public Array<Integer> getHighscore() {
		return highscore;
	}

	public void setHighscore(Array<Integer> highscore) {
		this.highscore = highscore;
	}

	public boolean addToHighscores(int score)
	{
		System.out.print("Adicionou :");
		System.out.print(score);
		System.out.print("\n");

		for(int i = 0; i < highscore.size; i++)
		{
			if(highscore.get(i) < score)
			{
				highscore.pop();
				highscore.insert(i, score);
				break;
			}
		}

		System.out.println(highscore.get(0));
		System.out.println(highscore.get(1));
		System.out.println(highscore.get(2));
		System.out.println(highscore.get(3));
		System.out.println(highscore.get(4));
		System.out.println(highscore.get(5));
		System.out.println(highscore.get(6));
		System.out.println(highscore.get(7));
		System.out.println(highscore.get(8));
		System.out.println(highscore.get(9));
		return true;
	}

	public boolean isHighscore(int i)
	{
		if(i > highscore.peek()) //If the new score is higher than the lowest highscore
		{
			System.out.println("E uma highscore");
			return true;
		}
		else {
			System.out.println("Nao e uma highscore");
			return false;
		}
	}

	public void fillHighscoreFile()
	{
		highscoresFile.putString("ScoreTable", "Pronta");
		for(int i = 0; i < HIGHSCORE_SIZE; i++)
		{
			highscoresFile.putInteger("score"+Integer.toString(i), 0);
		}

		highscoresFile.flush();
	}

	public void readHighscoreFile()
	{
		for(int i = 0; i < HIGHSCORE_SIZE; i++)
		{
			highscore.add(highscoresFile.getInteger("score"+Integer.toString(i)));
		}
	}

	public void writeHighscoreFile()
	{
		for(int i = 0; i < HIGHSCORE_SIZE; i++)
		{
			highscoresFile.putInteger("score"+Integer.toString(i), highscore.get(i));
		}

		highscoresFile.flush();
	}

	public Player getActualPlayer() {
		return actualPlayer;
	}

	public Music getMenuSong() {
		return menuSong;
	}

}