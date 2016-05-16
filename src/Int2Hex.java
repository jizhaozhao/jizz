
import static org.junit.Assert.*;
import org.junit.Test;

public class Int2Hex {
	//ʹ�ù̶��õĶ�Ӧ�ֽ��������洢
	private static final byte[] digitHexs = new byte[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
			'C', 'D', 'E', 'F' };

	/**
	 * ��ȡһ��������Ӧ��ʮ�������ַ���������������ǰ����ӷ���
	 * <pre>
	 *   int2hex(0) == "0"
	 *   int2hex(9) == "9"
	 *   int2hex(10) == "A"
	 *   int2hex(-10) == "-A"
	 *   int2hex(16) == "10"
	 * </pre>
	 * @param num
	 * @return
	 */
	public String int2hex(int num) {
		if (num == 0) {
			return "0";
		}
		if (num == Integer.MIN_VALUE) { // ��Integer.MIN_VALUEת��Ϊ����ֵʱ���ᳬ�����ͱ߽磬�ʽ������⴦��
			return "-80000000";
		}
		//����ʱ��ʼ����С����ʡ�ڴ�
		byte[] chars = new byte[num < 0 ? 9 : 8];
		boolean isNegtive = num < 0;//�Ƿ�Ϊ����
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
	 * ��ȡһ��0~15��������Ӧ��ʮ�������ַ�
	 * @param digit
	 * @return
	 */
	private byte digit2HexChar(int digit) {
		if (digit < 0 || digit > 15) {
			throw new IllegalArgumentException("�޷�������[0,15]��Χ����ת��Ϊ16�����ַ�:" + digit);
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