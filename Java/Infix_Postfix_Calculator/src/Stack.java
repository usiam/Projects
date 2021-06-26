
public class Stack<T> {
	LinkedList<T> stack;

	public Stack() {
		stack = new LinkedList<T>();
	}

	public void push(T t) {
		stack.addLast(t);
	}

	public T pop() {
		return stack.removeLast();
	}

	public boolean isEmpty() {
		return stack.size() == 0;
	}

	public T getTop() {
		return stack.getLast();
	}

	public int size() {
		return stack.size();
	}
}
