package BasicStructures;

public class BinarySearchTree<T extends Comparable<T>> {
	NodeTree<T> root;
	int size;
	LinkedList<T>list = new LinkedList<T>();

	
	public void insert(T data) {
		NodeTree<T>node = new NodeTree<T>(data);
		if(root==null)
		{
			root = node;
		}
		else {
			Queue<NodeTree<T>>queue = new Queue<>();
			queue.enque(root);
			while(!queue.isEmpty()) {
				NodeTree<T> temp = queue.deque();
				if(node.getData().compareTo(temp.getData())==-1) {
					if(temp.getLeft()==null) {
						temp.setLeft(node);
						return;
					}
					else {
						queue.enque(temp.getLeft());
					}
					
				}
				if(node.getData().compareTo(temp.getData())==1) {
					if(temp.getRight()==null) {
						temp.setRight(node);
						return;
					}
					else {
						queue.enque(temp.getRight());
					}
				}
			}
		}
	}

	public boolean insert1(T data) {
		NodeTree<T>node = insertRecursively(root,data);
		if(node!=null) {
			root = node;
			return true;
		}
		return false;
	}
	private NodeTree<T> insertRecursively(NodeTree<T> root, T data) {
		// TODO Auto-generated method stub
		if(root==null) {
			NodeTree<T>node = new NodeTree<>(data);
			return node;
		}
		if(data.compareTo(root.getData())==-1) {
			NodeTree<T>Bleft = insertRecursively(root.getLeft(), data);
			root.setLeft(Bleft);
		}
		if(data.compareTo(root.getData())==1) {
			NodeTree<T>Bright = insertRecursively(root.getRight(), data);
			root.setRight(Bright);
		}
		return root;
	}

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
	public void preOrderTraversal() {
		list.deleteList();
		preOrdder(root);
		System.out.println();
	}


	public void inOrderTraversal() {
		list.deleteList();
		inOrder(root);
		System.out.println();
	}


	public void postOrderTraversal() {
		list.deleteList();
		postOrder(root);
		System.out.println();
	}


	private void preOrdder(NodeTree<T> root) {
		if(root==null)
			return;
		list.insert(root.data);
		System.out.print(root+" ");
		preOrdder(root.getLeft());
		preOrdder(root.getRight());
	}
	
	private void inOrder(NodeTree<T> root) {
		// TODO Auto-generated method stub
		if(root==null)
			return;
		inOrder(root.getLeft());
		list.insert(root.data);
		System.out.print(root+" ");
		inOrder(root.getRight());
		
	}


	private void postOrder(NodeTree<T> root) {
		// TODO Auto-generated method stub
		if(root==null)
			return;
		postOrder(root.getLeft());
		postOrder(root.getRight());
		list.insert(root.data);
		System.out.print(root+" ");
	}
	
	public void levelOrder() {
		levelOrderQueue(root);
	}


	private void levelOrderQueue(NodeTree<T> root) {
		// TODO Auto-generated method stub
		Queue<NodeTree<T>> queue = new Queue<>();
		queue.enque(root);
		while(!queue.isEmpty()) {
			root = queue.deque();
			System.out.print(root+" ");
			if(root.getLeft()!=null)
				queue.enque(root.getLeft());
			if(root.getRight()!=null)
				queue.enque(root.getRight());
		}
		System.out.println();
	}
	
	public void iterationPreOrder() {
		iterationPreOrder(root);
		iterationPreOrder1(root);
	}


	private void iterationPreOrder(NodeTree<T> root) {
		// TODO Auto-generated method stub
		Stack<NodeTree<T>> s = new Stack<NodeTree<T>>();
		s.push(root);
		while(!s.isEmpty()) {
			NodeTree<T> node = s.pop();
			System.out.print(node+" ");
			if(node.getRight()!=null)
				s.push(node.getRight());
			if(node.getLeft()!=null)
				s.push(node.getLeft());
		}
		System.out.println();
	}
	private void iterationPreOrder1(NodeTree<T> root) {
		// TODO Auto-generated method stub
		if(root==null)
			return;
		Stack<NodeTree<T>>s = new Stack<NodeTree<T>>();
		while(root!=null||!s.isEmpty()) {
			while(root!=null) {
				System.out.print(root+" ");
				s.push(root);
				root = root.getLeft();
			}
			root = s.pop();
			root=root.getRight();
		}
	}

	
	public void iterationInorder() {
		iterationInOrder(root);
	}
	private void iterationInOrder(NodeTree<T> root) {
		// TODO Auto-generated method stub
		if(root==null)
			return;
		Stack<NodeTree<T>>s = new Stack<NodeTree<T>>();
		while(root!=null||!s.isEmpty()) {
			while(root!=null) {
				s.push(root);
				root = root.getLeft();
			}
			root = s.pop();
			System.out.print(root+" ");
			root=root.getRight();
		}
	}


	public void printBoundries() {
		printBoundriesLeft(root);
		printLeafNodes(root);
		printBoundriesRight(root.getRight());
		System.out.println();
	}


	private void printBoundriesLeft(NodeTree<T> root) {
		// TODO Auto-generated method stub
		if(root.getLeft()==null)
			return;
		System.out.print(root+" ");
		if(root.getLeft()!=null)
		printBoundriesLeft(root.getLeft());
		else
			printBoundriesLeft(root.getRight());
	}


	private void printLeafNodes(NodeTree<T> root) {
		// TODO Auto-generated method stub
		if(root==null)
			return;
		if(root.getLeft()==null&&root.getRight()==null)
			System.out.print(root+" ");
		printLeafNodes(root.getLeft());
		printLeafNodes(root.getRight());
	}


	private void printBoundriesRight(NodeTree<T> root) {
		// TODO Auto-generated method stub
		if(root.getRight()==null)
			return;
		if(root.getRight()!=null)
			printBoundriesRight(root.getRight());
		else
			printBoundriesRight(root.getLeft());
		System.out.print(root+" ");
	}
	
	public int getSize() {
		size = 0;
		size = getSize(root);
		return size;
	}

	private int getSize(NodeTree<T> root) {
		// TODO Auto-generated method stub
		if(root==null)
			return 0;
		return 1+getSize(root.getLeft())+getSize(root.getRight());
	}
}
class TestBST{
	public static void main(String[] args) {
		int []arr = {8,4,12,2,6,10,14,1,3,5,7,9,11,13,15};
		BinarySearchTree<Integer>t = new BinarySearchTree<>();
		BinarySearchTree<Integer>t1 = new BinarySearchTree<>();
		for(int i = 0;i<15;i++) {
			t.insert(arr[i]);
			t1.insert1(arr[i]);
		}
		System.out.println(t);
		System.out.println(t1);
		t.inOrderTraversal();
		t1.inOrderTraversal();
	}
}