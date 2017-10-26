package barnard;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class Subsets {
    public static <T> Set<Set<T>> of(Set<T> set) {
        Iterator<T> i = set.iterator();
        // If the set is empty, return a set containing this set (the empty set).
        // This is our recursive base case.
        if (!i.hasNext()) return Set.of(set);

        // Otherwise, take the "first" item (sets are unordered, so this is best
        // thought of as "some item").
        T firstItem = i.next();

        // Find all subsets of the input set without this item
        Set<Set<T>> subsetsWithoutFirstItem = Subsets.of(new Copy(set).without(firstItem));

        // Then return those subsets, in addition each of those sets with the
        // first item.
        HashSet<Set<T>> subsets = new HashSet(subsetsWithoutFirstItem);
        for (Set<T> subset : subsetsWithoutFirstItem) {
            subsets.add(new Copy(subset).with(firstItem));
        }
        return subsets;
    }

    public static void main(String... args) {
        for (Set<String> set : Subsets.of(Set.of(args))) {
            System.out.println("(" + set.stream().collect(Collectors.joining(", ")) + ")");
        }
    }
}

class Copy<T> {
    Copy(Set<T> set) {
        this.set = set;
    }

    public Set<T> with(T item) {
        HashSet<T> copy = this.copy();
        copy.add(item);
        return copy;
    }

    public Set<T> without(T item) {
        HashSet<T> copy = this.copy();
        copy.remove(item);
        return copy;
    }

    public HashSet<T> copy() {
        return new HashSet(set);
    }

    public Set<T> set;
}