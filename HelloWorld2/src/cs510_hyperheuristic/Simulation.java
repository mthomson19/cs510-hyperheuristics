package cs510_hyperheuristic;

import java.util.List;

public class Simulation {

	public static void main(String[] args) {

		// generate simulation data
		List<Resource> testResources = TestData.MakeResources(3);
		List<User> testUsers = TestData.MakeUsers(3, testResources);
		
		// generate initial empty schedule
		Schedule rootSchedule = new Schedule(testResources, testUsers);
		
		// generate tree
		List<Schedule> level1Schedule = rootSchedule.getChildren();
	}

}
