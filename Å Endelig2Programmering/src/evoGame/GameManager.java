package evoGame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import engine.GameObject;
import engine.Vector2;

public class GameManager extends EvoGameObject {
	

	private EvoPlots plots = new EvoPlots();
	
	
	
	boolean nextRound = false;
	boolean roundFinished = true;
	
	public boolean showSenseRadius = true;
	public boolean automaticNewRound = false;
	
	int genNumber;
	

	Random rand = new Random();
	
	ArrayList<Blob> blobsInField = new ArrayList<Blob>();
	ArrayList<Blob> blobsAlive = new ArrayList<Blob>();
	
	
	
	@Override
	public void Start() {
		super.Start();
		tag = "GameManager";
		genNumber = 0;
		
	
		//Spawn  blobs for first time
		
		for (int i = 0; i < EvoGame().config.blobCount; i++) {
			SpawnBlob(new Genes(EvoGame().config.startGene));
		}

		
	}
	
	@Override
	public void Update() {
		
		if(blobsInField.size() == 0) roundFinished = true;
		else roundFinished = false;
		
		
		if(roundFinished && automaticNewRound) nextRound = true;
		
		if(nextRound) {
			StartNextRound();
			nextRound = false;
		}
	}
	
	public void Reproduce(Blob b1) {
		
		Genes gene = new Genes(b1.gene);
		
		
		float mp = rand.nextFloat()*0.2f + 1;
		mp =(rand.nextInt(100) > 50) ? mp : 1/mp;
		
		gene.speed *= mp; 

		gene.energyUsage *= Math.pow(mp,  1 + EvoGame().config.speedCost);
		
		
		mp = rand.nextFloat()*0.1f + 1;
		mp = (rand.nextInt(100) > 50) ? mp : 1/mp;
		
		gene.senseRadius *= mp;
		gene.energyUsage *= Math.pow(mp, EvoGame().config.senseRadiusCost);
		
		SpawnBlob(new Genes(gene));
		
	}
	
	void SpawnBlob(Genes genes) {
		Vector2 worldSize = EvoGame().world.size;
		Vector2 randPos = new Vector2(0, 0);
		
		//choose a random position to spawn blob. Only at edges
		if(rand.nextInt(100) > 50) {
			//top or bottom edge
			
			randPos.x = rand.nextFloat(worldSize.x - 2) + 1; //random
			randPos.y = rand.nextInt(2) * (worldSize.y - 2) + 1; //edge
		}else {
			//right or left edge
			
			randPos.x = rand.nextInt(2) * (worldSize.y - 2) + 1; //edge
			randPos.y = rand.nextFloat(worldSize.y - 2) + 1; //random
		}
		
		//spawn with pos and gene
		
		Blob b = new Blob(randPos, genes);
		EvoGame().SpawnGameObject(b);
		blobsAlive.add(b);
	}
	
	
	public EvoPlots getPlots() {
		return plots;
	}
	
	void UpdateGenerationPlots(){
		plots.senseForCurrentGen.clear();
		plots.speedForCurrentGen.clear();
		
		for(int i = 0; i < blobsAlive.size(); i++) {
			Blob b = blobsAlive.get(i);
			plots.senseForCurrentGen.add(i, b.gene.senseRadius);
			plots.speedForCurrentGen.add(i, b.gene.speed);
		}
	}
	
	void StartNextRound() {
		genNumber++;
		float avgSpeed = 0; //Variable for plot
		float avgSense = 0; //Variable for plot
		//start all blobs
		for(Blob b : blobsAlive) {
			b.newRound = true;
			blobsInField.add(b);
			avgSpeed += b.gene.speed; //update variable for plot
			avgSense += b.gene.senseRadius; //update variable for plot
		}
		
		//update plots here
		plots.avgSpeed.add(genNumber, avgSpeed / blobsAlive.size());
		plots.avgSense.add(genNumber, avgSense / blobsAlive.size());
		plots.populationOverTime.add(genNumber, blobsAlive.size());
		UpdateGenerationPlots();
		
		//remove old food
		for(GameObject go : EvoGame().findGameObjectsWithTag("Food")) {
			EvoGame().Destroy(go);
		}
		
		//add new food
		Vector2 randPos = new Vector2(0, 0);
		for (int i = 0; i < EvoGame().config.foodCount; i++) {
			randPos.x = rand.nextFloat(EvoGame().world.size.x - 6) + 3;
			randPos.y = rand.nextFloat(EvoGame().world.size.x - 6) + 3;
			EvoGame().SpawnGameObject(new Food(randPos));
		}
		

	}
	
	public boolean isRoundFinished() {
		return roundFinished;
	}
	

	
	
	
	
}
