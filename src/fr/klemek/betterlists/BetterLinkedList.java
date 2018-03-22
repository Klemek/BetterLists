package fr.klemek.betterlists;

import java.util.Collection;
import java.util.LinkedList;

/**
 * An extension of the java.util.LinkedList class which include some of the C#
 * LINQ useful functions.
 * 
 * @author Klemek
 *
 * @see java.util.LinkedList
 */
public class BetterLinkedList<T> extends LinkedList<T> implements BetterList<T> {

	private static final long serialVersionUID = 4837198308074701770L;

	/**
	 * Constructs a list containing the elements of the specified collection, in the
	 * order they are returned by the collection's iterator.
	 * 
	 * @param c
	 *            - the collection whose elements are to be placed into this list
	 */
	public static <T> BetterLinkedList<T> fromList(Collection<T> c) {
		return new BetterLinkedList<>(c);
	}

	/**
	 * Constructs an empty list.
	 */
	public BetterLinkedList() {
		super();
	}

	/**
	 * Constructs a list containing the elements of the specified collection, in the
	 * order they are returned by the collection's iterator.
	 * 
	 * @param c
	 *            - the collection whose elements are to be placed into this list
	 */
	public BetterLinkedList(Collection<? extends T> c) {
		super(c);
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
	 * @param fromIndex
	 *            - low endpoint (inclusive) of the subList
	 * @param toIndex
	 *            - high endpoint (exclusive) of the subList
	 * @return a view of the specified range within this list
	 * @throws IndexOutOfBoundsException
	 *             for an illegal endpoint index value (fromIndex < 0 || toIndex >
	 *             size || fromIndex > toIndex)
	 * @see java.util.List
	 */
	@Override
	public BetterLinkedList<T> subList(int fromIndex, int toIndex) {
		return (BetterLinkedList<T>) this.subList(fromIndex, toIndex);
	}

}
