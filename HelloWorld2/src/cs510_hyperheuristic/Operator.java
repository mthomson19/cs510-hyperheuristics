package cs510_hyperheuristic;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Operator {
	private Range timeRange; //the start and stop time of the user
	private double bw;  //the amount of bandwidth this user needs
	List<Resource> validResources; //a list of resources this user can be scheduled under
	private int priority; //the priority of this user.  1 is highest priority, 10 is lowest
	private String name; //the name of the operator
	
	public Operator(double start, double stop, double size, int p, String n,List<String> validResourceNames, List<Resource> availableResources) {
		timeRange = new Range(start, stop);
		bw = size;
		validResources = new LinkedList<Resource>();
		priority = p;
		name = n;

		//for each available resource, if the name of the resources is in valid resourcenames, add to validResources
		Iterator<Resource> availResourceIterator = availableResources.iterator();
		while(availResourceIterator.hasNext())
		{
			if(validResourceNames.contains(availResourceIterator.next().getName()))
			{
				validResources.add(availResourceIterator.next());
			}
		}
	}
	
	public Range getRange()
	{
		return timeRange;
	}
	
	public double getSize()
	{
		return bw;
	}
	
	public int getPriority()
	{
		return priority;
	}
	
	public String getName()
	{
		return name;
	}
}
