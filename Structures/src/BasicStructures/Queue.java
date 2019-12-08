package BasicStructures;

import java.util.Iterator;

public class Queue<T> implements Iterable<T>{
	protected Node<T> head;
	private int size;
	private Node<T> last;
	public int getSize() {
		return size;
	}
	public boolean isEmpty() {
		if(head==null)
			return true;
		return false;
	}
	@Override
	public String toString() {
		int i =0;
		StringBuilder sb = new StringBuilder();
		Node<T>temp = head;
		while(temp!=null){
			sb.append(temp);
			sb.append("->");
			temp=temp.getNext();
			if(i==10) {
				i = 0;
				sb.append("\n");
			}
			i++;
		}
		sb.append("\n size = "+this.getSize());
		Node<T>temp1 = last;
		StringBuilder sb1 = new StringBuilder();
		while(temp1!=null) {
			sb1.append(temp1);
			temp1 = temp1.getPrevious();
		}
		sb1.append("\n size = "+this.getSize());
		return sb.toString();
	}

	public void enque(T data) {
		Node<T> newNode = new Node<T>(data);
		if(head==null) {
			head=newNode;
			head.setPrevious(null);
			last=newNode;
		}
		else {
			last.setNext(newNode);
			newNode.setPrevious(last);
			last = newNode;
		}
		size++;
	}
	public T deque() {
		T data = null;
		if(head!=null) {
			data = head.getData();
			if(last==head)
				last=null;
			head.setPrevious(null);
			head=head.getNext();
			size--;
		}
		else {
			throw new IllegalArgumentException(" no elements in queue");
		}
		return data;
	}
	public T getFront() {
		if(head!=null)
			return head.getData();
		else
			throw new IllegalArgumentException("fill the queue");
	}
	public T getRare() {
		if(last!=null)
			return last.getData();
		else
			throw new IllegalArgumentException("fill the queue");
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new QueueIterrator<T>(this.head);
	}
}
class QueueIterrator<T> implements Iterator<T>{

	Node<T> itrtor;
	public QueueIterrator(Node<T> head) {
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
		itrtor=itrtor.getNext();
		return temp.getData();
	}
	
}
class testQueue{
	public static void main(String[] args) {
		Queue<Integer>q = new Queue<Integer>();
		for(int i = 1; i<=10;i++)
			q.enque(i);
		System.out.println(q);
		for(int i = 1; i<=10;i++) {
			q.deque();
		}
		System.out.println(q);
	}
}

class StackUsingQueue<T>{
	private Queue<Node<T>> q1 = new Queue<Node<T>>();
	private Queue<Node<T>> q2 = new Queue<Node<T>>();
	private int size;
	public int getSize() {
		return this.size;
	}
	public void push(T data) {
		Node<T> newNode = new Node<T>(data);
		q1.enque(newNode);
	}
	public T pop() {
		if(q1.isEmpty() && q2.isEmpty())
			throw new IllegalArgumentException("fill stack");
		else {
			while(!q1.isEmpty())
				q2.enque(q1.deque());
			return q2.deque().getData();
		}
	}
	public boolean isEmpty() {
		if(q1.isEmpty()&&q2.isEmpty())
			return true;
		return false;
	}
}
class testStackUsingQueue{
	public static void main(String[] args) {
		StackUsingQueue<Integer> s = new StackUsingQueue<Integer>();
		for(int i = 1; i<=10; i++)
			s.push(i);
		while(!s.isEmpty())
			System.out.println(s.pop());
	}
}