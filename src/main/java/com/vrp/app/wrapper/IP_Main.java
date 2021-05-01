package com.vrp.app.wrapper;

import java.util.ArrayList;

import com.vrp.app.Algorithm;
import com.vrp.app.VRP;

public class IP_Main {

	public static final String EXPERIMENTS_HOME = System.getenv("EXPERIMENTS_HOME");
	
	public static void main(String[] args) {

//		ProblemInstance instance = InstanceHandler.createRandom(2, 70);
//		InstanceHandler.saveInstance(instance);
		
		ProblemInstance instance = InstanceHandler.loadFromID("269903");

		runInstances(instance);
	}
	
	private static void runInstances(ProblemInstance... instances) {
		VRP solver = new VRP();
		for(ProblemInstance instance: instances) {
			solver.setup(instance);
			
			ArrayList<Solution> solutions = new ArrayList<Solution>();
			double bestCost = Float.POSITIVE_INFINITY;
			Solution bestSol = null;
			for(Algorithm alg: Algorithm.values()) {
				Solution sol = solver.run(alg);
				if(sol.getTotalCosts() < bestCost) {
					bestCost = sol.getTotalCosts();
					bestSol = sol;
				}
				solutions.add(sol);
			}
			
			System.out.println(" ------------------- Done ------------------- \n\n");
			
			for(Solution sol: solutions) {
				sol.print();
			}

			System.out.println(" ------------------- Best Solution ------------------- \n\n");
			
			System.out.println("Algorithm:  " + bestSol.getAlgorithm());
			
			bestSol.print();
			InstanceHandler.saveSolution(bestSol);
		}
	}
}
