package application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands;

import java.util.List;

import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.ProgramTree;

public interface Command {
	void execute(List<ProgramTree> commands);
	int getNumOfChilds();
}
