package com.mygdx.actorStuff;

import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.math.Polygon;
import com.mygdx.actorStuff.states.State;
import com.mygdx.actorStuff.states.ramStates.StateRamIdle;

public class Ram extends Actor{

	public Ram(float posx, float posy) {
		
		super(posx, posy, 100, 1, 4, new Polygon(new float[] {-.5f,-.5f,.5f,-.5f,.5f,.5f,-.5f,.5f}), new ArrayList<State>());
		states.add(new StateRamIdle(this));
	}

	@Override
	public float getDamage() {
		return 0;
	}

	@Override
	public int getTeam() {
		return 1;
	}

}
