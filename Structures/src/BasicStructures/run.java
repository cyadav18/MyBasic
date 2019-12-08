package BasicStructures;

public class run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node <String>n=  new Node<String>("10");
		System.out.println(n);
		Node <String>n1 = new Node<String>("20");
		n1.setNext(n);
		n.setPrevious(n1);
		n.setNext(n1);
		System.out.println(n1);
		Node<String> n2 = n1;
		int i = 0;
		while(i<10)
		{
			System.out.println(n2);
			n2=n2.getNext();
			i++;
		}
	}

}
