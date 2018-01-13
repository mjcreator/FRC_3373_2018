package org.usfirst.frc.team3373.robot;
import com.ctre.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
public class Actuator extends SupremeTalon{
	//pot 1 max -960 29.2   min 100 1 cm     29.2, min 1 cm -101 relationship: .03279069767      287
	/*m = deltaY/deltaX
	 * cm = y
	 * 28.2/860 (x-100) + 1cm = cms
	 */
	// Pot 1, 6cm, -219
	//pot 1, 12cm, -385
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
		double deltaPot = maxPot-minPot;
		double deltaDistance = maxDistance - minDistance;
		double slope = deltaDistance/deltaPot;
		return slope*(this.getSelectedSensorPosition(0)-100)+1;
	}
	public double getRawPossition(int num){
		return this.getSelectedSensorPosition(num);
	}
	public double getVelocity(){
		return 2;
	}
	public void set(double speed){
		if(getRawPossition(0)>maxPot){
			if(speed<0){
				super.set(speed);
			}else{
				super.set(0);
			}
		}else if(getRawPossition(0)<minPot){
			if(speed<0){
				super.set(speed);
			}else{
				super.set(0);
			}
		}else {
			super.set(speed);
		}
	}
}
