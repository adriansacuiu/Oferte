package test;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xddf.usermodel.text.XDDFTextRun;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import entities.Client;
import entities.DTO.Request;
import services.ppt.DataAppender;


public class SimpleTest {

	public static void main(String[] args) throws Exception {
		String inputFileName = "target/ppt/input.pptx";
		String outputFileName = "target/ppt/output.pptx";
		
        DataAppender dataAppender = new DataAppender(inputFileName, outputFileName); 
        Client client = new Client("Siemens SRL", "Cristi", "Șova", "+40 754 432 245", "cristisova@yahoo.com", "");
        Request request = new Request(client, null, null);
        
        dataAppender.processPPT(request);
	}
}
