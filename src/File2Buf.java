import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 将文件内容转换成byte数组返回
 * 
 * @author jizz @
 */
public class File2Buf {

	/**
	 * 将文件内容转换成byte数组返回 变量total表示当前应该读取的字节数 变量n表示已经读取的字节数 变量a表示存储需要的硬盘磁道的数目
	 * 变量b表示最后一个磁道需要的字节长度
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static byte[] file2buf(File file) {
		if (file.isDirectory()) {
			System.out.println("该路径为文件夹目录");
			return null;
		}
		if (file.length() > Integer.MAX_VALUE) {
			System.out.println("文件大小大于2G");
			return null;
		}
		InputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			System.out.println("文件不存在");
			return null;
		}

		int fileSize = (int) file.length();
		byte[] buf = new byte[fileSize];
		int total = 0;
		int n = 0;
		int a = fileSize / 4096;
		int b = fileSize % 4096;

		try {
			while (0 != a--) {
				n = fis.read(buf, total, 4096);
				total += 4096;
			}
			n = fis.read(buf, total, b);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeStream(fis);
		}
		return buf;
	}

	/**
	 * @param closeable
	 */
	public static void closeStream(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 判断应该返回的提示字符串，"正确","错误" 或者 "读取文件异常"
	 * @param b1
	 * @param b2
	 * @return
	 */
	public String check(byte[] b1, byte[] b2) {
		if (null == b1) {
			return "读取文件异常";
		}
		if (b1.length != b2.length)
			return "错误";
		int i = 0;
		for (i = 0; i < b1.length; i++) {
			if (b1[i] == b2[i])
				continue;
			else
				return "错误";
		}
		if (i == b1.length)
			return "正确";
		return "错误";
	}

	@Test
	public void test() {

		try {
			assertEquals(
					"读取文件异常",
					check(file2buf(new File(
							"C:\\Users\\lenovo\\Desktop\\tesst.txt")), "中国"
							.getBytes()));
			assertEquals(
					"读取文件异常",
					check(file2buf(new File("D:\\succezIDE2.rar")),
							"中国".getBytes()));
			assertEquals("读取文件异常",
					check(file2buf(new File("D:\\")), "中国".getBytes()));
			assertEquals(
					"正确",
					check(file2buf(new File(
							"C:\\Users\\lenovo\\Desktop\\test.txt")), "中国"
							.getBytes("UTF-8")));
			assertEquals(
					"正确",
					check(file2buf(new File(
							"C:\\Users\\lenovo\\Desktop\\test2.txt")), "中国"
							.getBytes("GB2312")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
