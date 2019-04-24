package application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program;

import java.util.ArrayList;


import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.Command;

public class ProgramTree {
	private Command root;
	private ArrayList<ProgramTree> children;
	private int height;
	
	public ProgramTree() {}
	
	public ProgramTree(Command root) {
		this.root = root;
		children = new ArrayList<ProgramTree>();
		height = 1;
	}
	
	public ProgramTree(ProgramTree pt) {
		root = pt.root;
		for (ProgramTree programTree : children) {
			children.add(programTree.clone());
		}
	}
	
	public void addChildren(ProgramTree t) {
		children.add(t);
		height += t.getHeight();
	}

	
	
	public void executeTree() {
		root.execute(children);
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
