package util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;


public class OperationsUtils {
	private static final Logger logger = Logger.getLogger(OperationsUtils.class);
	
	public static void validateImage(MultipartFile image) {
		logger.info("Inside validateImage method.");
		
		if (!image.getContentType().equals("image/jpeg")) {
			throw new ImageUploadException("Only JPG images accepted.");
		}
	}

	public static void saveImage(String username, MultipartFile image) throws ImageUploadException {
		logger.info("Inside saveImage method.");
		
		try {
			File userFolder = new File(Constants.IMAGES_FOLDER + username);
			userFolder.mkdirs();
			userFolder = new File(userFolder, "/" + username + ".jpg");
			FileUtils.writeByteArrayToFile(userFolder, image.getBytes());
			
		} catch (IOException e) {
			logger.error("in saveImage method IOException: " + e.getMessage() + ";Cause: " + e.getCause());
			throw new ImageUploadException("Unable to save image.", e);
		}
	}
	
	public static boolean deleteDir(String username) throws Exception{
		logger.info("Inside deleteDirString method.");
		File userFolder = null;
		
		try{
			userFolder = new File(Constants.IMAGES_FOLDER + username);
		} catch (Exception e) {
			logger.error("in deleteDirString method Exception: " + e.getMessage() + ";Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		} 
		
		return deleteDir(userFolder);
	}
	
	public static boolean deleteDir(File userFolder) throws Exception{
		logger.info("Inside deleteDir method.");
		boolean result = false;
		
	    try {
			if (userFolder.exists() && userFolder.isDirectory()) {
			    String[] children = userFolder.list();
			    for (int i = 0; i < children.length; i++) {
			        boolean success = deleteDir(new File(userFolder, children[i]));
			        if (!success) {
			            return false;
			        }
			    }
			}
			
			result = userFolder.delete();
			
		} catch (Exception e) {
			logger.error("in deleteDir method Exception: " + e.getMessage() + ";Cause: " + e.getCause());
			e.printStackTrace();
			throw e;
		} 
	    
	    return result; 
	}
}
