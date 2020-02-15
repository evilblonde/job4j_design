package tdd.kiss;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class MaxMin<T> {

    public T max(List<T> value, Comparator<T> comparator) {
        if (comparator == null) {
            return null;
        }
        return conditionalSearch(value, (v1, v2) -> comparator.compare(v1, v2) < 0);
    }

    public T min(List<T> value, Comparator<T> comparator) {
        if (comparator == null) {
            return null;
        }
        return conditionalSearch(value, (v1, v2) -> comparator.compare(v1, v2) > 0);
    }

    private T conditionalSearch(List<T> value, BiPredicate<T, T> condition) {
        if (value == null || value.isEmpty()) {
            return null;
        }
//        в условии было не использовать стрим апи, но я его использовал только чтобы отфильтровать null'ы и не писать кучу проверок дальше
        List<T> filtered = value.stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            return null;
        }
        Iterator<T> iterator = filtered.iterator();
        T result = iterator.next();
        while (iterator.hasNext()) {
            T checkedValue = iterator.next();
            if (condition.test(result, checkedValue)) {
                result = checkedValue;
            }
        }
        return result;
    }
}
