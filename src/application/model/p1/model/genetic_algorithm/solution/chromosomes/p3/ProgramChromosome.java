package application.model.p1.model.genetic_algorithm.solution.chromosomes.p3;

import java.util.ArrayList;
import java.util.List;

import application.model.p1.model.genetic_algorithm.solution.chromosomes.Chromosome;
import application.model.p1.model.genetic_algorithm.solution.genes.Gene;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.TreeGene;
import application.model.p3_board.Board;

public class ProgramChromosome extends Chromosome<TreeGene> { 
	
	private int maxHeight = 5; 
	
	public ProgramChromosome(Boolean maximize, Integer maxHeight, boolean isHalf, Board board) {
		super();
		if(maximize != null)
			this.maximize = maximize;
		else
			this.maximize = true;
		if(maxHeight !=null)
			this.maxHeight = maxHeight;
		genes = new ArrayList<TreeGene>(1);
		for(int i = 0; i < 1; i++) {
			genes.add(new TreeGene(maxHeight, isHalf, board));
			chromosomeLength += genes.get(i).getSize();
		}
		fitness = this.calculateFitness();
		this.calculateFenotype();
	}
	
	public ProgramChromosome(ProgramChromosome pc) {
		super();
		maximize = pc.isMaximize();
		genes = new ArrayList<TreeGene>(1);
		for (TreeGene treeGene : pc.getGenes())
			genes.add((TreeGene) treeGene.clone());
		chromosomeLength = pc.getChromosomeLength();
		fitness = pc.getFitness();
		normalizedFitness = pc.getNormalizedFitness();
		fenotype = pc.getFenotype();
		score = pc.getScore();
		accuScore = pc.getAccuScore();
		maxHeight = pc.getMaxHeight();
		
	}
	
	public ProgramChromosome(boolean maximize, List<? extends Gene<?>> genes, int chromosomeLength, int maxHeight) {
		super();
		this.maximize = maximize;
		this.genes = new ArrayList<TreeGene>(2);
		for (Gene<?> g : genes)
			this.genes.add((TreeGene) g.clone());
		this.chromosomeLength = chromosomeLength;
		this.maxHeight = maxHeight;
		fitness = this.calculateFitness();
		this.calculateFenotype();
	}
	
	@Override
	protected void calculateFenotype() {
		fenotype = 0;
		for (TreeGene treeGene : genes)
			fenotype += treeGene.getDecodedValue();
	}

	@Override
	protected double calculateFitness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <U> Chromosome<TreeGene> createChildren(List<Gene<U>> childGenes) {
		return new ProgramChromosome(maximize, childGenes, chromosomeLength, maxHeight);
	}

	@Override
	public Chromosome<TreeGene> clone() {
		return new ProgramChromosome(this);
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}
	
	@Override
	public String toString() {
		return genes.get(0).toString();
	}
	
	public Board getFinalBoard() {
		return this.genes.get(0).getFinalBoard();
	}
}
