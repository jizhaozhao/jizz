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
	public static byte[] file2buf(File file) throws FileNotFoundException {

		InputStream fis = null;
		fis = new FileInputStream(file);

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
	 * 判断两个字符数组是否相等
	 * 
	 * @param b1
	 * @param b2
	 * @return
	 */
	public boolean check(byte[] b1, byte[] b2) {
		if (b1.length != b2.length)
			return false;
		int i = 0;
		for (i = 0; i < b1.length; i++) {
			if (b1[i] == b2[i])
				continue;
			else
				return false;
		}
		if (i == b1.length)
			return true;
		return true;
	}

	/**
	 * 返回该文件转换为字符数组后与正确结果是否相等
	 * 
	 * @param path
	 * @param b2
	 * @return
	 */
	public String Result(String path, byte[] b2) {
		File file = new File(path);
		byte[] buf = null;
		if (file.isDirectory()) {
			System.out.println("该路径为文件夹目录");
			return "该路径为文件夹目录";
		}
		if (file.length() > Integer.MAX_VALUE) {
			System.out.println("文件大小大于2G");
			return "文件大小大于2G";
		}
		try {
			buf = file2buf(file);
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在");
			return "文件不存在";
		}
		if (check(buf, b2))
			return "转换成功";

		return "转换失败";

	}

	@Test
	public void test() {

		try {
			assertEquals(
					"文件不存在",
					Result("C:\\Users\\lenovo\\Desktop\\tesst.txt",
							"中国".getBytes()));
			assertEquals(
					"转换成功",
					Result("C:\\Users\\lenovo\\Desktop\\test.txt",
							"中国".getBytes("UTF-8")));
			assertEquals(
					"转换成功",
					Result("C:\\Users\\lenovo\\Desktop\\test2.txt",
							"中国".getBytes("GB2312")));
			assertEquals("文件大小大于2G",
					Result("D:\\succezIDE2.rar", "中国".getBytes()));
			assertEquals("该路径为文件夹目录", Result("D:\\", "中国".getBytes()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
