package fr.klemek.betterlists;

import java.util.*;
import java.util.function.Function;

/**
 * An extension of the java.util.List interface which include some of the C#
 * LINQ useful functions.
 *
 * @author Klemek
 * @see java.util.List
 */
public interface BetterList<T> extends List<T> {

    /**
     * Determines whether all elements of the sequence satisfy a condition.
     *
     * @param predicate - A function to test each element for a condition.
     * @return true if every element of the source sequence passes the test in the
     * specified predicate, or if the sequence is empty; otherwise, false.
     */
    default boolean all(Function<T, Boolean> predicate) {
        for (T element : this)
            if (!predicate.apply(element))
                return false;
        return true;
    }

    /**
     * Determines whether any element of the sequence satisfies a condition.
     *
     * @param predicate - A function to test each element for a condition.
     * @return true if any elements in the source sequence pass the test in the
     * specified predicate; otherwise, false.
     */
    default boolean any(Function<T, Boolean> predicate) {
        for (T element : this)
            if (predicate.apply(element))
                return true;
        return false;
    }

    /**
     * Returns the number of elements in the sequence.
     *
     * @return The number of elements in the input sequence.
     */
    default int count() {
        return count(e -> true);
    }

    /**
     * Returns a number that represents how many elements in the specified sequence
     * satisfy a condition.
     *
     * @param predicate - A function to test each element for a condition.
     * @return A number that represents how many elements in the sequence satisfy
     * the condition in the predicate function.
     */
    default int count(Function<T, Boolean> predicate) {
        int out = 0;
        for (T element : this)
            if (predicate.apply(element))
                out++;
        return out;
    }

    /**
     * Produces the set exclusion of two sequences.
     *
     * @param other - Another List whose distinct elements form the second set for the
     *              exclusion.
     * @return A List that contains the elements from the first sequence not present
     * in the other.
     */
    default BetterList<T> exclusion(List<T> other) {
        BetterList<T> out = new BetterArrayList<>();
        for (T element : this)
            if (!other.contains(element))
                out.add(element);
        return out;
    }

    /**
     * Returns the first element in the sequence.
     *
     * @return The first element in the sequence.
     * @throws NoSuchElementException If the sequence is empty.
     */
    default T first() {
        return first(e -> true);
    }

    /**
     * Returns the first element in the sequence that satisfies a specified
     * condition.
     *
     * @param predicate - A function to test each element for a condition.
     * @return The first element in the sequence that passes the test in the
     * specified predicate function.
     * @throws NoSuchElementException No element satisfies the condition in predicate or the sequence
     *                                is empty.
     */
    default T first(Function<T, Boolean> predicate) {
        for (T element : this)
            if (predicate.apply(element))
                return element;
        throw new NoSuchElementException();
    }

    /**
     * Returns the first element of the sequence that satisfies a condition or the
     * default value if no such element is found.
     *
     * @param predicate    - A function to test each element for a condition.
     * @param defaultValue - A default value to be returned if no element passes the test
     * @return defaultValue if the sequence is empty or if no element passes the
     * test specified by predicate; otherwise, the first element in the
     * sequence that passes the test specified by predicate.
     */
    default T firstOrDefault(Function<T, Boolean> predicate, T defaultValue) {
        for (T element : this)
            if (predicate.apply(element))
                return element;
        return defaultValue;
    }

    /**
     * Returns the first element of the sequence or a default value if the sequence
     * is empty.
     *
     * @param defaultValue - A default value to be returned if the sequence is empty
     * @return defaultValue if the sequence is empty otherwise, the first element in
     * the sequence.
     */
    default T firstOrDefault(T defaultValue) {
        return firstOrDefault(e -> true, defaultValue);
    }

    /**
     * Returns the last element of the sequence.
     *
     * @return the last element of the sequence.
     * @throws NoSuchElementException If the sequence is empty.
     */
    default T last() {
        return last(e -> true);
    }

    /**
     * Returns the last element of the sequence that satisfies a specified
     * condition.
     *
     * @param predicate - A function to test each element for a condition.
     * @return the last element of the sequence that satisfies a specified
     * condition.
     * @throws NoSuchElementException No element satisfies the condition in predicate or the sequence
     *                                is empty.
     */
    default T last(Function<T, Boolean> predicate) {
        T value = null;
        for (T element : this)
            if (predicate.apply(element))
                value = element;
        if (value == null)
            throw new NoSuchElementException();
        return value;
    }

    /**
     * Returns the last element of the sequence that satisfies a condition or the
     * default value if no such element is found.
     *
     * @param predicate    - A function to test each element for a condition.
     * @param defaultValue - A default value to be returned if no element passes the test
     * @return defaultValue if the sequence is empty or if no element passes the
     * test specified by predicate; otherwise, the last element in the
     * sequence that passes the test specified by predicate.
     */
    default T lastOrDefault(Function<T, Boolean> predicate, T defaultValue) {
        T value = null;
        for (T element : this)
            if (predicate.apply(element))
                value = element;
        return value == null ? defaultValue : value;
    }

    /**
     * Returns the last element of the sequence or a default value if the sequence
     * is empty.
     *
     * @param defaultValue - A default value to be returned if the sequence is empty
     * @return defaultValue if the sequence is empty otherwise, the last element in
     * the sequence.
     */
    default T lastOrDefault(T defaultValue) {
        return lastOrDefault(e -> true, defaultValue);
    }

    /**
     * Invokes a transform function on each element of the sequence and returns the
     * maximum nullable Double value.
     *
     * @param selector - A transform function to apply to each element.
     * @return The value of type Double that corresponds to the maximum value in the
     * sequence or null if the sequence is empty.
     */
    default Double max(Function<T, Double> selector) {
        Double max = null;
        for (T element : this)
            if (max == null || selector.apply(element) > max)
                max = selector.apply(element);
        return max;
    }

    /**
     * Computes the mean of the sequence of Double values that are obtained by
     * invoking a transform function on each element of the input sequence.
     *
     * @param selector - A transform function to apply to each element.
     * @return The mean of the projected values. Null if the sequence contains no
     * elements.
     */
    default Double mean(Function<T, Double> selector) {
        int count = this.count();
        if (count == 0)
            return null;
        return this.sum(selector) / this.count();
    }

    /**
     * Invokes a transform function on each element of the sequence and returns the
     * minimum nullable Double value.
     *
     * @param selector - A transform function to apply to each element.
     * @return The value of type Double that corresponds to the minimum value in the
     * sequence or null if the sequence is empty.
     */
    default Double min(Function<T, Double> selector) {
        Double min = null;
        for (T element : this)
            if (min == null || selector.apply(element) < min)
                min = selector.apply(element);
        return min;
    }

    /**
     * Sorts the elements of a sequence in ascending order by using a specified comparer.
     *
     * @param <E>      The type of the projected values lists
     * @param selector - A transform function to apply to each element.
     * @return a List whose elements are sorted according to a key.
     */
    default <E extends Comparable<E>> BetterList<T> orderBy(Function<T, E> selector) {
        BetterList<T> out = new BetterArrayList<>();
        out.addAll(this);
        Collections.sort(out, (o1, o2) -> selector.apply(o1).compareTo(selector.apply(o2)));
        return out;
    }

    /**
     * Sorts the elements of a sequence in descending order by using a specified comparer.
     *
     * @param <E>      The type of the projected values lists
     * @param selector - A transform function to apply to each element.
     * @return a List whose elements are sorted according to a key.
     */
    default <E extends Comparable<E>> BetterList<T> orderByDescending(Function<T, E> selector) {
        BetterList<T> out = new BetterArrayList<>();
        out.addAll(this);
        Collections.sort(out, (o1, o2) -> selector.apply(o2).compareTo(selector.apply(o1)));
        return out;
    }

    /**
     * Inverts the order of the elements in the sequence.
     *
     * @return A sequence whose elements correspond to those of the sequence in
     * reverse order.
     */
    default BetterList<T> reverse() {
        BetterList<T> out = new BetterArrayList<>(this.size());
        for (T element : this)
            out.add(0, element);
        return out;
    }

    /**
     * Projects each element of a sequence into a new form.
     *
     * @param <E>      The type of the projected values
     * @param selector - A transform function to apply to each element.
     * @return A List whose elements are the result of invoking the transform
     * function on each element of the sequence.
     */
    default <E> BetterList<E> select(Function<T, E> selector) {
        BetterList<E> out = new BetterArrayList<>();
        for (T element : this)
            out.add(selector.apply(element));
        return out;
    }

    /**
     * Projects each element of a sequence into a new list and flattens the
     * resulting sequences into one sequence.
     *
     * @param <E>      The type of the projected values lists
     * @param selector - A transform function to apply to each element.
     * @return A List whose elements are the result of invoking the one-to-many
     * transform function on each element of the input sequence.
     */
    default <E> BetterList<E> selectMany(Function<T, Collection<? extends E>> selector) {
        BetterList<E> out = new BetterArrayList<>();
        for (T element : this)
            out.addAll(selector.apply(element));
        return out;
    }

    /**
     * Projects each element of a sequence into a new list and flattens the
     * resulting sequences into one sequence.
     *
     * @param <E>      The type of the projected values lists
     * @param selector - A transform function to apply to each element.
     * @return A List whose elements are the result of invoking the one-to-many
     * transform function on each element of the input sequence.
     */
    default <E> BetterList<E> selectManyArrays(Function<T, E[]> selector) {
        BetterList<E> out = new BetterArrayList<>();
        for (T element : this)
            out.addAll(Arrays.asList(selector.apply(element)));
        return out;
    }

    /**
     * Bypasses a specified number of elements in the sequence and then returns the
     * remaining elements.
     *
     * @param count - The number of elements to skip before returning the remaining
     *              elements.
     * @return a List that contains the elements that occur after the specified
     * index in the sequence.
     */
    default BetterList<T> skip(int count) {
        BetterList<T> out = new BetterArrayList<>();
        int n = 0;
        for (T element : this) {
            if (n >= count)
                out.add(element);
            n++;
        }
        return out;
    }

    /**
     * Bypasses elements in the sequence as long as a specified condition is true
     * and then returns the remaining elements.
     *
     * @param predicate - A function to test each element for a condition.
     * @return a List that contains the elements from the sequence starting at the
     * first element in the linear series that does not pass the test
     * specified by predicate.
     */
    default BetterList<T> skipWhile(Function<T, Boolean> predicate) {
        BetterList<T> out = new BetterArrayList<>();
        boolean match = true;
        for (T element : this)
            if (!match || !predicate.apply(element)) {
                match = false;
                out.add(element);
            }
        return out;
    }

    /**
     * Computes the sum of the sequence of Double values that are obtained by
     * invoking a transform function on each element of the input sequence.
     *
     * @param selector - A transform function to apply to each element.
     * @return The sum of the projected values. Zero if the sequence contains no
     * elements.
     */
    default Double sum(Function<T, Double> selector) {
        double sum = 0d;
        for (T element : this)
            sum += selector.apply(element);
        return sum;
    }

    /**
     * Returns a specified number of contiguous elements from the start of the
     * sequence.
     *
     * @param count - The number of elements to return.
     * @return a List that contains the specified number of elements from the start
     * of the input sequence.
     */
    default BetterList<T> take(int count) {
        BetterList<T> out = new BetterArrayList<>(count);
        int n = 0;
        for (T element : this) {
            if (n < count)
                out.add(element);
            else
                break;
            n++;
        }
        return out;
    }

    /**
     * Returns elements from the sequence as long as a specified condition is true.
     *
     * @param predicate - A function to test each element for a condition.
     * @return a List that contains the elements from the sequence that occur before
     * the element at which the test no longer passes.
     */
    default BetterList<T> takeWhile(Function<T, Boolean> predicate) {
        BetterList<T> out = new BetterArrayList<>();
        for (T element : this)
            if (predicate.apply(element))
                out.add(element);
            else
                break;
        return out;
    }

    /**
     * Produces the set union of two sequences.
     *
     * @param other - Another List whose distinct elements form the second set for the
     *              union.
     * @return A List that contains the elements from both sequences, excluding
     * duplicates.
     */
    default BetterList<T> union(List<T> other) {
        BetterList<T> out = new BetterArrayList<>();
        for (T element : this)
            if (other.contains(element))
                out.add(element);
        return out;
    }

    /**
     * Filters a sequence of values based on a predicate.
     *
     * @param predicate - A function to test each element for a condition.
     * @return a List that contains elements from the sequence that satisfy the
     * condition.
     */
    default BetterList<T> where(Function<T, Boolean> predicate) {
        BetterList<T> out = new BetterArrayList<>();
        for (T element : this)
            if (predicate.apply(element))
                out.add(element);
        return out;
    }

}
