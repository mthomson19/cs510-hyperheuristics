package cs510_hyperheuristic;

public class HeuristicGenerator {
	public double[] weights;
	
	public HeuristicGenerator(double[] w) {
		weights = w;
	}
	
	// count the number of users
	/*private double baseHeuristic0(Schedule s){
		//return s.userAllocation.size() / Simulation.getUserQuanity();
		return 0;
	}*/
	
	//heuristic says how much is left
	private double baseHeuristic0(Schedule s) {
		if (s.mostRecentResource != null && s.mostRecentUser != null) {
			return s.mostRecentResource.getRemainingSpace(s.mostRecentUser.getRange());
		}
		return 0;
	}
	
	// summarize the priority of the users allocated
	private double baseHeuristic1(Schedule s) {
		int totalPriority = 0;
		for(User key : s.userAllocation.keySet())
		{
			totalPriority += key.getPriority();
		}
		return totalPriority / Simulation.getUserQuanity() * 10;
	}
	
	// determine the percentage of total bandwidth allocated
	private double baseHeuristic2(Schedule s) {
		/*double totalMaxBW = 100 * Simulation.resourceQuanity;
		double totalUsedBW = 0;
		
		for(User key : s.userAllocation.keySet())
		{
			totalUsedBW += key.getSize();
		}
		return totalUsedBW / totalMaxBW;*/
		return 0;
	}
	
	public double getHeuristic(Schedule s) {
		return weights[0] * baseHeuristic0(s);// weights[0] * baseHeuristic0(s) + weights[1] * baseHeuristic1(s) + weights[2] * baseHeuristic2(s);
	}
}
