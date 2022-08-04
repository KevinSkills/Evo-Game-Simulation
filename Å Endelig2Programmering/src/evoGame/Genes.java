package evoGame;

public class Genes {
	public float speed;
	public float radius;
	public float senseRadius;
	public float energyUsage;
	
	
	
	public Genes() {
	}
	
	public Genes(float speed, float radius, float senseRadius, float energyUsage) {
		this.speed = speed;
		this.radius = radius;
		this.senseRadius = senseRadius;
		this.energyUsage = energyUsage;
	}
	
	public Genes(Genes c) {
		this.speed = c.speed;
		this.radius = c.radius;
		this.senseRadius = c.senseRadius;
		this.energyUsage = c.energyUsage;
	}
	
}
