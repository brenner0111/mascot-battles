package com.mygdx.actorStuff.states.devilStates;

import com.badlogic.gdx.Gdx;
import com.mygdx.actorStuff.Actor;
import com.mygdx.actorStuff.Collidable;
import com.mygdx.actorStuff.ProjectileDevil;
import com.mygdx.actorStuff.states.State;
import com.mygdx.server.MascotServer;

public class StateDevilShoot360 extends State{

	public StateDevilShoot360(Actor context) {
		super(context);
	}

	@Override
	public Object useAbility(int abilityNum, Object extraStuff) {
		return null;
	}

	@Override
	public void move(float angle) {
		stateDeltaTime += MascotServer.deltatime;
		if (stateDeltaTime < .25)	//how long to stay in this state
			return;
		System.out.println("I am shooting 360");
		for (int i = 0; i < 25; i++)
			MascotServer.projectiles.add(new ProjectileDevil(context.getPosx(),context.getPosy()
					,(float)(Math.random() * 6) * (Math.random() > .5 ? -1 : 1) + (float) Math.cos(-context.getAngle()) * context.getMovementSpeed()
					,(float)( Math.random() * 6) * (Math.random() > .5 ? -1 : 1) + (float) Math.sin(-context.getAngle()) * context.getMovementSpeed()));
		context.setAbilityTime(0);
		context.setCurState(context.getState("StateDevilIdle"));
		stateDeltaTime = 0;
	}

	@Override
	public Object standard() {
		stateDeltaTime += MascotServer.deltatime;
		if (stateDeltaTime < .25)	//how long to stay in this state
			return null;
		System.out.println("I am shooting 360");
		for (int i = 0; i < 25; i++)
			MascotServer.projectiles.add(new ProjectileDevil(context.getPosx(),context.getPosy()
					,(float)(Math.random() * 6) * (Math.random() > .5 ? -1 : 1) + (float) Math.cos(-context.getAngle()) * context.getMovementSpeed()
					,(float)( Math.random() * 6) * (Math.random() > .5 ? -1 : 1) + (float) Math.sin(-context.getAngle()) * context.getMovementSpeed()));
		context.setAbilityTime(0);
		context.setCurState(context.getState("StateDevilIdle"));
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
