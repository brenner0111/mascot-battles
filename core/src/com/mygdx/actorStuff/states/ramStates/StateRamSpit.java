package com.mygdx.actorStuff.states.ramStates;

import com.badlogic.gdx.Gdx;
import com.mygdx.actorStuff.Actor;
import com.mygdx.actorStuff.Collidable;
import com.mygdx.actorStuff.states.State;

public class StateRamSpit extends State{

	public StateRamSpit(Actor context) {
		super(context);
	}

	@Override
	public Object useAbility(int abilityNum, Object extraStuff) {
		return null;
	}

	@Override
	public void move(float angle) {
		stateDeltaTime += Gdx.graphics.getDeltaTime();
		if (stateDeltaTime < .25)	//how long to stay in this state
			return;
		System.out.println("I am spitting");
		context.setAbilityTime(0);
		context.setCurState(context.getState("StateRamIdle"));
		stateDeltaTime = 0;
	}

	@Override
	public Object standard() {
		stateDeltaTime += Gdx.graphics.getDeltaTime();
		if (stateDeltaTime < .25)	//how long to stay in this state
			return null;
		System.out.println("I am spitting");
		context.setAbilityTime(0);
		context.setCurState(context.getState("StateRamIdle"));
		stateDeltaTime = 0;
		return null;
	}

	@Override
	public boolean collision(Collidable[] collisions) {

		float tmp = 0;
		int team = 1;
		float maxDamage = 0;
		boolean hitWall = false;
		for (Collidable collidable : collisions) {
			tmp = collidable.getDamage();
			team = collidable.getTeam();
			switch (team) {
			case 0:
				hitWall = true;
				break;
			case 1:
				break;
			default:
				maxDamage = (tmp > maxDamage) ? tmp : maxDamage;
			}
		}
		context.setHitPoints(context.getHitPoints() - maxDamage);
		if (maxDamage > 0)
			context.setCurState(context.getState("StateRamDamaged"));
		
		if (context.getHitPoints() <= 0)
			context.setCurState(context.getState("StateRamDead"));
		return hitWall;
	}

}
