package cs510_hyperheuristic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Andrew Miller, Marc Thomson
 *
 * a range describes two values, a start and a stop.
 * it contains many of the useful functions for ranges, such as intersections, union, subtraction, etc.
 * 
 */
public class Range {
	public double start;
	public double stop;
	private int hashcode;

	public Range(double start, double stop) {
		this.start = start;
		this.stop = stop;
		hashcode = (start + "" + stop).hashCode();
	}

	public Range(Range r) {
		start = r.start;
		stop = r.stop;
	}

	public boolean canContain(Range r) {
		return r.start >= start && r.stop <= stop;
	}

	public boolean overlaps(Range r) {
		return r.start < stop && r.stop > start;
	}

	public Range union(Range r) {
		double[] edges = { start, r.start, stop, r.stop };
		Arrays.sort(edges);
		return new Range(edges[0], edges[3]);
	}

	public List<Range> subtract(Range r) {
		List<Range> ranges = new ArrayList<Range>();
		if (r.canContain(this))
			return ranges;
		if (canContain(r)) {
			ranges.add(new Range(start, r.start));
			ranges.add(new Range(r.stop, stop));
			return ranges;
		}
		if (!overlaps(r)) {
			ranges.add(this);
			return ranges;
		}
		if (start < r.start) {
			ranges.add(new Range(start, r.start));
			return ranges;
		}
		if (stop > r.stop) {
			ranges.add(new Range(r.stop, stop));
			return ranges;
		}
		return ranges;
	}

	public Range getOverlap(Range r) throws Exception {
		if (overlaps(r)) {
			double[] edges = { start, stop, r.start, r.stop };
			Arrays.sort(edges);
			return new Range(edges[1], edges[2]);
		}
		throw new Exception("The two ranges do not overlap");
	}

	public double size() {
		return stop - start;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Range) {
			Range r = (Range) o;
			return start == r.start && stop == r.stop;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return hashcode;
	}

	@Override
	public String toString() {
		return start + "->" + stop;
	}
}
