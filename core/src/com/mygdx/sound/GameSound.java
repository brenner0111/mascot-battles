package com.mygdx.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class GameSound {
	private Sound buttonClick;
	private Sound devilDieing;
	
	private Sound devilInPain;
	private Sound eatingGrass;
	private Sound gameMusic;
	private Sound headButt;
	private Sound lazerOne;
	private Sound lazerTwo;
	private Sound lazerThree;
	private Sound defeatedMusic;
	private Sound ramBeingHit;
	private Sound ramDieing;
	private Sound ramInPain;
	
	private Sound retroLosingMusic;
	private Sound spinAttack;
	private Sound spitAttack;
	private Sound victoryMusic;
	
	public GameSound() {
		buttonClick = Gdx.audio.newSound(Gdx.files.internal("Sounds/ButtonClick.wav"));
		devilDieing= Gdx.audio.newSound(Gdx.files.internal("Sounds/devil dieing.wav"));
		devilInPain = Gdx.audio.newSound(Gdx.files.internal("Sounds/devil in pain.wav"));
		eatingGrass= Gdx.audio.newSound(Gdx.files.internal("Sounds/EATING Sound.wav"));
		gameMusic = Gdx.audio.newSound(Gdx.files.internal("Sounds/GameMusic.wav"));
		headButt = Gdx.audio.newSound(Gdx.files.internal("Sounds/Headbutt Sound.wav"));
		lazerOne = Gdx.audio.newSound(Gdx.files.internal("Sounds/Lazer1.wav"));
		lazerTwo = Gdx.audio.newSound(Gdx.files.internal("Sounds/Lazer2.wav"));
		lazerThree = Gdx.audio.newSound(Gdx.files.internal("Sounds/Lazer3.wav"));
		defeatedMusic = Gdx.audio.newSound(Gdx.files.internal("Sounds/losingmusic.wav"));
		ramBeingHit = Gdx.audio.newSound(Gdx.files.internal("Sounds/Ram being hit Sound.wav"));
		ramDieing = Gdx.audio.newSound(Gdx.files.internal("Sounds/Ram dieing Sound.wav"));
		ramInPain= Gdx.audio.newSound(Gdx.files.internal("Sounds/Ram in pain Sound.wav"));
		retroLosingMusic = Gdx.audio.newSound(Gdx.files.internal("Sounds/Retrolosingmusic.wav"));
		
		spinAttack = Gdx.audio.newSound(Gdx.files.internal("Sounds/Spinning attack Sound.wav"));
		spitAttack = Gdx.audio.newSound(Gdx.files.internal("Sounds/Spitting Sound.wav"));
		victoryMusic= Gdx.audio.newSound(Gdx.files.internal("Sounds/winnermusic.wav"));

	}
	
	
	public Sound getButtonClick() {
		return buttonClick;
	}

	public void setButtonClick(Sound buttonClick) {
		this.buttonClick = buttonClick;
	}

	public Sound getDevilDieing() {
		return devilDieing;
	}

	public void setDevilDieing(Sound devilDieing) {
		this.devilDieing = devilDieing;
	}

	public Sound getDevilInPain() {
		return devilInPain;
	}

	public void setDevilInPain(Sound devilInPain) {
		this.devilInPain = devilInPain;
	}

	public Sound getEatingGrass() {
		return eatingGrass;
	}

	public void setEatingGrass(Sound eatingGrass) {
		this.eatingGrass = eatingGrass;
	}

	public Sound getGameMusic() {
		return gameMusic;
	}

	public void setGameMusic(Sound gameMusic) {
		this.gameMusic = gameMusic;
	}

	public Sound getHeadButt() {
		return headButt;
	}

	public void setHeadButt(Sound headButt) {
		this.headButt = headButt;
	}

	public Sound getLazerOne() {
		return lazerOne;
	}

	public void setLazerOne(Sound lazerOne) {
		this.lazerOne = lazerOne;
	}

	public Sound getLazerTwo() {
		return lazerTwo;
	}

	public void setLazerTwo(Sound lazerTwo) {
		this.lazerTwo = lazerTwo;
	}

	public Sound getLazerThree() {
		return lazerThree;
	}

	public void setLazerThree(Sound lazerThree) {
		this.lazerThree = lazerThree;
	}

	public Sound getDefeatedMusic() {
		return defeatedMusic;
	}

	public void setDefeatedMusic(Sound defeatedMusic) {
		this.defeatedMusic = defeatedMusic;
	}

	public Sound getRamBeingHit() {
		return ramBeingHit;
	}

	public void setRamBeingHit(Sound ramBeingHit) {
		this.ramBeingHit = ramBeingHit;
	}

	public Sound getRamDieing() {
		return ramDieing;
	}

	public void setRamDieing(Sound ramDieing) {
		this.ramDieing = ramDieing;
	}

	public Sound getRamInPain() {
		return ramInPain;
	}

	public void setRamInPain(Sound ramInPain) {
		this.ramInPain = ramInPain;
	}

	public Sound getRetroLosingMusic() {
		return retroLosingMusic;
	}

	public void setRetroLosingMusic(Sound retroLosingMusic) {
		this.retroLosingMusic = retroLosingMusic;
	}

	public Sound getSpinAttack() {
		return spinAttack;
	}

	public void setSpinAttack(Sound spinAttack) {
		this.spinAttack = spinAttack;
	}

	public Sound getSpitAttack() {
		return spitAttack;
	}

	public void setSpitAttack(Sound spitAttack) {
		this.spitAttack = spitAttack;
	}

	public Sound getVictoryMusic() {
		return victoryMusic;
	}

	public void setVictoryMusic(Sound victoryMusic) {
		this.victoryMusic = victoryMusic;
	}

	
	
}
