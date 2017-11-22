package cs510_hyperheuristic;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TestData {
	
	public TestData()
	{
	}
	
	public static List<Resource> MakeResources(int quanity)
	{
		double maxBW = 4;	// 100 percent available
		String name;
		List<Resource> resourceTestData = new LinkedList<Resource>();
		
		// generate  Resources
		for(int i=0; i<quanity; i++)
		{
			name = "Resource"+i;
			Resource resource = new Resource(maxBW, name);
			resourceTestData.add(resource);
		}
		return resourceTestData;
	}
	
	public static List<User> MakeUsers(int quanity, List<Resource> availableResources)
	{
		double duration;
		LocalDateTime start;
		LocalDateTime stop;
		double startValue;
		double stopValue;
		double size;
		int priority;
		String name;
		List<String> validResourceNames = new LinkedList<String>();
		List<Resource> subAvailResources = new LinkedList<Resource>();
		List<User> operatorTestData = new LinkedList<User>();
		Random random = new Random();
		
		// generate  Users
		for(int i=0; i<quanity; i++)
		{
			duration = random.nextDouble() * 2 + 1;
			start = LocalDateTime.now().plusHours(random.nextInt(24 - 3)); 
			stop = start.plusHours((long) duration);
			startValue = start.toEpochSecond(ZoneOffset.UTC);
			stopValue = stop.toEpochSecond(ZoneOffset.UTC);
			size = random.nextInt(5 - 1) + 1;		// between 1 and 5 percent
			priority = random.nextInt(10 - 1) + 1;	// between 1 and 10
			name = "Operator"+i;
			Collections.shuffle(availableResources);
			subAvailResources = availableResources.subList(0, 3);
			
			for(int j=0; j<3; j++)
			{
				validResourceNames.add(subAvailResources.get(j).getName());
			}

			User operator = new User(startValue, stopValue, size, priority, name, validResourceNames, availableResources);
			
			operatorTestData.add(operator);
			
		}

		return operatorTestData;
	}
}
