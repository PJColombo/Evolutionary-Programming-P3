package application.model.p1.model.genetic_algorithm.solution.genes.p3_gene;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import application.model.p1.model.genetic_algorithm.solution.genes.Gene;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.ProgramTree;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.Command;
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
		this.alleles.add(this.initializeGene(1));
		this.decodeGene();
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
		finalBoard = initialBoard.clone();
		this.decodeGene();
	}
	
	
	
	protected ProgramTree initializeGene(int currentDepth){
		ProgramTree currentProgramTree = null;
		int cD = currentDepth;
		
		if(currentDepth == this.maxDepth)
			return new ProgramTree(pickCommand(randomSelector(1)), finalBoard);
		else{
			Command c;
			if(isHalf) {
				if(currentDepth == 1)
					c = pickCommand(randomSelector(0));
				else 
					c = pickCommand(randomSelector(2));
			}else 
					c = pickCommand(randomSelector(0));
			
			
			currentProgramTree = new ProgramTree(c, finalBoard);
			for (int i = 0; i < c.getNumOfChilds(); i++) {
				ProgramTree child = initializeGene(cD + 1);
				currentProgramTree.addChildren(child);
			}
		}
		return currentProgramTree;
	}

	@Override
	public void decodeGene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Gene<ProgramTree> createGene(List<ProgramTree> alleles) {
		return new TreeGene(alleles, this.maxDepth, this.isHalf, this.initialBoard);
	}

	@Override
	public Gene<ProgramTree> clone() {
		return new TreeGene(this);
	}

	@Override
	public void mutate(int allelePos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initializeGene() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @param num : eleccion del comando a generar
	 * @return 3 opciones:
	 *  - Una funcion
	 *  - Un terminal
	 *  - Aleatorio
	 */
	private int randomSelector(int num) {
		int elem;
		switch (num) {
		case 0:
			elem = ThreadLocalRandom.current().nextInt(0, 3);
			break;
		case 1:
			elem = ThreadLocalRandom.current().nextInt(3, 6);
			break;
		case 2:
			elem = ThreadLocalRandom.current().nextInt(0, 6);
			break;
		default:
			elem = ThreadLocalRandom.current().nextInt(0, 6);
		}
		return elem;
	}
	
	private Command pickCommand(int elem) {	
		switch(elem) {
		case 0: 
			return new IfFoodFunctionCommand();
		case 1: 
			return new Progn2FunctionCommand();
		case 2: 
			return new Progn3FunctionCommand();
		case 3: 
			return new MoveForwardTerminalCommand();
		case 4: 
			return new TurnLeftTerminalCommand();
		case 5: 
			return new TurnRightTerminalCommand();
		default:
			return new MoveForwardTerminalCommand();
		}
	}
	
	public String toString() {
		return this.alleles.get(0).toString();
	}

	public Board getFinalBoard() {
		return finalBoard;
	}
	
	
}
