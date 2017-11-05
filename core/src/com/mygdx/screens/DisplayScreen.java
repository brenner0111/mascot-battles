package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DisplayScreen{
	
	private Texture startScreen;
	private Texture loadingScreen;
	private Texture gameScreen;
	private Texture victoryScreen;
	private Texture defeatScreen;
	private Texture greenGrass;
	private Texture greyGrass;
	private Texture spin;
	private Texture greySpin;
	private Texture spit;
	private Texture greySpit;
	private Texture ram;
	private Texture greyRam;
	private boolean spitIsReady;
	private boolean ramIsReady;
	private boolean spinIsReady;
	private boolean eatGrassIsReady;
	
	public DisplayScreen() {
		startScreen = new Texture("menu/MenuScreen.png");
		loadingScreen = new Texture("menu/loadingScreen.png");
		gameScreen = new Texture("world/Map.png");
		victoryScreen = new Texture("menu/victoryScreen.png");
		defeatScreen = new Texture("menu/defeatedScreen.png");
		
		greenGrass = new Texture("abilities/grass.png");
		greyGrass = new Texture("abilities/greyGrass.png");
		spin = new Texture("abilities/spin.png");
		greySpin = new Texture("abilities/greySpin.png");
		spit = new Texture("abilities/spit.png");
		greySpit = new Texture("abilities/greySpit.png");
		ram = new Texture("abilities/ram.png");
		greyRam = new Texture("abilities/greyRam.png");
		
		spitIsReady = true;
		ramIsReady = true;
		spinIsReady = true;
		eatGrassIsReady = true;
	}

	public void drawStartScreen(SpriteBatch batch) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(startScreen, 0, 0);
		batch.end();
	}
	
	public void drawLoadingScreen(SpriteBatch batch) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		batch.draw(loadingScreen, 0, 0);
		batch.end();
	}
	
	public void drawGameScreen(SpriteBatch batch) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(gameScreen, 0, 0);
		
		if(spitIsReady)
			batch.draw(spit, 135, 0);
		else
			batch.draw(greySpit, 135, 0);
		
		if (ramIsReady)
			batch.draw(ram, 405, 0);
		else
			batch.draw(greyRam, 405, 0);
		
		if (spinIsReady)
			batch.draw(spin, 675, 0);
		else
			batch.draw(greySpin, 675, 0);
		
		if (eatGrassIsReady)
			batch.draw(greenGrass, 945, 0);
		else
			batch.draw(greyGrass, 945, 0);
		
		batch.end();
	}
	public void drawVictoryScreen(SpriteBatch batch) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(victoryScreen, 0, 0);
		batch.end();
	}
	
	public void drawDefeatScreen(SpriteBatch batch) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(defeatScreen, 0, 0);
		batch.end();
	}
	public Sprite createSprite(Texture texture, int initX, int initY) {
		return new Sprite(texture);
	}


}
