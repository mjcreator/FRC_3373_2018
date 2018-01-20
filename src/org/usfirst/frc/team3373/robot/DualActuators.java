package org.usfirst.frc.team3373.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DualActuators {
	Actuator actuator1;
	Actuator actuator2;
	
	public DualActuators(int port1, int port2, int port3, int port4,double maxPot1,double maxPot2, double minPot1, double minPot2,
			double maxDistance1,double maxDistance2,double minDistance1,double minDistance2){
		actuator1 = new Actuator(port1,port3,maxPot1, minPot1, maxDistance1, minDistance1);
		actuator2 = new Actuator(port2,port4,maxPot2,minPot2,maxDistance2,minDistance2);
	}
	public void goToPosition(double position){
		int direction;
		double deltaPosition1 = position - actuator1.getPosition(); // Distance of Actuator 1 to target Position
		double deltaPosition2 = position -actuator2.getPosition(); // Distance of Actuator 2 to target Position
		double deltaPositions = deltaPosition1 - deltaPosition2; // The Difference between the distance from each Target
		SmartDashboard.putNumber("DeltaPositions", deltaPositions); 
		double speed1 = .15*deltaPosition1; // sets the speed to be proportional to the Distance form target
		double speed2 = .15*deltaPosition2; // As it approaches target, it decelerates
		if(speed1 <0){ // going down
			direction = -1;
		}
		else{ //going up
			direction = 1;
		}
		//prevents any speed from being sent that is greater than 1 and -1
		if(speed1 > 1)
			speed1= 1;
		if(speed2 > 1)
			speed2= 1;
		if(speed1 < -1)
			speed1= -1;
		if(speed2<-1)
			speed2= -1;
		
		if(Math.abs(deltaPosition1)<Math.abs(deltaPosition2)){ //Actuator 1 is moving more quickly
			speed1-= .02*deltaPositions; //Decreases the speed of the first Actuator proportional to the error between the two  
			if(Math.abs(speed2) < 1) // if the magnitude of the slower actuator is less than 1
				speed2+=.05*direction; //increases the speed of the motor
		}else if(Math.abs(deltaPosition2) < Math.abs(deltaPosition1)){ // Actuator 2 is moving more quickly
			speed2-= .02*deltaPositions; // same as for actuator1 logic
			if(Math.abs(speed1) < 1)
				speed1+=.05*direction;
		}
		SmartDashboard.putNumber("speed1", speed1);
		SmartDashboard.putNumber("speed2", speed2);
		SmartDashboard.putNumber("deltaSpeed", speed1-speed2);
		
		
		
		actuator1.set(speed1); //Sets the actuators speeds
		actuator2.set(speed2);
	}
	public double getPosition(){
		return actuator1.getPosition();
	}
	public void calibrate(SuperJoystick stick, boolean isFirstActuator){
		// Go to Actuator.calibrate to view 
		if(isFirstActuator)
			actuator1.calibrate(stick);
		else
			actuator2.calibrate(stick);
	}
}
