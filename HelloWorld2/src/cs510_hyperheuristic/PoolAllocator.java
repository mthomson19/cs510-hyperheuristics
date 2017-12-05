package cs510_hyperheuristic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//this handles adding blocks to a schedule
//if given a range and a height, computes that the overlap does not exceed a value
//runs in O(n) with linear memory allocation
/**
 * @author Andrew Miller, Marc Thomson
 *
 */
public class PoolAllocator {
	public Map<Range, Double> allocatedSpace;
	public double unallocatedSize;

	public PoolAllocator(double space) {
		unallocatedSize = space;
		allocatedSpace = new HashMap<Range, Double>();
	}

	public PoolAllocator(PoolAllocator original) {
		unallocatedSize = original.unallocatedSize;
		allocatedSpace = new HashMap<Range, Double>(original.allocatedSpace);
	}

	public boolean canAllocateSpace(Range range, double s) {
		return s <= unallocatedSize && allocatedSpace.keySet().stream().filter(r -> r.overlaps(range))
				.allMatch(r -> allocatedSpace.get(r) + s <= unallocatedSize);
	}

	public boolean allocateSpace(Range range, double s) {

		if (s <= unallocatedSize) {
			List<Range> overlap = allocatedSpace.keySet().stream().filter(r -> r.overlaps(range))
					.collect(Collectors.toList());

			if (overlap.stream().anyMatch(r -> allocatedSpace.get(r) + s > unallocatedSize))
				return false;

			List<Range> remaining = new ArrayList<Range>();
			remaining.add(range);
			overlap.forEach(r -> {

				try {
					double d = allocatedSpace.remove(r);
					allocatedSpace.put(r.getOverlap(range), d + s);
					List<Range> temp = remaining.stream().map(remain -> remain.subtract(r))
							.flatMap(remain -> remain.stream()).collect(Collectors.toList());
					remaining.clear();
					remaining.addAll(temp);
					List<Range> separatedR = r.subtract(range);
					separatedR.forEach(sr -> allocatedSpace.put(sr, d));
				} catch (Exception e) {
					// this never happens
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
