package org.usfirst.frc.team3373.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DualActuators {
	Actuator actuator1;
	Actuator actuator2;
	private double maxSpeed;
	private double previousPosition;
	private double target;
	private boolean isToPosition;
	private int integralCounter;
	
	public DualActuators(int port1, int port2, int port3, int port4,double maxPot1,double maxPot2, double minPot1, double minPot2,
			double maxDistance1,double maxDistance2,double minDistance1,double minDistance2){
		actuator1 = new Actuator(port1,port3,maxPot1, minPot1, maxDistance1, minDistance1);
		actuator2 = new Actuator(port2,port4,maxPot2,minPot2,maxDistance2,minDistance2);
		previousPosition = this.getPosition();
		maxSpeed= .6;
		integralCounter = 0;
		isToPosition = false;
	}
	public void goToPosition(double position){
		if(isToPosition){
			integralCounter = 0;
		}
		//System.out.println("Max SPEED: " + getMaxSpeed());
		//Prevents the target position from being past extrema on actuators
		if(position > actuator1.getMaxHeight()) 
			position = actuator1.getMaxHeight(); 
		if(position > actuator2.getMaxHeight()) 
			position = actuator2.getMaxHeight(); 
		if(position < actuator1.getMinHeight()) 
			position = actuator1.getMinHeight();
		if(position < actuator2.getMinHeight())
			position = actuator2.getMinHeight();
		// -----------------------------------------
		int direction;
		double deltaPosition1 = position - actuator1.getPosition(); // Distance of Actuator 1 to target Position
		double deltaPosition2 = position -actuator2.getPosition(); // Distance of Actuator 2 to target Position
		//double deltaPositions = deltaPosition1 - deltaPosition2; // The Difference between the distance from each Target
		double deltaPositions = actuator1.getPosition() - actuator2.getPosition();
		SmartDashboard.putNumber("DeltaPositions", deltaPositions); 
		double speed1 = .075*deltaPosition1 + .001*integralCounter*deltaPosition1; // sets the speed to be proportional to the Distance form target
		double speed2 = .075*deltaPosition2 + .001*integralCounter*deltaPosition2; // As it approaches target, it decelerates
		if(speed1 <0){ // actuator going down --> going up on lift
			direction = -1;
			if(this.getOutputCurrent()>30){
				this.setMaxSpeed(1);
			}else if(this.getOutputCurrent() > 15){
				this.setMaxSpeed(.8);
			}
			else{
				this.setMaxSpeed(.6);
			}
		}
		else{ //actuator going up --> going down on lift
			direction = 1;
			if(this.getOutputCurrent()>30){
				this.setMaxSpeed(1);
			}else if(this.getOutputCurrent() > 15){
				this.setMaxSpeed(.8);
			}
			else{
				this.setMaxSpeed(.4);
			}		}
		//prevents any speed from being sent that is greater than the maximum speed wanted
		if(speed1 > maxSpeed)
			speed1= maxSpeed;
		if(speed2 > maxSpeed)
			speed2= maxSpeed;
		if(speed1 < -maxSpeed)
			speed1= -maxSpeed;
		if(speed2<-maxSpeed)
			speed2= -maxSpeed;
		
		if(Math.abs(deltaPosition1)<Math.abs(deltaPosition2)){ //Actuator 1 is moving more quickly
			speed1-= .025*Math.abs(deltaPositions)*direction; //Decreases the speed of the first Actuator proportional to the error between the two  
			if(Math.abs(speed2) < maxSpeed) // if the magnitude of the slower actuator is less than maxSpeed
				speed2+=.05*Math.abs(deltaPositions)*direction; //increases the speed of the motor
		}else if(Math.abs(deltaPosition2) < Math.abs(deltaPosition1)){ // Actuator 2 is moving more quickly
			speed2-= .025*Math.abs(deltaPositions)*direction; // same logic as for actuator1 logic
			if(Math.abs(speed1) < maxSpeed)
				speed1+=.005*Math.abs(deltaPositions)*direction;
		}
		SmartDashboard.putNumber("speed1", speed1);
		SmartDashboard.putNumber("speed2", speed2);
		SmartDashboard.putNumber("deltaSpeed", speed1-speed2);
		if((this.getPosition() <target+.25)&&(this.getPosition()>target-.25))
			isToPosition = true;
		else{
			isToPosition = false;
		}
		if(isToPosition){
			speed1 = 0;
			speed2 = 0;
		}
		
		
		actuator1.set(speed1); //Sets the actuators speeds
		actuator2.set(speed2);
		if(this.getPosition()>25 || this.getPosition()<3)
		integralCounter++;
		else{
			integralCounter = 0;
		}
	}
	public double getPosition(){
		return (actuator1.getPosition()+actuator2.getPosition())/2;
	}
	public boolean isToPosition(){
		return isToPosition;
	}
	public void resetIsToPosition(){
		isToPosition = false;
	}
	public double getMaxSpeed(){
		return maxSpeed;
	}
	public void calibrate(SuperJoystick stick, boolean isFirstActuator){
		// Go to Actuator.calibrate to view proccedure
		if(isFirstActuator)
			actuator1.calibrate(stick);
		else
			actuator2.calibrate(stick);
	}
	public double getVelocity(){
		double currentPosition = this.getPosition();
		return (currentPosition - previousPosition)/.01; //Centimeters per second, runs 100 times per second Therefore change in position per second
	}
	public void setMaxSpeed(double speed){
		maxSpeed = speed;
	}
	public double getOutputCurrent(){
		return (actuator1.getOutputCurrent()+actuator2.getOutputCurrent())/2;
	}
}