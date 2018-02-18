package org.usfirst.frc.team3373.robot;

import java.util.ArrayList;

public class Vision {
	public Vision() {

	}
	// cameras

	ArrayList<VisionObject> objects = new ArrayList<VisionObject>();

	// setup cameras
	public boolean addCamera(int num, String name, double calibrateFactor, boolean onPi) {

		return false;
	}

	// switch to a camera
	public void switchCamera(String name) {

	}

	// switch to a camera
	public void switchCamera(int num) {

	}

	// switch to a camera
	public void switchCamera(String name, int stream) {

	}

	// switch to a camera
	public void switchCamera(int num, int stream) {

	}

	// preloads a camera for quicker switching
	public void preLoadCamera(String name) {

	}

	// preloads a camera for quicker switching
	public void preLoadCamera(int num) {

	}

	// Detection

	public VisionObject getClosestObject(int id){
        int closest = -1;
        double cdist = 100000.0;
        for (int i = 0; i < objects.size(); i++) {
            VisionObject object = objects.get(i);
            if(object.id == id && object.distance < cdist){
                closest = i;
                cdist = object.distance;
            }
        }
        if (closest == -1) {
            return null;
        }
        return objects.get(closest);
    }
    //Gets any object that is closest
    public VisionObject getClosestObject(){
        int closest = -1;
        double cdist = 100000.0;
        for (int i = 0; i < objects.size(); i++) {
            VisionObject object = objects.get(i);
            if(object.distance < cdist){
                closest = i;
                cdist = object.distance;
            }
        }
        if (closest == -1) {
            return null;
        }
        return objects.get(closest);
    }
    //Gets objects with a lower distance and the same id as parameters
    public ArrayList<VisionObject> getObjectsInRange(int id, double distance){
        ArrayList<VisionObject> inRange = new ArrayList<VisionObject>();
        for (int i = 0; i < objects.size(); i++) {
            VisionObject object = objects.get(i);
            if(object.id == id && object.distance < distance){
                inRange.add(object);
            }
        }
        if (inRange.size() == 0) {
            return null;
        }
        return inRange;
    }
    //Gets objects with a lower distance than parameter
    public ArrayList<VisionObject> getObjectsInRange(double distance){
        ArrayList<VisionObject> inRange = new ArrayList<VisionObject>();
        for (int i = 0; i < objects.size(); i++) {
            VisionObject object = objects.get(i);
            if(object.distance < distance){
                inRange.add(object);
            }
        }
        if (inRange.size() == 0) {
            return null;
        }
        return inRange;
    }
    //Gets object with highest score for id
    public VisionObject getBestObject(int id){
        int best = -1;
        int bscore = 0;
        for (int i = 0; i < objects.size(); i++) {
            VisionObject object = objects.get(i);
            if(object.id == id && object.score > bscore){
                best = i;
                bscore = object.score;
            }
        }
        if (best == -1) {
            return null;
        }
        return objects.get(best);
    }
    //Used with getObjectsInRange
    private VisionObject getBestObject(ArrayList<VisionObject> objectsIn){
        int best = -1;
        int bscore = 0;
        for (int i = 0; i < objectsIn.size(); i++) {
            VisionObject object = objectsIn.get(i);
            if(object.score > bscore){
                best = i;
                bscore = object.score;
            }
        }
        if (best == -1) {
            return null;
        }
        return objects.get(best);
    }
    //Gets certain object with highest score and a distance below the parameters
    public VisionObject getBestObjectInRange(int id, double distance) {
        ArrayList<VisionObject> inRange = getObjectsInRange(id, distance);
        if (inRange == null) {
            return null;
        }
        VisionObject bestInRange = getBestObject(inRange);
        return bestInRange;
    }
    //Gets object with x absolute value closest to zero (center of screen)
    public VisionObject getObjectClosestToCenter(int id) {
        int closest = -1;
        double cx = 2.0;
        for (int i = 0; i < objects.size(); i++) {
            VisionObject object = objects.get(i);
            if(object.id == id && Math.abs(object.X) < cx){
                closest = i;
                cx = Math.abs(object.X);
            }
        }
        if (closest == -1) {
            return null;
        }
        return objects.get(closest);
    }
    //Used with getObjectsInRange
    private VisionObject getObjectClosestToCenter(ArrayList<VisionObject> objectsIn) {
        int closest = -1;
        double cx = 2.0;
        for (int i = 0; i < objectsIn.size(); i++) {
            VisionObject object = objectsIn.get(i);
            if(Math.abs(object.X) < cx){
                closest = i;
                cx = Math.abs(object.X);
            }
        }
        if (closest == -1) {
            return null;
        }
        return objects.get(closest);
    }
    //Gets object with absolute x value closest to zero with distance less than in parameters
    public VisionObject getObjectClosestToCenterInRange(int id, double distance) {
        ArrayList<VisionObject> inRange = getObjectsInRange(id, distance);
        if (inRange == null) {
            return null;
        }
        VisionObject closestInRange = getObjectClosestToCenter(inRange);
        return closestInRange;
    }
    //Same as above, but all ids
    public VisionObject getObjectClosestToCenterInRange(double distance) {
        ArrayList<VisionObject> inRange = getObjectsInRange(distance);
        if (inRange == null) {
            return null;
        }
        VisionObject closestInRange = getObjectClosestToCenter(inRange);
        return closestInRange;
    }
    //Gets objects with a score greater than parameters
    public ArrayList<VisionObject> getObjectsInScore(int id, int score) {
         ArrayList<VisionObject> inScore = new ArrayList<VisionObject>();
        for (int i = 0; i < objects.size(); i++) {
            VisionObject object = objects.get(i);
            if(object.id == id && object.score < score){
                inScore.add(object);
            }
        }
        if (inScore.size() == 0) {
            return null;
        }
        return inScore;
    }
    //Used with getObjectsInRange
    private ArrayList<VisionObject> getObjectsInScore(int score, ArrayList<VisionObject> objectsIn) {
         ArrayList<VisionObject> inScore = new ArrayList<VisionObject>();
        for (int i = 0; i < objectsIn.size(); i++) {
            VisionObject object = objectsIn.get(i);
            if(object.score < score){
                inScore.add(object);
            }
        }
        if (inScore.size() == 0) {
            return null;
        }
        return inScore;
    }
    //Gets objects with distance smaller and score greater than parameters
    public ArrayList<VisionObject> getObjectsInScoreInRange(int id, double distance, int score) {
        ArrayList<VisionObject> inRange = getObjectsInRange(id, distance);
        if (inRange == null) {
            return null;
        }
        ArrayList<VisionObject> inScoreInRange = getObjectsInScore(score, inRange);
        if (inScoreInRange == null) {
            return null;
        }
        return inScoreInRange;
    }


	public int size() {
		return objects.size();
	}

}
