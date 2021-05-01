package com.vrp.app.wrapper;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.vrp.app.Algorithm;
import com.vrp.app.components.Node;
import com.vrp.app.components.Result;

public class Solution {
	
	private ArrayList<ArrayList<Integer>> routes;
	private double[] routeCosts;
	private double totalCosts;
	private ProblemInstance instance;
	private Algorithm algorithm;
	
	public Solution(ArrayList<ArrayList<Integer>> routes, double[] routeCosts, double totalCosts, ProblemInstance instance, Algorithm algorithm) {
		this.routes = routes;
		this.routeCosts = routeCosts;
		this.totalCosts = totalCosts;
		this.instance = instance;
		this.algorithm = algorithm;
	}
	
	public void print() {
		System.out.println(this.toString());
	}
	
	public String printForDesmos() {
		String asString = String.format(" * * * * Printing for Desmos * * * *\n");
		Node[] locations = instance.getLocations();
		for(Node loc: locations) {
			asString += String.format("%d\t%d\n", loc.getX(), loc.getY());
		}
		asString += String.format("\n");
		for(ArrayList<Integer> rt: routes) {
			for(Integer ix: rt)
				asString += String.format("%d\t%d\n", locations[ix].getX(), locations[ix].getY());
			asString += String.format("\n");
		}
		return asString;
	}
	
	public String format() {
		String asString = "";
		asString += String.format("Algorithm: %s\n", algorithm);
		asString += String.format("MaxRouteCost: %1$,.2f\n", totalCosts);
		asString += String.format("Routes:\n");
		for(ArrayList<Integer> rt: routes) {
			asString += rt.stream().map(i -> i.toString()).collect(Collectors.joining(" ")) + "\n";
		}
		return asString;
	}

	@Override
	public String toString() {
		String asString = String.format("Total Costs: %f\n", totalCosts);
		for(int k = 0; k < routeCosts.length; k++) {
			String path = routes.get(k)
					.stream()
			        .map( n -> n.toString() )
			        .collect( Collectors.joining( " -> " ) );
			asString += String.format("Vehicle %d (Costs: %3f):\t%s\n", k+1, routeCosts[k], path);
		}
		return asString;
	}
	
	public static Solution from(ProblemInstance instance, Result solution, double[][] distanceMatrix, Algorithm algorithm) {

		int n = instance.getNumLocations();
		int K = instance.getNumVehicles();
		
		ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>(); 
		double[] routeCosts = new double[K];
		double totalCosts = 0;
        for (int j = 0; j < K; j++) {
			routeCosts[j] = 0;
			ArrayList<Integer> currentRoute = new ArrayList<Integer>();
			int prev = -1;
            for (int k = 0; k < solution.getRoute().get(j).getNodes().size(); k++) {
            	int id = solution.getRoute().get(j).getNodes().get(k).getId();
            	currentRoute.add(id);
            	if(k > 0) {
        			routeCosts[j] += distanceMatrix[prev][id];
            	}
    			prev = id;
            }
			routes.add(currentRoute);
			totalCosts = Math.max(totalCosts, routeCosts[j]);
		}
		
		return new Solution(routes, routeCosts, totalCosts, instance, algorithm);
			
	}

	public ArrayList<ArrayList<Integer>> getRoutes() {
		return routes;
	}


	public void setRoutes(ArrayList<ArrayList<Integer>> routes) {
		this.routes = routes;
	}


	public double[] getRouteCosts() {
		return routeCosts;
	}


	public void setRouteCosts(double[] routeCosts) {
		this.routeCosts = routeCosts;
	}


	public double getTotalCosts() {
		return totalCosts;
	}


	public void setTotalCosts(double totalCosts) {
		this.totalCosts = totalCosts;
	}


	public ProblemInstance getInstance() {
		return instance;
	}


	public void setInstance(ProblemInstance instance) {
		this.instance = instance;
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	
	
//	for (int i = 0; i < n; i++) {
//	    for (int j = 0; j < n; j++) {
//	        System.out.print((int) z[i][j][k].get(GRB.DoubleAttr.X) + " ");
//	    }
//	    System.out.println();
//	}
//	System.out.println("\n");
}
