package cs510_hyperheuristic;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule {
	private List<Resource> resources;
//	private Map<User, Boolean> userAllocation;
	private double score;
	private Map<String, String> userAllocation = new HashMap<String,String>();
	
	
	//this object needs to have some way of putting the users in, and calculate a score.
	
	//eventually, rather than passing a list of users, we will have a way to create as schedule 
	//where you take a base schedule and add 1 user to it.  (produce children of a node)
	//we will traverse the tree generated to find a good schedule
	public Schedule(List<Resource> resources, List<User> users)
	{
		// traverse all resources
		for(Resource r: resources)
		{
			// traverse all users
			for(User u: users)
			{
				// determine if user can be added to resource
				if(r.canAddUser(u))
				{
					// add user
					r.addUser(u);
					// update map
					userAllocation.put(u.getName(), r.getName());
				}
			}
		}
		score = userAllocation.size();
	}
}
