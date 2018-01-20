package org.usfirst.frc.team3373.robot;

public class Grabber {
	private boolean isCubeIn;
	SupremeTalon grab1;
	SupremeTalon grab2;
	public Grabber(int port1, int port2){
		isCubeIn = false;
		grab1 = new SupremeTalon(port1);
		grab2 = new SupremeTalon(port2);
	}
	public void importCube(){ //put cube in grabber
		isCubeIn = true;
		grab1.accelerate(.4,.05,false);
		grab2.accelerate(.4,.05,false);
	}
	public void exportCube(){ //remove cube from grabber
		isCubeIn = false;
		grab1.accelerate(-.4,.05,false);
		grab2.accelerate(-.4,.05,false);
	}
	public void keepCube(){ // keep cube in grabber
		grab1.accelerate(.2,.05,false);
		grab2.accelerate(.2,.05,false);
	}
	public void idle(){ // grabber wait for cube input
		grab1.accelerate(0,.05,false);
		grab2.accelerate(0,.05,false);
	}
	
	public boolean hasCube(){
		return isCubeIn;
	}
}
