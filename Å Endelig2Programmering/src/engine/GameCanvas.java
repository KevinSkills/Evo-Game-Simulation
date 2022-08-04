package engine;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GameCanvas extends JPanel {
	Game gameToDisplay;
	float frameRate;
	Timer timer;
	float scale = 5;

	public GameCanvas(Game game, float frameRate) {
		this.gameToDisplay = game;
		this.frameRate = frameRate;
		

		ActionListener task = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		};
		timer = new Timer((int) (1000/frameRate), task);
		timer.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
	
		scale =  this.getHeight() / gameToDisplay.world.size.y;
		
		gameToDisplay.world.paintWorld(g2d, scale);
		
		//draw gameObjects with world size
		for(int i = 0; i < gameToDisplay.gameObjects.size(); i++) {
			gameToDisplay.gameObjects.get(i).paintGameObject((Graphics2D)g, scale);
		}

	}
		
	
}
