package org.usfirst.frc.team3373.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalOutput;

public class Ultrasonic {
	AnalogInput sensor;

	double previousDistance;
	double previousAccurateDistance;
	int inaccuracyCounter;
	boolean inaccurate;
	public Ultrasonic(int analogport){
		sensor = new AnalogInput(analogport);
		previousAccurateDistance = this.getRawDistance();
		previousDistance = previousAccurateDistance;
		inaccuracyCounter = 0;
		inaccurate = false;
	}
	public double getDistance(){
		double currentDistance = ((sensor.getAverageVoltage()/.00097656258)/25.4)-2;//Vcc/5120 Vcc =.00097656258 supplied voltage (5v) v/vcc/5120 = mm /25.4 = in. - 2 inches because range 0 is the inside of the cone
		if(Math.abs(currentDistance - previousDistance) > 10 && !inaccurate){
			previousAccurateDistance = previousDistance;
			inaccurate = true;
			
		}
		
		if(inaccurate){
			if(Math.abs(currentDistance-previousDistance) < 10){
				inaccuracyCounter++;
			}
			else{
				inaccuracyCounter = 0;
			}
		}
		if(inaccuracyCounter > 2 && inaccurate){
			inaccurate = false;
			inaccuracyCounter = 0;
		}
		else if(inaccurate){
			previousDistance = currentDistance;
			return previousAccurateDistance;
		}
			
		//previousDistance = currentDistance;
		previousDistance = currentDistance;
		return currentDistance; 
	}
	public double getVoltage(){
		return sensor.getAverageVoltage();
	}
	public double getRawDistance(){
		return ((sensor.getAverageVoltage()/.00097656258)/25.4)-2;
	}
}
