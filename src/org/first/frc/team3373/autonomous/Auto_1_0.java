package org.first.frc.team3373.autonomous;

import org.usfirst.frc.team3373.robot.DualActuators;
import org.usfirst.frc.team3373.robot.Grabber;
import org.usfirst.frc.team3373.robot.SwerveControl;

public class Auto_1_0 {

	SwerveControl swerve;
	DualActuators lifter;
	Grabber grabber;
	boolean isLeft;
	boolean hasRisen;
	boolean toDistance1R;
	boolean toDistance2R;
	boolean toDistance3R;
	boolean toRotate1R;
	boolean isAtDistance1;
	boolean isAtDistance2;
	int driveTimer = 0;
	int ejectTimer = 0;
	int shakeCounter = 0;
	public Auto_1_0(SwerveControl swerveDrive, DualActuators actuators, Grabber cubeGrabber, boolean isScaleLeft){
		swerve = swerveDrive;
		lifter = actuators;
		grabber = cubeGrabber;
		isLeft= isScaleLeft;
		toDistance1R = false;
		toRotate1R = false;
		}
		
	//Reenable lifter!
	public void run(){
		shakeCounter++;
		swerve.setStartOffset(0);
		swerve.setAutonomousOffset(0);
		System.out.println("run");
		
		if(shakeCounter < 25){
			swerve.calculateSwerveControl(1, 0, 0);
		}else if(shakeCounter < 50){
			swerve.calculateSwerveControl(-1, 0, 0);
			swerve.resetBump();
		}else if(shakeCounter < 150){
			swerve.resetBump();
		}else if(isLeft){ //If scale is left
			if(!swerve.hasHitBump()){//if the robot has not yet hit the bump
				swerve.setDriveDistance(30);
				swerve.autonomousDrive(90, 0,1,1,3);//drive straight--> drive 90 degrees forward for wheels for robot orientation 0 degrees is forward
				lifter.goToPosition(24);
			}else{//  robot has hit the bump
				if(!isAtDistance1){
					swerve.driveXInchesFromSurface(21.5, 0, 3);
					if(swerve.isToDistanceFromWall()){
						isAtDistance1 = true;
					}
				}else{
				if(!hasRisen){
				swerve.calculateSwerveControl(0, 0, 0);
				lifter.goToPosition(2);
				if(lifter.isToPosition()){
					hasRisen = true;
					swerve.resetIsToDistance();
				}
				}else{
					if(!isAtDistance2){
						swerve.driveXInchesFromSurface(39.5, 0, 3);
						if(swerve.isToDistanceFromWall()){
							isAtDistance2 = true;
						}
					}else{
						if(ejectTimer < 100){
							grabber.exportCube();
							ejectTimer++;
							lifter.resetIsToPosition();
							swerve.resetIsToDistance();
						}else{
							if(!swerve.isToDistanceFromWall()){
								swerve.driveXInchesFromSurface(21.5, 0, 3);
							}else{
							
							
							if(!lifter.isToPosition()){
								lifter.goToPosition(26.5);
							}
							}
							
							
						}
					}
					

					
					
					
				}
				}
				/*swerve.setSpinAngle(90); turn to the right
				if(!swerve.isAtSpinAngle())
				swerve.spintoXdegrees();
				else{
					swerve.driveForwardXInchesFromSurface(21.5, 90);*/
				//}
				
			}
			
			
			
			
		}else{
			if(!toDistance1R){
				swerve.setDriveDistance(30);
				swerve.driveXInchesFromSurface(175, 0, 2, false, 3);
				if(swerve.isToDistanceFromWall()){
					toDistance1R = true;
					swerve.setSpinAngle(180);
				}
			}else if(!toRotate1R){
				swerve.spinToXdegrees();
				if(swerve.isAtSpinAngle())
					toRotate1R = true;
			}else if(driveTimer < 100){
				swerve.autonomousDrive(180, 180);
				swerve.resetIsToDistance();
				driveTimer++;
			}else if(!toDistance2R){
				swerve.setDriveDistance(20);
				swerve.driveXInchesFromSurface(21.5, 180, 3, true, 1);
				swerve.resetBump();
				if(swerve.isToDistanceFromWall()){
					toDistance2R = true;
				}
			}else if(!swerve.hasHitBump()){
				swerve.setDriveDistance(21.5);
				swerve.autonomousDrive(90, 180, 1, 1, 3);
				lifter.goToPosition(24);
				lifter.resetIsToPosition();
				swerve.resetIsToDistance();
			}else if(!toDistance3R || !hasRisen){
				swerve.driveXInchesFromSurface(21.5, 180, 3);
				if(swerve.isToDistanceFromWall()){
					toDistance3R = true;
				}
				lifter.goToPosition(2);
				if(lifter.isToPosition()){
					hasRisen = true;
				}
			}else if(ejectTimer < 25){
				grabber.exportCube();
				swerve.resetIsToDistance();
				lifter.resetIsToPosition();
			}else if(!swerve.isToDistanceFromWall()){
				swerve.driveXInchesFromSurface(21.5, 180, 3);
			}else{
				lifter.goToPosition(26.5);
			}
		}
	}

}
