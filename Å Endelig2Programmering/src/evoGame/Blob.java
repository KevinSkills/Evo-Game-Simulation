package evoGame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import engine.GameObject;
import engine.Vector2;

public class Blob extends CirclePositioned {
	
	boolean newRound = false;
	Genes gene;
	Random rand = new Random();
	Vector2 home = new Vector2(0, 0);
	int foodCount;
	float startEnergy;
	float energy;
	
	BlobState currentState;
	
	public Blob(Vector2 spawnPos, Genes c) {
		this.pos = spawnPos.clone();
		this.gene = c;
		this.home = spawnPos.clone();
		
	}
	
	@Override
	public void Start() {
		startEnergy = EvoGame().config.startEnergy;
		tag = "Blob";
		currentState = new BlobIsHome(this, false);
		radius = gene.radius;
		
	}
	
	@Override
	public void Update() {
		StateMachine();
	}
	
	public void StateMachine() {
		
		//run current state and get next state
		BlobState nextState = currentState.RunState();
		
		//from anyState:
		
		
		
		//go to nextState
		if(nextState != null) {
			currentState = nextState;
		}
	}
	
	public float UseEnergy() {
		energy -= gene.energyUsage;
		return energy;
	}
	
	public void Move(Vector2 dir) { 
		pos.AddTo(Vector2.Multiply(dir, gene.speed * EvoGame().getDeltaTime()));	
	}
	
	public void MoveTowards(Vector2 towards) {//for actually ending the place
		Vector2 dir = Vector2.Add(towards, pos.neg()).direction();
		Vector2 moveVector = Vector2.Multiply(dir, gene.speed * EvoGame().getDeltaTime());
		
		if (moveVector.length() > Vector2.Distance(pos, towards)) {
			pos = towards.clone();
		}else {
			pos.AddTo(moveVector);	
		}
		
		
	}
	
	
	public void Die() {
		EvoGame().manager.blobsAlive.remove(this);
		EvoGame().Destroy(this);
	}
	
	@Override
	public void paintGameObject(Graphics2D g, float s) {
		g.setColor(Color.red);
		
		if(energy <= 0)
			g.setColor(Color.gray);
		
		super.paintGameObject(g, s);
		
		//to see sense radius
		g.setColor(Color.pink);
		if(EvoGame().manager.showSenseRadius)
		g.drawOval((int)((pos.x - gene.senseRadius)*s), (int)((pos.y - gene.senseRadius)*s), (int)(2*gene.senseRadius*s), (int)(2*gene.senseRadius*s));
		
		
	}

	
	public CirclePositioned checkClosestCollision(float detectRadius) { //returns closest CirclePositioned object
		CirclePositioned closest = null;
		float closestLength = detectRadius;
		for (GameObject g : EvoGame().gameObjects) {
			if(g.tag.equals("Blob") || g.tag.equals("Food") && !g.equals(this)) {
				CirclePositioned c = (CirclePositioned)g;
				float distance = Vector2.Add(c.pos, pos.neg()).length();
				if(distance < c.radius + detectRadius && distance < closestLength) {
					closest = c;
				}
			}
		}
		return closest;
	}
	
	

	
	
	
}
