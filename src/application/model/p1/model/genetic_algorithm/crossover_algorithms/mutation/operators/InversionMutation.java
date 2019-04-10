package application.model.p1.model.genetic_algorithm.crossover_algorithms.mutation.operators;

import application.model.p1.model.genetic_algorithm.crossover_algorithms.mutation.MutationOperator;
import application.model.p1.model.genetic_algorithm.solution.chromosomes.Chromosome;
import application.model.p1.model.genetic_algorithm.solution.genes.Gene;

public class InversionMutation extends MutationOperator {

	public InversionMutation(double prob) {
		super(prob);
	}

	@Override
	public <T> Chromosome<? extends Gene<T>> mutation(Chromosome<? extends Gene<T>> c1) {
		// TODO Auto-generated method stub
		return null;
	}	
}
