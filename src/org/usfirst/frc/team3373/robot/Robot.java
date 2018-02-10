/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3373.robot;


import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	int robotCounter = 0;
	
	int FLEncoderMin = 300;
	int FLEncoderMax = 300;
	int FREncoderMin = 300;
	int FREncoderMax = 300;
	int BLEncoderMin = 300;
	int BLEncoderMax = 300;
	int BREncoderMin = 300;
	int BREncoderMax = 300;
	
	
	//Swerve Initialization
	SwerveControl swerve;
	
	double robotWidth = 21.125; //TODO change robot dimensions to match this years robot
	double robotLength = 33.5;
	int LBdriveChannel = 1;
	int LBrotateID = 2;
	int LBencOffset = 433; // Zero values (value when wheel is turned to default 
	int LBEncMin = 11; // zero- bolt hole facing front.)
	int LBEncMax = 873;

	int LFdriveChannel = 4;
	int LFrotateID = 3;
	int LFencOffset = 598;
	int LFEncMin = 15;
	int LFEncMax = 894;

	int RBdriveChannel = 8;
	int RBrotateID = 7;
	int RBencOffset = 475;
	int RBEncMin = 12;
	int RBEncMax = 897;

	int RFdriveChannel = 6;
	int RFrotateID = 5;
	int RFencOffset = 207;
	int RFEncMin = 12;
	int RFEncMax = 895;
	
	//Dual Linear Actuator Configs
	//Look at Actuator.calibrate to view documentaion about how to calculate individual Actuators
	static int actuator1Port1 = 7;
	static int actuator1Port2 = 8;
	static int actuator2Port1 = 10;
	static int actuator2Port2 = 9;
	static double minPot1 = 97;
	static double minPot2 = 95;
	static double maxPot1 = 725;
	static double maxPot2 = 726;
	static double minDistance1 = 2;
	static double minDistance2 = 2;
	static double maxDistance1 = 26.5;
	static double maxDistance2 = 26.5;
	
	DigitalInput ones; // Input for the 16-slot dial
	DigitalInput twos;
	DigitalInput fours;
	
	//Grabber Initialization
	int grabberPort1 =0; // Need to updtate for the Robot
	int grabberPort2 =0;
	///Grabber grabber;
	
	
	
	//Values for SuperJoystick getRawAxis()
	int LX = 0;
	int LY = 1;
	int Ltrigger = 2;
	int Rtrigger = 3;
	int RX = 4;
	int RY = 5;
	
	Actuator act1;
	
	DualActuators actuators;
	
	SupremeTalon sim;
	
	SuperJoystick driver;
	SuperJoystick shooter;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		robotCounter = 0;
		this.setPeriod(.01);
				
		swerve.LBWheel.rotateMotor.setSelectedSensorPosition(swerve.LBWheel.rotateMotor.getSensorCollection().getAnalogInRaw(), 0, 0);
		swerve.RBWheel.rotateMotor.setSelectedSensorPosition(swerve.RBWheel.rotateMotor.getSensorCollection().getAnalogInRaw(), 0, 0);
		swerve.LFWheel.rotateMotor.setSelectedSensorPosition(swerve.LFWheel.rotateMotor.getSensorCollection().getAnalogInRaw(), 0, 0);
		swerve.RFWheel.rotateMotor.setSelectedSensorPosition(swerve.RFWheel.rotateMotor.getSensorCollection().getAnalogInRaw(), 0, 0);
		
		ones = new DigitalInput(0);
		twos = new DigitalInput(1);
		fours = new DigitalInput(2);
		
		actuators = new DualActuators(actuator1Port1,actuator2Port1,actuator1Port2,actuator2Port2,maxPot1,maxPot2,minPot1,minPot2,maxDistance1,maxDistance2,minDistance1,minDistance2);
		driver = new SuperJoystick(0);
		shooter = new SuperJoystick(1);
		//grabber = new Grabber(grabberPort1,grabberPort2);
	}

	/**
	 * Up is negative, Down is positive
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		String locations = DriverStation.getInstance().getGameSpecificMessage();
		if(locations.charAt(0) == 'L'){
			System.out.println("LeftSide Switch");
		}else{
			System.out.println("RightSide Switch");
		}
		if(locations.charAt(1) == 'L'){
			System.out.println("LeftSide Scale");
		}else{
			System.out.println("RightSide Scale");
		}

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
		swerve.setAutonomousBoolean(true);
		
		
		
		swerve.LBWheel.rotateMotor.setSelectedSensorPosition(swerve.LBWheel.rotateMotor.getSensorCollection().getAnalogInRaw(), 0, 0);
		swerve.RBWheel.rotateMotor.setSelectedSensorPosition(swerve.RBWheel.rotateMotor.getSensorCollection().getAnalogInRaw(), 0, 0);
		swerve.LFWheel.rotateMotor.setSelectedSensorPosition(swerve.LFWheel.rotateMotor.getSensorCollection().getAnalogInRaw(), 0, 0);
		swerve.RFWheel.rotateMotor.setSelectedSensorPosition(swerve.RFWheel.rotateMotor.getSensorCollection().getAnalogInRaw(), 0, 0);
		
		int startingPositionIndex = 1;// for testing purposes
		if (ones.get()) {
			startingPositionIndex += 1;
		}
		if (twos.get()) {
			startingPositionIndex += 2;
		}
		if (fours.get()) {
			startingPositionIndex += 4;
		}
		if (startingPositionIndex == 8) {
			startingPositionIndex = 0;
		}
		
		switch (startingPositionIndex) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		swerve.setAutonomousBoolean(false);
		
		
		
		swerve.LBWheel.rotateMotor.setSelectedSensorPosition(swerve.LBWheel.rotateMotor.getSensorCollection().getAnalogInRaw(), 0, 0);
		swerve.RBWheel.rotateMotor.setSelectedSensorPosition(swerve.RBWheel.rotateMotor.getSensorCollection().getAnalogInRaw(), 0, 0);
		swerve.LFWheel.rotateMotor.setSelectedSensorPosition(swerve.LFWheel.rotateMotor.getSensorCollection().getAnalogInRaw(), 0, 0);
		swerve.RFWheel.rotateMotor.setSelectedSensorPosition(swerve.RFWheel.rotateMotor.getSensorCollection().getAnalogInRaw(), 0, 0);
		
		
		
		//Lift Code for Actuators Moving Together
		if(shooter.getRawAxis(Rtrigger)>.1)
			actuators.goToPosition(26.5);
		else if(shooter.getRawAxis(Ltrigger)>.1)
			actuators.goToPosition(3);
		else
			actuators.goToPosition(actuators.getPosition());
		
		//Grabber Code
		/*if(shooter.isLBHeld())
			grabber.exportCube();
		else if(shooter.isRBHeld())
			grabber.importCube();
		else if(grabber.hasCube())
			grabber.keepCube();
		else
			grabber.idle();
			*/
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		int index = 1;// for testing purposes
		if (ones.get()) {
			index += 1;
		}
		if (twos.get()) {
			index += 2;
		}
		if (fours.get()) {
			index += 4;
		}
		if (index == 8) {
			index = 0;
		}
		
		switch (index) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			calibrateSwerve();
			break;
		}
		
		
		/*actuators.calibrate(shooter, false);
		actuators.calibrate(shooter, true); */
		
	}
	
	public void calibrateSwerve(){
		
		swerve.RFWheel.rotateMotor.set(ControlMode.Disabled, 0);
		swerve.RBWheel.rotateMotor.set(ControlMode.Disabled, 0);
		swerve.LFWheel.rotateMotor.set(ControlMode.Disabled, 0);
		swerve.LBWheel.rotateMotor.set(ControlMode.Disabled, 0);

		if (swerve.RFWheel.rotateMotor.getSensorCollection().getAnalogInRaw() < FREncoderMin) {
			FREncoderMin = swerve.RFWheel.rotateMotor.getSensorCollection().getAnalogInRaw();
		}
		if (swerve.RFWheel.rotateMotor.getSensorCollection().getAnalogInRaw() > FREncoderMax) {
			FREncoderMax = swerve.RFWheel.rotateMotor.getSensorCollection().getAnalogInRaw();
		}

		if (swerve.RBWheel.rotateMotor.getSensorCollection().getAnalogInRaw() < BREncoderMin) {
			BREncoderMin = swerve.RBWheel.rotateMotor.getSensorCollection().getAnalogInRaw();
		}
		if (swerve.RBWheel.rotateMotor.getSensorCollection().getAnalogInRaw() > BREncoderMax) {
			BREncoderMax = swerve.RBWheel.rotateMotor.getSensorCollection().getAnalogInRaw();
		}

		if (swerve.LFWheel.rotateMotor.getSensorCollection().getAnalogInRaw() < FLEncoderMin) {
			FLEncoderMin = swerve.LFWheel.rotateMotor.getSensorCollection().getAnalogInRaw();
		}
		if (swerve.LFWheel.rotateMotor.getSensorCollection().getAnalogInRaw() > FLEncoderMax) {
			FLEncoderMax = swerve.LFWheel.rotateMotor.getSensorCollection().getAnalogInRaw();
		}

		if (swerve.LBWheel.rotateMotor.getSensorCollection().getAnalogInRaw() < BLEncoderMin) {
			BLEncoderMin = swerve.LBWheel.rotateMotor.getSensorCollection().getAnalogInRaw();
		}
		if (swerve.LBWheel.rotateMotor.getSensorCollection().getAnalogInRaw() > BLEncoderMax) {
			BLEncoderMax = swerve.LBWheel.rotateMotor.getSensorCollection().getAnalogInRaw();
		}
		SmartDashboard.putNumber("FL Encoder Min: " , FLEncoderMin);
		SmartDashboard.putNumber("FL Encoder Max: " , FLEncoderMax);

		SmartDashboard.putNumber("FR Encoder Min: " , FREncoderMin);
		SmartDashboard.putNumber("FR Encoder Max: " , FREncoderMax);

		SmartDashboard.putNumber("BL Encoder Min: " , BLEncoderMin);
		SmartDashboard.putNumber("BL Encoder Max: " , BLEncoderMax);

		SmartDashboard.putNumber("BR Encoder Min: " , BREncoderMin);
		SmartDashboard.putNumber("BR Encoder Max: " , BREncoderMax);

		SmartDashboard.putNumber("Current FL Encoder: " , swerve.LFWheel.rotateMotor.getSensorCollection().getAnalogInRaw());
		SmartDashboard.putNumber("Current FR Encoder: " , swerve.RFWheel.rotateMotor.getSensorCollection().getAnalogInRaw());
		SmartDashboard.putNumber("Current BL Encoder: " , swerve.LBWheel.rotateMotor.getSensorCollection().getAnalogInRaw());
		SmartDashboard.putNumber("Current BR Encoder: " , swerve.RBWheel.rotateMotor.getSensorCollection().getAnalogInRaw());

	}
	
	public void activateControl(){
		swerve.LBWheel.rotateMotor.setSelectedSensorPosition(swerve.LBWheel.rotateMotor.getSensorCollection().getAnalogInRaw(), 0, 0);
		swerve.RBWheel.rotateMotor.setSelectedSensorPosition(swerve.RBWheel.rotateMotor.getSensorCollection().getAnalogInRaw(), 0, 0);
		swerve.LFWheel.rotateMotor.setSelectedSensorPosition(swerve.LFWheel.rotateMotor.getSensorCollection().getAnalogInRaw(), 0, 0);
		swerve.RFWheel.rotateMotor.setSelectedSensorPosition(swerve.RFWheel.rotateMotor.getSensorCollection().getAnalogInRaw(), 0, 0);
		swerve.setPID();
		
		SmartDashboard.putNumber("Navx", swerve.ahrs.getYaw());
		
		
		
		
		//   *_*_*_*_*_*_*_* DRIVER MAIN CONTROLS *_*_*_*_*_*_*_*
		
		if (driver.isLBHeld()) {
			swerve.sniper();
		} else if (driver.isRBHeld()) {
			swerve.turbo();
		} else {
			swerve.normalSpeed();
		}
		
		
		
		
		
	}
	
	
}
