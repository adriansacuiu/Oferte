package services.ppt.appender.impl;

import java.util.List;
import java.util.regex.Matcher;

import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xddf.usermodel.text.XDDFTextParagraph;
import org.apache.poi.xddf.usermodel.text.XDDFTextRun;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import entities.Car;
import entities.Client;
import entities.Product;
import entities.DTO.Request;
import services.ppt.appender.PPTDataAppender;
import util.CarUtils;
import util.ClientInfo;
import util.DataAppenderUtils;

/**
 * @author Adrian
 *
 */
public class HomeLineDataAppender implements PPTDataAppender {
	private XMLSlideShow ppt;
	
	public HomeLineDataAppender(XMLSlideShow ppt) {
		this.ppt = ppt;
	}
	
	public void proccesPPTBody(Request request) throws Exception
	{
		// MODIFICA
		//processWelcomePage(request.getClient());
		//proccesChargingRecomandationsPage(request.getCar(), 2);
		proccesHomeLineWithSocketPage(request.getProducts(), 4);
	}
	
	/**
	 * Replaces the placeholders in the first page with the client's information.
	 */
	public void processWelcomePage(Client client)
	{
        List<XSLFShape> shapes = ppt.getSlides().get(0).getShapes();
        XDDFTextBody clientInfoBody = ((XSLFTextShape) shapes.get(2)).getTextBody();
        
        String clientName = client.getFirstName() + " " + client.getLastName();
        
		clientInfoBody = DataAppenderUtils.appendClientContactInfo(client.getCompanyName(), clientName, 
				client.getPhone(), client.getEmail(), clientInfoBody);

		System.out.println("Welcome page finished...");
	}
	
	/**
	 * Replaces the placeholders in the charging recomandations page with the client's car data.
	 * 
	 * @param car
	 * @param pageNumber
	 */
	public void proccesChargingRecomandationsPage (Car car, int pageNumber)
	{
		List<XSLFShape> shapes = ppt.getSlides().get(pageNumber).getShapes();
		List<XDDFTextParagraph> paragraphs = ((XSLFTextShape)shapes.get(0)).getTextBody().getParagraphs();
		double chargeRate = car.getChargeRates().get(0).getChargeRate();
		
		String placeholderText;
		Matcher placeholderMatcher;
		for(XDDFTextParagraph paragraph : paragraphs){
			for(XDDFTextRun textRun : paragraph.getTextRuns())
			{
				placeholderText = textRun.getText().trim();
				placeholderMatcher = DataAppenderUtils.getPlaceholderMatcher(placeholderText);
				
				if(placeholderMatcher != null)
				{
					placeholderText = placeholderMatcher.group();
					switch (ClientInfo.getClientInfo(placeholderText.toLowerCase())) {
					
					case CAR:
						DataAppenderUtils.replacePlaceholder(textRun, placeholderText,
								car.getBrand() + " " + car.getModel());
						break;
						
					case POWER:
						DataAppenderUtils.replacePlaceholder(textRun, placeholderText,
								String.valueOf(chargeRate));
						break;	
						
					case POWER2:
						DataAppenderUtils.replacePlaceholder(textRun, placeholderText,
								String.valueOf(CarUtils.getOptimalChargeRate(chargeRate)));
						break;	
					
					case SOURCE:
						textRun.setText(" " + car.getLink());
						break;	
						
					default:
						break;
					}
				}
			}
		}
		
		System.out.println("Charging recomandations page finished...");
	}
	
	/**
	 * Adds the products in the table.
	 * 
	 * @param productIds
	 * @param pageNumber
	 * 
	 * @throws Exception
	 */
	public void proccesHomeLineWithSocketPage(List<String> productIds, int pageNumber) throws Exception
	{
		//productIds = DataAppenderUtils.removeStationsWithCableConnector(productIds);
		
		List<XSLFShape> shapes = ppt.getSlides().get(pageNumber).getShapes();
		XSLFTable table = null;
		
		for(XSLFShape shape : shapes)
		{
			if(shape instanceof XSLFTable)
			{
				table = (XSLFTable)shape;
				break;
			}
		}
		
		if(table == null)
		{
			throw new Exception("No table found");
		}
		
		// MODIFICA
		Product product = new Product("H3321-0020", "Home Line", "Autostart", 22, 1, "priză", 970, 0, null);
		
		DataAppenderUtils.addNewTableRow(table, product);
	}
}
