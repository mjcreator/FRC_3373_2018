package org.first.frc.team3373.autonomous;
import org.usfirst.frc.team3373.robot.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto_Drive_Straight {

	SwerveControl swerve;
	DualActuators lifter;
	Grabber grabber;
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
	
		public Auto_Drive_Straight(SwerveControl swerveDrive, DualActuators actuators, Grabber cubeGrabber){
		swerve = swerveDrive;
		lifter = actuators;
		grabber = cubeGrabber;
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
			SmartDashboard.putNumber("Angle", (360 - swerve.ahrs.getYaw())%360);
			if(!spinCheck){
				swerve.setSpinAngle(0);
				swerve.spinToXdegrees();
				if(swerve.isAtSpinAngle()){
					spinCheck = true;
					swerve.setDriveDistance(swerve.ultraSonicSensors.getDistance(1));
				}
			}else{
				if(!swerve.hasHitBump()){
					swerve.autonomousDrive(180, 0, 1, 1, 1);
					SmartDashboard.putNumber("Distance from wall: ", swerve.ultraSonicSensors.getDistance(1));
				}else{
					driveCounter++;
					if(driveCounter < 100){
					swerve.autonomousDrive(0, 0, 1, 1, 1);
					swerve.setSpinAngle(90);
					}else if(!swerve.isAtSpinAngle()){
						swerve.setSpinAngle(90);
						swerve.spinToXdegrees();
					}else{
						swerve.calculateSwerveControl(0, 0, 0);
					}
					
					
					
				}
			}
			
/*			if(!spinCheck1){
				swerve.setSpinAngle(0);
				swerve.spinToXdegrees();
				if(swerve.isAtSpinAngle()){
					spinCheck1 = true;
				}
			}else{
				if(!spinCheck2){
					swerve.setSpinAngle(30);
					swerve.spinToXdegrees();
					if(swerve.isAtSpinAngle()){
						spinCheck2 = true;
					}
				}else{
					if(!spinCheck3){
						swerve.setSpinAngle(60);
						swerve.spinToXdegrees();
						if(swerve.isAtSpinAngle()){
							spinCheck3 = true;
						}
					}else{
						if(!spinCheck4){
							swerve.setSpinAngle(90);
							swerve.spinToXdegrees();
							if(swerve.isAtSpinAngle()){
								spinCheck4 = true;
							}
						}else{
							if(!spinCheck5){
								swerve.setSpinAngle(120);
								swerve.spinToXdegrees();
								if(swerve.isAtSpinAngle()){
									spinCheck5 = true;
								}
							}else{
								if(!spinCheck6){
									swerve.setSpinAngle(150);
									swerve.spinToXdegrees();
									if(swerve.isAtSpinAngle()){
										spinCheck6 = true;
									}
								}else{
									if(!spinCheck7){
										swerve.setSpinAngle(180);
										swerve.spinToXdegrees();
										if(swerve.isAtSpinAngle()){
											spinCheck7 = true;
										}
									}else{
										if(!spinCheck8){
											swerve.setSpinAngle(210);
											swerve.spinToXdegrees();
											if(swerve.isAtSpinAngle()){
												spinCheck8 = true;
											}
										}else{
											if(!spinCheck9){
												swerve.setSpinAngle(240);
												swerve.spinToXdegrees();
												if(swerve.isAtSpinAngle()){
													spinCheck9 = true;
												}
											}else{
												if(!spinCheck10){
													swerve.setSpinAngle(270);
													swerve.spinToXdegrees();
													if(swerve.isAtSpinAngle()){
														spinCheck10 = true;
													}
												}else{
													if(!spinCheck11){
														swerve.setSpinAngle(300);
														swerve.spinToXdegrees();
														if(swerve.isAtSpinAngle()){
															spinCheck11 = true;
														}
													}else{
														if(!spinCheck12){
															swerve.setSpinAngle(330);
															swerve.spinToXdegrees();
															if(swerve.isAtSpinAngle()){
																spinCheck12 = true;
															}
														}else{
															if(!spinCheck2){
																swerve.setSpinAngle(360);
																swerve.spinToXdegrees();
																if(swerve.isAtSpinAngle()){
																	spinCheck2 = true;
																}
															}else{
																swerve.calculateSwerveControl(0, 0, 0);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					
				}
			}*/
			
			
			
		/*	swerve.calculateSwerveControl(1, 0, 0);
			lifter.goToPosition(22);*/
			//Grabber deployment.
		}

}
