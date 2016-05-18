package com.francisca.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.francisca.game.PiggyCoins;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = PiggyCoins.WIDTH;
		config.height = PiggyCoins.HEIGHT;
		config.title = PiggyCoins.TITLE;
		new LwjglApplication(new PiggyCoins(), config);
	}
}
