package application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program;

import java.util.ArrayList;


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
		board = board;
		for (ProgramTree programTree : children) {
			children.add(programTree.clone());
		}
	}
	
	public void addChildren(ProgramTree t) {
		children.add(t);
		height += t.getHeight();
	}

	
	
	public void executeTree() {
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
	public ArrayList<ProgramTree> getChildren() {
		return children;
	}
	
	public ProgramTree clone() {
		return new ProgramTree(this);
	}
}