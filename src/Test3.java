import java.util.ArrayList;
import java.util.List;

//给定二叉树tree,tree的节点对象为TNode
class TNode{
	String value;
	TNode left,right;
	
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
	private static List<String> list = new ArrayList<String>();
	
	public static void main (String[] args){
		TNode root = init();
		TreeLevel(root,3);
		for (String s:list){
			System.out.println(s);
		}
	}
	
	public static TNode init(){
		TNode root,b,c,d,f,g,h;
		g = new TNode("G",null,null);
		h = new TNode("H",null,null);
		c = new TNode("C",null,null);
		f = new TNode("F",null,null);
		b = new TNode("B",g,h);
		d = new TNode("D",c,f);
		root = new TNode("A",b,d);
		return root;
	}
	
	public static void TreeLevel(TNode root, int n){
		if (root!=null){
			if (1==n){
				list.add(root.value);
			}
			TreeLevel(root.left,n-1);
			TreeLevel(root.right,n-1);
			
		}
	}
}
