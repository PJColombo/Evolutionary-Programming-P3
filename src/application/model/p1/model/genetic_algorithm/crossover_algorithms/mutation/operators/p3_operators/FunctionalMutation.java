package application.model.p1.model.genetic_algorithm.crossover_algorithms.mutation.operators.p3_operators;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import application.model.p1.model.genetic_algorithm.crossover_algorithms.mutation.MutationOperator;
import application.model.p1.model.genetic_algorithm.solution.chromosomes.Chromosome;
import application.model.p1.model.genetic_algorithm.solution.genes.Gene;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.TreeGene;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.ProgramTree;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.commands.CommandFactory;

public class FunctionalMutation extends MutationOperator {

	public FunctionalMutation(double mutationProbability) {
		super(mutationProbability);
	}

	@Override
	public <T> Chromosome<? extends Gene<T>> mutation(Chromosome<? extends Gene<T>> c1) {
		List<? extends Gene<T>> genes = c1.getGenes();
		List<T> alleles;
		double p;
		boolean mutate = false;
		for(int i = 0; i < genes.size(); i++) {
			p = ThreadLocalRandom.current().nextDouble();
			if(p < mutationProbability){
				alleles = genes.get(i).getAlleles();
				for(int j = 0; j < alleles.size(); j++) {
					ProgramTree pt = (ProgramTree) alleles.get(j);
					mutate = mutateFunctional(pt);
				}			
			}
		}
		
		if(mutate)
			c1.evaluate();
		return c1;
	}
	
	private boolean mutateFunctional(ProgramTree node) {
		CommandFactory cf = CommandFactory.getInstance();
		boolean isMutated = false;
		double prob = ThreadLocalRandom.current().nextDouble();
		if(0.5 < prob && node.getRoot().getNumOfChilds() == 2) {
			node.setRoot(cf.createRandomCommand(3));
		}else {
			for (int i = 0; i < node.getChildren().size() && !isMutated; i++) {
				isMutated = mutateFunctional(node.getChildren().get(i));
			}
		}
		return isMutated;
	}
}
