package cs510_hyperheuristic;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Schedule {
	private List<Resource> resources;
	private List<User> users;
	private Map<String, String> userAllocation;
	private double score;
	
	
	//this object needs to have some way of putting the users in, and calculate a score.
	
	//eventually, rather than passing a list of users, we will have a way to create as schedule 
	//where you take a base schedule and add 1 user to it.  (produce children of a node)
	//we will traverse the tree generated to find a good schedule
	public Schedule(List<Resource> Resources, List<User> Users)
	{
		// generate a empty schedule
		// make a copy of resources and users for each schedule
		resources = new LinkedList<Resource>(Resources);
		users = new LinkedList<User>(Users);
		userAllocation = new HashMap<String,String>();
		score = 0;
	}

	public Schedule(Schedule original)
	{
		// make a copy of the original schedule
		resources = new LinkedList<Resource>(original.resources);
		users = new LinkedList<User>(original.users);
		userAllocation = new HashMap<String,String>(original.userAllocation);
		score = original.score;
	}
	
	public double getScore() {
		return score;
	}
	
	public void addUser(Resource r, User u)
	{
		r.addUser(u);
		userAllocation.put(u.getName(), r.getName());
	}
	
	public boolean equals(Schedule sch1, Schedule sch2)
	{
		return sch1.userAllocation.equals(sch2.userAllocation);
	}
	
	public List<Schedule> getChildren()
	{
		List<Schedule> children = new LinkedList<Schedule>();
		
		// traverse all resources
		for(Resource r: resources)
		{
			// traverse all users
			for(User u: users)
			{
				// determine if user can be added to resource
				if(r.canAddUser(u))
				{
					Schedule copy = new Schedule(this);
					copy.addUser(r, u);
					children.add(copy);
				}
			}
		}
		return children;		
	}

}
