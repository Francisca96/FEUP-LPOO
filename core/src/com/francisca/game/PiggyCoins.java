package com.francisca.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.francisca.game.states.GameStateManager;
import com.francisca.game.states.MenuState;

public class PiggyCoins extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final String TITLE = "PiggyCoins";
	public static final int HIGHSCORE_SIZE = 10;


	private GameStateManager gsm;
	private SpriteBatch batch;
	private Player actualPlayer; /*TODO criar um state em que se cria um player*/
	private Array<Player> players;
	private Array<Integer> highscore;


	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);

		/*TODO ler os players dum ficheiro de texto, e depois, caso esteja vazio, comecar o ecra do escolher jogador */
		/*Este é apenas um metodo default*/
		players = new Array<Player>();
		actualPlayer = new Player("PlayerDefault");

		gsm.push(new MenuState(gsm, actualPlayer));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	public Array<Integer> getHighscore() {
		return highscore;
	}

	public void setHighscore(Array<Integer> highscore) {
		this.highscore = highscore;
	}

	public boolean addToHighscores(int i)
	{
		//First Highscore
		if(highscore.size == 1 && highscore.get(0) == 0)
		{
			highscore.insert(0, i);
			return true;
		}
		//When highscore isn't filled
		else if(highscore.size < HIGHSCORE_SIZE)
		{
			highscore.add(i);
			return true;
		}
		//When highscore is filled
		else if(highscore.size == HIGHSCORE_SIZE)
		{
			highscore.add(i);
			highscore.pop();
			return true;
		}
		//When there's an error
		else
		{
			return false;
		}
	}

	public boolean isHighscore(int i)
	{
		if(i > highscore.peek()) //If the new score is higher than the lowest highscore
			return true;
		else
			return false;
	}

}
