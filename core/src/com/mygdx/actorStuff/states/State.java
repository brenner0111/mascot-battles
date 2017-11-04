package com.mygdx.actorStuff.states;

import com.mygdx.actorStuff.Actor;
import com.mygdx.actorStuff.Collidable;

public abstract class State {
	
	protected float stateDeltaTime;
	protected Actor context;
	
	public State(Actor context) {
		this.context = context;
		stateDeltaTime = 0;
	}
	
	public abstract Object useAbility(int abilityNum, Object extraStuff);
	
	public abstract void move(float angle);
	
	public abstract Object standard();
	
	public abstract boolean collision(Collidable [] collisions);
}
