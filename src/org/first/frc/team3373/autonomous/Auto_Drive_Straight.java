package org.first.frc.team3373.autonomous;
import org.usfirst.frc.team3373.robot.*;

public class Auto_Drive_Straight {

	SwerveControl swerve;
	DualActuators lifter;
	Grabber grabber;
	
		public Auto_Drive_Straight(SwerveControl swerveDrive, DualActuators actuators, Grabber cubeGrabber){
		swerve = swerveDrive;
		lifter = actuators;
		grabber = cubeGrabber;
		}
		
		public void run(){
			swerve.calculateSwerveControl(.3, 0, 0);
			lifter.goToPosition(22);
			//Grabber deployment.
		}

}
