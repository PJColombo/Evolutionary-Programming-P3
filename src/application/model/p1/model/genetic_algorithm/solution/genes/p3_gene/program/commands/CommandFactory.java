package application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands;

public abstract class CommandFactory {
	private static CommandFactory cf;

	public synchronized CommandFactory getInstance() {
		if(cf == null)
			cf = new CommandFactoryImp();
		return cf;
	}
	public abstract Command createCommand(String command);
	public abstract Command createRandomCommand(int type);
}
