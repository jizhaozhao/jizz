
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

import org.junit.Test;

public class File2Buf {

	public static byte[] file2buf(File file) throws IOException {
		InputStream fis = new FileInputStream(file);
		long fileSize = file.length();
		byte[] buf = new byte[(int) fileSize];
		int total = 0;
//		int n = 0;
//		while ((n = fis.read(buf, total, 4096)) != -1 && fileSize > total) {
//			total += n;
//		}
		fis.read(buf, total, (int) fileSize);
		closeStream(fis);
		return buf;
	}

	public static void closeStream(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void test(){
		try {
			File file = new File("C:\\Users\\lenovo\\Desktop\\test.txt");
			byte[] buf = file2buf(file);
			StringBuilder str = new StringBuilder();
			for (byte b:buf){
				str.append(b);
			}
			assertEquals("656565495051", str.toString());
		} catch (FileNotFoundException e){
			System.out.println("系统找不到指定的文件");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
