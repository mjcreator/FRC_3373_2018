package org.first.frc.team3373.autonomous;
import org.usfirst.frc.team3373.robot.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto_Drive_Straight {

	SwerveControl swerve;
	DualActuators lifter;
	Grabber grabber;
	boolean switchLeft;
	boolean scaleLeft;
	int driveCounter;
	boolean spinCheck;
	boolean spinCheck1;
	boolean spinCheck2;
	boolean spinCheck3;
	boolean spinCheck4;
	boolean spinCheck5;
	boolean spinCheck6;
	boolean spinCheck7;
	boolean spinCheck8;
	boolean spinCheck9;
	boolean spinCheck10;
	boolean spinCheck11;
	boolean spinCheck12;
	
		public Auto_Drive_Straight(SwerveControl swerveDrive, DualActuators actuators, Grabber cubeGrabber, boolean isSwitchLeft, boolean isScaleLeft){
		swerve = swerveDrive;
		lifter = actuators;
		grabber = cubeGrabber;
		switchLeft = isSwitchLeft;
		scaleLeft  = isScaleLeft;
		driveCounter = 0;
		spinCheck = false;
		spinCheck1 = false;
		spinCheck2 = false;
		spinCheck3 = false;
		spinCheck4 = false;
		spinCheck5 = false;
		spinCheck6 = false;
		spinCheck7 = false;
		spinCheck8 = false;
		spinCheck9 = false;
		spinCheck10 = false;
		spinCheck11 = false;
		spinCheck12 = false;
		}
		
		public void run(){
			driveCounter ++;
			System.out.println(driveCounter + "...");
		//swerve.sniper();
			if(!switchLeft){
				System.out.println("Right...");
				swerve.autonomousDrive(0, 90,.8,.8);
				if(driveCounter > 350){
					grabber.exportCube();
				}
			}else{
				System.out.println("Left...");
				if(driveCounter < 350){
					swerve.autonomousDrive(90, 90, .5, .5);
				}else{
					swerve.autonomousDrive(0, 90,.8,.8);
					if(driveCounter > 650){
						grabber.exportCube();
					}
				}
			}
		}

}
