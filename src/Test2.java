import java.util.ArrayList;
import java.util.List;

//实现intToHex 将一个整数转换为16进制的字符串
public class Test2 {
	public static void main (String[] args){
		
	}
	
	public String intToHex(int n){
		StringBuffer s = null;
		while(n!=1){
			s.append(n%2);
			n = n/2;
		}
		s.append(1);
		s.reverse();
		int length = s.length();
		
		List<String> list= new ArrayList<String>();
	}
}
