package fr.klemek.betterlists;

import java.util.Arrays;
import java.util.Collection;
import java.util.Vector;

/**
 * An extension of the java.util.Vector class which include some of the C# LINQ
 * useful functions.
 *
 * @author Klemek
 * @see java.util.Vector
 */
public class BetterVector<T> extends Vector<T> implements BetterList<T> {

    private static final long serialVersionUID = -704157461726911759L;

    /**
     * Constructs a vector containing the elements of the specified collection, in
     * the order they are returned by the collection's iterator.
     *
     * @param c - the collection whose elements are to be placed into this vector
     */
    public static <T> BetterVector<T> fromList(Collection<T> c) {
        return new BetterVector<>(c);
    }

    /**
     * Constructs a vector containing the elements given in argument.
     *
     * @param a - the elements to be placed into this vector
     */
    public static <T> BetterVector<T> asVector(T... a) {
        return new BetterVector<>(a);
    }

    /**
     * Constructs an empty vector so that its internal data array has size 10 and
     * its standard capacity increment is zero.
     */
    public BetterVector() {
        super();
    }

    /**
     * Constructs a vector containing the elements of the specified collection, in
     * the order they are returned by the collection's iterator.
     *
     * @param c - the collection whose elements are to be placed into this vector
     */
    public BetterVector(Collection<? extends T> c) {
        super(c);
    }

    /**
     * Constructs a vector containing the elements given in argument.
     *
     * @param a - the elements to be placed into this list
     */
    public BetterVector(T... a) {
        super(Arrays.asList(a));
    }

    /**
     * Constructs an empty vector with the specified initial capacity and with its
     * capacity increment equal to zero.
     *
     * @param initialCapacity - the initial capacity of the vector
     */
    public BetterVector(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Constructs an empty vector with the specified initial capacity and capacity
     * increment.
     *
     * @param initialCapacity   - the initial capacity of the vector
     * @param capacityIncrement - the amount by which the capacity is increased when the vector
     *                          overflows
     */
    public BetterVector(int initialCapacity, int capacityIncrement) {
        super(initialCapacity, capacityIncrement);
    }

    /**
     * Returns a view of the portion of this list between the specified fromIndex,
     * inclusive, and toIndex, exclusive. (If fromIndex and toIndex are equal, the
     * returned list is empty.) The returned list is backed by this list, so
     * non-structural changes in the returned list are reflected in this list, and
     * vice-versa. The returned list supports all of the optional list operations
     * supported by this list. This method eliminates the need for explicit range
     * operations (of the sort that commonly exist for arrays). Any operation that
     * expects a list can be used as a range operation by passing a subList view
     * instead of a whole list. (see List.subList)
     *
     * @param fromIndex - low endpoint (inclusive) of the subList
     * @param toIndex   - high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException for an illegal endpoint index value (fromIndex < 0 || toIndex >
     *                                   size || fromIndex > toIndex)
     * @see java.util.List
     */
    @Override
    public BetterVector<T> subList(int fromIndex, int toIndex) {
        return (BetterVector<T>) super.subList(fromIndex, toIndex);
    }

}
