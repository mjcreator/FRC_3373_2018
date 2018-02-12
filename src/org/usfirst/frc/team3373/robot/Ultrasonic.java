package org.usfirst.frc.team3373.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class Ultrasonic {
	AnalogInput sensor;
	public Ultrasonic(int analogport){
		sensor = new AnalogInput(0);
	}
	public double getDistance(){
		return ((sensor.getAverageVoltage()/.00097656258)/25.4); //Vcc/5120 Vcc = supplied voltage (5v) v/vcc/5120 = mm /25.4 = in.
	}
}
