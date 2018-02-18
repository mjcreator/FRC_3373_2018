package org.first.frc.team3373.autonomous;
import org.usfirst.frc.team3373.robot.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto_Drive_Straight {

	SwerveControl swerve;
	DualActuators lifter;
	Grabber grabber;
	int driveCounter;
	boolean spinCheck;
	
		public Auto_Drive_Straight(SwerveControl swerveDrive, DualActuators actuators, Grabber cubeGrabber){
		swerve = swerveDrive;
		lifter = actuators;
		grabber = cubeGrabber;
		driveCounter = 0;
		spinCheck = false;
		}
		
		public void run(){
			SmartDashboard.putNumber("Angle", (360 - swerve.ahrs.getYaw())%360);
			if(!spinCheck){
				swerve.setSpinAngle(180);
				swerve.spinToXdegrees();
				if(swerve.isAtSpinAngle()){
					spinCheck = true;
				}
			}else{
				if(!swerve.hasHitBump()){
					swerve.autonomousDrive(0, 180, 1, 1, 1);
				}else{
					driveCounter++;
					if(driveCounter < 100){
					swerve.autonomousDrive(180, 180, 1, 1, 1);
					swerve.setSpinAngle(90);
					}else if(!swerve.isAtSpinAngle()){
						swerve.setSpinAngle(90);
						swerve.spinToXdegrees();
					}else{
						swerve.calculateSwerveControl(0, 0, 0);
					}
					
					
					
				}
			}
			
			
			
			
		/*	swerve.calculateSwerveControl(1, 0, 0);
			lifter.goToPosition(22);*/
			//Grabber deployment.
		}

}
