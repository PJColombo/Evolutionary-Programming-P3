package application;

import java.util.Arrays;

import application.model.p1.model.genetic_algorithm.GeneticAlgorithm;
import application.model.p1.model.genetic_algorithm.solution.chromosomes.p3_chromosome.ProgramChromosome;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.ProgramTree;

public class DebugMain {

	public static void main(String[] args) {
		GeneticAlgorithm ge = new GeneticAlgorithm(null, "TSP", 100, 200, "roullete", "pmx", 0.6, "terminal", 0.05, 0.0, false, "roullete");
		
		ge.execute();
	}

}
