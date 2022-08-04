package evoGame;

public abstract class BlobState extends EvoGameObject {
	Blob owner;
	
	public BlobState(Blob owner) {
		this.owner = owner;
		this.game = owner.game;
	}
	
	
	public abstract BlobState RunState();
	
}
