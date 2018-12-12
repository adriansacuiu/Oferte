package services.ppt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xslf.usermodel.XMLSlideShow;

import entities.DTO.Request;
import services.ppt.appender.PPTDataAppender;
import util.DataAppenderUtils;

/**
 * @author Adrian
 *
 */
public class PPTProcessor {
	
	private XMLSlideShow ppt;
	private String outputFileName;

	public PPTProcessor(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	/**
	 * Process the ppt.
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void processPPT(Request request) throws Exception
	{
		getInput(request.getRequestType().getInputFile());
		
		PPTDataAppender dataAppender = DataAppenderUtils.getDataAppender(ppt, request.getRequestType());
		dataAppender.proccesPPTBody(request);
		
		closeAndWriteToPPT();
	}
	
	/**
	 * Read from the input PPT file.
	 * 
	 * @param inputFile
	 * @throws Exception
	 */
	public void getInput(String inputFile) throws Exception
	{
		FileInputStream is = null;
		
		try {
			is = new FileInputStream(inputFile);
			ppt = new XMLSlideShow(is);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		finally {
			if(is != null)
			{
				is.close();
			}
		}
	}
	
	/**
	 * Writes the output to a PPT file.
	 * 
	 * @throws Exception
	 */
	public void closeAndWriteToPPT() throws Exception
	{
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(outputFileName);
	        ppt.write(out);
		}
		finally {
			try {
				if (out != null) {
					out.close();
				}
			}
			finally {
				if (ppt != null) {
					ppt.close();
				}
			}
		}
	}
}
