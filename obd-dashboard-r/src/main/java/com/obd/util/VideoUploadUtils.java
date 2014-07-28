package com.obd.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.Inflater;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("restriction")
public class VideoUploadUtils {
	
	private static final Log log = LogFactory.getLog(VideoUploadUtils.class);
	
	private static final SimpleDateFormat FORMAT_FILEPATH = new SimpleDateFormat("yyyy/MMdd");
	//private static final SimpleDateFormat FORMAT_FILEPATH = new SimpleDateFormat("yyyy\\MMdd");
	
	public static final String HISTORY_THUMB_KEY = "alri$$!fg0o01fjg_";
	
	private static final SimpleDateFormat FORMAT_THUMB_PATH = new SimpleDateFormat("HH-mm");
	
	public static String uploadVideo(MultipartFile multipartFile, String rootDir, String filePrefix, int limitWidth) {
		String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename().toLowerCase());
		InputStream in = null;
		try {
			
			String path = getFilePath(rootDir, filePrefix, extension);
			in = multipartFile.getInputStream();
			
			FileOutputStream f = new FileOutputStream(path);//创建文件输出流
			byte [] bb=new byte[1024];  //接收缓存
			int len;
			while( (len=in.read(bb))>0){ //接收
			  f.write(bb, 0, len);  //写入文件
			}
			f.close();
			in.close();
			
			log.info("upload successfully,path:" + path);
			return path;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("upload fail, file:" + multipartFile.getOriginalFilename());
			return null;
		}
	}
	
	public static String getFilePath(String rootDir, String filePrefix, String extension){
		String dirPath = rootDir + "/" + FORMAT_FILEPATH.format(new Date());
		//String dirPath = rootDir + "\\" + FORMAT_FILEPATH.format(new Date());
		String fileName = filePrefix + RandomStringUtils.randomAlphanumeric(12) + "." + extension;
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		return dirPath + "/" + fileName;
		//return dirPath + "\\" + fileName;
	}
	
	/**
	 * 是否有效的图片格式
	 * @param file
	 * @return
	 */
	public static boolean isValidFormats(MultipartFile file) {
		if (file != null) {
			String filename = file.getOriginalFilename().toLowerCase();
			if (!filename.endsWith("mp4") && !filename.endsWith(".MP4")) {
				return true;
			}
		}
		return false;
	}
	
	private static final String HOME_PIC_DIR = "/data/img/cms/";
	
	public static byte[] decompressBytes(byte input[]) { 
		byte output[] = new byte[0]; 
		Inflater decompresser = new Inflater(); 
		decompresser.setInput(input); 
		ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length); 
		try { 
			byte[] buf = new byte[1024];
			int got; 
			while (!decompresser.finished()) { 
				got = decompresser.inflate(buf); 
				bos.write(buf, 0, got); 
			} 
			output = bos.toByteArray(); 
		}
		catch(Exception e) { 
			log.error("decompress failed with exception:" + e.getMessage());
		}
		finally { 
			try { 
				bos.close(); 
			}
			catch (IOException e) { 
				e.printStackTrace(); 
			} 
		} 
		
		return output; 
	} 
}
