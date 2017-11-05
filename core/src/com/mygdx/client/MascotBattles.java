package com.mygdx.client;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.screens.DisplayScreen;

public class MascotBattles extends ApplicationAdapter {
	private final float GAME_WORLD_WIDTH = 1080;
	private final float GAME_WORLD_HEIGHT = 720;
	private boolean displayStartScreen;
	private boolean displayGameScreen;
	private boolean displayLoadingScreen;
	private boolean displayVictoryScreen;
	private boolean displayDefeatScreen;
	private DisplayScreen displayScreen;

	
	private SpriteBatch batch;
	private ClientNetworkThread nt;
	private int renderCounter;
	
	private OrthographicCamera camera;
	private Viewport viewport;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		displayScreen = new DisplayScreen();
		displayStartScreen = true;
		displayGameScreen = false;
		displayVictoryScreen = false;
		displayDefeatScreen = false;
		displayLoadingScreen = false;
		camera = new OrthographicCamera();
		viewport =  new StretchViewport(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, camera);
		viewport.apply();
		camera.position.set(GAME_WORLD_WIDTH/2, GAME_WORLD_HEIGHT/2, 0);

		nt = new ClientNetworkThread();
		nt.start();
	}
	
	@Override
	public void resize(int width, int height) {
	    viewport.update(width, height);
	    camera.position.set(GAME_WORLD_WIDTH/2, GAME_WORLD_HEIGHT/2, 0);
	}
	
	@Override
	public void render () {
		renderLogic();
		getMouseCoordsForTesting();
		
		if (renderCounter % 10 == 0 && renderCounter != 0) {
			//System.out.println(nt.getDataFromServer());
		}
		renderCounter++;

	}
	
	private void renderLogic() {
		updateScreenFlags();
		
		if (displayStartScreen) {
			displayScreen.drawStartScreen(batch);
		}
		else if (displayLoadingScreen) {
			displayScreen.drawLoadingScreen(batch);
		}
		else if (displayGameScreen) {
			displayScreen.drawGameScreen(batch);
		}
		else if (displayVictoryScreen) {
			displayScreen.drawVictoryScreen(batch);
		}
		else if (displayDefeatScreen) {
			displayScreen.drawDefeatScreen(batch);
		}
		else {
			System.out.println("Render Logic error");
		}
	}
	private void getMouseCoordsForTesting() {
		if (Gdx.input.justTouched()) {
			 Vector3 tmpCoords = new Vector3(Gdx.input.getX(),Gdx.input.getY(), 0);
	         camera.unproject(tmpCoords);
	         System.out.println("World ClickXY: " + tmpCoords);
		}
	}
	private void updateScreenFlags() {
		
		if (displayStartScreen) {
			if(Gdx.input.justTouched()) {
				displayStartScreen = false;
				displayLoadingScreen = true;
			}
		}
		else if (displayLoadingScreen) {
			if(Gdx.input.justTouched()) {
				displayLoadingScreen = false;
				displayGameScreen = true;
			}
			
		}
		else if (displayGameScreen) {
			if(Gdx.input.justTouched()) {
				displayGameScreen = false;
				displayVictoryScreen = true;
			}
		}
		else if (displayVictoryScreen) {
			if(Gdx.input.justTouched()) {
				displayVictoryScreen = false;
				displayDefeatScreen = true;
			}
		}
		else if (displayDefeatScreen) {
			if(Gdx.input.justTouched()) {
				displayDefeatScreen = false;
				displayGameScreen = true;
			}
		}
		else {
			System.out.println("Updateing Screen flags error");
		}
	}
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
		nt.interrupt();
	}
}
