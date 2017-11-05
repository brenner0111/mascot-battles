package com.mygdx.actorStuff.states.ramStates;

import com.badlogic.gdx.Gdx;
import com.mygdx.actorStuff.Actor;
import com.mygdx.actorStuff.Collidable;
import com.mygdx.actorStuff.states.State;
import com.mygdx.server.MascotServer;

public class StateRamRam extends State{

	public StateRamRam(Actor context) {
		super(context);
	}

	@Override
	public Object useAbility(int abilityNum, Object extraStuff) {
		return null;
	}

	@Override
	public void move(float angle) {
		System.out.println("I am ramming");
		
		stateDeltaTime += MascotServer.deltatime;
		if (stateDeltaTime < 1)	//how long to stay in this state
			return;

		context.setAbilityTime(1);
		context.setCurState(context.getState("StateRamIdle"));
		stateDeltaTime = 0;
	}

	@Override
	public Object standard() {
		System.out.println("I am ramming");
		
		stateDeltaTime += MascotServer.deltatime;
		if (stateDeltaTime < 1)	//how long to stay in this state
			return null;

		context.setAbilityTime(1);
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
//		context.setHitPoints(context.getHitPoints() - maxDamage);
//		if (maxDamage > 0)
//			context.setCurState(context.getState(""));
		return hitWall;
	}

}
