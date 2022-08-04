package evoGame;

import engine.Game;

public class EvoGame extends Game {
	public Config config;
	GameManager manager;
	
	public EvoGame(Config c) {
		super();
		
		this.config = c;
		
	}
	
	@Override
	public void StartGame() {
		//start of game
		manager = new GameManager();
		SpawnGameObject(manager);
	}
	
	
	
	
}
