package engine;
import java.util.ArrayList;


public abstract class Game implements Runnable {
	public ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	public ArrayList<GameObject> toDestroy = new ArrayList<GameObject>();
	public ArrayList<GameObject> toSpawn = new ArrayList<GameObject>();
	
	
	public World world;
	
	private long gameDeltaTime = 20; //the deltaTime for gameLogic. Alwaysc constant no matter the speed of game.
	private long deltaTime; //this deltaTime has nothing to do with game logic but only with the 'real' deltatime in the real world.
	public boolean running;
	long lastTime;
	long time = 0;
	
	
	public float getTime() {
		return (float)time/1000;
	}
	
	
	public float getDeltaTime() {
		return (float)gameDeltaTime/1000;
	}
	
	public Game() {
		setSpeed(1);
		world = new World(100, 100);
		
	}
	
	
	public void setSpeed(float speedMP) {
		deltaTime = (long) (gameDeltaTime / speedMP);
	}
	
	
	
	
	
	@Override
	public void run() { //Runs GameLoop() every 'deltaTime' seconds
		running = true;
		
		lastTime = System.currentTimeMillis();
		StartGame();
		while (running) {
			if(System.currentTimeMillis() > lastTime + deltaTime) {
				lastTime = System.currentTimeMillis();
				time += gameDeltaTime;
				GameLoop();
				
			}
		}
	}
	
	public void GameLoop() {
		
		UpdateGameObjects();
		
		SpawnGameObjects();
		DestroyGameObjects();
		
		
	}

	public void SpawnGameObject(GameObject go) { //Spawning means it is now part of game and has acces to game class;
		toSpawn.add(go);
	}
	
	public void Destroy(GameObject go) {
		toDestroy.add(go);
	}
	
	void DestroyGameObjects() {
		for (GameObject go : toDestroy){
			gameObjects.remove(go);
		}
		toDestroy = new ArrayList<GameObject>();
	}
	
	void SpawnGameObjects() {		
		//Spawn into game
		for (GameObject go : toSpawn){
			go.game = this;
			gameObjects.add(go);
			
		}
		
		//clear the queue but clone it for start function
		ArrayList<GameObject> justSpawned = (ArrayList<GameObject>) toSpawn.clone();
		toSpawn = new ArrayList<GameObject>();
		
		//run start function
		for (GameObject go : justSpawned){
			go.Start();
		}
		
	}
	
	
	public void UpdateGameObjects() {
		for(GameObject go : gameObjects) {
			go.Update();
		}
	}
	
	public void StartGame() {
		
	}
	
	public GameObject findGameObjectWithTag(String tag) {
		for(GameObject go : gameObjects) {
			if (go.tag.equals(tag)) return go;
		}
		return null;
	}
	
	public ArrayList<GameObject> findGameObjectsWithTag(String tag) {
		ArrayList<GameObject> withTag = new ArrayList<GameObject>();
		for(GameObject go : gameObjects) {
			if (go.tag.equals(tag)) withTag.add(go);
		}
		return withTag;
	}
	
	
	

}
