package cs510_hyperheuristic;
import java.util.LinkedList;
import java.util.List;

public class Resource {
	private PoolAllocator allocator;
	private double maxBW;
	private List<User> users;
	private String name;
	
	public Resource(double size, String name) {
		maxBW = size;
		allocator = new PoolAllocator(maxBW);
		users = new LinkedList<User>();
		this.name = name;
	}
	
	public Resource(Resource original) {
		allocator = new PoolAllocator(original.allocator);;
		maxBW = original.maxBW;
		users = new LinkedList<User>(original.users);;
		name = original.name;
	}
	
	public boolean canAddUser(User o) {
		//verifies with the allocator that we can add the user to this resources
		return o.validResources.contains(this) && allocator.canAllocateSpace(o.getRange(), o.getSize());
	}
	
	public void addUser(User o) {
		//adds the user to the allocator and list of users
		allocator.allocateSpace(o.getRange(), o.getSize());
		users.add(o);
	}
	
	public String getName(){ 
		return name;
	}
	
}
