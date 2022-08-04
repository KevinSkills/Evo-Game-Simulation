package evoGame;

import engine.Vector2;

public class BlobGoToFood extends BlobState {
	CirclePositioned targetFood;
	
	public BlobGoToFood(Blob owner, CirclePositioned targetFood) {
		super(owner);
		
		this.targetFood = targetFood;
	}

	@Override
	public BlobState RunState() {
		
		if(owner.UseEnergy() > 0)
			owner.Move(Vector2.Add(targetFood.pos, owner.pos.neg()).direction());
		else {
			return new BlobGoingHome(owner);
		}
		
		
		
		if(Vector2.Distance(targetFood.pos, owner.pos) < targetFood.radius + owner.radius) {
			EvoGame().Destroy(targetFood);
			owner.foodCount++;
			return new BlobLooking(owner);
			
			
		}
		
		if(!EvoGame().gameObjects.contains(targetFood)) return new BlobLooking(owner);

		return null;
	}

}
