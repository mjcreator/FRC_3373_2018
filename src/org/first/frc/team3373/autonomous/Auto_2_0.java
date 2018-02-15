package org.first.frc.team3373.autonomous;

import org.usfirst.frc.team3373.robot.DualActuators;
import org.usfirst.frc.team3373.robot.Grabber;
import org.usfirst.frc.team3373.robot.SwerveControl;

public class Auto_2_0 {

	SwerveControl swerve;
	DualActuators lifter;
	Grabber grabber;
	boolean switchLeft;
	boolean scaleLeft;
	public Auto_2_0(SwerveControl swerveDrive, DualActuators actuators, Grabber cubeGrabber, boolean isSwitchLeft, boolean isScaleLeft){
		swerve = swerveDrive;
		lifter = actuators;
		grabber = cubeGrabber;
		switchLeft = isSwitchLeft;
		scaleLeft  = isScaleLeft;
		}
		
		public void run(){
			if(switchLeft){
				if(!swerve.hasCollidedNegativeX()){
					swerve.autonomousDrive(90, 0,1.5,1.5);
				}
			}
			else{
					
			}

		}
}
