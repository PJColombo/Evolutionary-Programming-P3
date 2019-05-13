package application.model.p1.model.genetic_algorithm.crossover_algorithms.crossover.operators.p3_operators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


import application.model.p1.model.genetic_algorithm.crossover_algorithms.crossover.CrossoverOperator;
import application.model.p1.model.genetic_algorithm.solution.chromosomes.Chromosome;
import application.model.p1.model.genetic_algorithm.solution.genes.Gene;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.TreeGene;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.ProgramTree;
import application.model.p1_utils.Pair;

public class TreeSwapCrossover extends CrossoverOperator {

	private final static double TREE_SWAP_PROBABILITY = 0.7;
	public TreeSwapCrossover(double crossoverProbability) {
		super(crossoverProbability);
	}

	@Override
	public <T> Pair<? extends Chromosome<? extends Gene<T>>, ? extends Chromosome<? extends Gene<T>>> chromosomeCrossover(
			Chromosome<? extends Gene<T>> parent1, Chromosome<? extends Gene<T>> parent2) {
		Pair<Chromosome<? extends Gene<T>>, Chromosome<? extends Gene<T>>> childChromosomes = new Pair<>();
		@SuppressWarnings("unchecked")
		List<Gene<T>> parentGenes1 = (List<Gene<T>>) parent1.getGenes(), parentGenes2 = (List<Gene<T>>) parent2.getGenes(), 
				childGenes1 = new ArrayList<Gene<T>>(parent1.getGenes().size()), childGenes2 = new ArrayList<Gene<T>>(parent1.getGenes().size());
		List<T> childAlleles1, childAlleles2;
		
		for(int i = 0; i < parentGenes1.size(); i++) {
			Gene<T> gene1 = parentGenes1.get(i).clone(),
					gene2 = parentGenes2.get(i).clone();
			int p1 = ((TreeGene) gene1).getAlleles().get(0).getNumTrees(), p2 = ((TreeGene) gene2).getAlleles().get(0).getNumTrees(),
					p3,p4;
			childAlleles1 = gene1.getAlleles(); childAlleles2 = gene2.getAlleles();
			for(int j = 0; j < childAlleles1.size(); j++) {
				ProgramTree checkTree;
				do {
					checkTree = this.recursiveExchangeTrees((ProgramTree) childAlleles1.get(j), (ProgramTree) childAlleles2.get(j), null);
				}while(checkTree == null);
			}
			p3 = ((TreeGene) gene1).getAlleles().get(0).getNumTrees(); p4 = ((TreeGene) gene2).getAlleles().get(0).getNumTrees();			
			childGenes1.add(parentGenes1.get(i).createGene(childAlleles1)); childGenes2.add(parentGenes2.get(i).createGene(childAlleles2));
		}
		childChromosomes.setLeftElement(parent1.createChildren(childGenes1));
		childChromosomes.setRightElement(parent2.createChildren(childGenes2));
		
		return childChromosomes;
	}
	
	private ProgramTree recursiveExchangeTrees(ProgramTree currTree, ProgramTree secondParentTree, ProgramTree exchangeTree) {
		ProgramTree secondTree = null, selectedTree = null;
		int i = 0;
		double rand, p;
		ProgramTree currChild;
		while(selectedTree == null && i < currTree.getChildren().size()) {
			currChild = currTree.getChildren().get(i);
			rand = ThreadLocalRandom.current().nextDouble();
			if(!(currChild.getChildren().size() > 0))
				p = 1 - TREE_SWAP_PROBABILITY;
			else
				p = TREE_SWAP_PROBABILITY;
			

			if(rand < p) {
				selectedTree = currChild;
				if(secondParentTree != null) {
					while(secondTree == null)
						secondTree = recursiveExchangeTrees(secondParentTree, null, selectedTree);
				}
				else
					secondTree = exchangeTree;
				currTree.replaceChildren(i, secondTree);
			}
			else
				selectedTree = recursiveExchangeTrees(currChild, secondParentTree, exchangeTree);
			
			i++;
		}
		return selectedTree;
	}

}
