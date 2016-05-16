import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

//给定二叉树tree,tree的节点对象为TNode
class TNode {
	String value;
	TNode left, right;

	public TNode() {
		super();
	}

	public TNode(String value, TNode left, TNode right) {
		super();
		this.value = value;
		this.left = left;
		this.right = right;
	}

}

class Tree {
	private static List<String> list = new ArrayList<String>(6);

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		Tree.list = list;
	}

	public void treeLevel(TNode root, int n) {
		if (n <= 0) {
			System.out.println("行数为大于零的整数");
		}
		if (root != null) {
			if (1 == n) {
				list.add(root.value);
			}
			treeLevel(root.left, n - 1);
			treeLevel(root.right, n - 1);
		}
	}
}

public class Test3 {

	@Test
	public void test() {
		TNode root = init();
		Tree tree = new Tree();
		tree.treeLevel(root, 3);
		//treeLevel(root, 3);
		assertEquals("[G, H, C, F]", tree.getList().toString());

		tree.getList().clear();
		tree.treeLevel(root, 2);
		assertEquals("[B, D]", tree.getList().toString());

		tree.getList().clear();
		tree.treeLevel(root, 1);
		assertEquals("[A]", tree.getList().toString());

		tree.getList().clear();
		tree.treeLevel(root, 4);
		assertEquals("[E, I, J, K, L, M]", tree.getList().toString());
	}

	public static TNode init() {
		TNode root, b, c, d, e, f, g, h, i, j, k, l, m;
		e = new TNode("E", null, null);
		i = new TNode("I", null, null);
		j = new TNode("J", null, null);
		k = new TNode("K", null, null);
		l = new TNode("L", null, null);
		m = new TNode("M", null, null);
		g = new TNode("G", e, i);
		h = new TNode("H", j, k);
		c = new TNode("C", l, m);
		f = new TNode("F", null, null);
		b = new TNode("B", g, h);
		d = new TNode("D", c, f);
		root = new TNode("A", b, d);
		return root;
	}
	
	public static TNode init2() {
		TNode root, b, c, d, e, f, g, h, i, j, k, l, m;
		e = new TNode("E", null, null);
		i = new TNode("I", null, null);
		j = new TNode("J", null, null);
		k = new TNode("K", null, null);
		l = new TNode("L", null, null);
		m = new TNode("M", null, null);
		g = new TNode("G", e, i);
		h = new TNode("H", j, k);
		c = new TNode("C", l, m);
		f = new TNode("F", null, null);
		b = new TNode("B", g, h);
		d = new TNode("D", c, f);
		root = new TNode("A", b, d);
		return root;
	}

}