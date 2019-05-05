package application.model.p1.model.genetic_algorithm.crossover_algorithms.mutation.operators.p3_operators;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import application.model.p1.model.genetic_algorithm.crossover_algorithms.mutation.MutationOperator;
import application.model.p1.model.genetic_algorithm.solution.chromosomes.Chromosome;
import application.model.p1.model.genetic_algorithm.solution.genes.Gene;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.TreeGene;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.ProgramTree;

public class TreeMutation extends MutationOperator {

	private final static double TREE_MUTATION_PROBABILITY = 0.7;
	
	public TreeMutation(double mutationProbability) {
		super(mutationProbability);
	}

	@Override
	public <T> Chromosome<? extends Gene<T>> mutation(Chromosome<? extends Gene<T>> c1) {
		List<? extends Gene<T>> genes = c1.getGenes();
		List<T> alleles;
		double p;
		for(int i = 0; i < genes.size(); i++) {
			p = ThreadLocalRandom.current().nextDouble();
			if(p < mutationProbability) {
				alleles = genes.get(i).getAlleles();
				for(int j = 0; j < alleles.size(); j++) {
					ProgramTree pt = (ProgramTree) alleles.get(j);
					while(!mutateRandomTree(pt, pt.getHeight(), ((TreeGene) genes.get(i)).isHalf()));
				}
			}			
		}
		return c1;
	}

	private boolean mutateRandomTree(ProgramTree currTree, int maxHeight, boolean isHalf) {
		int i = 0, newTreeHeight;
		double p;
		boolean nodeSelected = false;
		while(!nodeSelected && i < currTree.getChildren().size()) {
			p = ThreadLocalRandom.current().nextDouble();
			
			if(p < TREE_MUTATION_PROBABILITY) {
				nodeSelected = true;
				newTreeHeight = ThreadLocalRandom.current().nextInt(1, maxHeight + 1);
				currTree.replaceChildren(i, ProgramTree.initializeTree(1, newTreeHeight, isHalf));
			}
			else
				nodeSelected = mutateRandomTree(currTree.getChildren().get(i), maxHeight, isHalf);

			i++;
		}
		
		return nodeSelected;
	}
}
