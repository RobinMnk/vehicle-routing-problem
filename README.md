
# Vehicle Routing Problem

This repository contains a framework for algorithms to solve the Vehicle Routing Problem (VRP) approximately via Local Search Heuristics. It is a wrapper for the following four algorithms implemented by [emarkou](https://github.com/emarkou) in [this repository](https://github.com/emarkou/Large-Scale-Optimization-Vehicle-Routing-Problem):

- Nearest Neighbor
- Local Search with intra- relocation moves
- Local Search with intra- and inter- relocation moves
- Tabu Search

The framework runs all algorithms on the given problem instance(s) and returns the best solution.

## Features

The framework additionally offers

- A simple simulation environment management system
- An `InstanceHandler` to provide functions to systematically generate Problem instances
- A plotter for visualizing the solutions

## Usage

Make sure the `EXPERIMENTS_HOME` environment variable is set and points to a directory that will then contain the simulation results and plots.

## Plotter

The plotter will automatically generate the plots for all instances in the `input/` directory if a corresponding solution file exists in the `output/` directory.  Run the plotter with

```console
 py plotter/VRPplotter.py
```
