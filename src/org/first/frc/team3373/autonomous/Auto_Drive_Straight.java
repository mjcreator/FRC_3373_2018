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
			if(!swerve.hasHitBump()){
				swerve.turbo();
			swerve.calculateSwerveControl(-1, 0, 0);
			}
			else{
				swerve.calculateSwerveControl(0, 0, 0);
			}
			lifter.goToPosition(22);
			//Grabber deployment.
		}

}
