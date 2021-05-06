package com.vrp.app;

import java.util.ArrayList;

import com.vrp.app.wrapper.Args;
import com.vrp.app.wrapper.InstanceHandler;
import com.vrp.app.wrapper.ProblemInstance;
import com.vrp.app.wrapper.Solution;

public class VRP {

	public static final String EXPERIMENTS_HOME = System.getenv("EXPERIMENTS_HOME");
	
	public static void main(String[] args) {
		Args arguments = Args.parse(args);

		ProblemInstance instance = InstanceHandler.createRandom(arguments.numVehicles, arguments.numLocations);
		InstanceHandler.saveInstance(instance);
		
//		ProblemInstance instance = InstanceHandler.loadFromID("269903");

		runInstances(instance);
	}
	
	private static void runInstances(ProblemInstance... instances) {
		Runner solver = new Runner();
		for(ProblemInstance instance: instances) {
			solver.setup(instance);
			
			ArrayList<Solution> solutions = new ArrayList<Solution>();
			double bestCost = Float.POSITIVE_INFINITY;
			Solution bestSol = null;
			for(Algorithm alg: Algorithm.values()) {
				Solution sol = solver.run(alg);
				if(sol.getCosts() < bestCost) {
					bestCost = sol.getCosts();
					bestSol = sol;
				}
				solutions.add(sol);
			}
			
			System.out.println(" ------------------- Done ------------------- \n\n");
			
			for(Solution sol: solutions) {
				sol.print();
			}

			System.out.println(" ------------------- Best Solution ------------------- \n\n");
			
			bestSol.print();
			InstanceHandler.saveSolution(bestSol);
		}
	}
}
