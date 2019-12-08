package BasicStructures;

public class NodeTree<T extends Comparable<T>>{
	T data;
	NodeTree<T> left;
	NodeTree<T> right;
	public T getData() {
		return data;
	}
	
	public NodeTree(T data) {
		super();
		this.data = data;
	}
	
	public NodeTree(NodeTree<T> node) {
		super();
		this.data = node.getData();
		this.left = node.getLeft();
		this.right = node.getRight();
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public NodeTree<T> getLeft() {
		return left;
	}
	
	public void setLeft(NodeTree<T> left) {
		this.left = left;
	}
	
	public NodeTree<T> getRight() {
		return right;
	}
	
	public void setRight(NodeTree<T> right) {
		this.right = right;
	}
	
	public NodeTree<T> getMax(NodeTree<T>n1){
		if(n1==null)
			return this;
		else {
			if(this.data.compareTo(n1.data)>=0)
				return this;
			else
				return n1;
		}
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
		NodeTree<T> other = (NodeTree<T>) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "("+data+")";
	}
	
}

class TestNode{
	public static void main(String[] args) {
		NodeTree<Integer> n = new NodeTree<Integer>(20);
		NodeTree<Integer> n2 = new NodeTree<Integer>(32);
		System.out.println(n);
		System.out.println(n2);
		System.out.println(n.getData().compareTo(n2.getData()));
	}
}