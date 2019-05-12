package application.model.p1.model.genetic_algorithm.bloating_methods;

import java.util.List;

import application.model.p1.model.genetic_algorithm.solution.chromosomes.Chromosome;
import application.model.p1.model.genetic_algorithm.solution.genes.Gene;

public abstract class BloatingFactory {
	private static BloatingFactory bloatingFactory;
	
	public static BloatingFactory getInstance() {
		if(bloatingFactory == null)
			bloatingFactory = new BloatingFactoryImp();
		return bloatingFactory;
	}
	
	public abstract BloatingMethod createBloatingMethod(String method, List<Chromosome< ? extends Gene<?>>> population);
}
