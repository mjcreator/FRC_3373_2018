package org.usfirst.frc.team3373.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalOutput;

public class Ultrasonic {
	AnalogInput sensor;

	double previousDistance;
	public Ultrasonic(int analogport){
		sensor = new AnalogInput(analogport);

		previousDistance = 195;
	}
	public double getDistance(){
		double currentDistance = ((sensor.getAverageVoltage()/.00097656258)/25.4);//Vcc/5120 Vcc =.00097656258 supplied voltage (5v) v/vcc/5120 = mm /25.4 = in.
		if(currentDistance > 190)
			currentDistance = previousDistance;
		previousDistance = currentDistance;
		return currentDistance; 
	}
	public double getVoltage(){

		return sensor.getAverageVoltage();
	}
}
