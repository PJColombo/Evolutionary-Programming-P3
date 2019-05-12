package application.model.p3_board;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import application.model.p1_utils.Pair;

public class Board {
	private final static int 
			DEFAULT_HEIGHT = 32,
			DEFAULT_WIDTH = 32;
	private final double FOOD_PERCENTAGE = 0.2; 
	private final static char 
			FOOD_CELL = '#',
			EMPTY_CELL = '0',
			CURR_CELL = '@',
			STEPPED_CELL = '.',
			ATE_CELL = '-';
	
	private Integer maxActionUnits = 400;
	
	protected char[][] board;
	
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
		board = new char[h][w];
	}
	
	public Board(Board b) {
		board = new char[b.board.length][];
		for (int i = 0; i < board.length; i++)
			board[i] = b.board[i].clone();
		ant = b.ant.clone();
	}
	public void moveAnt() {
		Pair<Integer, Integer> pos, newPos;
		pos = ant.getCurrPos();
		board[pos.getLeftElement()][pos.getRightElement()] = STEPPED_CELL;
		
		newPos = this.calaculateAntNewPosition();
		ant.setCurrPos(newPos);
		if(board[newPos.getLeftElement()][newPos.getRightElement()] == FOOD_CELL) {
			ant.incrementFoodCounter();
			
		}
		
		board[newPos.getLeftElement()][newPos.getRightElement()] = CURR_CELL;
		ant.incrementActionCounter();
	}
	
	private Pair<Integer, Integer> calaculateAntNewPosition() {
		Pair<Integer, Integer> pos, newPos = null;
		pos = ant.getCurrPos();
		switch(ant.getCurrDir()) {
		case NORTH:
			if(pos.getLeftElement() - 1 < 0)
				newPos = new Pair<Integer, Integer>(DEFAULT_HEIGHT - 1, pos.getRightElement());
			else
				newPos = new Pair<Integer, Integer>(pos.getLeftElement() - 1, pos.getRightElement());
			break;
		case EAST:
			if(pos.getRightElement() + 1 >= DEFAULT_WIDTH)
				newPos = new Pair<Integer, Integer>(pos.getLeftElement(), 0);
			else
				newPos = new Pair<Integer, Integer>(pos.getLeftElement(), pos.getRightElement() + 1);
			break;
		case SOUTH:
			if(pos.getLeftElement() + 1 >= DEFAULT_HEIGHT)
				newPos = new Pair<Integer, Integer>(0, pos.getRightElement());
			else
				newPos = new Pair<Integer, Integer>(pos.getLeftElement() + 1, pos.getRightElement());
			break;
		case WEST:
			if(pos.getRightElement() - 1 < 0)
				newPos = new Pair<Integer, Integer>(pos.getLeftElement(), DEFAULT_WIDTH - 1);
			else
				newPos = new Pair<Integer, Integer>(pos.getLeftElement(), pos.getRightElement() - 1);
			break;		
		}
		return newPos;
	}
	
	public boolean areActionUnitsLeft() {
		return ant.getActionCounter() < maxActionUnits;
	}
	
	public boolean isFoodAhead() {
		Pair<Integer, Integer> newPos = this.calaculateAntNewPosition();
		
		return board[newPos.getLeftElement()][newPos.getRightElement()] == FOOD_CELL;
	}
	
	public Ant getAnt() {
		return ant;
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
	
	public Board clone() {
		return new Board(this);
	}
	
	public String toString() {
		String s = "";
		for (char[] row : board) {
			for (char col : row) 
				s += col + " ";
			s += "\n";
		}
		return s;
	}
}
