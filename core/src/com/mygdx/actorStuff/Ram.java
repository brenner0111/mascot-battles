package com.mygdx.actorStuff;

import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.math.Polygon;
import com.mygdx.actorStuff.states.State;
import com.mygdx.actorStuff.states.ramStates.StateRamDamaged;
import com.mygdx.actorStuff.states.ramStates.StateRamDead;
import com.mygdx.actorStuff.states.ramStates.StateRamHeal;
import com.mygdx.actorStuff.states.ramStates.StateRamIdle;
import com.mygdx.actorStuff.states.ramStates.StateRamRam;
import com.mygdx.actorStuff.states.ramStates.StateRamRamCharge;
import com.mygdx.actorStuff.states.ramStates.StateRamSpin;
import com.mygdx.actorStuff.states.ramStates.StateRamSpinCharge;
import com.mygdx.actorStuff.states.ramStates.StateRamSpit;

public class Ram extends Actor{

	public Ram(float posx, float posy) {
		
		super(posx, posy, 100, 1, 4, new Polygon(new float[] {-.5f,-.5f,.5f,-.5f,.5f,.5f,-.5f,.5f}), new ArrayList<State>());
		states.add(new StateRamIdle(this));
		states.add(new StateRamDamaged(this));
		states.add(new StateRamHeal(this));
		states.add(new StateRamDead(this));
		states.add(new StateRamRam(this));
		states.add(new StateRamRamCharge(this));
		states.add(new StateRamSpin(this));
		states.add(new StateRamSpinCharge(this));
		states.add(new StateRamSpit(this));
		curState = getState("StateRamIdle");
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
