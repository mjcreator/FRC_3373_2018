package org.first.frc.team3373.autonomous;

import org.usfirst.frc.team3373.robot.DualActuators;
import org.usfirst.frc.team3373.robot.Grabber;
import org.usfirst.frc.team3373.robot.SwerveControl;
import org.usfirst.frc.team3373.robot.Vision;
import org.usfirst.frc.team3373.robot.VisionObject;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto_4_0 {

	SwerveControl swerve;
	DualActuators lifter;
	Grabber grabber;
	boolean switchLeft;
	boolean scaleLeft;
	boolean emergencyDrive = false;
	int shakeCounter = 0;
	int placeTimer = 0;
	double previousError;
	int stillCounter = 0;
	//Vision vision;
	boolean toLeftTape = false;
	boolean linedUpWithTape = false;
	double height;
	public Auto_4_0(SwerveControl swerveDrive, DualActuators actuators, Grabber cubeGrabber, boolean isSwitchLeft, boolean isScaleLeft){
		swerve = swerveDrive;
		lifter = actuators;
		grabber = cubeGrabber;
		switchLeft = isSwitchLeft;
		scaleLeft  = isScaleLeft;
	//	vision = new Vision();
		swerve.vision.switchCamera(0);
		height = 27.5;
		}
		
		public void run(){
			swerve.setAutonomousBoolean(false);
			shakeCounter ++;
			if(shakeCounter < 40){
				swerve.calculateSwerveControl(1, 0, 0);
			}else if(shakeCounter < 80){
				swerve.calculateSwerveControl(-1, 0, 0);
				swerve.resetBump();
			}else if(shakeCounter < 150){
				swerve.resetBump();
				swerve.resetPositiveX();
				swerve.calculateSwerveControl(0, 0, 0);
			}else if(!switchLeft){
		//		System.out.println("Right?");
				this.driveToSwitch();
			}else{
		//		System.out.println("Left?");
				VisionObject object = swerve.vision.getBestObject(3);
				try{
				if((object.X+.3>0) && !toLeftTape){
					swerve.autonomousDrive(165,90);
					height = 8;
				}
				else if(((object.X < 0)|| toLeftTape)&&!linedUpWithTape){
					toLeftTape = true;
					double error = object.X * .5;
					if(Math.abs(error) < .3){
						error *= 1.5;
					}
					if(object == null)
						error = -.3;
					swerve.autonomousDrive(0, 90,error,error);
					if((object.X < .1 && object.X > -.1) || stillCounter > 100){
						linedUpWithTape = true;
					}
					if(stillCounter > 100){
						emergencyDrive = true;
					}
					if(Math.abs(error - previousError) < .01 && error != -.3){
						stillCounter ++;
					}else if(Math.abs(error - previousError) > .01 && error != -.3){
						stillCounter = 0;
					}
					if(error != -.3){
					previousError = error;
					}
					swerve.resetBump();
					swerve.resetPositiveX();
				}
				else{
					this.driveToSwitch();
				}
				}catch(Exception e){
					
				}
			}
			/*	SmartDashboard.putBoolean("Has Collided", swerve.hasCollidedPositiveX());
			shakeCounter++;
			if(shakeCounter < 25){
				swerve.calculateSwerveControl(1, 0, 0);
			}else if(shakeCounter < 40){
				swerve.calculateSwerveControl(-1, 0, 0);
				swerve.resetBump();
			}else if(shakeCounter < 150){
				swerve.calculateSwerveControl(0, 0, 0);
				swerve.resetBump();
				swerve.resetPositiveX();
				swerve.resetNegativeX();
			}else if(!switchLeft){
				if(!swerve.hasCollidedPositiveX() && !swerve.hasHitBump()){
					//swerve.autonomousDrive(0, 90);
					swerve.driveToTape(0,90, 0);
				//	lifter.goToPosition(10);
				}
				else{ 
					swerve.calculateSwerveControl(0, 0, 0);
				//	lifter.goToPosition(10);
					grabber.exportCube();
				}
			}
			else{
				if(!swerve.isToDistanceFromWall()){
					swerve.driveXInchesFromSurface(96, 90, 1, false, 3);
				}else if(!swerve.hasCollidedPositiveX()){
					//swerve.autonomousDrive(0, 90);
					swerve.driveToTape(0, 90, 0);
				//	lifter.goToPosition(10);
				}else{
					swerve.calculateSwerveControl(0, 0, 0);
				//	lifter.goToPosition(10);
					grabber.exportCube();
				}
			}*/
			lifter.goToPosition(height);

		}
		public void driveToSwitch(){
			placeTimer ++;
			if(!swerve.hasCollidedPositiveX() && !swerve.hasHitBump() && placeTimer < 200){
				swerve.autonomousDrive(90, 90);
				//System.out.println("Moving to Switch.");
			}
			else{
				swerve.calculateSwerveControl(0, 0, 0);
		//		System.out.println("Arrived.");
				if(!emergencyDrive || toLeftTape){
				grabber.exportCube();
				}else{
					System.out.println("Emergency. Right tape.");
				}
				if(emergencyDrive){
					System.out.println("Emergency.");
				}
			}
		}
}
