package com.mygdx.actorStuff;

public class ProjectileDevil extends Projectile{

	public ProjectileDevil(float posx, float posy, float vx, float vy) {
		super(posx, posy, vx, vy, 4000);
	}

	@Override
	public float getDamage() {
		
		return 20;
	}

	@Override
	public int getTeam() {

		return 2;
	}

}
