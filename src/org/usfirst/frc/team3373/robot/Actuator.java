package org.usfirst.frc.team3373.robot;
import com.ctre.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
public class Actuator extends SupremeTalon{
	double position1;
	double maxPot;
	double minPot;
	double maxDistance;
	double minDistance;
	public Actuator(int port, double maxDelta,double maxPot1, double minPot1, double maxTravel, double minTravel){
		super(port,maxDelta);
		maxPot = maxPot1;
		minPot = minPot1;
		maxDistance = maxTravel; // Distance is in Centimeters
		minDistance = minTravel;
		position1 = this.getPosition();
		}
	public double getPosition(){
		//returns the distance in centimeters
		double deltaPot = maxPot-minPot;
		double deltaDistance = maxDistance - minDistance;
		double slope = deltaDistance/deltaPot;
		return slope*(this.getSelectedSensorPosition(0)-100)+1;
	}
	public double getRawPosition(int num){
		//returns Analog Position
		return this.getSelectedSensorPosition(num);
	}
	public void set(double speed){
		//
		if(getRawPosition(0)>maxPot-100){
			if(speed<0){
				super.accelerate(speed,.075);
			}else{
				super.accelerate(0,.75);
			}
		}else if(getRawPosition(0)<minPot+100){
			if(speed>0){
				super.accelerate(speed,.075);
			}else{
				super.accelerate(0,.75);
			}
		}else {
			super.accelerate(speed,.075);
		}
	}
}
