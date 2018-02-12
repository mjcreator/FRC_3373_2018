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
			}
		}

}
