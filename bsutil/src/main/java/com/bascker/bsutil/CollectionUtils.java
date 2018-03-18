package com.bascker.bsutil;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * 集合工具类
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    /**
     * Desc sort
     *
     * @param numbers
     */
    public static void sortDesc(final List<Integer> numbers) {
        numbers.sort((o1, o2) -> {
            if (o1 > o2) {
                return -1;
            } else if (o1 < o2) {
                return 1;
            }

            return 0;
        });
    }

    public static String toString(final Collection collection) {
        final StringBuffer sb = new StringBuffer();
        final Consumer action = o -> sb.append(o.toString() + " ");
        collection.forEach(action);
        sb.delete(sb.length() - 1, sb.length());

        return sb.toString();
    }

}
