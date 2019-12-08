package BasicStructures;

import java.util.Iterator;

public class Stack<T> implements Iterable<T>{
	private Node<T> head;
	private Node<T> top;
	private Node<T> last;
	private int size;
	public int getSize() {
		return this.size;
	}
	@Override
	public String toString() {
		Node<T>temp = head;
		StringBuilder sb = new StringBuilder();
		//sb.append('\n');
		while(temp!=null) {
			sb.append(temp);
			temp=temp.getNext();
			//sb.append('\n');
		}
		sb.append('\n');
		sb.append("size = "+this.getSize());
		Node<T>temp1 = last;
		StringBuilder sb1 = new StringBuilder();
		while(temp1!=null) {
			sb1.append(temp1);
			temp1 = temp1.getPrevious();
		}
		sb1.append("\n size = "+this.getSize());
		return sb1.toString();
	}
	public void push(T data) {
		Node<T> newNode = new Node<T>(data);
		if(head==null) {
			head = newNode;
			top = head;
			last = head;
			size++;
		}
		else {
			newNode.setNext(head);
			head.setPrevious(newNode);
			head = newNode;
			size++;
		}
	}
	public T pop() {
		T data = null;
		if(head!=null) {
			Node<T> node = head;
			data = node.getData();
			if(head.equals(last)) {
				last = null;
			}
			head=head.getNext();
			node.setNext(null);
			if(head!=null)
			head.setPrevious(null);
			size--;
			top = head;
		}
		else {
			throw new IllegalArgumentException(" no elements in stack");
		}
		return data;
	}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new StackIterator<T>(this.head);
	}
	
	public boolean isEmpty() {
		return (this.head==null);
	}
	public T peep() {
		if(!this.isEmpty())
			return head.getData();
		else
			throw new IllegalArgumentException("Stack is empty ");
	}
	public boolean contains(T data) {
		Node<T> temp = head;
		while(temp!=null) {
			if(temp.getData()==data)
				return true;
			temp=temp.getNext();
		}
		return false;
	}
	public T getTop() {
		if(top!=null)
			return top.getData();
		throw new IllegalArgumentException("Stack is empty");
	}
	public T getLast() {
		if(last!=null)
			return last.getData();
		throw new IllegalArgumentException("Stack is empty");
	}
}
class StackIterator<T> implements Iterator<T>{

	Node<T> itrtor;
	public StackIterator(Node<T> head) {
		// TODO Auto-generated constructor stub
		itrtor = head;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if(itrtor!=null)
			return true;
		return false;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		Node<T> temp = itrtor;
		itrtor = itrtor.getNext();
		return temp.getData();
	}
	
}
class testStack{
	public static void main(String[] args) {
		Stack<Integer>s = new Stack<Integer>();
		for(int i = 1; i<=15; i++)
			s.push(i);
		//Iterator<Integer> it = s.iterator();
		//while(it.hasNext()) {
			//System.out.println(it.next());
		//}
		s.pop();
		s.push(11);
		System.out.println(s);
		s.pop();
		s.pop();
		System.out.println(s);
	}
}
