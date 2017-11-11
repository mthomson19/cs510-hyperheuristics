package cs510_hyperheuristic;

import java.util.List;

public class Simulation {

	public static void main(String[] args) {

		// generate simulation data
		List<Resource> testResources = TestData.MakeResources(10);
		List<User> testUsers = TestData.MakeUsers(1000, testResources);
		
		// add users to resources
		Schedule simSchedule = new Schedule(testResources, testUsers);
	}

}
