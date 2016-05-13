import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//将文件内容转换成byte数组返回，如果文件不存在或者读入错误返回null
public class Test {
	public static void main (String[] args){
		buffer = file2buf("C:\\Users\\lenovo\\Desktop\\test.txt");
		if (buffer==null){
			System.out.println("文件不存在或者读入错误");
		}
		else {
			for (byte b:buffer){
				System.out.println(b);
			}
		}
	}
	private static byte[] buffer = null;
	
	public static byte[] file2buf(String filePath){
		try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
            return null;
        } catch (IOException e) {  
            e.printStackTrace();  
            return null;
        }  
        return buffer;  
	}
}
