package com.obd.util;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageConverter {

	public static boolean isImage(String filePath) throws IOException {
		boolean flag = false;

		FileInputStream img = null;
		try {
			img = new FileInputStream(filePath);

			BufferedImage bufImg = ImageIO.read(img);
			if (bufImg.getWidth() != 0 && bufImg.getHeight() != 0) {
				flag = true;
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			img.close();
		}

		return flag;
	}

	public static void checkAndTransform(String sourcePath, String targetPath)throws Exception {
		if (isImage(sourcePath)) {
			transform(sourcePath, targetPath);
		}
	}
	
	
	/* 
	 * 当  width 和 length 为 0 的时候 表示不压缩 
	 */
	public static void transform(String sourcePath, String targetPath) throws Exception {
		EasyImage sourceImg = new EasyImage(sourcePath);
		int imgWidth = sourceImg.getWidth();
		int imgHeight = sourceImg.getHeight();
		sourceImg.saveAs(targetPath);
	}	
	
	
}
	
