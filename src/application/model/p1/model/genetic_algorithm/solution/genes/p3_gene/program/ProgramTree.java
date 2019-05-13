package application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.Command;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.CommandFactory;
import application.model.p3_board.Board;

public class ProgramTree {
	private ProgramTree parent;
	private Command root;
	private ArrayList<ProgramTree> children;
	private int height;
	
	private int numTrees;
	
	public ProgramTree() {}
	
	public ProgramTree(Command root) {
		parent = null;
		this.root = root;
		children = new ArrayList<ProgramTree>();
		height = 1;
		numTrees = 1;
	}
	
	public ProgramTree(ProgramTree pt) {
		parent = null;
		root = pt.root;
		height = pt.height;
		children = new ArrayList<ProgramTree>(pt.children.size());
		numTrees = pt.numTrees;
		ProgramTree cloneTreeChild;
		for (ProgramTree programTree : pt.children) {
			cloneTreeChild = programTree.clone(); 
			cloneTreeChild.parent = this;
			children.add(cloneTreeChild);
		}
	}
	
	public void addChildren(ProgramTree t) {
		t.parent = this;
		children.add(t);
		if(t.height + 1 > height)
			height = 1 + t.height;
		numTrees += t.numTrees;
		
		int total = 0;
		for (ProgramTree programTree : children) {
			total += programTree.numTrees;
		}
		if(numTrees != total + 1) {
			System.out.println("aqui");
		}
	}
	
	
	public void replaceChildren(int i, ProgramTree newPt) {
		int oldNumTrees = children.get(i).numTrees;
		newPt.parent = this;
		
		//numTrees -= oldNumTrees;
		children.set(i, newPt);
		//numTrees += newPt.numTrees;
		
		
		recalculateNumTreeNodes(newPt.numTrees - oldNumTrees);
		
		int total = 1;
		for (ProgramTree programTree : children) {
			total += programTree.numTrees;
		}
		if(numTrees != total) {
			System.out.println("distinto");
		}
			
		recalculateHeight(newPt.height, i);
	}
	
	private void recalculateHeight(int newHeight, int newPtIndex) {
		ProgramTree parentTree = this;
		int currHeight = newHeight;
		Integer secondLargest;
		boolean increaseHeight = currHeight + 1 > parentTree.height;
		
		secondLargest = increaseHeight ? null : decreaseHeight(parentTree, newPtIndex);
		while(parentTree != null && (increaseHeight ||  secondLargest != null)) {
			if(increaseHeight)
				parentTree.height = currHeight + 1;
			else
				parentTree.height = secondLargest;		
			currHeight = parentTree.height;
			parentTree = parentTree.parent;
			secondLargest = Integer.MIN_VALUE; 
			if(parentTree != null) {
				increaseHeight = currHeight + 1 > parentTree.height;
				secondLargest = increaseHeight ? null: decreaseHeight(parentTree, newPtIndex);
			}
				
		}
	}
	
	private void recalculateNumTreeNodes(int increment) {
		ProgramTree parentTree = this;
		while(parentTree != null) {
			parentTree.numTrees += increment;
			parentTree = parentTree.parent;
		}
	}

	private Integer decreaseHeight(ProgramTree currTree, int newPtIndex) {
		int i = 0;
		Integer newMaxHeight = Integer.MIN_VALUE;
		boolean decreaseHeight = true;
		
		while(i < currTree.children.size() && decreaseHeight) {
			decreaseHeight = !(currTree.children.get(i).height + 1 == currTree.height);	
			if(currTree.children.get(i).height  + 1 > newMaxHeight)
				newMaxHeight = currTree.children.get(i).height + 1;
			i++;
		}
		return decreaseHeight ? newMaxHeight : null;
	}
	public void executeTree(Board board) {
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
	
	public int getNumTrees() {
		return numTrees;
	}

	public void setRoot(Command newRoot) {
		root = newRoot;
	}
	public ArrayList<ProgramTree> getChildren() {
		return children;
	}
	
	public static ProgramTree initializeTree(int currentDepth, int maxDepth, boolean isHalf){
		ProgramTree currentProgramTree = null;
		int cD = currentDepth;
		CommandFactory cf = CommandFactory.getInstance();
		
		if(currentDepth == maxDepth)
			//Terminal command
			return new ProgramTree(cf.createRandomCommand(1));
		else{
			Command c;
			if(isHalf) {
				
				if(currentDepth == 1)
					//Function command
					c = cf.createRandomCommand(0);
				else 
					//Random type command
					c = cf.createRandomCommand(2);
			}else 
					//Function command.
					c = cf.createRandomCommand(0);
			
			
			currentProgramTree = new ProgramTree(c);
			for (int i = 0; i < c.getNumOfChilds(); i++) {
				ProgramTree child = initializeTree(cD + 1, maxDepth, isHalf);
				currentProgramTree.addChildren(child);
			}
		}
		return currentProgramTree;
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
	
	public String toString() {
		return  " | Height: " + height + /*System.lineSeparator() +*/ " | NumTrees: " + numTrees + System.lineSeparator() + recurToString(1);
	}
	
	private String recurToString(int nTab) {
		String s = root.toString() + System.lineSeparator();
		String tabs = "";
		for(int i = 0; i < nTab; i++)
			tabs += " \t ";
		for (ProgramTree pt : children) {
			s += tabs + pt.recurToString(nTab + 1);
		}
		return s;
	}
	
	public String toStringId(int nTab) {
		String s = "";
		if(parent != null)
			s = "PARENT ID: " + System.identityHashCode(this.parent) + System.lineSeparator();
		s += System.identityHashCode(this) + "  " + root.toString();
		s += System.lineSeparator();
		String tabs = "";
		for(int i = 0; i < nTab; i++)
			tabs += " \t ";
		for (ProgramTree pt : children) {
			s += tabs + pt.toStringId(nTab + 1);
		}
		return s;
	}
}
