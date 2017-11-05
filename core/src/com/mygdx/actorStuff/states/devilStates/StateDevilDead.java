package com.mygdx.actorStuff.states.devilStates;

import com.badlogic.gdx.Gdx;
import com.mygdx.actorStuff.Actor;
import com.mygdx.actorStuff.Collidable;
import com.mygdx.actorStuff.states.State;

public class StateDevilDead extends State{

	public StateDevilDead(Actor context) {
		super(context);
	}

	@Override
	public Object useAbility(int abilityNum, Object extraStuff) {
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
