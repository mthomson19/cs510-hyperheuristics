package cs510_hyperheuristic;

public class HeuristicGenerator {
	public double[] weights;
	
	public HeuristicGenerator(double[] w) {
		weights = w;
	}
	
	//heuristic says how much is left
	private double baseHeuristic0(Schedule s) {
		if (s.mostRecentResource != null && s.mostRecentUser != null) {
			return s.mostRecentResource.getRemainingSpace(s.mostRecentUser.getRange());
		}
		return 0;
	}
	
	private double baseHeuristic1(Schedule s) {
		if (s.mostRecentResource != null && s.mostRecentUser != null) {
			//size out of base size
			return s.mostRecentUser.getSize();
		}
		return 0;
	}
	
	public double getHeuristic(Schedule s) {
		return weights[0] * baseHeuristic0(s) + weights[1] * baseHeuristic1(s);// weights[0] * baseHeuristic0(s) + weights[1] * baseHeuristic1(s) + weights[2] * baseHeuristic2(s);
	}
}
