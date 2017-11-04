package com.mygdx.client;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MascotBattles extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ClientNetworkThread nt;
	int renderCounter;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		nt = new ClientNetworkThread();
		nt.start();
		renderCounter = 0;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (renderCounter % 10 == 0 && renderCounter != 0) {
			System.out.println(nt.getDataFromServer());
		}
		renderCounter++;
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		nt.interrupt();
	}
}
