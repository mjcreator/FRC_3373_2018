package org.first.frc.team3373.autonomous;
import org.usfirst.frc.team3373.robot.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto_Drive_Straight {

	SwerveControl swerve;
	DualActuators lifter;
	Grabber grabber;
	int driveCounter;
	boolean spinCheck;
	boolean spinCheck1;
	boolean spinCheck2;
	boolean spinCheck3;
	boolean spinCheck4;
	boolean spinCheck5;
	boolean spinCheck6;
	boolean spinCheck7;
	boolean spinCheck8;
	boolean spinCheck9;
	boolean spinCheck10;
	boolean spinCheck11;
	boolean spinCheck12;
	
		public Auto_Drive_Straight(SwerveControl swerveDrive, DualActuators actuators, Grabber cubeGrabber){
		swerve = swerveDrive;
		lifter = actuators;
		grabber = cubeGrabber;
		driveCounter = 0;
		spinCheck = false;
		spinCheck1 = false;
		spinCheck2 = false;
		spinCheck3 = false;
		spinCheck4 = false;
		spinCheck5 = false;
		spinCheck6 = false;
		spinCheck7 = false;
		spinCheck8 = false;
		spinCheck9 = false;
		spinCheck10 = false;
		spinCheck11 = false;
		spinCheck12 = false;
		}
		
		public void run(){
		swerve.sniper();
		swerve.calculateSwerveControl(.5, 0, 0);
		}

}
