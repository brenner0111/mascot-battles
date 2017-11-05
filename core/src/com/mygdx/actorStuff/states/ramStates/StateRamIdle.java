package com.mygdx.actorStuff.states.ramStates;

import com.badlogic.gdx.Gdx;
import com.mygdx.actorStuff.Actor;
import com.mygdx.actorStuff.Collidable;
import com.mygdx.actorStuff.states.State;
import com.mygdx.server.MascotServer;

public class StateRamIdle extends State{

	public StateRamIdle(Actor context) {
		super(context);
	}

	@Override
	public Object useAbility(int abilityNum, Object extraStuff) {

		switch (abilityNum) {
			//spit ability
			case 0:
				if (System.currentTimeMillis() - context.getAbilityTime(abilityNum) < 1000) //ability cooldown
					break;
				context.setCurState(context.getState("StateRamSpit"));
				return null;
			//ram ability
			case 1:
				if (System.currentTimeMillis() - context.getAbilityTime(abilityNum) < 5000) //ability cooldown
					break;
				context.setCurState(context.getState("StateRamRamCharge"));
				return null;
			//spin ability
			case 2:
				if (System.currentTimeMillis() - context.getAbilityTime(abilityNum) < 10000) //ability cooldown
					break;
				context.setCurState(context.getState("StateRamSpinCharge"));
				return null;
			//heal ability
			case 3:
				if (System.currentTimeMillis() - context.getAbilityTime(abilityNum) < 20000) //ability cooldown
					break;
				context.setCurState(context.getState("StateRamHeal"));
				return null;
			//do nothing
			default:
				break;
		}
		return null;
	}

	@Override
	public void move(float angle) {
		context.setPosx((float) (context.getPosx() + (Math.cos(angle) * context.getMovementSpeed() * MascotServer.deltatime)));
		context.setPosy((float) (context.getPosy() + (Math.sin(angle) * context.getMovementSpeed() * MascotServer.deltatime)));
		context.setAngle(angle);
		stateDeltaTime = 0;
	}

	@Override
	public Object standard() {

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
		if (maxDamage > 0) {
			context.setCurState(context.getState("StateRamDamaged"));
		}
		
		if (context.getHitPoints() <= 0)
			context.setCurState(context.getState("StateRamDead"));
		
		return hitWall;
	}

}
