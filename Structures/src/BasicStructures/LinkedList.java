package BasicStructures;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T>{
	protected Node<T> head;
	private int size;
	private Node<T> last;
	public int getSize() {
		return size;
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
		sb.append("N");
		sb.append("\n size = "+this.getSize());
		Node<T>temp1 = last;
		StringBuilder sb1 = new StringBuilder();
		while(temp1!=null) {
			sb1.append(temp1);
			sb1.append("->");
			temp1 = temp1.getPrevious();
		}
		sb1.append("N");
		sb1.append("\n size = "+this.getSize());
		return sb.toString();
	}

	public void insert(T data) {
		Node<T> n = new Node<T>(data);
		if(head==null) {
			head=n;
			head.setPrevious(null);
			last=n;
		}
		else {
			//Node<T> temp = head;
			//while(temp.getNext()!=null)
				//temp=temp.getNext();
			last.setNext(n);
			n.setPrevious(last);
			last = n;
		}
		size++;
	}
	
	public void printElements() {
		StringBuilder sb = new StringBuilder();
		Node<T>temp = head;
		while(temp!=null){
			sb.append(temp.getData());
			sb.append(" ");
			temp=temp.getNext();
		}
		System.out.println(sb.toString());
	}
	
	public void insertAtBegining(T data) {
		Node<T> n = new Node<T>(data);
		if(head==null) {
			head = n;
			size++;
		}
		else
		{
			n.setNext(head);
			head.setPrevious(n);
			head = n;
			size++;
		}
	}
	
	public boolean contains(T data) {
		Node<T>n = new Node<T>(data);
		Node<T>temp = head;
		while(temp!=null) {
			if(temp.equals(n)) 
				return true;
			temp=temp.getNext();
		}
		return false;
	}
	
	public boolean delete(T data) {
		Node<T> temp = head;
		while(temp!=null) {
			if(temp.getData().equals(data))
				break;
			temp = temp.getNext();
		}
		if(this.contains(data)) {
			if(this.head.getData().equals(data)) {
				head = head.getNext();
				head.setPrevious(null);
				size--;
				return true;
			}
			else {
				Node<T>previous = temp.getPrevious();
				Node<T>next = temp.getNext();
				previous.setNext(next);
				next.setPrevious(previous);
				temp.setNext(null);
				temp.setPrevious(null);
				size--;
			return true;
			}
		}
		return false;
	}
	
	public void inserAtPosition(T data,int position) throws IllegalArgumentException{
		if(position<0)
			throw new IllegalArgumentException("invalid position");
		if(position>this.getSize())
			this.insert(data);
		else {
			int i = 1;
			Node<T>temp = head;
			while(temp!=null&&i!=position) {
				temp = temp.getNext();
				i++;
			}
			Node<T>n = new Node<T>(data);
			Node<T>previous = temp.getPrevious();
			previous.setNext(n);
			n.setPrevious(previous);
			n.setNext(temp);
			temp.setPrevious(n);
			this.size++;
		}
	}
	
	public void reverse() {
		reverser(this.head);
		modifyLast();
	}
	
	private void modifyLast() {
		Node<T>temp = this.head;
		while(temp.getNext()!=null) {
			temp=temp.getNext();
			//System.out.println("last");
			//System.out.println("temp"+temp+"next"+temp.getNext()+"previous"+temp.getPrevious());
		}
		this.last=temp;
	}
	
	private void reverser(Node<T> head) {
		if(head.getNext()==null) {
			this.head = head;
			Node<T> prev = head.getPrevious();
			head.setNext(prev);
			head.setPrevious(null);
			return;
		}
		else {
			reverser(head.getNext());
			Node<T> temp = head;
			Node<T> next = head.getNext();
			Node<T> previous = head.getPrevious();
			temp.setNext(previous);
			temp.setPrevious(next);
			//System.out.println("temp"+temp+" next.getNext()"+next.getNext()+"temp.getPrevious()"+temp.getPrevious());
			
		}
	}
	
	public void reverseFormAPoint(int position) {
		Node<T>temp = head;
		int count = 1;
		while(count!=position&&temp.getNext()!=null) {
			temp=temp.getNext();
			count++;
		}
		reverser(temp.getPrevious(),temp);
		modifyLast();
	}
	
	private void reverser(Node<T>from,Node<T>current) {
		if(current.getNext()==null) {
			from.setNext(current);
			Node<T> prev = current.getPrevious();
			current.setPrevious(from);
			current.setNext(prev);
			return;
		}
		else {
			reverser(from,current.getNext());
			Node<T>temp = current;
			Node<T>next = current.getNext();
			Node<T>previous = current.getPrevious();
			temp.setNext(previous);
			temp.setPrevious(next);
			if(from.equals(previous)) {
				temp.setNext(null);
			}
			//System.out.println("temp"+temp+" next.getNext()"+next.getNext()+"temp.getPrevious()"+temp.getPrevious());
		}
	}
	
	public void reverseToAPoint(int position) {
		if(position>this.getSize())
			throw new IllegalArgumentException("the position is greagter than the size");
		reverser(this.head,1,--position);
		
	}
	
	private Node<T> reverser(Node<T>current,int count,int position) {
		if(count==position) {
			this.head = current;
			Node<T> prev = current.getPrevious();
			current.setPrevious(null);
			Node<T> next = current.getNext();
			current.setNext(prev);
			return next;
		}
		else {
			Node<T> point = reverser(current.getNext(), ++count, position);
			Node<T> temp = current;
			Node<T> next = current.getNext();
			Node<T> previous = current.getPrevious();
			temp.setNext(previous);
			temp.setPrevious(next);
			if(previous==null) {
				temp.setNext(point);
				point.setPrevious(temp);
			}
			return point;
		}
	}
	public void deleteList() {
		Node<T> node = head;
		while(node!=null) {
			node = node.getNext();
			head.deleteNode();
			head = node;
		}
		this.last=null;
		this.size = 0;
	}
	@Override
	public Iterator<T> iterator() {
		return new linkedListIterator<T>(this.head);
	}

	public void reversePairwise() {
		reversePair(head);
		this.modifyLast();
	}

	private void reversePair(Node<T> head) {
		// TODO Auto-generated method stub
		if(head==null||head.getNext()==null)
			return;
		else {
			reversePair(head.getNext().getNext());
			Node<T> first = head;
			Node<T> second = head.getNext();
			if(second!=null) {
				if(first.getPrevious()!=null)
					first.getPrevious().setNext(second);
				
				if(second.getNext()!=null)
					second.getNext().setPrevious(first);
				
				second.setPrevious(first.getPrevious());
				first.setNext(second.getNext());
				second.setNext(first);
				if(second.getPrevious()==null)
					this.head=second;
			}
			first.setPrevious(second);
		}
	}
	public void reverseK(int k) {
		Node<T>temp = reverserK(head,k);
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
		while(count<=k&&current!=null) {
			nxt = current.getNext();
			prev = current.getPrevious();
			current.setNext(prev);
			current.setPrevious(nxt);
			count++;
			prev = current;
			current = nxt;
		}
		if(nxt!=null) {
			Node<T>temp = reverserK(nxt, k);
			fst.setNext(temp);
			if(temp!=null)
				temp.setPrevious(fst);
		}
		return prev;
	}
	
	public void reverseOddK(int k) {
		Node<T>temp = reverserOddK(head,k,1);
		temp.setPrevious(null);
		head = temp;
		this.modifyLast();
	}
	public void reverseEvenK(int k) {
		Node<T>temp = reversereEvenK(head,k,1);
		temp.setPrevious(null);
		head = temp;
		this.modifyLast();
	}
	private Node<T> reverserOddK(Node<T> head, int k,int odd) {
		int count = 1;
		head.setPrevious(null);
		Node<T> next = null;
		Node<T> prev = null;
		Node<T> current = head;
		Node<T> frst = head;
		if(odd%2!=0) {
			while(count<=k&&current!=null) {
				next = current.getNext();
				prev = current.getPrevious();
				current.setNext(prev);
				current.setPrevious(next);
				prev = current;
				current = next;
				count++;
			}
		}
		else {
			prev = head;
			while(count<=k&&head!=null) {
				current = head;
				head=head.getNext();
				count++;
			}
			next = head;
			frst = current;
			frst.setNext(null);
		}
		if(next!=null) {
			Node<T> temp = reverserOddK(next, k, ++odd);
			frst.setNext(temp);
			if(temp!=null)
				temp.setPrevious(frst);
		}
		return prev;
	}

	private Node<T> reversereEvenK(Node<T> head, int k,int odd) {
		int count = 1;
		head.setPrevious(null);
		Node<T> next = null;
		Node<T> prev = null;
		Node<T> current = head;
		Node<T> frst = head;
		if(odd%2==0) {
			while(count<=k&&current!=null) {
				next = current.getNext();
				prev = current.getPrevious();
				current.setNext(prev);
				current.setPrevious(next);
				prev = current;
				current = next;
				count++;
			}
		}
		else {
			prev = head;
			while(count<=k&&head!=null) {
				current = head;
				head=head.getNext();
				count++;
			}
			next = head;
			frst = current;
			frst.setNext(null);
		}
		if(next!=null) {
			Node<T> temp = reversereEvenK(next, k, ++odd);
			frst.setNext(temp);
			if(temp!=null)
				temp.setPrevious(frst);
		}
		return prev;
	}
}

class linkedListIterator<T> implements Iterator<T> {
	Node<T> current;
	public linkedListIterator(Node<T> head) {
		// TODO Auto-generated constructor stub
		current = head;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return(current!=null);
	}

	@Override
	public T next() {
		T data = current.getData();
		current=current.getNext();
		return data;
	}
	
}
class test{
	public static void main(String[] args) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		for(int i = 1;i<=15;i++) {
			l.insert(i);
		}
		System.out.println(l);
		//System.out.println(l.contains(4));
		//System.out.println(l.delete(5));
		//System.out.println(l);
		//l.inserAtPosition(5, 5);
		//System.out.println(l);
		 l.reverseEvenK(3);
		//l.reverseFormAPoint(5);
		System.out.println("After reversing");
		//System.out.println(l);
		//Iterator<Integer> it = l.iterator();
		//System.out.println(it.hasNext());
		//while(it.hasNext()) {
			//System.out.println(it.next());
		//}
		//l.deleteList();
		System.out.println(l);
	}
}