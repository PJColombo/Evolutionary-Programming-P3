package application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands;

import java.util.List;

import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.ProgramTree;
import application.model.p3_board.Board;

public interface Command {
	void execute(List<ProgramTree> commands, Board board);
}
