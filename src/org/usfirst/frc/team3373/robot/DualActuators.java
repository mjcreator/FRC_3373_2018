package org.usfirst.frc.team3373.robot;



public class DualActuators {
	Actuator actuator1;
	Actuator actuator2;
	
	public DualActuators(int port1, int port2, double maxDelta,double maxPot1,double maxPot2, double minPot1, double minPot2,
			double maxDistance1,double maxDistance2,double minDistance1,double minDistance2){
		actuator1 = new Actuator(port1,maxDelta,maxPot1, minPot1, maxDistance1, minDistance1);
		actuator2 = new Actuator(port2,maxDelta,maxPot2,minPot2,maxDistance2,minDistance2);
	}
	public void goToPosition(double position){
		
	}
	public double getPosition(){
		return actuator1.getPosition();
	}
}
