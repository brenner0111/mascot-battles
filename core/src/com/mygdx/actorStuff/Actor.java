package com.mygdx.actorStuff;

import java.util.Collection;
import java.util.NoSuchElementException;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.actorStuff.states.State;

public abstract class Actor {
	protected float posx;
	protected float posy;
	protected float hitPoints;
	protected float movementSpeed;
	protected float angle;
	protected Sprite sprite;
	protected State curState;
	protected Collection<State> states;
	
	public Actor(float posx, float posy, float hitpoints, float movementSpeed, Sprite sprite, Collection<State> states) {
		this.posx = posx;
		this.posy = posy;
		this.hitPoints = hitpoints;
		this.movementSpeed = movementSpeed;
		this.sprite = sprite;
		this.states = states;
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

		try {
			Class tos = Class.forName(typeOfState);
			for (State state : states) {
				if (state.getClass() == tos) 
					return state;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public State getCurState() {
		return curState;
	}

	public void setCurState(State curState) {
		this.curState = curState;
	}
	
	
}
