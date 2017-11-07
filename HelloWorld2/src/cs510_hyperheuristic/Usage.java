package cs510_hyperheuristic;
import java.util.List;
import java.util.Map;

public class Usage {
	private List<Resource> resources;
	private Map<Operator, Boolean> userAllocation;
	private double score;
	
	
	//this object needs to have some way of putting the users in, and calculate a score.
	
	//eventually, rather than passing a list of users, we will have a way to create as schedule 
	//where you take a base schedule and add 1 user to it.  (produce children of a node)
	//we will traverse the tree generated to find a good schedule
	public Usage(List<Resource> resourceNames, List<Operator> u) {
		
	}
}
