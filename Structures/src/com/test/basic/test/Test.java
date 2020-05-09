package com.test.basic.test;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node<Integer> n = new Node<Integer>(10);
		System.out.println(n);
		LinkedList<Integer> l = new LinkedList<Integer>();
		for (int i = 1; i <= 12; i++) {
			l.insert(i);
		}
		System.out.println(l);
		l.reverseK(3);
		System.out.println(l);
	}

}

//Node has data Next and previous along with setters , getters and constructors and .equal to method and to string method
class Node<T> {
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

class LinkedList<T> {
	protected Node<T> head;
	private int size;
	private Node<T> last;

	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		int i = 0;
		StringBuilder sb = new StringBuilder();
		Node<T> temp = head;
		while (temp != null) {
			sb.append(temp);
			sb.append("->");
			temp = temp.getNext();
			if (i == 10) {
				i = 0;
				sb.append("\n");
			}
			i++;
		}
		sb.append("N");
		sb.append("\n size = " + this.getSize());
		Node<T> temp1 = last;
		StringBuilder sb1 = new StringBuilder();
		while (temp1 != null) {
			sb1.append(temp1);
			sb1.append("->");
			temp1 = temp1.getPrevious();
		}
		sb1.append("N");
		sb1.append("\n size = " + this.getSize());
		return sb.toString();
	}

	public void insert(T data) {
		Node<T> n = new Node<T>(data);
		if (head == null) {
			head = n;
			head.setPrevious(null);
			last = n;
		} else {
			// Node<T> temp = head;
			// while(temp.getNext()!=null)
			// temp=temp.getNext();
			last.setNext(n);
			n.setPrevious(last);
			last = n;
		}
		size++;
	}
	
	private void modifyLast() {
		Node<T> temp = this.head;
		while (temp.getNext() != null) {
			temp = temp.getNext();
			// System.out.println("last");
			// System.out.println("temp"+temp+"next"+temp.getNext()+"previous"+temp.getPrevious());
		}
		this.last = temp;
	}

	public void printElements() {
		StringBuilder sb = new StringBuilder();
		Node<T> temp = head;
		while (temp != null) {
			sb.append(temp.getData());
			sb.append(" ");
			temp = temp.getNext();
		}
		System.out.println(sb.toString());
	}
	public void reverseK(int k) {
		Node<T> temp = reverserK(head, k);
		temp.setPrevious(null);
		head = temp;
		this.modifyLast();
	}

	private Node<T> reverserK(Node<T> head, int k) {
		int count = 1;
		Node<T> fst = head;
		Node<T> current = head;
		Node<T> nxt = null;
		Node<T> prev = null;
		head.setPrevious(null);
		while (count <= k && current != null) {
			nxt = current.getNext();
			prev = current.getPrevious();
			current.setNext(prev);
			current.setPrevious(nxt);
			count++;
			prev = current;
			current = nxt;
		}
		if (nxt != null) {
			Node<T> temp = reverserK(nxt, k);
			fst.setNext(temp);
			temp.setPrevious(fst);
		}
		return prev;
	}
}
