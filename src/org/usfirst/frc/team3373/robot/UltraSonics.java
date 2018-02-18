package org.usfirst.frc.team3373.robot;

import edu.wpi.first.wpilibj.DigitalOutput;

public class UltraSonics {
	Ultrasonic leftSensor;
	Ultrasonic rightSensor;
	Ultrasonic backSensor;
	DigitalOutput trigger;
	public UltraSonics(int digitalPort,int ultraSonic1Port,int ultraSonic2Port, int ultraSonic3Port){
		trigger = new  DigitalOutput(digitalPort);
		leftSensor = new Ultrasonic(ultraSonic1Port);
		rightSensor = new Ultrasonic(ultraSonic2Port);
		backSensor = new Ultrasonic(ultraSonic3Port);
		trigger.pulse(.002);
	}
	public double getDistance(int whichSensor){//Left = 1, Right = 2, Back = 3
		if(!trigger.isPulsing())
			trigger.set(true);
		if(whichSensor == 1){
			return leftSensor.getDistance();
		}
		else if(whichSensor == 2){
			return rightSensor.getDistance();
		}
		else{
			return backSensor.getDistance();
		}
	}
	public double getVoltage(int whichSensor){
		if(!trigger.isPulsing())
			trigger.set(true);
		if(whichSensor == 1){
			return leftSensor.getVoltage();
		}
		else if(whichSensor == 2){
			return rightSensor.getVoltage();
		}
		else{
			return backSensor.getVoltage();
		}
	}
}
