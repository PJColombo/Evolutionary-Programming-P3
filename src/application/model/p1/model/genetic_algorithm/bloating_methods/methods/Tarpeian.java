package application.model.p1.model.genetic_algorithm.bloating_methods.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import application.model.p1.model.genetic_algorithm.bloating_methods.BloatingMethod;
import application.model.p1.model.genetic_algorithm.solution.chromosomes.Chromosome;
import application.model.p1.model.genetic_algorithm.solution.chromosomes.p3_chromosome.ProgramChromosome;
import application.model.p1.model.genetic_algorithm.solution.genes.Gene;
import application.model.p1_utils.Statistics;

public class Tarpeian implements BloatingMethod {

	private final double DELETE_PROBABILITY = 0.5;
	private double avgPopSize;
	
	public Tarpeian(List<Chromosome<? extends Gene<?>>> population) {
		List<ProgramChromosome> p = new ArrayList<ProgramChromosome>(population.size());
		List<Double> popSizes = new ArrayList<Double>(population.size());
		p.add((ProgramChromosome) population.get(0));
		for (Chromosome<? extends Gene<?>> c : population)
			p.add((ProgramChromosome) c);
		for (ProgramChromosome pc : p) 
			popSizes.add(Double.valueOf(pc.getGenes().get(0).getAlleles().get(0).getHeight()));
		
		avgPopSize = Statistics.calculateMean(popSizes);
	}
	@Override
	public double calculateFitness(Chromosome<? extends Gene<?>> c) {
		ProgramChromosome pc = (ProgramChromosome) c;
		Double p = ThreadLocalRandom.current().nextDouble();
		
		if(p < DELETE_PROBABILITY && pc.getGenes().get(0).getAlleles().get(0).getHeight() > avgPopSize)
			return Double.MIN_VALUE;
		else
			return pc.getFitness();
	}


}
