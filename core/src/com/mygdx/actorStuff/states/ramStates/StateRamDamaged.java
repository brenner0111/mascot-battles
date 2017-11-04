package com.mygdx.actorStuff.states.ramStates;

import com.badlogic.gdx.Gdx;
import com.mygdx.actorStuff.Actor;
import com.mygdx.actorStuff.Collidable;
import com.mygdx.actorStuff.states.State;

public class StateRamDamaged extends State{

	public StateRamDamaged(Actor context) {
		super(context);
	}

	@Override
	public Object useAbility(int abilityNum, Object extraStuff) {
		return null;
	}

	@Override
	public void move(float angle) {
		context.setPosx((float) (context.getPosx() + (Math.cos(angle) * context.getMovementSpeed() * Gdx.graphics.getDeltaTime())));
		context.setPosy((float) (context.getPosy() + (Math.sin(angle) * context.getMovementSpeed() * Gdx.graphics.getDeltaTime())));
		context.setAngle(angle);
		stateDeltaTime += Gdx.graphics.getDeltaTime();
		if (stateDeltaTime < 2)	//how long to stay in this state
			return;
		context.setCurState(context.getState("StateRamIdle"));
		stateDeltaTime = 0;
	}

	@Override
	public Object standard() {
		stateDeltaTime += Gdx.graphics.getDeltaTime();
		if (stateDeltaTime < 2)	//how long to stay in this state
			return null;
		context.setCurState(context.getState("StateRamIdle"));
		stateDeltaTime = 0;
		return null;
	}

	@Override
	public boolean collision(Collidable[] collisions) {
		System.out.println("Hit but currently invulnerable");
		return false;
	}

}
