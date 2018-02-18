package org.usfirst.frc.team3373.robot;

public class VisionObject {
	public VisionObject(int id,int score, double positionX, double positionY,double distance){
		this.id = id;
		this.score=score;
		this.X=positionX;
		this.Y=positionY;
		this.distance=distance;
	}
	
	public int id;
	public int score;
	public double X;
	public double Y;
	public double distance;
}
