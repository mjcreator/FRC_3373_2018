package org.first.frc.team3373.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import org.usfirst.frc.team3373.robot.*;

public class AutonomousControl {
	int positionalID;
	int programID;
	String locations;
	
	SwerveControl swerve;
	DualActuators lifter;
	Grabber grabber;
	
	Auto_Drive_Straight driveStraight;
	Auto_1_0 auto_1_0;
	Auto_1_1 auto_1_1;
	Auto_2_0 auto_2_0;
	Auto_2_1 auto_2_1;
	Auto_2_2 auto_2_2;
	Auto_3_0 auto_3_0;
	Auto_3_1 auto_3_1;
	Auto_3_2 auto_3_2;
	Auto_4_0 auto_4_0;
	Auto_4_1 auto_4_1;
	Auto_4_2 auto_4_2;
	Auto_5_0 auto_5_0;
	Auto_5_1 auto_5_1;
	
	public AutonomousControl(int positional, int program, SwerveControl swerveDrive, DualActuators actuators, Grabber cubeGrabber){
		positionalID = positional;
		programID = program;
		locations = DriverStation.getInstance().getGameSpecificMessage();

		swerve = swerveDrive;
		lifter = actuators;
		grabber = cubeGrabber;
		
		
		driveStraight = new Auto_Drive_Straight(swerve, lifter ,grabber);
		auto_1_0 = new Auto_1_0(swerve, lifter ,grabber, this.isScaleLeft());
		auto_1_1 = new Auto_1_1(swerve, lifter ,grabber, this.isScaleLeft());
		auto_2_0 = new Auto_2_0(swerve, lifter ,grabber,this.isSwitchLeft(),this.isScaleLeft());
		auto_2_1 = new Auto_2_1(swerve, lifter ,grabber);
		auto_2_2 = new Auto_2_2(swerve, lifter ,grabber);
		auto_3_0 = new Auto_3_0(swerve, lifter ,grabber);
		auto_3_1 = new Auto_3_1(swerve, lifter ,grabber);
		auto_3_2 = new Auto_3_2(swerve, lifter ,grabber);
		auto_4_0 = new Auto_4_0(swerve, lifter ,grabber);
		auto_4_1 = new Auto_4_1(swerve, lifter ,grabber);
		auto_4_2 = new Auto_4_2(swerve, lifter ,grabber);
		auto_5_0 = new Auto_5_0(swerve, lifter ,grabber);
		auto_5_1 = new Auto_5_1(swerve, lifter ,grabber);

	}
	
	public void activateAuto(){
		swerve.setAutonomousOffset(90);
		auto_1_1.run();
	/*	switch (positionalID) {
		case 0:
			driveStraight.run();
			
			
			break;
		case 1:
			
			switch (programID) {
			case 0:
				auto_1_0.run();
				break;
			case 1:
				auto_1_1.run();
				break;
			case 2:
				driveStraight.run();
				break;
			case 3:
				driveStraight.run();
				break;
			case 4:
				driveStraight.run();
				break;
			case 5:
				driveStraight.run();
				break;
			case 6:
				driveStraight.run();
				break;
			case 7:
				driveStraight.run();
				break;
			}
			
			
			break;
		case 2:
			
			switch (programID) {
			case 0:
				auto_2_0.run();
				System.out.println("running");
				break;
			case 1:
				auto_2_1.run();
				break;
			case 2:
				auto_2_2.run();
				break;
			case 3:
				driveStraight.run();
				break;
			case 4:
				driveStraight.run();
				break;
			case 5:
				driveStraight.run();
				break;
			case 6:
				driveStraight.run();
				break;
			case 7:
				driveStraight.run();
				break;
			}
			
			
			break;
		case 3:
			
			switch (programID) {
			case 0:
				auto_3_0.run();
				break;
			case 1:
				auto_3_1.run();
				break;
			case 2:
				auto_3_2.run();
				break;
			case 3:
				driveStraight.run();
				break;
			case 4:
				driveStraight.run();
				break;
			case 5:
				driveStraight.run();
				break;
			case 6:
				driveStraight.run();
				break;
			case 7:
				driveStraight.run();
				break;
			}
			
			
			
			break;
		case 4:

			switch (programID) {
			case 0:
				auto_4_0.run();
				break;
			case 1:
				auto_4_1.run();
				break;
			case 2:
				auto_4_2.run();
				break;
			case 3:
				driveStraight.run();
				break;
			case 4:
				driveStraight.run();
				break;
			case 5:
				driveStraight.run();
				break;
			case 6:
				driveStraight.run();
				break;
			case 7:
				driveStraight.run();
				break;
			}
			
			
			
			break;
		case 5:

			switch (programID) {
			case 0:
				auto_5_0.run();
				break;
			case 1:
				auto_5_1.run();
				break;
			case 2:
				driveStraight.run();
				break;
			case 3:
				driveStraight.run();
				break;
			case 4:
				driveStraight.run();
				break;
			case 5:
				driveStraight.run();
				break;
			case 6:
				driveStraight.run();
				break;
			case 7:
				driveStraight.run();
				break;
			}
			
			
			break;
		case 6:
			driveStraight.run();
			
			
			
			break;
		case 7:
			driveStraight.run();
			
			
			

			break;
	}*/
	
	}
	
	public boolean isSwitchLeft(){
		if(locations.charAt(0) == 'L'){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isScaleLeft(){
		if(locations.charAt(1) == 'L'){
			return true;
		}else{
			return false;
		}
	}
	
}
