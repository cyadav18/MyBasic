package BasicStructures;

import java.text.DecimalFormat;

public class Tree_remake <T extends Comparable<T>>{
	NodeTree<T> root;
	int size;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(root==null) {
			return "No element";
		}
		else {
			Queue<NodeTree<T>> queue = new Queue<NodeTree<T>>();
			queue.enque(root);
			NodeTree<T> temp;
			while(!queue.isEmpty()) {
				temp = queue.deque();
				sb.append(temp+" ");
				if(temp.getLeft()!=null)
					queue.enque(temp.getLeft());
				if(temp.getRight()!=null)
					queue.enque(temp.getRight());
			}
			return sb.toString();
		}
	}

	
	
	public void add(T data) {
		NodeTree<T> node = new NodeTree<T>(data);
		if(root==null) {
			root = node;
			size++;
		}
		else {
			Queue<NodeTree<T>>q = new Queue<NodeTree<T>>();
			q.enque(root);
			NodeTree<T> temp = null;
			while(!q.isEmpty()) {
				temp = q.deque();
				if(temp.getLeft()==null) {
					temp.setLeft(node);
					size++;
					return;
				}
				if(temp.getRight()==null) {
					temp.setRight(node);
					size++;
					return;
				}
				q.enque(temp.getLeft());
				q.enque(temp.getRight());
			}
		}
	}
	public void printLeaf() {
		printLeaf(root);
		System.out.println();
	}



	private void printLeaf(NodeTree<T> root) {
		// TODO Auto-generated method stub
		if(root==null)
			return;
		if(root.getLeft()==null&&root.getRight()==null)
			System.out.print(root+" ");
		else {
			printLeaf(root.getLeft());
			printLeaf(root.getRight());
		}	
	}
	
	public int getSize() {
		size = getsize(root);
		return this.size;
	}



	private int getsize(NodeTree<T> root) {
		if(root==null)
			return 0;
		else 
			return 1+getsize(root.getLeft())+getsize(root.getRight());
	}
	
	public int getHeight() {
		int height = getheight(root);
		return height;
	}



	private int getheight(NodeTree<T> root) {
		if(root==null)
			return 0;
		else
			return (1+Math.max(getheight(root.getLeft()), getheight(root.getRight())));
	}
}
class Test_Remake{
	public static void main(String[] args) {
		Tree_remake<Integer> t = new Tree_remake<Integer>();
		for(int i = 1; i <=15;i++) {
			t.add(i);
		}
		System.out.println(t);
		t.printLeaf();
		System.out.println(t.getSize());
		System.out.println(t.getHeight());
		System.out.println();
		//System.out.println(t.getSize());
		//SpaceNumber s = new SpaceNumber(8);
		//System.out.println(s.getInSpace(2345));
	}
}
class SpaceNumber{
	final int space;
	public SpaceNumber(int space) {
		if(space<=0)
			throw new IllegalArgumentException("space cannot be less than 0");
		this.space = space;
	}
	public String getInSpace(int num) {
		StringBuilder sb1 = new StringBuilder();
		for(int i = 0;i<=space;i++)
			sb1.append("0");
		DecimalFormat ft = new DecimalFormat(sb1.toString()); 
	    String s1 = ft.format(num);
	    int i = 0;
	    StringBuilder sb = new StringBuilder();
	    while(i<s1.length()) {
	    	if(s1.charAt(i) == '0') {
	    		i++;
	    		sb.append(" ");
	    	}
	    	else
	    		break;
	    }
	    sb.append(s1.substring(i));
	    return sb.toString();
	}
}