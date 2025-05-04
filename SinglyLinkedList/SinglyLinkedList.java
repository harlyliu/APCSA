import java.util.NoSuchElementException;

/**
 *	SinglyLinkedList - (description)
 *
 *	@author	
 *	@since	
 */
public class SinglyLinkedList<E extends Comparable<E>>
{
	/* Fields */
	private ListNode<E> head, tail;		// head and tail pointers to list
	
	/* No-args Constructors */
	public SinglyLinkedList() {}
	
	/** Copy constructor */
	public SinglyLinkedList(SinglyLinkedList<E> oldList) {
		head = oldList.head;
		tail = oldList.tail;
	}
	
	/**	Clears the list of elements */
	public void clear() {
		head = null;
		tail = null;
	}
	
	/**	Add the object to the end of the list
	 *	@param obj		the object to add
	 *	@return			true if successful; false otherwise
	 */
	public boolean add(E obj) {
		if (tail == null){
			head = new ListNode<E>(obj);
			tail = head;
		}
		else{
			ListNode<E> newNode = new ListNode<E>(obj);
			tail.setNext(newNode);
			tail = newNode;
		}
		return true;
	}
	
	/**	Add the object at the specified index
	 *	@param index		the index to add the object
	 *	@param obj			the object to add
	 *	@return				true if successful; false otherwise
	 *	@throws NoSuchElementException if index does not exist
	 */
	public boolean add(int index, E obj) {
		ListNode<E> newNode = new ListNode<E>(obj);
		if (index == 0){
			newNode.setNext(head);
			head = newNode;
			return true;
		}
		ListNode<E> before = head;
		for (int i = 0; i < index -1 ; i++){
			if (before == null){
				i = index+1;
			}
			else{
				before = before.getNext();
			}
		}
		if (before == null){
			System.err.println("ERROR: no index = " + index);
			return false;
		}
		newNode.setNext(before.getNext());
		before.setNext(newNode);
		return true;
	}
	
	/**	@return the number of elements in this list */
	public int size() {
		int ans = 0;
		if (head == null) return 0;
		ListNode<E> curr = head;
		while(curr != null){
			ans++;
			curr = curr.getNext();
		}
		return ans;
	}
	
	/**	Return the ListNode at the specified index
	 *	@param index		the index of the ListNode
	 *	@return				the ListNode at the specified index
	 *	@throws NoSuchElementException if index does not exist
	 */
	public ListNode<E> get(int index) {
		ListNode<E> curr = head;
		for (int i = 0; i < index; i++){
			if (curr == null){
				System.err.println("ERROR: no index = " + index);
				return null;
			}
			curr = curr.getNext();
		}
		return curr;
	}
	
	/**	Replace the object at the specified index
	 *	@param index		the index of the object
	 *	@param obj			the object that will replace the original
	 *	@return				the object that was replaced
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E set(int index, E obj) {
		ListNode<E> ind = get(index);
		if (ind != null)
			ind.setValue(obj);
		return null;
	}
	
	/**	Remove the element at the specified index
	 *	@param index		the index of the element
	 *	@return				the object in the element that was removed
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E remove(int index) {
		ListNode<E> ret = head;
		if (index == 0){
			head = head.getNext();
			return ret.getValue();
		}
		ListNode<E> curr = head;
		for (int i = 0; i < index-1; i++){
			if (curr == null){
				System.err.println("ERROR: no index = " + index);
				return null;
			}
			curr = curr.getNext();
		}
		ret = curr.getNext();
		if (curr.getNext() != null){
			curr.setNext(curr.getNext().getNext());
			if (curr.getNext() == null)
				tail = curr;
			return ret.getValue();
		}
			
		else{
			System.err.println("ERROR: no index = " + index);
		}
				
		return null;
	}
	
	/**	@return	true if list is empty; false otherwise */
	public boolean isEmpty() {
		if (head == null) return true;
		return false;
	}
	
	/**	Tests whether the list contains the given object
	 *	@param object		the object to test
	 *	@return				true if the object is in the list; false otherwise
	 */
	public boolean contains(E object) {
		ListNode<E> curr = head;
		while (curr != null){
			if (curr.getValue().equals(object)) return true;
			curr = curr.getNext();
		}
		return false;
	}
	
	/**	Return the first index matching the element
	 *	@param element		the element to match
	 *	@return				if found, the index of the element; otherwise returns -1
	 */
	public int indexOf(E element) {
		int i = 0;
		ListNode<E> curr = head;
		while (curr != null){
			if (curr.getValue().equals(element)) return i;
			curr = curr.getNext();
			i++;
		}
		return -1;
	}
	
	/**	Prints the list of elements */
	public void printList()
	{
		ListNode<E> ptr = head;
		while (ptr != null)
		{
			System.out.print(ptr.getValue() + "; ");
			ptr = ptr.getNext();
		}
	}
	

}
