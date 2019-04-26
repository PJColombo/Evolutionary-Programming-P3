package application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.function_commands;

import java.util.List;

import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.ProgramTree;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.Command;
import application.model.p3_board.Board;

public class IfFoodFunctionCommand implements Command {

	@Override
	public void execute(List<ProgramTree> parameters, Board board) {
		//TODO call model and ask for food.
		boolean food = true;
		if(parameters.size() == 2) {
			if(food)
				parameters.get(0).getRoot().execute(parameters.get(0).getChildren(), board);
			else
				parameters.get(1).getRoot().execute(parameters.get(0).getChildren(), board);
		}
	}

}