package cs510_hyperheuristic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HyperHeuristic {

	public static void main(String[] args) {
		//start with random heuristic
		//run simulation with random heuristic
		//run hill climbing on simulation result.
		
		double[] weights = {0, 0, 0};
		List<List<Resource>> resourceGroups = new LinkedList<List<Resource>>();
		List<List<User>> userGroups = new LinkedList<List<User>>();
		for (int i = 0; i < 10; i++) {
			resourceGroups.add(TestData.MakeResources(3));
			userGroups.add(TestData.MakeUsers(100, resourceGroups.get(i)));
		}
		double[] optimal = weightClimb(resourceGroups, userGroups, 2, .001, 2, 1000);
		System.out.println(Arrays.toString(optimal));
		//double[] trialWeights = {0.129, 0, 0};
		//System.out.println(getAverageScore(trialWeights, resourceGroups, userGroups, 1000));
	}
	
	public static double[] weightClimb(List<List<Resource>> resourceGroups, List<List<User>> userGroups, double initialStep, double accuracy, double falloff, int depth) {
		double stepSize = initialStep;
		double[] weights = {Math.random() * stepSize, 0, 0};
		double bestScore = getAverageScore(weights, resourceGroups, userGroups, depth);
		System.out.println(Arrays.toString(weights) + ": " + bestScore);
		while (stepSize > accuracy) {
			double[] localBestWeights = weights;
			double localBestScore = bestScore;
			double[] t1 = weights.clone();
			t1[0] += stepSize;
			double t1Score = getAverageScore(t1, resourceGroups, userGroups, depth);
			if (t1Score >= localBestScore) {
				localBestScore = t1Score;
				localBestWeights = t1;
			}
			double[] t2 = weights.clone();
			t2[0] -= stepSize;
			double t2Score = getAverageScore(t2, resourceGroups, userGroups, depth);
			if (t2Score >= localBestScore) {
				localBestScore = t2Score;
				localBestWeights = t2;
			}
			if (localBestScore == bestScore) {
				stepSize /= falloff;
				if (stepSize < accuracy) {
					return weights;
				}
			}
			bestScore = localBestScore;
			weights = localBestWeights;
			System.out.println(Arrays.toString(weights) + ": " + bestScore);
		}
		return weights;
	}
	
	private static double getAverageScore(double[] weights, List<List<Resource>> resourceGroups, List<List<User>> userGroups, int depth) {
		double cumulative = 0;
		for (int i = 0; i < resourceGroups.size(); i++) {
			cumulative += Simulation.Simulate(weights, resourceGroups.get(i), userGroups.get(i), depth);
		}
		return cumulative / resourceGroups.size();
	}
}
