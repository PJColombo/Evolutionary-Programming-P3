package application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands;

import java.util.concurrent.ThreadLocalRandom;

import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.function_commands.IfFoodFunctionCommand;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.function_commands.Progn2FunctionCommand;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.function_commands.Progn3FunctionCommand;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.terminal_commands.MoveForwardTerminalCommand;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.terminal_commands.TurnLeftTerminalCommand;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.terminal_commands.TurnRightTerminalCommand;

public class CommandFactoryImp extends CommandFactory {

	private final int TERMINAL_SIZE = 3;
	private final int FUNCTION_SIZE = 3;
	private final int COMMAND_TYPE = 2;
	
	@Override
	public Command createCommand(String command) {
		return null;
	}

	@Override
	public Command createRandomCommand(int type) {

		switch(type) {
			case 0:
				return getTerminalCommand(ThreadLocalRandom.current().nextInt(TERMINAL_SIZE));
			case 1:
				return getTerminalCommand(ThreadLocalRandom.current().nextInt(FUNCTION_SIZE));
			case 2:
				int commandType = ThreadLocalRandom.current().nextInt(COMMAND_TYPE);
				if(commandType == 0)
					return getTerminalCommand(ThreadLocalRandom.current().nextInt(TERMINAL_SIZE));
				else
					return getFunctionCommand(ThreadLocalRandom.current().nextInt(FUNCTION_SIZE));
			default:
				return createRandomCommand(2);
		}
		
	}
	
	private Command getTerminalCommand(int n) {
		switch(n) {
			case 0:
				return new MoveForwardTerminalCommand();
			case 1:
				return new TurnLeftTerminalCommand();
			case 2:
				return new TurnRightTerminalCommand();
			default:
				return new MoveForwardTerminalCommand();
		}
	}
	
	private Command getFunctionCommand(int n) {
		switch(n) {
		case 0:
			return new IfFoodFunctionCommand();
		case 1:
			return new Progn2FunctionCommand();
		case 2:
			return new Progn3FunctionCommand();
		default:
			return new Progn2FunctionCommand();
		}
	}
}
