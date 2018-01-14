/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3373.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	
	
	int LX = 0;
	int LY = 1;
	int Ltrigger = 2;
	int Rtrigger = 3;
	int RX = 4;
	int RY = 5;
	
	DualActuators actuators;
	Actuator actuator1;
	Actuator actuator2;
	
	SupremeTalon sim;
	
	SuperJoystick shooter;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		actuators = new DualActuators(5,4,.05,986,986,103,105,30,30,1,1);
		actuator1= new Actuator(5,.05,986,103,30,1);
		actuator2 = new Actuator(4,.05,986,105,30,1);
		
		sim = new SupremeTalon(1,.05);
		
		shooter = new SuperJoystick(0);
		
		
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
	}

	/**
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
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		actuator1.set(shooter.getRawAxis(LY));
		actuator2.set(shooter.getRawAxis(RY));
		SmartDashboard.putNumber("Pot1", actuator1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Pot2", actuator2.getSelectedSensorPosition(0));
		/*
		switch (m_autoSelected) {
			case kCustomAuto:
				// Put custom auto code here
				break;
			case kDefaultAuto:
			default:
				// Put default auto code here
				break;*/
		//}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		//System.out.println("Pot1"+ actuator1.getSelectedSensorPosition(0));
		//System.out.println("Pot2"+ actuator2.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Pot1", actuator1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Pot2", actuator2.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Distance1", actuator1.getPosition());
		SmartDashboard.putNumber("Distance2", actuator2.getPosition());
		//sim.accelerate(-1,.05);
		if(shooter.getRawAxis(Rtrigger)>.1){
			
			actuators.goToPosition(20);
		}
		else if(shooter.getRawAxis(Ltrigger)>.1){
			actuators.goToPosition(5);
		}else
			actuators.goToPosition(actuators.getPosition());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		actuator1.superSet(shooter.getRawAxis(LY));
		actuator2.superSet(shooter.getRawAxis(RY));
		SmartDashboard.putNumber("Pot1", actuator1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Pot2", actuator2.getSelectedSensorPosition(0));
	}
}
