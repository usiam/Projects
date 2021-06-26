public class Queue<T> {

	LinkedList<T> queue;

	public Queue() {
		queue = new LinkedList<T>();
	}

	public void enqueue(T t) {
		queue.addLast(t);
	}

	public T dequeue() {
		return queue.removeFirst();
	}

	public boolean isEmpty() {
		return queue.size() == 0;
	}

	public int size() {
		return queue.size();
	}
}