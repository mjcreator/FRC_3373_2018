package org.usfirst.frc.team3373.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClimbBar {
	Relay solenoid;
	boolean heldUp = true;
	
	public ClimbBar(int relayPort){
		solenoid = new Relay(relayPort, Relay.Direction.kBoth);
	//	solenoid.set(Relay.Value.kForward);
	}
	
	public void setState(boolean isHeldUp){
		heldUp = isHeldUp;
		SmartDashboard.putBoolean("Heldup: ", heldUp);
	}
	
	public void controlDropBar(){
		SmartDashboard.putBoolean("HELDUP!: ", heldUp);
		//System.out.println(solenoid.get());
		if(heldUp == true){
			solenoid.set(Relay.Value.kReverse);
		}else{
			solenoid.set(Relay.Value.kForward);
		}
	}

}
