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
	
	public AutonomousControl(int positional, int program, SwerveControl swerveDrive, DualActuators actuators, Grabber cubeGrabber){
		positionalID = positional;
		programID = program;
		locations = DriverStation.getInstance().getGameSpecificMessage();

		swerve = swerveDrive;
		lifter = actuators;
		grabber = cubeGrabber;
		
		
		driveStraight = new Auto_Drive_Straight(swerve, lifter ,grabber);
	}
	
	public void activateAuto(){
		switch (positionalID) {
		case 0:
			driveStraight.run();
			
			
			break;
		case 1:
			
			switch (programID) {
			case 0:
				break;
			case 1:
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
				break;
			case 1:
				break;
			case 2:
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
				break;
			case 1:
				break;
			case 2:
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
				break;
			case 1:
				break;
			case 2:
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
				break;
			case 1:
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
	}
	
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
