package application.model.p3_board;


import application.model.p1_utils.Pair;

public class Ant {
	private Pair<Integer, Integer> currPos;
	private Direction currDir;
	private int foodCounter;
	private int actionCounter;
	
	public Ant(Pair<Integer, Integer> currPos, Direction currDir) {
		this.currPos = currPos;
		this.currDir = currDir;
		foodCounter = 0;
		actionCounter = 0;
	}
	
	public Ant(Ant ant) {
		currPos = new Pair<>(ant.currPos.getLeftElement(), ant.currPos.getRightElement());
		currDir = ant.currDir;
		foodCounter = ant.foodCounter;
		actionCounter = ant.actionCounter;
	}
	
	public void incrementFoodCounter() {
		foodCounter++;
	}
	
	public void incrementActionCounter() {
		actionCounter++;
	}
	
	public void turn(boolean left) {
		int nextDir, dirLength;
		if(!left)
			nextDir = currDir.ordinal() + 1;
		else
			nextDir = currDir.ordinal() - 1;
		dirLength = Direction.values().length;
		if(nextDir >= dirLength)
			nextDir = 0;
		else if(nextDir < 0)
			nextDir = dirLength - 1;
		currDir = Direction.values()[nextDir];
		
		actionCounter++;
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
	
	public int getFoodCounter() {
		return foodCounter;
	}

	public int getActionCounter() {
		return actionCounter;
	}

	public Ant clone() {
		return new Ant(this);
	}
}
