package application.model.p1.model.genetic_algorithm.bloating_methods;


import application.model.p1.model.genetic_algorithm.solution.chromosomes.Chromosome;
import application.model.p1.model.genetic_algorithm.solution.chromosomes.p3_chromosome.ProgramChromosome;
import application.model.p1.model.genetic_algorithm.solution.genes.Gene;

public interface BloatingMethod {
	double calculateFitness(Chromosome<? extends Gene<?>> c);
}
