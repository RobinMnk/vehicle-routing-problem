package com.vrp.app.wrapper;

public class Args {
	
	public int numVehicles;
	public int numLocations;
	public boolean noSave;
	
	
	public Args(int numVehicles, int numLocations, boolean noSave) {
		this.numVehicles = numVehicles;
		this.numLocations = numLocations;
		this.noSave = noSave;
	}


	public static Args parse(String[] args) {
		if (args.length != 3) {
			System.out.printf("Expected 2 arguments, got %d. Usage:\n\t<numVehicles> <numLocations>", args.length - 1);
		}

		int numVehicles = Integer.parseInt(args[1]);
		int numLocations = Integer.parseInt(args[2]);
		
		assert 1 <= numVehicles && numVehicles <= 20;
		assert 1 <= numLocations && numLocations <= 10000;
		
		return new Args(numVehicles, numLocations, false);
	}

}
