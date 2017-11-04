package com.mygdx.actorStuff;


import java.util.Collection;
import java.util.NoSuchElementException;


import com.badlogic.gdx.math.Polygon;
import com.mygdx.actorStuff.states.State;


public abstract class Actor implements Collidable{
	protected float posx;
	protected float posy;
	protected float hitPoints;
	protected float movementSpeed;
	protected float angle;
	protected Polygon hitbox;
	protected State curState;
	protected Collection<State> states;
	protected long[] abilityCooldowns;
	
	public Actor(float posx, float posy, float hitpoints, float movementSpeed, int numAbilities, Polygon hitbox, Collection<State> states) {
		this.posx = posx;
		this.posy = posy;
		this.hitPoints = hitpoints;
		this.movementSpeed = movementSpeed;
		this.states = states;
		this.hitbox = hitbox;
		abilityCooldowns = new long[numAbilities];
	}
	
	public long getAbilityTime(int ability) {
		return abilityCooldowns[ability];
	}
	
	public void setAbilityTime(int ability) {
		abilityCooldowns[ability] = System.currentTimeMillis();
	}
	
	public Object useAbility(int abilityNum, Object extraStuff) {
		return curState.useAbility(abilityNum, extraStuff);
	}
	
	public void move(float angle) {
		curState.move(angle);
	}
	
	public Object standard() {
		return curState.standard();
	}
	
	public boolean collision(Collidable [] collisions) {
		return curState.collision(collisions);
	}
	
	public float getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(float hitPoints) {
		this.hitPoints = hitPoints;
	}

	public float getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(float movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public State getState(String typeOfState) {

		for (State state : states) {
			if (state.getClass().getSimpleName().equals(typeOfState)) 
				return state;
		}

		throw new NoSuchElementException("A state of " + typeOfState + " could not be found") ;
	}

	public float getPosx() {
		return posx;
	}

	public void setPosx(float posx) {
		this.posx = posx;
	}

	public float getPosy() {
		return posy;
	}

	public void setPosy(float posy) {
		this.posy = posy;
	}

	public Polygon getHitbox() {
		return hitbox;
	}

	public void setHitbox(Polygon hitbox) {
		this.hitbox = hitbox;
	}

	public State getCurState() {
		return curState;
	}

	public void setCurState(State curState) {
		this.curState = curState;
	}
	
	
}
