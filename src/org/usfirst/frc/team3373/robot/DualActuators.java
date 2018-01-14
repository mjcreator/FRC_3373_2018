package org.usfirst.frc.team3373.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		double deltaPositions = deltaPosition1 - deltaPosition2;
		SmartDashboard.putNumber("DeltaPositions", deltaPositions);
		double speed1 = .15*deltaPosition1;
		double speed2 = .15*deltaPosition2;
		/*
		if(deltaPositions < 0){
			//2nd actuator is faster
		/*	double speed2Magnitude = Math.abs(speed2);
			double speed2Direction = speed2/speed2Magnitude;
			speed2Magnitude = speed2Magnitude-deltaPositions;
			speed2 = speed2Magnitude * speed2Direction; */
			//speed2 *= Math.pow(deltaPositions,2);
		//}else if(deltaPositions>0){
			//first actuator is faster
			/*double speed1Magnitude = Math.abs(speed1);
			double speed1Direction = speed1/speed1Magnitude;
			speed1Magnitude = speed1Magnitude-deltaPositions;
			speed1 = speed1Magnitude * speed1Direction;*/
			//speed1 *= Math.pow(deltaPositions,2);
		//}*/
		if(deltaPosition1>deltaPosition2){
			speed1-= .02*Math.abs(deltaPositions);
			if(speed2 < 1)
				speed2+=.05;
		}else if(deltaPosition2 > deltaPosition1){
			speed2-= .02*Math.abs(deltaPositions);
			if(speed1 < 1)
				speed1+=.05;
		}
		SmartDashboard.putNumber("speed1", speed1);
		SmartDashboard.putNumber("speed2", speed2);
		SmartDashboard.putNumber("deltaSpeed", speed1-speed2);
		
		
		
		if(speed1 > 1)
			speed1= 1;
		if(speed2 > 1)
			speed2= 1;
		if(speed1 < -1)
			speed1= -1;
		if(speed2<-1)
			speed2= -1;
		
		actuator1.set(speed1);
		actuator2.set(speed2);
	}
	public double getPosition(){
		return actuator1.getPosition();
	}
}
