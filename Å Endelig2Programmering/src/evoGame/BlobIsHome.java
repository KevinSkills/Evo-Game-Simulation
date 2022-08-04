package evoGame;

public class BlobIsHome extends BlobState {
	
	boolean justArrived;
	
	public BlobIsHome(Blob owner, boolean justArrived) {
		super(owner);
		this.justArrived = justArrived;
		
	}

	@Override
	public BlobState RunState() {

		
		if(justArrived) {
			EvoGame().manager.blobsInField.remove(owner);
			
			//just arrived home logic ( reproduce, survive, or die)
			if(owner.foodCount < EvoGame().config.foodToSurvive) owner.Die();
			else if(owner.foodCount >= EvoGame().config.foodToReproduce) EvoGame().manager.Reproduce(owner);
			
			
			justArrived = false;
		}
		
		//after arriving ( waiting for next round)
		
		if(owner.newRound) {
			owner.newRound = false;
			owner.foodCount = 0;
			owner.energy = owner.startEnergy;
			return new BlobLooking(owner);
		}
		
		
		
		return null;
	}

}
