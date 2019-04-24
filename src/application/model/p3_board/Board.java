package application.model.p3_board;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import application.model.p1_utils.Pair;

public class Board {
	private final int DEFAULT_HEIGHT = 32;
	private final int DEFAULT_WIDTH = 32;
	private final double FOOD_PERCENTAGE = 0.2; 
	
	protected Character[][] board;
	
	protected Ant ant;
	public Board() {
		this.ant = new Ant(new Pair<Integer, Integer>(0,0), Direction.EAST);
	}
	
	public Board(Integer height, Integer width) {
		int h, w;
		if(height != null)
			h = height;
		else
			h = DEFAULT_HEIGHT;
		if(width != null)
			w = width;
		else
			w = DEFAULT_WIDTH;
		board = new Character[h][w];
	}
	
	public void moveAnt() {
		Pair<Integer, Integer> pos;
		pos = ant.getCurrPos();
		board[pos.getLeftElement()][pos.getRightElement()] = '0';
		switch(ant.getCurrDir()) {
		case NORTH:
			if(pos.getLeftElement() - 1 < 0)
				ant.setCurrPos(new Pair<Integer, Integer>(DEFAULT_HEIGHT - 1, pos.getRightElement()));
			else
				ant.setCurrPos(new Pair<Integer, Integer>(pos.getLeftElement() - 1, pos.getRightElement()));
			break;
		case EAST:
			if(pos.getRightElement() + 1 >= DEFAULT_WIDTH)
				ant.setCurrPos(new Pair<Integer, Integer>(pos.getLeftElement(), 0));
			else
				ant.setCurrPos(new Pair<Integer, Integer>(pos.getLeftElement(), pos.getRightElement() + 1));
			break;
		case SOUTH:
			if(pos.getLeftElement() + 1 >= DEFAULT_HEIGHT)
				ant.setCurrPos(new Pair<Integer, Integer>(0, pos.getRightElement()));
			else
				ant.setCurrPos(new Pair<Integer, Integer>(pos.getLeftElement() + 1, pos.getRightElement()));
			break;
		case WEST:
			if(pos.getRightElement() - 1 < 0)
				ant.setCurrPos(new Pair<Integer, Integer>(pos.getLeftElement(), DEFAULT_WIDTH - 1));
			else
				ant.setCurrPos(new Pair<Integer, Integer>(pos.getLeftElement(), pos.getRightElement() - 1));
			break;		
		}
		pos = ant.getCurrPos();
		if(board[pos.getLeftElement()][pos.getRightElement()] == '#') {
			ant.incrementFoodCounter();
			board[pos.getLeftElement()][pos.getRightElement()] = '@';
		}
	}
	
	private ArrayList<Boolean> createRandomFilledArray(int n) {
		ArrayList<Boolean> filledArray = new ArrayList<Boolean>(n);
		double p;
		for(int i = 0; i < n; i++) {
			p = ThreadLocalRandom.current().nextDouble();
			filledArray.add(p < FOOD_PERCENTAGE);	
		}
		return filledArray;
	}
}
