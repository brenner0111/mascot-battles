package com.mygdx.actorStuff;

import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.math.Polygon;
import com.mygdx.actorStuff.states.State;
import com.mygdx.actorStuff.states.devilStates.StateDevilDead;
import com.mygdx.actorStuff.states.devilStates.StateDevilIdle;
import com.mygdx.actorStuff.states.devilStates.StateDevilShoot360;
import com.mygdx.actorStuff.states.devilStates.StateDevilShootPlayer;


public class Devil extends Actor{

	public Devil(float posx, float posy) {
		
		super(posx, posy, 10000, .4f, 2, new Polygon(new float[] {-1.5f,-1.5f,1.5f,-1.5f,1.5f,1.5f,-1.5f,1.5f}), new ArrayList<State>());
		states.add(new StateDevilIdle(this));
		states.add(new StateDevilDead(this));
		states.add(new StateDevilShoot360(this));
		states.add(new StateDevilShootPlayer(this));

		curState = getState("StateDevilIdle");
	}

	@Override
	public float getDamage() {
		return 50;
	}

	@Override
	public int getTeam() {
		return 2;
	}
	
	

}
