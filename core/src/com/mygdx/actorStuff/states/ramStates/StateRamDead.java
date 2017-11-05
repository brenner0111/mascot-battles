package com.mygdx.actorStuff.states.ramStates;

import com.badlogic.gdx.Gdx;
import com.mygdx.actorStuff.Actor;
import com.mygdx.actorStuff.Collidable;
import com.mygdx.actorStuff.states.State;

public class StateRamDead extends State{

	public StateRamDead(Actor context) {
		super(context);
	}

	@Override
	public Object useAbility(int abilityNum, Object extraStuff) {
		move(context.getAngle());
		return null;
	}

	@Override
	public void move(float angle) {
		System.out.println("I am dead");
	}

	@Override
	public Object standard() {
		System.out.println("I am dead");
		return null;
	}

	@Override
	public boolean collision(Collidable[] collisions) {
		return true;
	}

}
