import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 给定二叉树tree,tree的节点对象为TNode
 * 
 * @author jizz
 *
 */
class TNode {
	String value;// 当前节点的值
	TNode left, right;// 左右孩子节点
	int level = 0;// 该二叉树的层数

	/**
	 * 获取该二叉树层数
	 * 
	 * @param node
	 * @return 二叉树层数
	 */
	public int getLevel(TNode node) {
		if (null == node)
			return 0;
		else
			return Math.max(getLevel(node.left), getLevel(node.right)) + 1;
	}

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

public class Test3 {

	/**
	 * 将该层的TNode节点的list拼接成字符串
	 * 
	 * @param list
	 * @return 二叉树某层的值拼接成的字符串
	 */
	public String getString(List<TNode> list) {
		if (null == list) {
			return "层数错误";
		}
		StringBuilder str = new StringBuilder();
		for (TNode t : list) {
			str.append(t.value + '-');
		}
		str.deleteCharAt(str.length() - 1);
		return str.toString();
	}

	@Test
	public void test() {
		TNode root = init();
		assertEquals("层数错误", getString(traval(root, 5)));
		assertEquals("层数错误", getString(traval(root, -1)));
		assertEquals("E-I-J-K-L-M", getString(traval(root, 3)));
		assertEquals("G-H-C-F", getString(traval(root, 2)));
		assertEquals("B-D", getString(traval(root, 1)));
		assertEquals("A", getString(traval(root, 0)));

		TNode root2 = init2();
		assertEquals("层数错误", getString(traval(root2, 5)));
		assertEquals("层数错误", getString(traval(root2, -1)));
		assertEquals("F-H-G", getString(traval(root2, 3)));
		assertEquals("I-A-D", getString(traval(root2, 2)));
		assertEquals("C-E", getString(traval(root2, 1)));
		assertEquals("B", getString(traval(root2, 0)));

	}

	/**
	 * 获取二叉树某层的所有节点，并添加到list中
	 * 
	 * @param node
	 * @param level
	 * @return 二叉树某一层所有节点的list
	 */
	public static List<TNode> traval(TNode node, int level) {
		// 如果层数为负
		if (level < 0) {
			return null;
		}
		// 如果层数超过二叉树的最大层数
		if (level > node.getLevel(node)) {
			return null;
		}
		// 调用递归函数求得该层的TNode节点的list
		return traval(node, 0, level,
				new ArrayList<TNode>((int) Math.pow(2, level)));
	}

	/**
	 * 递归获得当前层的节点
	 * 
	 * @param node
	 * @param currentLevel
	 * @param level
	 * @param list
	 * @return 二叉树某层所有节点的list
	 */
	private static List<TNode> traval(TNode node, int currentLevel, int level,
			List<TNode> list) {
		if (node == null) {
			return list;
		}
		if (currentLevel == level) {
			list.add(node);
			return list;
		}
		traval(node.left, currentLevel + 1, level, list);
		traval(node.right, currentLevel + 1, level, list);
		return list;
	}

	public static TNode init() {
		// 该二叉树为A(B(G(E,I),H(J,K)),D(C(L,M),F))
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
		// 该二叉树为A(C(,I), E(A(F,),D(H,G)))
		TNode root, c, d, e, f, g, h, i, a;
		f = new TNode("F", null, null);
		h = new TNode("H", null, null);
		g = new TNode("G", null, null);
		i = new TNode("I", null, null);
		a = new TNode("A", f, null);
		d = new TNode("D", h, g);
		c = new TNode("C", null, i);
		e = new TNode("E", a, d);
		root = new TNode("B", c, e);
		return root;
	}

}