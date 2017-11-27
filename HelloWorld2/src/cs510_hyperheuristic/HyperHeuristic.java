package cs510_hyperheuristic;

import java.util.List;

public class HyperHeuristic {

	public static void main(String[] args) {
		//start with random heuristic
		//run simulation with random heuristic
		//run hill climbing on simulation result.
		
		double[] weights = {0, 0, 0};
		List<Resource> resources = TestData.MakeResources(2);
		List<User> users = TestData.MakeUsers(300, resources);
		
		System.out.println(Simulation.Simulate(weights, resources, users, 1000));
		weights[0] = 0.5;
		System.out.println(Simulation.Simulate(weights, resources, users, 1000));
		weights[0] = 0.1;
		System.out.println(Simulation.Simulate(weights, resources, users, 1000));
	}
}
