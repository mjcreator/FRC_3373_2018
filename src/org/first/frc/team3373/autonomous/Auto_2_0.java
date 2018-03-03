package org.first.frc.team3373.autonomous;

import org.usfirst.frc.team3373.robot.DualActuators;
import org.usfirst.frc.team3373.robot.Grabber;
import org.usfirst.frc.team3373.robot.SwerveControl;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
			SmartDashboard.putBoolean("Has Collided", swerve.hasCollidedPositiveX());
			if(switchLeft){
				if(!swerve.hasCollidedNegativeX()){
					swerve.autonomousDrive(90, 90);
					lifter.goToPosition(23);
				}
				else{ 
					//System.out.println("Hi");
					grabber.exportCube();
				}
			}
			else{
					
			}

		}
}
