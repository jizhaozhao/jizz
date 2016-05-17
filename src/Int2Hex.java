import static org.junit.Assert.*;
import org.junit.Test;

public class Int2Hex {
	// 使用固定好的对应字节数组来存储
	private static final byte[] digitHexs = new byte[] { '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * 获取一个整数对应的十六进制字符串，负数将会在前面添加符号
	 * 
	 * <pre>
	 *   int2hex(0) == "0"
	 *   int2hex(9) == "9"
	 *   int2hex(10) == "A"
	 *   int2hex(-10) == "-A"
	 *   int2hex(16) == "10"
	 * </pre>
	 * 
	 * @param num
	 * @return
	 */
	public String int2hex(int num) {
		if (num == 0) {
			return "0";
		}
		if (num == Integer.MIN_VALUE) { // 将Integer.MIN_VALUE转换为绝对值时，会超出整型边界，故进行特殊处理
			return "-80000000";
		}
		// 创建时初始化大小，节省内存
		byte[] chars = new byte[num < 0 ? 9 : 8];
		boolean isNegtive = num < 0;// 是否为负数
		if (isNegtive) {
			num = Math.abs(num);
		}
		int index = chars.length;
		while (num > 0) {
			int digit = num & 0xF;
			chars[--index] = digit2HexChar(digit);
			num >>>= 4;
		}
		if (isNegtive) {
			chars[--index] = '-';
		}
		return new String(chars, index, chars.length - index);
	}

	/**
	 * 获取一个0~15的整数对应的十六进制字符
	 * 
	 * @param digit
	 * @return
	 */
	private byte digit2HexChar(int digit) {
		if (digit < 0 || digit > 15) {
			throw new IllegalArgumentException("无法将超过[0,15]范围的数转换为16进制字符:"
					+ digit);
		}
		return digitHexs[digit];
	}

	@Test
	public void test() {
		Int2Hex int2Hex = new Int2Hex();
		assertEquals("0", int2Hex.int2hex(0));
		assertEquals("9", int2Hex.int2hex(9));
		assertEquals("A", int2Hex.int2hex(10));
		assertEquals("F", int2Hex.int2hex(15));
		assertEquals("10", int2Hex.int2hex(16));
		assertEquals("FF", int2Hex.int2hex(255));
		assertEquals("-9", int2Hex.int2hex(-9));
		assertEquals("-10", int2Hex.int2hex(-16));
		assertEquals("-FF", int2Hex.int2hex(-255));
		assertEquals("-80000000", int2Hex.int2hex(Integer.MIN_VALUE));
		assertEquals("7FFFFFFF", int2Hex.int2hex(Integer.MAX_VALUE));
	}

}