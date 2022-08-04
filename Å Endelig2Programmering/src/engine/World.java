package engine;
import java.awt.Color;
import java.awt.Graphics2D;

public class World {
	public Vector2 size;
	
	
	public World(float x, float y) {
		this.size = new Vector2(x,y);
	}


	public void paintWorld(Graphics2D g, float s) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, (int)(size.x * s), (int)(size.y*s));
		
		
	}
	
	
	
}
