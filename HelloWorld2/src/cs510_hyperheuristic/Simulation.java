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
	
	public static double Simulate(double[] weights, List<Resource> testResources, List<User> testUsers, int maxIterations) {
		HeuristicGenerator hg = new HeuristicGenerator(weights);
		PriorityQueue<Schedule> q = new PriorityQueue<Schedule>((s1, s2) -> -Double.compare(s1.getScore() + hg.getHeuristic(s1), s2.getScore() + hg.getHeuristic(s2)));
		//PriorityQueue<Schedule> q = new PriorityQueue<Schedule>();
		q.offer(new Schedule(testResources, testUsers));
		Schedule best = q.peek();
		int score = 0;
		for (int i = 0; i < maxIterations && !q.isEmpty(); i++) {
			Schedule s = q.poll();
			for (Schedule c : s.getChildren()) {
				q.offer(c);
			}
			if (s.compareTo(best) > 0) {
				best = s;
			}
			score--;
			if (s.userAllocation.keySet().size() == testUsers.size()) {
				break;
			}
		}
		System.out.println(best);
		System.out.println(best.getScore());
		System.out.println(score);
		System.out.println("number of users allocated: " + best.userAllocation.keySet().size());
		return score + best.getScore();
	}


	public static void main(String[] args) {


		// generate simulation data
		List<Resource> testResources = TestData.MakeResources(resourceQuanity);
		List<User> testUsers = TestData.MakeUsers(userQuanity, testResources);
		
		int score = 0;
		int maxIterations = 500;
		double weights[] = {0.9, 0.8, 0.7};

		
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
