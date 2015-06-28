package model;

public class PileCoups {
	
	private Direction[] resolution;
	private int nbResolution;
	
	public PileCoups(Direction[] resolution){
		this.resolution = resolution;
		this.nbResolution = resolution.length;
	}
	
	
	public Direction getResolution(){
		return resolution[nbResolution-1];
	}
	
	public void depiler(){
		Direction[] olderResolution = resolution;
		
		if(this.nbResolution>1){
			this.resolution = new Direction[nbResolution-1];
			for(int i = 0; i < this.nbResolution-1; i++){
				this.resolution[i] = olderResolution[i];
			}
		}
		this.nbResolution --;
	}
	
	public int getNbCoup(){
		return nbResolution;
	}
	
	public void empiler(Direction dir){
		Direction[] olderResolution = resolution;
		
			this.resolution = new Direction[nbResolution+1];
			for(int i = 0; i < this.nbResolution; i++){
				this.resolution[i] = olderResolution[i];
			}
			this.resolution[nbResolution] = dir;
		
		this.nbResolution ++;
	}
}
