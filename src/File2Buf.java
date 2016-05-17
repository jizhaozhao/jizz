import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

import org.junit.Test;

public class File2Buf {

	public static byte[] file2buf(File file) throws FileNotFoundException {
		InputStream fis = new FileInputStream(file);

		int fileSize = (int) file.length();
		byte[] buf = new byte[fileSize];
		int total = 0;
		int n = 0;
		int a = fileSize / 4096;// 记录需要磁道的数目
		int b = fileSize % 4096;// 最后一个磁道需要的长度

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

	public static void closeStream(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getFileStirng(String path) {
		File file = new File(path);
		if (file.isDirectory()) {
			System.out.println("该路径为文件夹目录");
			return "该路径为文件夹目录";
		}
		if (file.length() > Integer.MAX_VALUE) {
			System.out.println("文件大小大于2G");
			return "文件大小大于2G";
		}
		byte[] buf;
		try {
			buf = file2buf(file);
			StringBuilder str = new StringBuilder();
			for (byte b : buf) {
				str.append(b);
				str.append('-');
			}
			str.deleteCharAt(str.length() - 1);
			return str.toString();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("文件不存在");
			return "文件不存在";
		}
	}

	@Test
	public void test() {
		assertEquals("文件大小大于2G", getFileStirng("D:\\succezIDE2.rar"));
		assertEquals("该路径为文件夹目录", getFileStirng("D:\\"));
		assertEquals("文件不存在", getFileStirng("D:\\1.txt"));
		assertEquals("65-65-65-49-50-51",
				getFileStirng("C:\\Users\\lenovo\\Desktop\\test.txt"));
		assertEquals(
				"102-97-106-107-108-59-97-100-106-115-108-107-102-106-108-107-97-59-100-115-106",
				getFileStirng("C:\\Users\\lenovo\\Desktop\\test2.txt"));
	}

}
