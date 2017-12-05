package cs510_hyperheuristic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Schedule implements Comparable<Schedule>
{
	public List<Resource> resources;
	private List<User> users;
	public Map<User, Resource> userAllocation;
	private double score;
	public User mostRecentUser;
	public Resource mostRecentResource;
	
	
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
		userAllocation = new HashMap<User,Resource>();
		score = 0;
	}

	public Schedule(Schedule original)
	{
		// make a copy of the original schedule
		resources = new LinkedList<Resource>();
		for (Resource r : original.resources) {
			resources.add(new Resource(r));
		}
		users = original.users;
		userAllocation = new HashMap<User,Resource>(original.userAllocation);
		score = original.score;
	}
	
	public double getScore() {
		return score;
	}
	
	public void addUser(Resource r, User u)
	{
		r.addUser(u);
		userAllocation.put(u, r);
		score += u.getPriority();
		mostRecentUser = u;
		mostRecentResource = r;
	}
	
	
	public List<Schedule> getChildren()
	{
		List<Schedule> children = new LinkedList<Schedule>();
		// traverse all resources
		Schedule copy = new Schedule(this);
		for(int i = 0; i < copy.resources.size(); i++)
		{
			Resource r = copy.resources.get(i);
			// traverse all users
			for(int j = 0; j < copy.users.size(); j++)
			{
				User u = copy.users.get(j);
				if (!copy.userAllocation.keySet().contains(u)) {
					
					// determine if user can be added to resource
					if(r.canAddUser(u))
					{
						copy.addUser(r, u);
						children.add(copy);
						copy = new Schedule(this);
					}
				}
			}
		}
		return children;		
	}

	public Map<User, Resource> getUserAllocation() {
		return userAllocation;
	}
	
	@Override
	public int compareTo(Schedule otherSchedule) {
		return Double.compare(score, otherSchedule.score);
	}
	
	@Override
	public String toString() {
		return userAllocation.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Schedule) {
			Schedule s = (Schedule) o;
			return s.getUserAllocation().equals(userAllocation);
		}
		return false;
	}

}
