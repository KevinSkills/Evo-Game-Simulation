package evoGame;

import java.awt.Graphics2D;

public class CirclePositioned extends Positioned {
	public float radius = 1;
	
	
	
	
	public void paintGameObject(Graphics2D g, float s) {
		g.fillOval((int)((pos.x - radius)*s), (int)((pos.y - radius)*s), (int)(2*radius*s), (int)(2*radius*s));
	};
}
