package org.first.frc.team3373.autonomous;

import org.usfirst.frc.team3373.robot.DualActuators;
import org.usfirst.frc.team3373.robot.Grabber;
import org.usfirst.frc.team3373.robot.SwerveControl;

public class Auto_1_1 {

	SwerveControl swerve;
	DualActuators lifter;
	Grabber grabber;
	boolean isLeft;
	boolean hasRisen;
	int ejectTimer = 0;
	public Auto_1_1(SwerveControl swerveDrive, DualActuators actuators, Grabber cubeGrabber, boolean isScaleLeft){
		swerve = swerveDrive;
		lifter = actuators;
		grabber = cubeGrabber;
		isLeft= isScaleLeft;
		}
		
	public void run(){
		swerve.setStartOffset(-90);
		swerve.setAutonomousOffset(0);
		System.out.println("run");
		
		
		if(isLeft){ //If scale is left
			if(!swerve.hasHitBump()){//if the robot has not yet hit the bump
				swerve.autonomousDrive(90, 0,1,1,3);//drive straight--> drive 90 degrees forward for wheels for robot orientation 0 degrees is forward
			}else{//  robot has hit the bump
				if(!swerve.isToDistanceFromWall()){
					swerve.driveXInchesFromSurface(21.5, 0, 3);
				}else{
				swerve.calculateSwerveControl(0, 0, 0);
				if(!hasRisen){
				//lifter.goToPosition(upPosition);
				if(lifter.isToPosition()){
					hasRisen = true;
				}
				}else{
				if(ejectTimer < 100){
					grabber.exportCube();
					ejectTimer++;
					lifter.resetIsToPosition();
				}else{
					if(!lifter.isToPosition()){
						//lifter.goToPosition(downPosition);
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
			if(!swerve.hasHitBump()){//if the robot has not yet hit the bump
				swerve.driveXInchesFromSurface(170, 0, 2);
			}else{
				swerve.calculateSwerveControl(0, 0, 0);
			}
			
			
		}
	}

}
