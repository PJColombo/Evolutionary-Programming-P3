package application.model.p1.model.genetic_algorithm.solution.genes.p3_gene;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import application.model.p1.model.genetic_algorithm.solution.genes.Gene;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.ProgramTree;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.Command;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.CommandFactory;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.function_commands.IfFoodFunctionCommand;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.function_commands.Progn2FunctionCommand;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.function_commands.Progn3FunctionCommand;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.terminal_commands.MoveForwardTerminalCommand;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.terminal_commands.TurnLeftTerminalCommand;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.terminal_commands.TurnRightTerminalCommand;
import application.model.p3_board.Board;

public class TreeGene extends Gene<ProgramTree> {

	private Integer maxDepth;
	private boolean isHalf;
	/*for crossover purposes*/
	private Board initialBoard;
	/*board which contains ant path.*/
	private Board finalBoard;
	
	public TreeGene(Integer maxDepth, boolean isHalf, Board initialBoard) {
		super();
		size = 1;
		this.alleles = new ArrayList<ProgramTree>(1);
		this.maxDepth = maxDepth;
		this.isHalf = isHalf;
		this.initialBoard = initialBoard;
		finalBoard = initialBoard.clone();
		initializeGene();
		decodeGene();
	}
	
	@SuppressWarnings("unchecked")
	public TreeGene(List<?> alleles, Integer maxDepth, boolean isHalf, Board initialBoard) {
		super();
		this.size = alleles.size();
		this.alleles = (List<ProgramTree>) alleles;
		
		this.maxDepth = maxDepth;
		this.isHalf = isHalf;
		this.initialBoard = initialBoard;
		finalBoard = initialBoard.clone();
		this.decodeGene();
	}
	
	/**
	 * Clone constructor
	 * */
	public TreeGene(TreeGene t) {
		super();	
		size = t.getSize();
		alleles = new ArrayList<ProgramTree>(size);
		for (ProgramTree programTree : t.getAlleles()) {
			alleles.add(programTree.clone());
		}
		
		maxDepth = t.maxDepth;
		isHalf = t.isHalf;
		initialBoard = t.initialBoard;
		finalBoard = t.finalBoard.clone();
		this.decodeGene();
	}
	
	@Override
	public void decodeGene() {}

	@Override
	public Gene<ProgramTree> createGene(List<ProgramTree> alleles) {
		return new TreeGene(alleles, this.maxDepth, this.isHalf, this.initialBoard);
	}

	@Override
	public Gene<ProgramTree> clone() {
		return new TreeGene(this);
	}

	
	@Override
	public void mutate(int flag) {}

	@Override
	protected void initializeGene() {
		ProgramTree pt = ProgramTree.initializeTree(1, maxDepth, isHalf);
		this.alleles.add(pt);
	}
	
	public int executeGeneTree() {
		alleles.get(0).executeTree(finalBoard);
		return getAntFoodEated();
	}
	
	private int getAntFoodEated() {
		return finalBoard.getAnt().getFoodCounter();
	}
	
	@Override
	public String toString() {
		//return this.alleles.get(0).toString() /*+ System.lineSeparator()*/;
		
		return this.alleles.get(0).toString() + System.lineSeparator() + "FINAL BOARD (" + finalBoard.getAnt().getCurrPos().getLeftElement()
				+ ", " + finalBoard.getAnt().getCurrPos().getRightElement() + ") "+ System.lineSeparator() +  finalBoard.toString();
	}

	public Board getFinalBoard() {
		return finalBoard;
	}

	public void restartFinalBoard() {
		finalBoard = initialBoard.clone();
	}
	public boolean isHalf() {
		return isHalf;
	}
	
	
	
}
