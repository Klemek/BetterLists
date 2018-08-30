package fr.klemek.betterlists;

import java.util.Stack;

/**
 * An extension of the java.util.Stack class which include some of the C# LINQ
 * useful functions.
 *
 * @author Klemek
 * @see java.util.Stack
 */
public class BetterStack<T> extends Stack<T> implements BetterList<T> {

    private static final long serialVersionUID = 5642889973315247461L;

    /**
     * Creates an empty Stack.
     */
    public BetterStack() {
        super();
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
    public BetterStack<T> subList(int fromIndex, int toIndex) {
        return (BetterStack<T>) super.subList(fromIndex, toIndex);
    }

}
