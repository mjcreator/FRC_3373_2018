package org.usfirst.frc.team3373.robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.EntryNotification;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision {
	NetworkTableInstance inst;
	NetworkTable table;
	NetworkTableEntry entry;

	private int camera1;
	private int camera2;
	
	private Map<String, Integer> cammap = new HashMap<String, Integer>();

	public Vision() {
		inst = NetworkTableInstance.getDefault();
		table = inst.getTable("VisionData");
		entry = table.getEntry("Objects");
		entry.addListener((event) -> dataRefresh(event), EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
		//Map<String, Integer> cammap = new HashMap<String, Integer>();
		cammap.put("front", 0);
		cammap.put("left", 1);
		cammap.put("right", 2);
		cammap.put("back", 3);
		
	}

	ArrayList<VisionObject> objects = new ArrayList<VisionObject>();

	private void dataRefresh(EntryNotification event) {
		try {
			String[] objectData = event.value.getStringArray();
			if (objectData.length == 0) {
				return;
			}
			// System.out.print(objectData);
			objects.clear();
			for (int i = 0; i < objectData.length; i++) {
				// System.out.print(objectData[i]);
				objectData[i] = objectData[i].replace("[", "");
				objectData[i] = objectData[i].replace("]", "");
				String[] data = objectData[i].split(", ");
				System.out.print(data[0]);
				objects.add(new VisionObject(Integer.parseInt(data[0]), Integer.parseInt(data[1]),
						Double.parseDouble(data[2]), Double.parseDouble(data[3]), Double.parseDouble(data[4])));
				// System.out.print(" : ");
			}
			for (int i = 0; i < objects.size(); i++) {
				objects.get(i).print();
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// cameras

	// switch to a camera
	public void switchCamera(String name) {
		int num = cammap.get(name);
		if (num < 5) {
			switchCamera(num, 0);
		}
	}

	// switch to a camera
	public void switchCamera(int num) {
		switchCamera(num, 0);
	}

	// switch to a camera
	public void switchCamera(String name, int stream) {
		int num = cammap.get(name);
		if (num < 5) {
			switchCamera(num, stream);
		}
	}

	// switch to a camera
	public void switchCamera(int num, int stream) {
		if (num < 5) {
			if (stream == 0)
				camera1 = num;
			else if (stream == 1) {
				camera2 = num;
			}
		}
	}

	// preloads a camera for quicker switching
	public void preLoadCamera(String name) {

	}

	// preloads a camera for quicker switching
	public void preLoadCamera(int num) {

	}

	// Detection

	public VisionObject getClosestObject(int id) {
		int closest = -1;
		double cdist = 100000.0;
		for (int i = 0; i < objects.size(); i++) {
			VisionObject object = objects.get(i);
			if (object.id == id && object.distance < cdist) {
				closest = i;
				cdist = object.distance;
			}
		}
		if (closest == -1) {
			return null;
		}
		return objects.get(closest);
	}

	// Gets any object that is closest
	public VisionObject getClosestObject() {
		int closest = -1;
		double cdist = 100000.0;
		for (int i = 0; i < objects.size(); i++) {
			VisionObject object = objects.get(i);
			if (object.distance < cdist) {
				closest = i;
				cdist = object.distance;
			}
		}
		if (closest == -1) {
			return null;
		}
		return objects.get(closest);
	}

	// Gets objects with a lower distance and the same id as parameters
	public ArrayList<VisionObject> getObjectsInRange(int id, double distance) {
		ArrayList<VisionObject> inRange = new ArrayList<VisionObject>();
		for (int i = 0; i < objects.size(); i++) {
			VisionObject object = objects.get(i);
			if (object.id == id && object.distance < distance) {
				inRange.add(object);
			}
		}
		if (inRange.size() == 0) {
			return null;
		}
		return inRange;
	}

	// Gets objects with a lower distance than parameter
	public ArrayList<VisionObject> getObjectsInRange(double distance) {
		ArrayList<VisionObject> inRange = new ArrayList<VisionObject>();
		for (int i = 0; i < objects.size(); i++) {
			VisionObject object = objects.get(i);
			if (object.distance < distance) {
				inRange.add(object);
			}
		}
		if (inRange.size() == 0) {
			return null;
		}
		return inRange;
	}

	// Gets object with highest score for id
	public VisionObject getBestObject(int id) {
		int best = -1;
		int bscore = 0;
		for (int i = 0; i < objects.size(); i++) {
			VisionObject object = objects.get(i);
			if (object.id == id && object.score > bscore) {
				best = i;
				bscore = object.score;
			}
		}
		if (best == -1) {
			return null;
		}
		return objects.get(best);
	}

	// Used with getObjectsInRange
	private VisionObject getBestObject(ArrayList<VisionObject> objectsIn) {
		int best = -1;
		int bscore = 0;
		for (int i = 0; i < objectsIn.size(); i++) {
			VisionObject object = objectsIn.get(i);
			if (object.score > bscore) {
				best = i;
				bscore = object.score;
			}
		}
		if (best == -1) {
			return null;
		}
		return objects.get(best);
	}

	// Gets certain object with highest score and a distance below the
	// parameters
	public VisionObject getBestObjectInRange(int id, double distance) {
		ArrayList<VisionObject> inRange = getObjectsInRange(id, distance);
		if (inRange == null) {
			return null;
		}
		VisionObject bestInRange = getBestObject(inRange);
		return bestInRange;
	}

	// Gets object with x absolute value closest to zero (center of screen)
	public VisionObject getObjectClosestToCenter(int id) {
		int closest = -1;
		double cx = 2.0;
		for (int i = 0; i < objects.size(); i++) {
			VisionObject object = objects.get(i);
			if (object.id == id && Math.abs(object.X) < cx) {
				closest = i;
				cx = Math.abs(object.X);
			}
		}
		if (closest == -1) {
			return null;
		}
		return objects.get(closest);
	}

	// Used with getObjectsInRange
	private VisionObject getObjectClosestToCenter(ArrayList<VisionObject> objectsIn) {
		int closest = -1;
		double cx = 2.0;
		for (int i = 0; i < objectsIn.size(); i++) {
			VisionObject object = objectsIn.get(i);
			if (Math.abs(object.X) < cx) {
				closest = i;
				cx = Math.abs(object.X);
			}
		}
		if (closest == -1) {
			return null;
		}
		return objects.get(closest);
	}

	// Gets object with absolute x value closest to zero with distance less than
	// in parameters
	public VisionObject getObjectClosestToCenterInRange(int id, double distance) {
		ArrayList<VisionObject> inRange = getObjectsInRange(id, distance);
		if (inRange == null) {
			return null;
		}
		VisionObject closestInRange = getObjectClosestToCenter(inRange);
		return closestInRange;
	}

	// Same as above, but all ids
	public VisionObject getObjectClosestToCenterInRange(double distance) {
		ArrayList<VisionObject> inRange = getObjectsInRange(distance);
		if (inRange == null) {
			return null;
		}
		VisionObject closestInRange = getObjectClosestToCenter(inRange);
		return closestInRange;
	}

	// Gets objects with a score greater than parameters
	public ArrayList<VisionObject> getObjectsInScore(int id, int score) {
		ArrayList<VisionObject> inScore = new ArrayList<VisionObject>();
		for (int i = 0; i < objects.size(); i++) {
			VisionObject object = objects.get(i);
			if (object.id == id && object.score < score) {
				inScore.add(object);
			}
		}
		if (inScore.size() == 0) {
			return null;
		}
		return inScore;
	}

	// Used with getObjectsInRange
	private ArrayList<VisionObject> getObjectsInScore(int score, ArrayList<VisionObject> objectsIn) {
		ArrayList<VisionObject> inScore = new ArrayList<VisionObject>();
		for (int i = 0; i < objectsIn.size(); i++) {
			VisionObject object = objectsIn.get(i);
			if (object.score < score) {
				inScore.add(object);
			}
		}
		if (inScore.size() == 0) {
			return null;
		}
		return inScore;
	}

	// Gets objects with distance smaller and score greater than parameters
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
