package cs510_hyperheuristic;
import java.util.LinkedList;
import java.util.List;

public class Resource {
	private PoolAllocator allocator;
	private double maxBW;
	private List<Operator> users;
	private String name;
	
	public Resource(double size) {
		maxBW = size;
		allocator = new PoolAllocator(maxBW);
		users = new LinkedList<Operator>();
	}
	
	public boolean canAddUser(Operator o) {
		//verifies with the allocator that we can add the user to this resources
		return allocator.canAllocateSpace(o.getRange(), o.getSize());
	}
	
	public void addUser(Operator o) {
		//adds the user to the allocator and list of users
		allocator.allocateSpace(o.getRange(), o.getSize());
		users.add(o);
	}
	
	public String getName(){ 
		return name;
	}
	
}
