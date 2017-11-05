package com.mygdx.actorStuff.states.devilStates;

import com.badlogic.gdx.Gdx;
import com.mygdx.actorStuff.Actor;
import com.mygdx.actorStuff.Collidable;
import com.mygdx.actorStuff.states.State;
import com.mygdx.server.MascotServer;

public class StateDevilIdle extends State{

	public StateDevilIdle(Actor context) {
		super(context);
	}

	@Override
	public Object useAbility(int abilityNum, Object extraStuff) {

		switch (abilityNum) {
			//shoot 360 ability
			case 0:
				if (System.currentTimeMillis() - context.getAbilityTime(abilityNum) < 1000) //ability cooldown
					break;
				context.setCurState(context.getState("StateDevilShoot360"));
				return null;
			//shoot at player ability
			case 1:
				if (System.currentTimeMillis() - context.getAbilityTime(abilityNum) < 5000) //ability cooldown
					break;
				context.setCurState(context.getState("StateDevilShootPlayer"));
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
		stateDeltaTime += MascotServer.deltatime;
		if (stateDeltaTime > 5000)
			stateDeltaTime = 0;
	}

	@Override
	public Object standard() {

		stateDeltaTime += MascotServer.deltatime;
		if (stateDeltaTime > 5000)
			stateDeltaTime = 0;
		return null;
	}

	@Override
	public boolean collision(Collidable[] collisions) {		
		
		float tmp = 0;
		int team = 2;
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
				maxDamage = (tmp > maxDamage) ? tmp : maxDamage;
				break;
			default:
				break;
			}
		}
		context.setHitPoints(context.getHitPoints() - maxDamage);

		
		if (context.getHitPoints() <= 0)
			context.setCurState(context.getState("StateDevilDead"));
		
		return hitWall;
	}

}
