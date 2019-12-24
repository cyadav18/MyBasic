package BasicStructures;

import java.util.Iterator;

public class Tree<T extends Comparable<T>> implements Iterable<T> {
	NodeTree<T> root;
	int size;
	LinkedList<T> list = new LinkedList<T>();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (root == null) {
			return "No element";
		} else {
			Queue<NodeTree<T>> queue = new Queue<NodeTree<T>>();
			queue.enque(root);
			NodeTree<T> temp;
			while (!queue.isEmpty()) {
				temp = queue.deque();
				sb.append(temp + " ");
				if (temp.getLeft() != null)
					queue.enque(temp.getLeft());
				if (temp.getRight() != null)
					queue.enque(temp.getRight());
			}
			return sb.toString();
		}
	}

	public void add(T data) {
		NodeTree<T> node = new NodeTree<T>(data);
		Queue<NodeTree<T>> queue = new Queue<NodeTree<T>>();
		if (root == null) {
			root = node;
			size++;
		} else {
			queue.enque(root);
			while (!queue.isEmpty()) {
				NodeTree<T> temp = queue.deque();
				if (temp.getLeft() == null) {
					temp.setLeft(node);
					return;
				}
				if (temp.getRight() == null) {
					temp.setRight(node);
					return;
				}
				queue.enque(temp.getLeft());
				queue.enque(temp.getRight());
			}
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new TreeIterator<T>(this.root);
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
		if (root == null)
			return;
		list.insert(root.data);
		System.out.print(root + " ");
		preOrdder(root.getLeft());
		preOrdder(root.getRight());
	}

	private void inOrder(NodeTree<T> root) {
		if (root == null)
			return;
		inOrder(root.getLeft());
		list.insert(root.data);
		System.out.print(root + " ");
		inOrder(root.getRight());

	}

	private void postOrder(NodeTree<T> root) {
		if (root == null)
			return;
		postOrder(root.getLeft());
		postOrder(root.getRight());
		list.insert(root.data);
		System.out.print(root + " ");
	}

	public void levelOrder() {
		levelOrderQueue(root);
	}

	private void levelOrderQueue(NodeTree<T> root) {
		Queue<NodeTree<T>> queue = new Queue<>();
		queue.enque(root);
		while (!queue.isEmpty()) {
			root = queue.deque();
			System.out.print(root + " ");
			if (root.getLeft() != null)
				queue.enque(root.getLeft());
			if (root.getRight() != null)
				queue.enque(root.getRight());
		}
		System.out.println();
	}

	public void iterationPreOrder() {
		iterationPreOrder(root);
		iterationPreOrder1(root);
	}

	private void iterationPreOrder(NodeTree<T> root) {
		Stack<NodeTree<T>> s = new Stack<NodeTree<T>>();
		s.push(root);
		while (!s.isEmpty()) {
			NodeTree<T> node = s.pop();
			System.out.print(node + " ");
			if (node.getRight() != null)
				s.push(node.getRight());
			if (node.getLeft() != null)
				s.push(node.getLeft());
		}
		System.out.println();
	}

	private void iterationPreOrder1(NodeTree<T> root) {
		if (root == null)
			return;
		Stack<NodeTree<T>> s = new Stack<NodeTree<T>>();
		while (root != null || !s.isEmpty()) {
			while (root != null) {
				System.out.print(root + " ");
				s.push(root);
				root = root.getLeft();
			}
			root = s.pop();
			root = root.getRight();
		}
	}

	public void iterationInorder() {
		iterationInOrder(root);
	}

	private void iterationInOrder(NodeTree<T> root) {
		if (root == null)
			return;
		Stack<NodeTree<T>> s = new Stack<NodeTree<T>>();
		while (root != null || !s.isEmpty()) {
			while (root != null) {
				s.push(root);
				root = root.getLeft();
			}
			root = s.pop();
			System.out.print(root + " ");
			root = root.getRight();
		}
	}

	public void printBoundries() {
		printBoundriesLeft(root);
		printLeafNodes(root);
		printBoundriesRight(root.getRight());
		System.out.println();
	}

	private void printBoundriesLeft(NodeTree<T> root) {
		if (root.getLeft() == null)
			return;
		System.out.print(root + " ");
		if (root.getLeft() != null)
			printBoundriesLeft(root.getLeft());
		else
			printBoundriesLeft(root.getRight());
	}

	private void printLeafNodes(NodeTree<T> root) {
		if (root == null)
			return;
		if (root.getLeft() == null && root.getRight() == null)
			System.out.print(root + " ");
		printLeafNodes(root.getLeft());
		printLeafNodes(root.getRight());
	}

	private void printBoundriesRight(NodeTree<T> root) {
		if (root.getRight() == null)
			return;
		if (root.getRight() != null)
			printBoundriesRight(root.getRight());
		else
			printBoundriesRight(root.getLeft());
		System.out.print(root + " ");
	}

	public int getSize() {
		size = 0;
		size = getSize(root);
		return size;
	}

	private int getSize(NodeTree<T> root) {
		if (root == null)
			return 0;
		return 1 + getSize(root.getLeft()) + getSize(root.getRight());
	}

	public T getMax() {
		NodeTree<T> data = findMax(root);
		return data.getData();
	}

	private NodeTree<T> findMax(NodeTree<T> root) {
		if (root == null)
			return null;
		NodeTree<T> d1 = findMax(root.getLeft());
		NodeTree<T> d2 = findMax(root.getRight());
		return findMaxOfRoot(root, d1, d2);
	}

	private NodeTree<T> findMaxOfRoot(NodeTree<T> n1, NodeTree<T> n2, NodeTree<T> n3) {
		NodeTree<T> max = n1.getMax(n2);
		if (n3 != null)
			max = n3.getMax(max);
		return max;
	}

	public int getHeight() {
		return findHeight(root);
	}

	private int findMax(int a, int b) {
		if (a > b)
			return a;
		else
			return b;
	}

	private int findHeight(NodeTree<T> root) {
		if (root == null)
			return 0;
		int left = findHeight(root.getLeft());
		int right = findHeight(root.getRight());
		return (1 + findMax(left, right));
	}

	public Tree<T> creatCopyOfTree() {
		NodeTree<T> node = creatCopy(root);
		Tree<T> t = new Tree<T>();
		t.root = node;
		return t;
	}

	private NodeTree<T> creatCopy(NodeTree<T> root) {
		if (root == null)
			return null;
		NodeTree<T> node1 = creatCopy(root.getLeft());
		NodeTree<T> node2 = creatCopy(root.getRight());
		NodeTree<T> node3 = null;
		if (node1 != null) {
			node3 = new NodeTree<T>(node1);
			// node3.setLeft(node1.getLeft());
			// node3.setRight(node1.getRight());
		}
		NodeTree<T> node4 = null;
		if (node2 != null) {
			node4 = new NodeTree<T>(node2);
			// node4.setLeft(node2.getLeft());
			// node4.setRight(node2.getRight());
		}
		NodeTree<T> node5 = new NodeTree<T>(root.getData());
		node5.setLeft(node3);
		node5.setRight(node4);
		return node5;
	}

	public Tree<T> creatMirrorOfTree() {
		NodeTree<T> node = creatMirror(root);
		Tree<T> t = new Tree<T>();
		t.root = node;
		return t;
	}

	private NodeTree<T> creatMirror(NodeTree<T> root) {
		if (root == null)
			return null;
		NodeTree<T> node1 = creatMirror(root.getLeft());
		NodeTree<T> node2 = creatMirror(root.getRight());
		NodeTree<T> node3 = null;
		if (node1 != null) {
			node3 = new NodeTree<T>(node1);
			// node3.setLeft(node1.getLeft());
			// node3.setRight(node1.getRight());
		}
		NodeTree<T> node4 = null;
		if (node2 != null) {
			node4 = new NodeTree<T>(node2);
			// node4.setLeft(node2.getLeft());
			// node4.setRight(node2.getRight());
		}
		NodeTree<T> node5 = new NodeTree<T>(root.getData());
		node5.setLeft(node4);
		node5.setRight(node3);
		return node5;
	}

	public boolean contains(T data) {
		NodeTree<T> node1 = new NodeTree<T>(data);
		NodeTree<T> node2 = search(root, node1);
		if (node2 != null)
			return node2.equals(node1);
		return false;
	}

	private NodeTree<T> search(NodeTree<T> root, NodeTree<T> node) {
		if (root == null)
			return null;
		if (root.equals(node))
			return root;
		NodeTree<T> left = search(root.getLeft(), node);
		if (left != null)
			return left;
		NodeTree<T> right = null;
		if (left == null) {
			right = search(root.getRight(), node);
		}
		return right;
	}

	public boolean checkMirror(Tree<T> t1) {
		boolean b = checkMirror(this.root, t1.root);
		return b;
	}

	private boolean checkMirror(NodeTree<T> root1, NodeTree<T> root2) {
		if (root1 == null && root2 == null)
			return true;
		if (root1 != null && root2 == null)
			return false;
		if (root1 == null && root2 != null)
			return false;
		if (root1.getData() != root2.getData())
			return false;
		boolean left = checkMirror(root1.getRight(), root2.getLeft());
		if (!left)
			return left;
		boolean right = checkMirror(root1.getRight(), root2.getLeft());
		return right;
	}

	public void getAnsistor(T data) {
		list.deleteList();
		NodeTree<T> node = new NodeTree<T>(data);
		getAnsistor(root, node);
		System.out.println(list);
	}

	private NodeTree<T> getAnsistor(NodeTree<T> root, NodeTree<T> node) {
		if (root == null)
			return null;
		if (root.equals(node)) {
			list.insert(root.getData());
			return root;
		}
		NodeTree<T> left = getAnsistor(root.getLeft(), node);
		NodeTree<T> right = getAnsistor(root.getRight(), node);
		if (left != null) {
			list.insert(root.getData());
			return left;
		}
		if (right != null) {
			list.insert(root.getData());
			return right;
		}
		return null;
	}

	static int level = 0;

	public int getLevel(T data) {
		NodeTree<T> node = new NodeTree<T>(data);
		if (getLevel(root, node))
			return level + 1;
		return 0;
	}

	private boolean getLevel(NodeTree<T> root, NodeTree<T> node) {
		if (root == null)
			return false;
		if (root.equals(node))
			return true;
		boolean left = getLevel(root.getLeft(), node);
		boolean right = getLevel(root.getRight(), node);
		if (left) {
			level++;
			return left;
		}
		if (right) {
			level++;
			return right;
		}
		return false;
	}

	public int getDiameter() {
		getDiameterD(root);
		System.out.println(this.diameter);
		return getDiameter(root);
	}

	public int diameter = 0;

	private int getDiameterD(NodeTree<T> root) {
		if (root == null)
			return 0;
		int leftDiameter = getDiameterD(root.getLeft());
		int rightDiameter = getDiameterD(root.getRight());
		int diameter = 1 + leftDiameter + rightDiameter;
		if (this.diameter < diameter)
			this.diameter = diameter;
		return 1 + findMax(leftDiameter, rightDiameter);
	}

	private int getDiameter(NodeTree<T> root) {
		if (root == null)
			return 0;
		int left = getDiameter(root.getLeft());
		int right = getDiameter(root.getRight());
		int diameter = getMaxNodes(root.getLeft()) + getMaxNodes(root.getRight()) + 1;
		return findMax(findMax(left, right), diameter);
	}

	private int getMaxNodes(NodeTree<T> root) {
		if (root == null)
			return 0;
		int left = getMaxNodes(root.getLeft());
		int right = getMaxNodes(root.getRight());
		return 1 + findMax(left, right);
	}
}

class TreeIterator<T extends Comparable<T>> implements Iterator<T> {
	Queue<NodeTree<T>> queue = new Queue<NodeTree<T>>();
	NodeTree<T> temp;

	public TreeIterator(NodeTree<T> root) {
		temp = root;
		queue.enque(temp);
	}

	@Override
	public boolean hasNext() {
		return (!queue.isEmpty());
	}

	@Override
	public T next() {
		temp = queue.deque();
		if (temp.getLeft() != null)
			queue.enque(temp.getLeft());
		if (temp.getRight() != null)
			;
		queue.enque(temp.getRight());
		return temp.getData();
	}

}

class TestTree {
	public static void main(String[] args) {
		Tree<Integer> t = new Tree<Integer>();
		for (int i = 1; i <= 15; i++)
			t.add(i);
		// System.out.println(t);
		// Iterator<Integer> it = t.iterator();
		// while(it.hasNext())
		// System.out.println(it.next());
		t.preOrderTraversal();
		// LinkedList<Integer> list = t.list;
		// Iterator<Integer>it1 = list.iterator();
		// while(it1.hasNext()) {
		// System.out.println(it1.next());
		// }
		// System.out.println();
		// t.postOrderTraversal();
		// t.levelOrder();
		// t.iterationPreOrder();
		t.inOrderTraversal();
		// t.iterationInorder();
		// t.printBoundries();
		// System.out.println(t.getSize());
		// System.out.println(t.getMax());
		// Tree<String> t1 = new Tree<String>();
		// for(int i = 65;i<65+26;i++) {
		// t1.add(Character.toString((char)i));
		// }
		// Tree<Integer>t2 = t.creatMirrorOfTree();
		// t2.preOrderTraversal();
		// System.out.println(t2.list);
		// System.out.println(t.checkMirror(t));
		// t1.getAnsistor("Y");
		// t1.list.printElements();
		// System.out.println(t.getLevel(13));
		// System.out.println(t1.getDiameter());
	}
}