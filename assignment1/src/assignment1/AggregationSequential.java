package assignment1;

import java.util.List;

public class AggregationSequential<T> implements Aggregation<T> {

	public int aggregate(Combination<T> combination, List<T> list) {
		int result = combination.neutral();
		for (T i : list) {
			result = combination.combine(result, combination.projectInt(i));
		}
		return result;
	}

}
