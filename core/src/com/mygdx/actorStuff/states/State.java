package com.mygdx.actorStuff.states;

public abstract class State {
	
	protected float stateDeltaTime;
	
	public abstract Object useAbility(int abilityNum, Object extraStuff);
	
	public abstract void move(float angle);
	
	public abstract Object standard();
	
}
