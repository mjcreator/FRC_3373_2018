package org.usfirst.frc.team3373.robot;

import edu.wpi.first.wpilibj.Relay;

public class Grabber {
	private boolean isCubeIn;
	Relay grab1;
	Relay grab2;
	public Grabber(int port1, int port2){
		isCubeIn = false;
		grab1 = new Relay(port1, Relay.Direction.kBoth);
		grab2 = new Relay(port2, Relay.Direction.kBoth);
	}
	public void importCube(){ //put cube in grabber
		isCubeIn = true;
		grab1.set(Relay.Value.kForward);
		grab2.set(Relay.Value.kReverse);
	}
	public void exportCube(){ //remove cube from grabber
		isCubeIn = false;
		grab1.set(Relay.Value.kReverse);
		grab2.set(Relay.Value.kForward);
	}
/*	public void keepCube(){ // keep cube in grabber
		grab1.accelerate(.2,.05,false);
		grab2.accelerate(.2,.05,false);
	}
	public void idle(){ // grabber wait for cube input
		grab1.accelerate(0,.05,false);
		grab2.accelerate(0,.05,false);
	}*/
	
	public boolean hasCube(){
		return isCubeIn;
	}
}
