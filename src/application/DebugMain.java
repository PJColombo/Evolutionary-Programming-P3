package application;

import java.util.Arrays;

import application.model.p1.model.genetic_algorithm.GeneticAlgorithm;
import application.model.p1.model.genetic_algorithm.solution.chromosomes.p3_chromosome.ProgramChromosome;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.ProgramTree;

public class DebugMain {

	public static void main(String[] args) {
		GeneticAlgorithm ge = new GeneticAlgorithm(null, "TSP", 3000, 1024, "roullete", "treeswap", 0.77, "tree", 0.78, 0.0, true, "roullete");
		
		ge.execute();
	}

}
