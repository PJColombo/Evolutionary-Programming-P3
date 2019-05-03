package application.model.p1.model.genetic_algorithm.crossover_algorithms.mutation.operators.p3_operators;

import application.model.p1.model.genetic_algorithm.crossover_algorithms.mutation.MutationOperator;
import application.model.p1.model.genetic_algorithm.solution.chromosomes.Chromosome;
import application.model.p1.model.genetic_algorithm.solution.genes.Gene;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.ProgramTree;

public class FunctionalMutation extends MutationOperator {

	public FunctionalMutation(double mutationProbability) {
		super(mutationProbability);
	}

	@Override
	public <T> Chromosome<? extends Gene<T>> mutation(Chromosome<? extends Gene<T>> c1) {
		Chromosome<? extends Gene<T>> mutatedChromosome = c1.clone();
		mutatedChromosome.getGenes().get(0).mutate(1);
		return mutatedChromosome;
	}

}
