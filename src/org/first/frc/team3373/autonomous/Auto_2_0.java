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
	int shakeCounter = 0;
	public Auto_2_0(SwerveControl swerveDrive, DualActuators actuators, Grabber cubeGrabber, boolean isSwitchLeft, boolean isScaleLeft){
		swerve = swerveDrive;
		lifter = actuators;
		grabber = cubeGrabber;
		switchLeft = isSwitchLeft;
		scaleLeft  = isScaleLeft;
		}
		
		public void run(){
			SmartDashboard.putBoolean("Has Collided", swerve.hasCollidedPositiveX());
			shakeCounter++;
			if(shakeCounter < 25){
				swerve.calculateSwerveControl(1, 0, 0);
			}else if(shakeCounter < 50){
				swerve.calculateSwerveControl(-1, 0, 0);
				swerve.resetBump();
			}else if(shakeCounter < 75){
				swerve.resetBump();
				swerve.resetPositiveX();
				swerve.resetNegativeX();
			}else if(switchLeft){
				if(!swerve.hasCollidedPositiveX() && !swerve.hasHitBump()){
					swerve.autonomousDrive(0, 90);
					//swerve.driveToTape();
					lifter.goToPosition(10);
				}
				else{ 
					swerve.calculateSwerveControl(0, 0, 0);
					lifter.goToPosition(10);
					grabber.exportCube();
				}
			}
			else{
				if(!swerve.isToDistanceFromWall()){
					swerve.driveXInchesFromSurface(96, 90, 2, false, 3);
				}else if(!swerve.hasCollidedPositiveX()){
					swerve.autonomousDrive(0, 90);
					//swerve.driveToTape();
					lifter.goToPosition(10);
				}else{
					swerve.calculateSwerveControl(0, 0, 0);
					lifter.goToPosition(10);
					grabber.exportCube();
				}
			}

		}
}
