package BasicStructures;

public class Node<T> {
	private T data;
	private Node<T> next;
	private Node<T> previous;
	public Node(T data) {
		super();
		this.data = data;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Node<T> getNext() {
		return next;
	}
	public void setNext(Node<T> next) {
		this.next = next;
	}
	public Node<T> getPrevious() {
		return previous;
	}
	public void setPrevious(Node<T> previous) {
		this.previous = previous;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Node<T> other = (Node<T>) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return " [data = "+data+"]";
	}
	public void deleteNode() {
		this.data=null;
		this.next=null;
		this.previous=null;
	}
	
}
