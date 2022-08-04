package evoGame;

import engine.Vector2;

public class BlobGoingHome extends BlobState {
	
	public BlobGoingHome(Blob owner) {
		super(owner);
	}

	@Override
	public BlobState RunState() {
		
		owner.MoveTowards(owner.home);
		
		if(Vector2.Distance(owner.home, owner.pos) < 1) return new BlobIsHome(owner, true);
		
		
		
		return null;
	}

}
