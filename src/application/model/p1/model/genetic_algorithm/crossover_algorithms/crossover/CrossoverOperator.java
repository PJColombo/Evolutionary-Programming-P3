package application.model.p1.model.genetic_algorithm.crossover_algorithms.crossover;



import application.model.p1.model.genetic_algorithm.solution.chromosomes.Chromosome;
import application.model.p1.model.genetic_algorithm.solution.genes.Gene;
import application.model.p1_utils.Pair;

public abstract class CrossoverOperator {
	
	protected double crossoverProbability;
	
	
	public CrossoverOperator(double crossoverProbability) {
		super();
		this.crossoverProbability = crossoverProbability;	
	}

	public abstract <T> Pair<? extends Chromosome<? extends Gene<T>>, ? extends Chromosome<? extends Gene<T>>> 
		chromosomeCrossover(Chromosome<? extends Gene<T>> parent1, Chromosome<? extends Gene<T>> parent2);
}
