package org.first.frc.team3373.autonomous;

import org.usfirst.frc.team3373.robot.DualActuators;
import org.usfirst.frc.team3373.robot.Grabber;
import org.usfirst.frc.team3373.robot.SwerveControl;

public class Auto_5_0 {

	SwerveControl swerve;
	DualActuators lifter;
	Grabber grabber;
	boolean switchLeft;
	boolean scaleLeft;
	boolean hasRisen1;
	boolean hasRisen2;
	boolean hasLowered;
	boolean toDistance1R;
	boolean toDistance2R;
	boolean toDistance3R;
	boolean toRotate1R;
	boolean isAtDistance1;
	boolean isAtDistance2;
	boolean placedOnScale;
	boolean hasSpun1;
	boolean gotCube;
	int liftTimer = 0;
	int driveTimer = 0;
	int exportTimer1 = 0;
	int exportTimer2 = 0;
	int shakeCounter = 0;
	int cubeTimer = 0;
	double height;

	public Auto_5_0(SwerveControl swerveDrive, DualActuators actuators, Grabber cubeGrabber, boolean isSwitchLeft,
			boolean isScaleLeft) {
		swerve = swerveDrive;
		lifter = actuators;
		grabber = cubeGrabber;
		switchLeft = isSwitchLeft;
		scaleLeft = isScaleLeft;
		toDistance1R = false;
		toRotate1R = false;
		gotCube = true;
		height = 27.5;
	}


	public void run() {
		shakeCounter++;
		swerve.setAutonomousBoolean(false);
		if (shakeCounter < 40) {
			swerve.calculateSwerveControl(1, 0, 0);
		} else if (shakeCounter < 80) {
			swerve.calculateSwerveControl(-1, 0, 0);
			swerve.resetBump();
		} else if (shakeCounter < 150) {
			swerve.resetBump();
			swerve.resetPositiveX();
			swerve.calculateSwerveControl(0, 0, 0);

			
		}else if(!switchLeft){
			this.placeSwitch();
		}else if(!scaleLeft){
			this.placeScale();
		}else{
			swerve.autonomousDrive(90, 90, .5, .5);
		}
/*		} else if (!scaleLeft) {

			this.placeScale();

			if (placedOnScale) {
				if (!switchLeft) {
					this.placeFinalSwitch();
				} else {
					this.placeFinalScale();
				}
			}
		} else {
			if (!switchLeft) {
				this.placeSwitch();
			} else {
				swerve.autonomousDrive(90, 90, .5, .5);
			}

		}*/
		lifter.goToPosition(height);
	}

	public void placeScale() {
		if (!isAtDistance1) {
			swerve.driveXInchesFromSurface(150, 90, 3);
			if (swerve.isToDistanceFromWall()) {
				isAtDistance1 = true;
				swerve.resetIsToDistance();
			}

		} else if (!isAtDistance2) {
			swerve.driveXInchesFromSurface(60, 90, 2);
			lifter.resetIsToPosition();
			if (swerve.isToDistanceFromWall()) {
				isAtDistance2 = true;
				height = 2;
			}
		} else if (!hasRisen1) {
			swerve.calculateSwerveControl(0, 0, 0);
			height = 2;
			if(lifter.isToPosition()) {
				hasRisen1 = true;
			}
		} else if (exportTimer1 < 50) {
			swerve.calculateSwerveControl(0, 0, 0);
			exportTimer1++;
			grabber.exportCube();
			lifter.resetIsToPosition();
			if(exportTimer1 == 49){
				height = 27.5;
			}
		} else if (!hasLowered) {
			height = 27.5;
			if (lifter.isToPosition()) {
				hasLowered = true;
			}
		} else if (!hasSpun1) {
			swerve.setSpinAngle(270);
			swerve.spinToXdegrees();
			if (swerve.isAtSpinAngle()) {
				hasSpun1 = true;
			}
		}else if(!grabber.hasCube() && cubeTimer < 200){
		swerve.setDriveDistance(60);
		swerve.autonomousDrive(270, 270, .5, .5, 1);
		cubeTimer ++;
		if(cubeTimer == 200){
			gotCube = false;
		}
		}else{
			if(gotCube){
			placedOnScale = true;
			if(!switchLeft){
			height = 2;
			}
			}else{
				swerve.calculateSwerveControl(0, 0, 0);
				grabber.idle();
			}
		}
	}
	
	public void placeSwitch(){
		if(!isAtDistance1){
			swerve.driveXInchesFromSurface(160, 90, 3);
			if(swerve.isToDistanceFromWall()){
				isAtDistance1 = true;
				height = 18;
			}
		}else if(!lifter.isToPosition() && liftTimer < 150){
		liftTimer++;
		swerve.calculateSwerveControl(0, 0, 0);
		}else if(!hasSpun1){
			swerve.setSpinAngle(180);
			swerve.spinToXdegrees();
			height = 18;
			swerve.resetPositiveX();
			if(swerve.isAtSpinAngle()){
				hasSpun1 = true;
			}
		}else if(!swerve.hasCollidedPositiveX() && driveTimer < 100){
		driveTimer ++;
		swerve.autonomousDrive(180, 180);
		exportTimer1 = 0;
		}else if(exportTimer1 < 200){
			exportTimer1 ++;
			grabber.exportCube();
			swerve.calculateSwerveControl(0, 0, 0);
		}else{
			swerve.calculateSwerveControl(0, 0, 0);
		}
	}
	
	public void placeFinalSwitch(){
		if(!hasRisen2 && liftTimer < 100){
			height = 18;
			liftTimer ++;
			if(lifter.isToPosition()){
				hasRisen2 = true;
				liftTimer = 0;
				exportTimer1 = 0;
			}
		}else{
			swerve.autonomousDrive(270, 270);
			exportTimer1++;
			if(exportTimer1 > 50){
			grabber.exportCube();
			}
		}
	}
	public void placeFinalScale(){
		swerve.calculateSwerveControl(0,0,0);
	}

}
