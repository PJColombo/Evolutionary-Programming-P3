package application;

import application.model.p1.model.genetic_algorithm.GeneticAlgorithm;

public class DebugMain {

	public static void main(String[] args) {
//		GeneticAlgorithm ge = new GeneticAlgorithm(null, "TSP", 27, 100, "roullete", "pmx", 0.6, "hrt_swap", 0.05, 0.0, false, "roullete");
//		
//		ge.execute();
		
		Character[][] c = new Character[2][32];
		c[0] = new Character[]{'0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '#', '0', '0'};
		System.out.println(c[0].length);
		for (int i = 0; i < c[0].length; i++) {			
//			System.out.print(c[0][i] + " ");
			System.out.println("board[" + i + "] =");
		}
	}

}
