package application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.function_commands;

import java.util.List;

import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.ProgramTree;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.Command;
import application.model.p3_board.Board;

public class Progn2FunctionCommand implements Command {

	private int numChild = 2;

	@Override
	public void execute(List<ProgramTree> parameters, Board board) {
		if(parameters.size() == 2 && board.areActionUnitsLeft()) {
			parameters.get(0).getRoot().execute(parameters.get(0).getChildren(), board);
			parameters.get(1).getRoot().execute(parameters.get(1).getChildren(), board);
		}
	}

	@Override
	public int getNumOfChilds() {
		return this.numChild ;
	}
	
	@Override
	public String toString() {
		return "PROGN2";
	}
}
