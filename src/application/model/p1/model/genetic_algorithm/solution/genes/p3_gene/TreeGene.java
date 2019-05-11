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
	public void mutate(int flag) {
		exeMutation(this.alleles.get(0), flag);
	}

	@Override
	protected void initializeGene() {
		this.alleles.add(ProgramTree.initializeTree(1, maxDepth, isHalf));
	}
	

//	private ProgramTree recurInitializeGene(int currentDepth){
//		ProgramTree currentProgramTree = null;
//		int cD = currentDepth;
//		
//		if(currentDepth == this.maxDepth)
//			return new ProgramTree(pickCommand(randomSelector(1)));
//		else{
//			Command c;
//			if(isHalf) {
//				if(currentDepth == 1)
//					c = pickCommand(randomSelector(0));
//				else 
//					c = pickCommand(randomSelector(2));
//			}else 
//					c = pickCommand(randomSelector(0));
//			
//			
//			currentProgramTree = new ProgramTree(c);
//			for (int i = 0; i < c.getNumOfChilds(); i++) {
//				ProgramTree child = recurInitializeGene(cD + 1);
//				currentProgramTree.addChildren(child);
//			}
//		}
//		return currentProgramTree;
//	}

//	/**
//	 * @param num : eleccion del comando a generar
//	 * @return 3 opciones:
//	 *  - Una funcion
//	 *  - Un terminal
//	 *  - Aleatorio
//	 *  - Una funcion de 2 hijos
//	 */
//	private int randomSelector(int num) {
//		int elem;
//		switch (num) {
//		case 0:
//			elem = ThreadLocalRandom.current().nextInt(0, 3);
//			break;
//		case 1:
//			elem = ThreadLocalRandom.current().nextInt(3, 6);
//			break;
//		case 2:
//			elem = ThreadLocalRandom.current().nextInt(0, 6);
//			break;
//		case 3:
//			elem = ThreadLocalRandom.current().nextInt(0, 2);
//			break;
//		default:
//			elem = ThreadLocalRandom.current().nextInt(0, 6);
//		}
//		return elem;
//	}
	
//	private Command pickCommand(int elem) {	
//		switch(elem) {
//		case 0: 
//			return new IfFoodFunctionCommand();
//		case 1: 
//			return new Progn2FunctionCommand();
//		case 2: 
//			return new Progn3FunctionCommand();
//		case 3: 
//			return new MoveForwardTerminalCommand();
//		case 4: 
//			return new TurnLeftTerminalCommand();
//		case 5: 
//			return new TurnRightTerminalCommand();
//		default:
//			return new MoveForwardTerminalCommand();
//		}
//	}
	
	/**
	 * 
	 * @param node: El nodo que estamos recorriendo
	 * @param flag: Para determinar si es una mutaci�n de terminal (0) o de function (1)
	 * @return un boolean para saber si se ha producido ya la mutaci�n
	 */
	private boolean exeMutation(ProgramTree node, int flag) {
		boolean isMutated = false;
		CommandFactory cf = CommandFactory.getInstance();
		if((flag == 0) ? node.isTerminal() : node.isFunction()) {
			double prob = ThreadLocalRandom.current().nextDouble();
			if (prob < 0.5) {
				return false; 
			}else if(node.getRoot().getNumOfChilds() == 2 && flag == 1){
				node.setRoot(cf.createRandomCommand(3));
				return true;
			}else if(flag == 0){
				node.setRoot(cf.createRandomCommand(1));
				return true;
			}else return false;
		}
		else {
			for (int i = 0; i < node.getChildren().size() && !isMutated; i++) {
				isMutated = exeMutation(node.getChildren().get(i), flag);
			}
		}
		return isMutated;
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
		return "Height: " + alleles.get(0).getHeight() + System.lineSeparator() + this.alleles.get(0).toString();
	}

	public Board getFinalBoard() {
		return finalBoard;
	}

	public boolean isHalf() {
		return isHalf;
	}
	
	
	
}
