package application.model.p1.model.genetic_algorithm.bloating_methods;

import java.util.List;

import application.model.p1.model.genetic_algorithm.bloating_methods.methods.Tarpeian;
import application.model.p1.model.genetic_algorithm.bloating_methods.methods.WellFounded;
import application.model.p1.model.genetic_algorithm.solution.chromosomes.Chromosome;
import application.model.p1.model.genetic_algorithm.solution.chromosomes.p3_chromosome.ProgramChromosome;
import application.model.p1.model.genetic_algorithm.solution.genes.Gene;

public class BloatingFactoryImp extends BloatingFactory {

	@Override
	public BloatingMethod createBloatingMethod(String method, List<Chromosome<? extends Gene<?>>> population) {
		switch(method.toLowerCase()) {
			case "tarpeian":
				return new Tarpeian(population);
			case "wellfounded":
				return new WellFounded(population);
			default:
				return new WellFounded(population);
		}
	}

}
