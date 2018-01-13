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
		double deltaPosition1 = position - actuator1.getPosition();
		double deltaPosition2 = position -actuator2.getPosition();
		double speed1 = .1*deltaPosition1;
		double speed2 = .1*deltaPosition2;
		if(speed1 > 1)
			speed1= 1;
		if(speed2 > 1)
			speed2= 1;
		if(speed1 < -1)
			speed1= -1;
		if(speed2<-1)
			speed2= -1;
		actuator1.accelerate(speed1,0.075);
		actuator2.accelerate(speed2, 0.075);
	}
	public double getPosition(){
		return actuator1.getPosition();
	}
}
