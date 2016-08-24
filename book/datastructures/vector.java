class vector {
	int added;
	int[] array;

	public vector(int size) {
		this.added = 0;
		this.array = new int[size];
	}

	void add(int n) {
		if (this.added == this.array.length)
			resize();
		this.array[this.added++] = n;
	}

	void remove(int i) {
		if (i < 0 || i >= this.added) return;
		for (int j = i + 1; j < this.added; j++)
			this.array[j-1] = this.array[j];
		this.added--;
	}

	int index(int n) {
		for (int i = 0; i < this.added; i++)
			if (this.array[i] == n)
				return i;
		return -1;
	}

	int get(int i) {
		if (i < 0 || i >= this.added) return -1;
		return this.array[i];
	}

	int set(int i, int n) {
		if (i < 0 || i >= this.added) return -1;
		this.array[i] = n;
	}

	int size() {
		return this.added;
	}

	void resize() {
		int[] new_array = new int[2 * this.array.length];
		for (int i = 0; i < this.added; i++)
			new_array[i] = this.array[i];
		this.array = new_array;
	}
}