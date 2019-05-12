package application.model.p1_utils;

import java.util.List;

import application.model.p1.model.genetic_algorithm.solution.chromosomes.Chromosome;
import application.model.p1.model.genetic_algorithm.solution.genes.Gene;

public class Statistics {
	public static <T> double calculateMean(List<Double> values) {
		double mean = 0;
		for (Double v : values)
			mean += v;
		
		mean /= values.size();
		
		return mean;
	}
	
	public static <T> double calculateStandardDeviation(List<Double> values) {
		double sD = 0,
				mean = calculateMean(values);
		
		for (Double v : values)
			sD += Math.pow(v - mean, 2);
		
		sD /= values.size();
		
		return Math.sqrt(sD);
	}
	
	public static <T> double calculateVariance(List<Double> values) {
		double var = 0,
				mean = calculateMean(values);
		
		for (Double v : values) 
			v += Math.pow(v - mean, 2);

		var /= values.size();
		
		return var;
	}
	
	public static <T> double calculateCovariance(List<Double> values1, List<Double> values2) {
		double c = 0, v1, v2,
				mean1 = calculateMean(values1),
				mean2 = calculateMean(values2);
		for(int i = 0; i < values1.size(); i++) {
			v1 = values1.get(i); v2 = values2.get(i);
			c += (v1 - mean1) * (v2 - mean2);
		}
		
		c /= values1.size();
		
		return c;
	}
}
