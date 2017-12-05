package cs510_hyperheuristic;

import java.util.List;
import java.util.PriorityQueue;

public class Simulation {

	public static int resourceQuanity = 3;
	public static int userQuanity = 100;


	public static int getResourceQuanity() {
		return resourceQuanity;
	}


	public static int getUserQuanity() {
		return userQuanity;
	}
	
	//always prefer solved.  If both solved, return fastest
	public static double Simulate(double[] weights, List<Resource> testResources, List<User> testUsers, int maxIterations) {
		HeuristicGenerator hg = new HeuristicGenerator(weights);
		PriorityQueue<Schedule> q = new PriorityQueue<Schedule>((s1, s2) -> -Double.compare(s1.getScore() + hg.getHeuristic(s1), s2.getScore() + hg.getHeuristic(s2)));
		q.offer(new Schedule(testResources, testUsers));
		Schedule best = q.peek();
		int score = 0;
		boolean solved = false;
		for (int i = 0; !solved && i < maxIterations && !q.isEmpty(); i++) {
			Schedule s = q.poll();
			for (Schedule c : s.getChildren()) {
				//if (!q.contains(c)) {
					q.offer(c);
				//}
			}
			if (s.compareTo(best) > 0) {
				best = s;
			}
			score--;
			solved = s.userAllocation.keySet().size() == testUsers.size();

		}
		return solved ? score + (testUsers.size() * 10) : best.getScore();
	}

}
