package com.mygdx.client;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
	private float mouseX;
	private float mouseY;

	private Sprite playerSprite;
	private Sprite devilSprite;
	private Sprite beams;
	

	private Texture img;

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
		img = new Texture("Characters/Ramcharacter.png");
		displayGameScreen = false;
		displayVictoryScreen = false;
		displayDefeatScreen = false;
		displayLoadingScreen = false;
		mouseX = 0.0f;
		mouseY = 0.0f;
		camera = new OrthographicCamera();
		viewport =  new StretchViewport(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, camera);
		viewport.apply();
		camera.position.set(GAME_WORLD_WIDTH/2, GAME_WORLD_HEIGHT/2, 0);
		playerSprite = new Sprite(new Texture("Characters/Ramcharacter.png"));
		devilSprite = new Sprite(new Texture("Characters/dukebluedevilcharacter.png"));
		beams = new Sprite(new Texture("Attacks/beams.png"));
		
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

		getMouseCoords();
		renderLogic();
    batch.begin();

		String tmp = nt.fromServer.substring(0, nt.fromServer.length());

		String[] strs = tmp.split("\\s+");
		if (strs.length > 1) {
			for (int i = 0; i < strs.length; i++) {
				String str = strs[i];
				if (str.equals("p")) {
					float angle = Float.parseFloat(strs[i + 3]);
					boolean isNotRunning = nt.isNotRunning;
					
					if (angle < (3 * 3.13 / 4) && angle > (3.15 / 4)) {
						//facing up
						if (isNotRunning)
							playerSprite.setRegion(0, 21, 15, 21);
						else
							playerSprite.setRegion((int)(15 * (System.currentTimeMillis()/100 % 3)), 21, 15, 21);
					} else if (angle >= (3 * 3.13 / 4) || angle <= (-3 * 3.13 / 4)) {
						//facing left
						if (isNotRunning)
							playerSprite.setRegion(0, 42, 15, 21);
						else
							playerSprite.setRegion((int)(15 * (System.currentTimeMillis()/100 % 3)), 42, 15, 21);
					} else if (angle > (-3 * 3.13 / 4) && angle < (-3.15 / 4)) {
						//facing down
						if (isNotRunning)
							playerSprite.setRegion(0, 0, 15, 21);
						else
							playerSprite.setRegion((int)(15 * (System.currentTimeMillis()/100 % 3)), 0, 15, 21);
					} else if (angle >= (-3.15 / 4) || angle < (3.15 / 4)) {
						//facing right
						if (isNotRunning)
							playerSprite.setRegion(0, 63, 15, 21);
						else
							playerSprite.setRegion((int)(15 * (System.currentTimeMillis()/100 % 3)), 63, 15, 21);
					}
					batch.draw(playerSprite,Float.parseFloat(strs[i + 1]) / 4, Float.parseFloat(strs[i + 2]) / 4);
					i += 3;
				} else if (str.equals("e")) {
					float angle = Float.parseFloat(strs[i + 3]);
					boolean isNotRunning = nt.isNotRunning;
					
					if (angle < (3 * 3.13 / 4) && angle > (3.15 / 4)) {
						//facing up
//						if (isNotRunning)
//							devilSprite.setRegion(0, 21, 15, 21);
//						else
						devilSprite.setRegion((int)(15 * (System.currentTimeMillis()/100 % 3)), 21, 15, 21);
					} else if (angle >= (3 * 3.13 / 4) || angle <= (-3 * 3.13 / 4)) {
						//facing left
//						if (isNotRunning)
//							devilSprite.setRegion(0, 42, 15, 21);
//						else
						devilSprite.setRegion((int)(15 * (System.currentTimeMillis()/100 % 3)), 42, 15, 21);
					} else if (angle > (-3 * 3.13 / 4) && angle < (-3.15 / 4)) {
						//facing down
//						if (isNotRunning)
//							devilSprite.setRegion(0, 0, 15, 21);
//						else
						devilSprite.setRegion((int)(15 * (System.currentTimeMillis()/100 % 3)), 0, 15, 21);
					} else if (angle >= (-3.15 / 4) || angle < (3.15 / 4)) {
						//facing right
//						if (isNotRunning)
//							devilSprite.setRegion(0, 63, 15, 21);
//						else
						devilSprite.setRegion((int)(15 * (System.currentTimeMillis()/100 % 3)), 63, 15, 21);
					}
					
					batch.draw(devilSprite,Float.parseFloat(strs[i + 1]) / 4, Float.parseFloat(strs[i + 2]) / 4,45,63);
					i += 3;
				} else if (str.equals("s")) {
					beams.setRegion(116, 6, 18, 18);
					batch.draw(beams, Float.parseFloat(strs[i + 1]) / 4f, Float.parseFloat(strs[i+2]) / 4f);
					i += 2;
				}
			}
		}
		else
			batch.draw(img, 0, 0);
		batch.end();

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
	private void getMouseCoords() {
		if (Gdx.input.justTouched()) {
			Vector3 tmpCoords = new Vector3(Gdx.input.getX(),Gdx.input.getY(), 0);
			camera.unproject(tmpCoords);
			System.out.println("World ClickXY: " + tmpCoords);
			mouseX = tmpCoords.x;
			mouseY = tmpCoords.y;
		}
	}
	private void updateScreenFlags() {

		if (displayStartScreen) {
			if ((mouseX > 408f && mouseX < 662f) && (mouseY > 223f && mouseY < 280f)) {
				System.exit(0);
			}
			if((mouseX > 408f && mouseX < 662f) && (mouseY > 354 && mouseY < 410) ||
					(mouseX > 408f && mouseX < 662f) && (mouseY > 286f && mouseY < 345f)) {

				displayStartScreen = false;
				displayLoadingScreen = true;
			}
		}
		else if (displayLoadingScreen) {
			/*if(Gdx.input.justTouched()) {
				displayLoadingScreen = false;
				displayGameScreen = true;
			}*/
			try {
				TimeUnit.SECONDS.sleep(3);
				displayLoadingScreen = false;
				displayGameScreen = true;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else if (displayGameScreen) {
			if(Gdx.input.justTouched()) {
				displayGameScreen = true;
				//displayVictoryScreen = true;
			}
		}
		else if (displayVictoryScreen) {
			/*if(Gdx.input.justTouched()) {
				displayVictoryScreen = false;
				displayDefeatScreen = true;
			}*/
			if ((mouseX > 320f && mouseX < 550f) && (mouseY > 37f && mouseY < 74f)) {
				displayVictoryScreen = false;
				displayDefeatScreen = true;
			}
			if ((mouseX > 608f && mouseX < 705f) && (mouseY > 30f && mouseY < 75f)) {
				System.exit(0);
			}
		}
		else if (displayDefeatScreen) {
			/*if(Gdx.input.justTouched()) {
				displayDefeatScreen = false;
				displayGameScreen = true;
			}*/
			if ((mouseX > 355f && mouseX < 575f) && (mouseY > 52f && mouseY < 88f)) {
				displayDefeatScreen = false;
				displayGameScreen = true;
			}
			if ((mouseX > 630f && mouseX < 724f) && (mouseY > 50f && mouseY < 88f)) {
				System.exit(0);
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
		playerSprite.getTexture().dispose();
		devilSprite.getTexture().dispose();
		nt.interrupt();
	}
}
