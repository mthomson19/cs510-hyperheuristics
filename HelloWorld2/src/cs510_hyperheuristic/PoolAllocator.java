package cs510_hyperheuristic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//this handles adding blocks to a schedule
//if given a range and a height, computes that the overlap does not exceed a value
//runs in O(n) with linear memory allocation
public class PoolAllocator {
	private Map<Range, Double> allocatedSpace;
	public double unallocatedSize;
	public PoolAllocator(double space) {
		unallocatedSize = space;
		allocatedSpace = new HashMap<Range, Double>();
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
	public boolean canAllocateSpace(Range range, double s) {
		return s <= unallocatedSize && allocatedSpace.keySet().stream().filter(r -> r.overlaps(range)).allMatch(r -> allocatedSpace.get(r) + s <= unallocatedSize);
	}
	
	public boolean allocateSpace(Range range, double s) {
		if (s <= unallocatedSize) {
			List<Range> overlap = allocatedSpace.keySet().stream().filter(r -> r.overlaps(range)).collect(Collectors.toList());
			if (overlap.stream().anyMatch(r -> allocatedSpace.get(r) + s > unallocatedSize)) return false;
			List<Range> remaining = new ArrayList<Range>();
			remaining.add(range);
			overlap.forEach(r -> {
				try {
					double d = allocatedSpace.remove(r);
					allocatedSpace.put(r.getOverlap(range), d + s);
					List<Range> temp = remaining.stream().map(remain -> remain.subtract(r)).flatMap(remain -> remain.stream()).collect(Collectors.toList());
					remaining.clear();
					remaining.addAll(temp);
					List<Range> separatedR = r.subtract(range);
					separatedR.forEach(sr -> allocatedSpace.put(sr, d));
				} catch (Exception e) {
					//this never happens
				}
			});
			remaining.forEach(r -> allocatedSpace.put(r, s));
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return allocatedSpace.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof PoolAllocator) {
			PoolAllocator h = (PoolAllocator) o;
			return h.unallocatedSize == unallocatedSize && h.allocatedSpace.equals(allocatedSpace);
		}
		return false;
	}
}
