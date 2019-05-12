package application.model.p1.model.genetic_algorithm.bloating_methods.methods;

import java.util.ArrayList;
import java.util.List;

import application.model.p1.model.genetic_algorithm.bloating_methods.BloatingMethod;
import application.model.p1.model.genetic_algorithm.solution.chromosomes.Chromosome;
import application.model.p1.model.genetic_algorithm.solution.chromosomes.p3_chromosome.ProgramChromosome;
import application.model.p1.model.genetic_algorithm.solution.genes.Gene;
import application.model.p1_utils.Statistics;

public class WellFounded implements BloatingMethod {
	
	private double k;
	
	
	public WellFounded(List<Chromosome<? extends Gene<?>>> population) {
		List<ProgramChromosome> p = new ArrayList<ProgramChromosome>(population.size());
		List<Double> treeSizes = new ArrayList<Double>(population.size()),
				treeFitness = new ArrayList<Double>(population.size());
		
		for (Chromosome<? extends Gene<?>> c : population)
			p.add((ProgramChromosome) c);
		
		for (ProgramChromosome c : p) {
			treeSizes.add(Double.valueOf(c.getGenes().get(0).getAlleles().get(0).getHeight()));
			treeFitness.add(c.getFitness());
		}
		
		System.out.println("Tree sizes " + treeSizes);
		System.out.println("Tree fitness  " + treeFitness);
		System.out.println("Covariance: " + Statistics.calculateCovariance(treeSizes, treeFitness));
		System.out.println("Variance: " + Statistics.calculateStandardDeviation(treeSizes));
		k = Statistics.calculateCovariance(treeSizes, treeFitness) / Statistics.calculateStandardDeviation(treeSizes);
	}

	@Override
	public double calculateFitness(Chromosome<? extends Gene<?>> c) {
		ProgramChromosome pc = (ProgramChromosome) c;
		int numTrees = pc.getGenes().get(0).getAlleles().get(0).getNumTrees();
		return pc.getFenotype() - (k * numTrees);
	}




	/**
	 * Current Program Tree (number of nodes and fitness
	 * Program sizes
	 * Individual fitnesses 
	 * 
	 */


}
