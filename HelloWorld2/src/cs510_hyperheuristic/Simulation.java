package cs510_hyperheuristic;


import java.util.List;
import java.util.PriorityQueue;

public class Simulation {

	public static void main(String[] args) {

		// generate simulation data
		List<Resource> testResources = TestData.MakeResources(3);
		List<User> testUsers = TestData.MakeUsers(3, testResources);
		
		int score = 0;
		int maxIterations = 500;
		double weights[] = {0.9, 0.8};

		
		PriorityQueue<Schedule> q = new PriorityQueue<Schedule>();
		HeuristicGenerator hg = new HeuristicGenerator(weights);		
		//PriorityQueue<Schedule> q = new PriorityQueue<Schedule>((s1, s2) -> Double.compare(s2.getScore() + hg.getHeuristic(s2), s1.getScore() + hg.getHeuristic(s1)));
		q.offer(new Schedule(testResources, testUsers));
		Schedule best = q.peek();
		for (int i = 0; i < maxIterations && !q.isEmpty(); i++) {
			Schedule s = q.poll();
			for (Schedule c : s.getChildren()) {
				q.offer(c);
			}
			if (s.compareTo(best) < 0) {
				best = s;
			}
			score--;
		}
		
		double hscore = hg.getHeuristic(best);
		
		System.out.println(best + " is the best schedule");
		System.out.println(score);
	}

}
