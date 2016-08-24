class linkedlist {
	node first, last;
	int size;

	public linkedlist() {
		this.first = this.last = null;
		this.size = 0;
	}

	void add(int n) {
		node item = new node(n);
		if (this.size == 0) {
			this.first = this.last = item;
		} else {
			this.last.next = item;
			item.prev = this.last;
			this.last = item;
		}
		this.size++;
	}

	int remove(boolean first) {
		int ans = -1;
		if (this.size == 0) {
			return ans;
		} else if (this.size == 1) {
			ans = this.first.item;
			this.first = this.last = null;
		} else {
			ans = first ? this.first.item : this.last.item;
			node new_node = first ? this.first.next : this.last.prev;
			if (first) {
				new_node.prev = null;
				this.first = new_node;
			else {
				new_node.next = null;
				this.last = new_node;
			}
		}
		this.size--;
		return ans;
	}

	int size() {
		return this.size;
	}
}