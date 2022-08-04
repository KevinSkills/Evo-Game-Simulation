package evoGame;

import java.util.Random;

import engine.Vector2;

public class BlobLooking extends BlobState {
	
	float lastUpdateTime = -10;
	
	Vector2 dir;
	
	
	public BlobLooking(Blob owner) {
		super(owner);
		
	}
	@Override
	public BlobState RunState() {
		
		Random rand = new Random();
		Vector2 target;
		
		//Updating direction 
		if(EvoGame().getTime() > lastUpdateTime + EvoGame().config.updateDirectionDelay) {
			target = new Vector2(rand.nextFloat(1000) - 500, rand.nextFloat(1000) - 500);
			lastUpdateTime = EvoGame().getTime();
			dir = Vector2.Add(target, owner.pos.neg()).direction();
				
		}
		
		//edges
		if((owner.pos.x > EvoGame().world.size.x || owner.pos.x < 0)||(owner.pos.y > EvoGame().world.size.y || owner.pos.y < 0)) {
			lastUpdateTime = -10;
		}
		
	
		//move towards target
		if(owner.UseEnergy() > 0)
		owner.Move(dir);
		else {
			return new BlobGoingHome(owner);
		}

		
		//look for food
		CirclePositioned inRadius = owner.checkClosestCollision(owner.gene.senseRadius);
		
		//seeing food
		if(inRadius != null && (inRadius.tag.equals("Food"))) {
			return new BlobGoToFood(owner, inRadius);
		}
		
		
		return null;
	}

}
