import java.lang.reflect.Array;

/**
 * Implementation of a simple generic dynamic
 * array in a programming competition style.
 * @author Yo C++ que ustedes
 */
class Vector<T extends Comparable<T>> {
	/*
	 * Constant for initial array size
	 */
	final static int ORIGIN = 10;

	/*
	 * Class type for array construction.
	 */
	Class<T> type;

	/*
	 * Count the number of items.
	 */
	int added;

	/*
	 * Maintain the current array size.
	 */
	int size;

	/*
	 * The inner array which holds items.
	 */
	Object[] array;

	/*
	 * Initialize the vector variables
	 * using default values.
	 */
	public Vector(Class<T> type) {
		this.type = type;
		this.added = 0;
		this.size = ORIGIN;
		this.array = new Object[this.size];
	}

	/*
	 * Add a new item to the array.
	 * If the array has no empty space,
	 * resize it!
	 * Time Complexity: O(1) (O(n) when resize)
	 * Space Complexity: O(1)
	 */
	void add(T item) {
		if (this.added == this.size)
			resize();
		array[this.added++] = item;
	}

	/*
	 * Removes an item given some
	 * index, if the index is not
	 * valid, do nothing.
	 * Time Complexity: O(n)
	 * Space Complexity: O(1)
	 */
	void remove(int index) {
		if (index < 0 || index >= this.added)
			return;
		for (int i = index + 1; i < this.added; i++)
			this.array[i-1] = this.array[i];
		this.added--;
	}

	/*
	 * Returns an item index or -1 if
	 * that item is not in the array.
	 * Time Complexity: O(n)
	 * Space Complexity: O(1)
	 */
	@SuppressWarnings("unchecked")
	int index(T item) {
		for (int i = 0; i < this.added; i++) {
			T element = (T) this.array[i];
			if (element.compareTo(item) == 0)
				return i;
		}
		return -1;
	}

	/*
	 * Get the item at a given index,
	 * in case the index is invalid,
	 * returns null.
	 * Time Complexity: O(1)
	 * Space Complexity: O(1)
	 */
	@SuppressWarnings("unchecked")
	T get(int index) {
		if (index < 0 || index >= this.added)
			return null;
		return (T) (this.array[index]);
	}

	/*
	 * Resize an array making it size
	 * doubles so it can hold more items.
	 * Time Complexity: O(n)
	 * Space Complexity: O(n)
	 */
	void resize() {
		Object[] newArray = new Object[2 * this.size];
		for (int i = 0; i < this.added; i++)
			newArray[i] = this.array[i];
		this.array = newArray;
		this.size *= 2;
	}

	/*
	 * Pretty print, just for test purposes.
	 */
	void print() {
		System.out.printf("Added: %d, Size: %d\n", this.added, this.size);
		System.out.print("[");
		for (int i = 0; i < this.added; i++) {
			System.out.print(this.array[i]);
			if (i < this.added - 1)
				System.out.print(", ");
		}
		System.out.println("]");
	}

	/*
	 * Main class, perform some dummy test.
	 */
	public static void main(String... args) {
		// Vector creation...
		System.out.println("================= Creating vector =================");
		Vector<Integer> vector = new Vector<Integer>(Integer.class);
		vector.print(); // Print the empty vector.
		// Adding some dummy data...
		System.out.println("================ Adding dummy data ================");
		for (int i = 0; i < 15; i++)
			vector.add(i);
		vector.print(); // Print the vector with some data.
		// Removing data at some index...
		System.out.println("================== Removing data ==================");
		vector.remove(20);
		vector.remove(3);
		vector.remove(0);
		vector.print();
		// Looking for some object index...
		System.out.println("=============== Looking for indexes ===============");
		System.out.printf("Index of %d: %d\n", 9, vector.index(9));
		System.out.printf("Index of %d: %d\n", 100, vector.index(100));
		// Get some object by index...
		System.out.println("================ Looking for data =================");
		System.out.printf("Object at index %d: %s\n", 4, vector.get(4));
		System.out.printf("Object at index %d: %s\n", 100, vector.get(100));
	}
}