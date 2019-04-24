package application.model.p1.model.genetic_algorithm.solution.genes.p3_gene;

import java.util.ArrayList;
import java.util.List;

import application.model.p1.model.genetic_algorithm.solution.genes.Gene;
import application.model.p1.model.genetic_algorithm.solution.genes.p3_gene.program.ProgramTree;

public class TreeGene extends Gene<ProgramTree> {

	public TreeGene() {
		super();
		size = 1;
		this.alleles = new ArrayList<>(1);
		this.initializeGene();
		this.decodeGene();
	}
	
	@SuppressWarnings("unchecked")
	public TreeGene(List<?> alleles) {
		super();
		this.size = alleles.size();
		this.alleles = (List<ProgramTree>) alleles;
		this.decodeGene();
	}
	
	/**
	 * Clone constructor
	 * */
	public TreeGene(TreeGene t) {
		super();	
		size = t.getSize();
		alleles = new ArrayList<ProgramTree>(size);
		for (ProgramTree programTree : t.getAlleles()) {
			alleles.add(programTree.clone());
		}
		this.decodeGene();
	}
	
	
	
	@Override
	protected void initializeGene() {
		// TODO Auto-generated method stub
	}

	@Override
	public void decodeGene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Gene<ProgramTree> createGene(List<ProgramTree> alleles) {
		return new TreeGene(alleles);
	}

	@Override
	public Gene<ProgramTree> clone() {
		return new TreeGene(this);
	}

	@Override
	public void mutate(int allelePos) {
		// TODO Auto-generated method stub
		
	}

}
