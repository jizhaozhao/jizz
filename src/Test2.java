import java.util.ArrayList;
import java.util.List;

//实现intToHex 将一个整数转换为16进制的字符串
public class Test2 {
	public static void main (String[] args){
		String ss = intToHex(255);
		System.out.println(ss);
	}
	
	public static String intToHex(int n){
		//s：将整数转化为二进制的字符串
		StringBuffer s = new StringBuffer();
		while(n!=1){
			s.append(n%2);
			n = n/2;
		}
		s.append(1);
		s.reverse();
		int length = s.length();
		int num1 = length/4+1;//分成的字符串个数
		int num2 = length%4;//第一个字符串的长度
		//list将二进制字符串按4位一组分成若干组字符串
		List<String> list= new ArrayList<String>();
		list.add(s.substring(0, num2));
		for (int i=0;i<num1-1;i++){
			list.add(s.substring(num2+4*i, num2+4*(i+1)));
		}
		s.setLength(0);
		//sb用来存储最后的字符串
		StringBuffer sb = new StringBuffer();
		for (String sss:list){
			switch(sss){
				case "1": sb.append(1); break;
				case "10": sb.append(2); break;
				case "11": sb.append(3); break;
				case "100": sb.append(4); break;
				case "101": sb.append(5); break;
				case "110": sb.append(6); break;
				case "111": sb.append(7); break;
				
				case "0000": sb.append(0); break;
				case "0001": sb.append(1); break;
				case "0010": sb.append(2); break;
				case "0011": sb.append(3); break;
				case "0100": sb.append(4); break;
				case "0101": sb.append(5); break;
				case "0110": sb.append(6); break;
				case "0111": sb.append(7); break;
				case "1000": sb.append(8); break;
				case "1001": sb.append(9); break;
				case "1010": sb.append('a'); break;
				case "1011": sb.append('b'); break;
				case "1100": sb.append('c'); break;
				case "1101": sb.append('d'); break;
				case "1110": sb.append('e'); break;
				case "1111": sb.append('f'); break;
			}
		}
		return sb.toString();
	}
}
