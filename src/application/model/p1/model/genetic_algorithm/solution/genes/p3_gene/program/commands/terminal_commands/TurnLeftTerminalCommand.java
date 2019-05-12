package application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.terminal_commands;

import java.util.List;

import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.ProgramTree;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.Command;
import application.model.p3_board.Board;

public class TurnLeftTerminalCommand implements Command {

	@Override
	public void execute(List<ProgramTree> commands, Board board) {
		if(board.areActionUnitsLeft())
			board.getAnt().turn(true);
	}
	@Override
	public int getNumOfChilds() {
		return 0;
	}
	
	@Override
	public String toString() {
		return "TURN_LEFT";
	}
}
