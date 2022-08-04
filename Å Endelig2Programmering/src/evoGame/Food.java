package evoGame;

import java.awt.Color;
import java.awt.Graphics2D;

import engine.Vector2;

public class Food extends CirclePositioned {
	boolean justSpawned;
	
	@Override
	public void Start() {
		super.Start();
		tag = "Food";
		radius = 0.5f;
		justSpawned = true;
		
		
		
	}
	
	
	
	public Food(Vector2 pos) {
		this.pos.x = pos.x;
		this.pos.y = pos.y;
	}
	
	@Override
	public void paintGameObject(Graphics2D g, float s) {
		g.setColor(Color.green);
		super.paintGameObject(g, s);
	}
	

}
