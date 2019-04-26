package application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.terminal_commands;

import java.util.List;

import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.ProgramTree;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.Command;
import application.model.p3_board.Board;

public class TurnLeftTerminalCommand implements Command {

	@Override
	public void execute(List<ProgramTree> commands, Board board) {
		board.getAnt().turn(true);
	}

}
