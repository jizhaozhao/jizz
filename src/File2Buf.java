import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 将文件内容转换成byte数组返回
 * 
 * @author jizz
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
			throw new RuntimeException("该路径为文件夹目录");
		}
		if (file.length() > Integer.MAX_VALUE) {
			System.out.println("文件大小大于2G");
			throw new RuntimeException("文件大小大于2G");
		}

		InputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			System.out.println("文件不存在");
			throw new RuntimeException("文件不存在");
		}

		int fileSize = (int) file.length();
		byte[] buf = new byte[fileSize];
		int total = 0;

		int a = fileSize / 4096;
		int b = fileSize % 4096;

		try {
			int n = 0;
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
		for (byte bb : buf) {
			System.out.println(bb);
		}
		System.out.println("============");
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

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * 测试路径为文件夹的异常
	 * 
	 * @author jizz
	 */
	@Test
	public void test1() {

		thrown.expect(RuntimeException.class);

		try {
			thrown.expectMessage("该路径为文件夹目录");
			assertArrayEquals("中国".getBytes("UTF-8"),
					file2buf(new File("C:\\")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试文件大于2G的异常
	 */
	@Test
	public void test2() {

		thrown.expect(RuntimeException.class);
		thrown.expectMessage("文件大小大于2G");

		try {
			assertArrayEquals("中国".getBytes("UTF-8"), file2buf(new File(
					"D:\\succezIDE2.rar")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试文件不存在的异常
	 */
	@Test
	public void test3() {

		thrown.expect(RuntimeException.class);
		thrown.expectMessage("文件不存在");

		try {
			assertArrayEquals("中国".getBytes("UTF-8"), file2buf(new File(
					"D:\\succezIE2.rar")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试正常情况
	 * 
	 * @author jizz
	 */
	@Test
	public void test4() {

		try {
			assertArrayEquals("中国123abc".getBytes("UTF-8"), file2buf(new File(
					"C:\\Users\\lenovo\\Desktop\\test.txt")));
			assertArrayEquals("中国123abc".getBytes("GB2312"), file2buf(new File(
					"C:\\Users\\lenovo\\Desktop\\test2.txt")));

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
