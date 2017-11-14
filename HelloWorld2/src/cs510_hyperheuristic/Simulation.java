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
		List<Schedule> level2Schedule = level1Schedule.getChildren();
		List<Schedule> level3Schedule = level2Schedule.getChildren();
	}

}

/**	
public static void main(String[] args) {
	PoolAllocator allocator = new PoolAllocator(100);
	
	Range block1Range = new Range(0, 100);
	double block1Size = 50;
	
	Range block2Range = new Range(50, 100);
	double block2Size = 50;
	
	Range block3Range = new Range(30, 51);
	double block3Size = 10;
	
	System.out.println(allocator.canAllocateSpace(block1Range, block1Size));
	allocator.allocateSpace(block1Range, block1Size);
	
	System.out.println(allocator.canAllocateSpace(block2Range, block2Size));
	allocator.allocateSpace(block2Range, block2Size);
	
	System.out.println(allocator.canAllocateSpace(block3Range, block3Size));
	allocator.allocateSpace(block3Range, block3Size);
}
**/	
