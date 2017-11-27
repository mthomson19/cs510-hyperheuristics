package cs510_hyperheuristic;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Resource {
	private PoolAllocator allocator;
	private double maxBW;
	private String name;
	
	public Resource(double size, String name) {
		maxBW = size;
		allocator = new PoolAllocator(maxBW);
		this.name = name;
	}
	
	public Resource(Resource original) {
		allocator = new PoolAllocator(original.allocator);
		maxBW = original.maxBW;
		name = original.name;
	}
	
	public double getRemainingSpace(Range r) {
		return (allocator.unallocatedSize - allocator.allocatedSpace.keySet().stream()
				.filter(a -> r.overlaps(a))
				.mapToDouble(a -> a.size() * allocator.allocatedSpace.get(a))
				.sum()) / r.size();
	}
	
	public boolean canAddUser(User o) {
		//verifies with the allocator that we can add the user to this resources
		return /*o.validResources.contains(this) && */allocator.canAllocateSpace(o.getRange(), o.getSize());
	}
	
	public void addUser(User o) {
		//adds the user to the allocator and list of users
		allocator.allocateSpace(o.getRange(), o.getSize());
	}
	
	public String getName(){ 
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Resource) {
			Resource r = (Resource) o;
			return name.equals(r.name);
		}
		return false;
	}
	
}
