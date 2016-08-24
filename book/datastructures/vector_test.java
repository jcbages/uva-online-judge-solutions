class vector_test {
	static void print(vector vec) {
		System.out.printf("Size: %d\n", vec.size());
		System.out.print("[");
		for (int i = 0; i < vec.size(); i++) {
			System.out.print(vec.get(i));
			if (i < vec.size() - 1)
				System.out.print(", ");
		}
		System.out.println("]");
	}

	public static void main(String... args) {
		// Vector creation...
		System.out.println("================= Creating vector =================");
		vector vec = new vector(10);
		print(vec); // Print the empty vector.
		// Adding some dummy data...
		System.out.println("================ Adding dummy data ================");
		for (int i = 0; i < 15; i++)
			vec.add(i);
		print(vec); // Print the vector with some data.
		// Removing data at some index...
		System.out.println("================== Removing data ==================");
		vec.remove(20);
		vec.remove(3);
		vec.remove(0);
		print(vec);
		// Looking for some object index...
		System.out.println("=============== Looking for indexes ===============");
		System.out.printf("Index of %d: %d\n", 9, vec.index(9));
		System.out.printf("Index of %d: %d\n", 100, vec.index(100));
		// Get some object by index...
		System.out.println("================ Looking for data =================");
		System.out.printf("Object at index %d: %s\n", 4, vec.get(4));
		System.out.printf("Object at index %d: %s\n", 100, vec.get(100));
	}
}