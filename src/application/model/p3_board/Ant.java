package application.model.p3_board;

import java.util.ArrayList;

import application.model.p1_utils.Pair;

public class Ant {
	private Pair<Integer, Integer> currPos;
	private Direction currDir;
	private int foodCounter;
	
	public Ant(Pair<Integer, Integer> currPos, Direction currDir) {
		this.currPos = currPos;
		this.currDir = currDir;
		foodCounter = 0;
	}
	
	public void incrementFoodCounter() {
		foodCounter++;
	}
	
	public void turn(boolean left) {
		int nextDir;
		if(!left)
			nextDir= currDir.ordinal() + 1;
		else
			nextDir= currDir.ordinal() - 1;
		if(nextDir >= Direction.values().length)
			nextDir = 0;
		currDir = Direction.values()[nextDir];
	}

	public Pair<Integer, Integer> getCurrPos() {
		return currPos;
	}

	public void setCurrPos(Pair<Integer, Integer> currPos) {
		this.currPos = currPos;
	}

	public Direction getCurrDir() {
		return currDir;
	}

	public void setCurrDir(Direction currDir) {
		this.currDir = currDir;
	}
	
	
}
