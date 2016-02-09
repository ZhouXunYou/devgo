package devgo.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtil {
	/**
	 * 文件复制
	 * @param fileInputStream
	 * @param fileOutputStream
	 */
	public void fileCopy(FileInputStream fileInputStream,FileOutputStream fileOutputStream){
		FileChannel fileInputChannel = fileInputStream.getChannel();
		FileChannel fileOutputChannel = fileOutputStream.getChannel();
		try {
			fileInputChannel.transferTo(0, fileInputChannel.size(), fileOutputChannel);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				fileInputStream.close();
				fileInputChannel.close();
				fileOutputStream.close();
				fileOutputChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
