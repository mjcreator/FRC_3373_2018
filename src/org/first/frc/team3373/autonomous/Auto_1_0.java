package org.first.frc.team3373.autonomous;

import org.usfirst.frc.team3373.robot.*;
import java.awt.Robot;

public class Auto_1_0 {
	SwerveControl swerve;
	DualActuators lifter;
	Grabber grabber;
	boolean isLeft;
	public Auto_1_0(SwerveControl swerveDrive, DualActuators actuators, Grabber cubeGrabber, boolean isScaleLeft){
		swerve = swerveDrive;
		lifter = actuators;
		grabber = cubeGrabber;
		isLeft= isScaleLeft;
		}
		public void run(){
			if(isLeft){ //If scale is left
				if(!swerve.hasHitBump()){//if the robot has not yet hit the bump
					swerve.autonomousDrive(90, 0,1,1);//drive straight--> drive 90 degrees forward for wheels for robot orientation 0 degrees is forward
				}else{ // robot has hit the bump
					swerve.setSpinAngle(90);//turn to the right
					if(!swerve.isAtSpinAngle())
					swerve.spintoXdegrees();
					else{
						swerve.driveForwardXInchesFromSurface(21.5, 90);
					}
					
				}
			}
		}

}
