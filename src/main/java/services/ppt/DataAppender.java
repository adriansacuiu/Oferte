package services.ppt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xddf.usermodel.text.XDDFTextParagraph;
import org.apache.poi.xddf.usermodel.text.XDDFTextRun;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import entities.Car;
import entities.Client;
import entities.DTO.Request;
import util.CarUtils;

public class DataAppender {
	
	private XMLSlideShow ppt;
	private String inputFileName;
	private String outputFileName;

	public DataAppender(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
	}

	/**
	 * Read from the input PPT file.
	 * 
	 * @throws Exception
	 */
	public void getInput() throws Exception
	{
		FileInputStream is = null;
		
		try {
			is = new FileInputStream(inputFileName);
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

	public void processPPT(Request request) throws Exception
	{
		getInput();
		
		////////
		processWelcomePage(request.getClient());
		proccesChargingRecomandationsPage(request.getCar(), 2);
		////////
		
		closeAndWriteToPPT();
	}
	
	/**
	 * Replaces the placeholders in the first page with the client's information.
	 */
	public void processWelcomePage(Client client)
	{
        List<XSLFShape> shapes = ppt.getSlides().get(0).getShapes();
        XDDFTextBody clientInfoBody = ((XSLFTextShape) shapes.get(2)).getTextBody();
        
        String clientName = client.getFirstName() + " " + client.getLastName();
        
		clientInfoBody = appendClientInfo(client.getCompanyName(), clientName, client.getPhone(), client.getEmail(), clientInfoBody);
		
		System.out.println("Welcome page finished...");
	}
	
	public void proccesChargingRecomandationsPage (Car car, int pageNumber)
	{
		List<XSLFShape> shapes = ppt.getSlides().get(pageNumber).getShapes();
		List<XDDFTextParagraph> paragraphs = ((XSLFTextShape)shapes.get(0)).getTextBody().getParagraphs();
		double chargeRate = car.getChargeRates().get(0).getChargeRate();
		
		for(XDDFTextParagraph paragraph : paragraphs){
			for(XDDFTextRun textRun : paragraph.getTextRuns())
			{
				String text = textRun.getText().trim().toLowerCase();
				switch (text) {
				
				case "placeholder_masina":
					textRun.setText(car.getBrand() + " " + car.getModel() + " ");
					break;
					
				case "placeholder_putere":
					textRun.setText(String.valueOf(chargeRate) + " ");
					break;	
					
				case "placeholder_putere2":
					textRun.setText(String.valueOf(CarUtils.getOptimalChargeRate(chargeRate)));
					break;	
				
				case "placeholder_sursa":
					textRun.setText(" " + car.getLink());
					break;	
					
				default:
					break;
				}
			}
		}
		
		System.out.println("Charging recomandations page finished...");
	}

	/**
	 * Appends the client's data into the client info text box.
	 * 
	 * @param clientCompanyName
	 * @param clientName
	 * @param clientPhone
	 * @param clientEmail
	 * @param clientInfoBody
	 * 
	 * @return
	 */
	private XDDFTextBody appendClientInfo(String clientCompanyName, String clientName, String clientPhone, String clientEmail, XDDFTextBody clientInfoBody) {
		List<Integer> clientInfoParagraphsToRemove = new ArrayList<Integer>(4);
		List<XDDFTextRun> textRuns;
		
		if ((clientCompanyName != null) && ("" != clientCompanyName)) {
            textRuns = clientInfoBody.getParagraph(0).getTextRuns();
            textRuns.get(textRuns.size() - 1).setText(clientCompanyName);
            
        } else {
            clientInfoParagraphsToRemove.add(0);
        }

        if ((clientName != null) && ("" != clientName)) {
            textRuns = clientInfoBody.getParagraph(1).getTextRuns();
            textRuns.get(textRuns.size() - 1).setText(clientName);
            
        } else {
            clientInfoParagraphsToRemove.add(1);
        }

        if ((clientPhone != null) && ("" != clientPhone)) {
            textRuns = clientInfoBody.getParagraph(2).getTextRuns();
            textRuns.get(textRuns.size() - 1).setText(clientPhone);
            
        } else {
            clientInfoParagraphsToRemove.add(2);
        }

        if ((clientEmail != null) && ("" != clientEmail)) {
            textRuns = clientInfoBody.getParagraph(3).getTextRuns();
            textRuns.get(textRuns.size() - 1).setText(clientEmail);
            
        } else {
            clientInfoParagraphsToRemove.add(3);
        }
        
        Collections.sort(clientInfoParagraphsToRemove, Collections.reverseOrder());
        for (int i = 0; i < clientInfoParagraphsToRemove.size(); i++) {
            clientInfoBody.removeParagraph(clientInfoParagraphsToRemove.get(i));
        }
        
        return clientInfoBody;
	}
}
