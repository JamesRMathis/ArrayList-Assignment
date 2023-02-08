import java.util.*;
public class ArrayList implements CapstoneList {
	//INSTANCE VARIABLES
	private int[] nums;
	private int count;
	

	//CONSTRUCTOR
	public ArrayList() {
		nums = new int[16];
		count = 0;
	}
	
	//METHODS
	private void makeRoom() {
		if(count >= nums.length) {
			nums = Arrays.copyOf(nums, count * 2);
		}
	}

	/**
	 * Returns the current size of the list
	 * 
	 * @return list length
	 */
	public int size() {
		return count;
	}
	
	/**
	 * Returns true if the list is empty
	 * 
	 * @return true if the list is empty
	 */
	public boolean isEmpty() {
		return count == 0;
	}
	
	/**
	 * Clears all values from the list
	 * @return void
	 */
	public void clear() {
		count = 0;
	}
	
	/**
	 * Appends the value to the end of the ArrayList
	 * @param value value to be added
	 * @return void
	 */
	public void add(int value) {
		makeRoom();
		nums[count] = value;
		count++;
	}

	/**
	 * Inserts the value at the specified index
	 * 
	 * @param index index at which to insert
	 * @param value value to insert
	 * @return void
	 * @throws IndexOutOfBoundsException if index is out of range (index &lt; 0 || index &gt;= size())
	 */
	public void add(int index, int value) {
		if(index < 0 || index >= count) throw new IndexOutOfBoundsException();

		if(count == nums.length) makeRoom();

		for(int i = count; i > index; i--) {
			nums[i] = nums[i - 1];
		}

		nums[index] = value;
		count++;
	}

	/**
	 * Gets the value at the specified index
	 * 
	 * @param index index to get the value of
	 * @return value at the given index
	 * @throws IndexOutOfBoundsException if index is out of
	 * range (index &lt; 0 || index &gt;= size())
	 */
	public int get(int index) {
		if(index < 0 || index >= count) throw new IndexOutOfBoundsException();
		return nums[index];
	}

	/**
	 * Returns a string representation of the ArrayList
	 * 
	 * @return string representation of the ArrayList
	 */
	public String toString() {
		return Arrays.toString(Arrays.copyOf(nums,count));
	}
	
	/**
	 * Copies the values of the ArrayList
	 * 
	 * @return a copy of the ArrayList
	 */
    public CapstoneList clone() {
		ArrayList list = new ArrayList();
		for(int i = 0; i < count; i++) {
			list.add(nums[i]);
		}

		return list;
	}
	
	/**
	 * Returns true if this list contains the specified element
	 * 
	 * @param value value to find
	 * @return true if this list contains the specified element
	 */
    public boolean contains(int value) {
		for(int i = 0; i < count; i++) {
			if(nums[i] == value) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Returns true if all corresponding values in the ArrayList
	 * and the specified list are equal
	 * 
	 * @param list CapstoneList to check for equality with
	 * @return true if lists are equal
	 */
    public boolean equals(CapstoneList list) {
		for(int i = 0; i < count; i++) {
			if(!(nums[i] == list.get(i))) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * Returns the index of the first occurrence of the specified 
	 * element in this list
	 * 
	 * @param value element to search for
	 * @return the index of the first occurrence of the specified 
	 * element in this list, or -1 if this list does not contain
	 * the element 
	 */
    public int indexOf(int value) {
		for(int i=0;i<count;i++)
			if(nums[i]==value)
				return i;
		
		return -1;
	}
	
	/**
	 * Returns the index of the last occurrence of the specified
	 * element in this list
	 * 
	 * @param value element to search for
	 * @return the index of the last occurrence of the specified
	 * element in this list, or -1 if this list does not contain
	 * the element
	 */
    public int lastIndexOf(int value) {
		for(int i = count - 1;i > 0;i--)
			if(nums[i] == value)
				return i;
		
		return -1;
	}
	
	/**
	 * Pops an item from the specified index and shift
	 * everything left over.
	 * 
	 * @param index the location of item to be popped.
	 * @return the value of popped item
	 * @throws IndexOutOfBoundsException if the index is out of
	 * range (index &lt; 0 || index &gt;= size())
	 */
    public int pop(int index) {
		if(index < 0 || index >= count) throw new IndexOutOfBoundsException();
		int out = nums[index];
		for(int i = index; i < count - 1; i++)
			nums[i] = nums[i+1];
		count--;
		return out;
	}
	
	/**
	 * Removes the specified element from the list
	 * 
	 * @param value element to remove
	 * @return whether or not the element was in the list
	 */
    public boolean remove(int value) {
		int index = -1;
		for (int i = 0; i < count; i++) {
			if (nums[i] == value) {
				index = i;
				break;
			}
		}

		if (index != -1) {
			for (int i = index; i < count - 1; i++) {
				nums[i] = nums[i + 1];
			}

			count--;
			return true;
		}

		return false;
	}

	/**
	 * Replaces the element at the specified index with the
	 * specified value
	 * 
	 * @param index index of element to replace
	 * @param value value to replace element with
	 * @return value that was replaced
	 */
    public int set(int index, int value) {
		int out = nums[index];
		int[] newNums = new int[count];
		for(int i = 0; i < count; i++) {
			if(i != index) {
				newNums[i] = nums[i];
			} else {
				newNums[i] = value;
			}
		}
		nums = newNums;
		return out;
	}

	/**
	 * Returns a new CapstoneList with the elements
	 * of the list from fromIndex (inclusive) to
	 * toIndex (exclusive)
	 * 
	 * @param fromIndex index to start copying from
	 * @param toIndex index to stop copying
	 * @return CapstoneList with elements from
	 * [fromIndex, toIndex)
	 */
    public CapstoneList subList(int fromIndex, int toIndex) {
		CapstoneList list = new ArrayList();
		for(int i = fromIndex; i < toIndex; i++) {
			list.add(nums[i]);
		}

		return list;
	}
	
	/**
	 * Returns an array representation of the list
	 * @return an array representation of the list
	 */
    public int[] toArray() {
		return nums;
	}

    public static void main(String[] args) {
		ArrayList myAL;
		java.util.ArrayList<Integer> javaAL;
        int i = 0;

		System.out.println("i,myALTime,javaALTime");
		while(true) {
            int itemsToAdd = (int)Math.pow(2.0, i + 0.0);
            String line = i + ",";

			myAL = new ArrayList();
			long startTime = System.nanoTime();
			for(int j = 0; j < itemsToAdd; j++) myAL.add(j);
			double myALTimeElapsed = (System.nanoTime() - startTime) / 1e9;
			line += myALTimeElapsed + ",";

			javaAL = new java.util.ArrayList<Integer>();
			startTime = System.nanoTime();
			for(int j = 0; j < itemsToAdd; j++) javaAL.add(j);
			double javaALTimeElapsed = (System.nanoTime() - startTime) / 1e9;
			line += javaALTimeElapsed + "";

			System.out.println(line);
			i++;
		}
    }
}