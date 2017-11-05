package com.mygdx.actorStuff;

public abstract class Projectile implements Collidable{
	public float posx;
	public float posy;
	public float lifetime;
	public float vx;
	public float vy;
	
	public Projectile(float posx, float posy, float vx, float vy, float lifetime) {
		this.posx = posx;
		this.posy = posy;
		this.vx = vx;
		this.vy = vy;
		this.lifetime = lifetime;
	}
}
