package tdd.kiss;

import java.util.*;
import java.util.function.BiPredicate;

public class MaxMin<T> {

    public T max(List<T> value, Comparator<T> comparator) throws Exception {
        validateComparator(comparator);
        return conditionalSearch(value, (v1, v2) -> comparator.compare(v1, v2) < 0);
    }

    public T min(List<T> value, Comparator<T> comparator) throws Exception {
        validateComparator(comparator);
        return conditionalSearch(value, (v1, v2) -> comparator.compare(v1, v2) > 0);
    }

    private T conditionalSearch(List<T> value, BiPredicate<T, T> condition) throws Exception {
        if (value == null || value.isEmpty()) {
            throw new Exception("Empty input list");
        }
        T result = value.get(0);
        for (T checkedValue : value) {
            if (checkedValue == null) {
                continue;
            }
            if (condition.test(result, checkedValue)) {
                result = checkedValue;
            }
        }
        return result;
    }

    private void validateComparator(Comparator<T> comparator) throws Exception {
        if (comparator == null) {
            throw new Exception("No comparator provided");
        }
    }
}
