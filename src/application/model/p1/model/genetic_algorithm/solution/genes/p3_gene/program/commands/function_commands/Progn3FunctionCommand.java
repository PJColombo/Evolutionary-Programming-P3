package application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.function_commands;

import java.util.List;

import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.ProgramTree;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.Command;

public class Progn3FunctionCommand implements Command {

	@Override
	public void execute(List<ProgramTree> parameters) {
		if(parameters.size() == 3) {
			parameters.get(0).getRoot().execute(parameters.get(0).getChildren());
			parameters.get(1).getRoot().execute(parameters.get(1).getChildren());
			parameters.get(2).getRoot().execute(parameters.get(2).getChildren());
		}
	}

}
