package com.bascker.bsutil;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 集合工具类
 *
 * @author bascker
 */
public class CollectionUtils {

    /**
     * Desc sort
     *
     * @param numbers
     */
    public static void sortDesc (final List<Integer> numbers) {
        numbers.sort((o1, o2) -> {
            if (o1 > o2) {
                return -1;
            } else if (o1 < o2) {
                return 1;
            }

            return 0;
        });
    }

    public static boolean isValid (final Collection collection) {
        return Objects.nonNull(collection) && !collection.isEmpty();
    }

    public static String toString(final Collection collection) {
        if (!isValid(collection)) {
            return "";
        }

        final StringBuffer sb = new StringBuffer();
        final Consumer action = o -> sb.append(o.toString() + " ");
        collection.forEach(action);
        sb.delete(sb.length() - 1, sb.length());

        return sb.toString();
    }

    // TODO
    public static <T> T[] toArray (final Collection<T> collection) {
        if (isValid(collection)) {
            return null;
        }



        return null;
    }

    private CollectionUtils() {}
}
