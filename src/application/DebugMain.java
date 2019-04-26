package application;

import java.util.Arrays;

import application.model.p1.model.genetic_algorithm.GeneticAlgorithm;

public class DebugMain {

	public static void main(String[] args) {
//		GeneticAlgorithm ge = new GeneticAlgorithm(null, "TSP", 27, 100, "roullete", "pmx", 0.6, "hrt_swap", 0.05, 0.0, false, "roullete");
//		
//		ge.execute();
		char[][] c1 = new char[2][2];
		c1[0][0] = '@';
		c1[0][1] = '0';
		c1[1][0] = '0';
		c1[1][1] = '#';
		
		char[][] c2 = new char[2][2];
		c2[0][0] = '#';
		c2[0][1] = '0';
		c2[1][0] = '0';
		c2[1][1] = '@';
		
		char[][] c3 = new char[2][2];
		for(int i = 0; i < c1.length; i++)
			c3[i] = c1[i].clone();
		
		System.out.println("c1: " + c1[0][0]);
		c3[0][0] = 'A';
		
		System.out.println("c1: " + c1[0][0]);
		System.out.println("c3: " + c3[0][0]);
		
		Character[][] c = new Character[2][32];
		c[0] = new Character[]{'0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '#', '0', '0'};
		System.out.println(c[0].length);
		for (int i = 0; i < c[0].length; i++) {			
//			System.out.print(c[0][i] + " ");
			System.out.println("board[" + i + "] =" + c[0][i]);
		}
	}

}
