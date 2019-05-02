package application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.Command;
import application.model.p3_board.Board;

public class ProgramTree {
	private Command root;
	private ArrayList<ProgramTree> children;
	private int height;
	private Board board;
	
	public ProgramTree() {}
	
	public ProgramTree(Command root, Board board) {
		this.root = root;
		this.board = board;
		children = new ArrayList<ProgramTree>();
		height = 1;
	}
	
	public ProgramTree(ProgramTree pt) {
		root = pt.root;
		board = pt.board;
		children = new ArrayList<ProgramTree>(pt.children.size());
		for (ProgramTree programTree : pt.children) {
			children.add(programTree.clone());
		}
	}
	
	public void addChildren(ProgramTree t) {
		children.add(t);
		height += t.getHeight();
	}
	
	public boolean hasChildren() {
		return children.size() > 0;
	}
	public void executeTree() {
		while(board.areActionUnitsLeft())
			root.execute(children, board);
	}

	public Command getRoot() {
		return root;
	}
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setRoot(Command newRoot) {
		root = newRoot;
	}
	public ArrayList<ProgramTree> getChildren() {
		return children;
	}
	
	public int getAntFoodEated() {
		return board.getAnt().getFoodCounter();
	}
	
	public ProgramTree clone() {
		return new ProgramTree(this);
	}
	
	public boolean isTerminal() {
		return (root.getNumOfChilds() == 0) ? true : false;
	}
	
	public boolean isFunction() {
		return (root.getNumOfChilds() > 0) ? true : false;
	}
	
	@Override
	public String toString() {
		String retVal = this.root.toString();
		if(this.root.getNumOfChilds() == 2) {
			retVal += "(" + this.children.get(0).toString() + "," + this.children.get(1).toString() + ")";
		}
		else if(this.root.getNumOfChilds() == 3) {
			retVal += "(" + this.children.get(0).toString() + "," + this.children.get(1).toString()
					+ "," + this.children.get(2).toString() + ")";
			
			
		}
		
		return retVal;
	}
}
