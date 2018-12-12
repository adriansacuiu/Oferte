package util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.poi.sl.usermodel.TableCell.BorderEdge;
import org.apache.poi.sl.usermodel.TextParagraph.TextAlign;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xddf.usermodel.text.XDDFTextParagraph;
import org.apache.poi.xddf.usermodel.text.XDDFTextRun;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;

import entities.Product;
import entities.DTO.RequestType;
import services.ppt.appender.PPTDataAppender;
import services.ppt.appender.impl.HomeLineDataAppender;

public class DataAppenderUtils {
	
	public static PPTDataAppender getDataAppender(XMLSlideShow ppt, RequestType requestType) throws Exception
	{
		switch (requestType) {
		
		case HOMELINE:
			return new HomeLineDataAppender(ppt);

		default:
			throw new Exception("Can't find type of data appender.");
		}
	}
	
	/**
	 * Appends the client's data into the client info text box.
	 * 
	 * @param clientCompany
	 * @param clientName
	 * @param clientPhone
	 * @param clientEmail
	 * @param clientInfoBody
	 * 
	 * @return
	 */
	public static XDDFTextBody appendClientContactInfo(String clientCompany, String clientName, String clientPhone, 
			String clientEmail, XDDFTextBody clientInfoBody) {
		
		List<Integer> clientInfoParagraphsToRemove = new ArrayList<Integer>(4);
		String placeholderText;
		Matcher placeholderMatcher;
		
		for(XDDFTextParagraph paragraph : clientInfoBody.getParagraphs())
		{
			for(XDDFTextRun textRun : paragraph.getTextRuns())
			{
				placeholderText = textRun.getText().trim();
				placeholderMatcher = DataAppenderUtils.getPlaceholderMatcher(placeholderText);
				
				if(placeholderMatcher != null)
				{
					placeholderText = placeholderMatcher.group();
					switch (ClientInfo.getClientInfo(placeholderText.toLowerCase())) 
					{
					
					case COMPANY:
						checkAndReplaceClientInfoPlaceholder(textRun, clientInfoParagraphsToRemove, 
								ClientInfo.COMPANY, placeholderText, clientCompany);
						break;
						
					case NAME:
						checkAndReplaceClientInfoPlaceholder(textRun, clientInfoParagraphsToRemove, 
								ClientInfo.NAME, placeholderText, clientName);
						break;
						
					case PHONE:
						checkAndReplaceClientInfoPlaceholder(textRun, clientInfoParagraphsToRemove, 
								ClientInfo.PHONE, placeholderText, clientPhone);
						break;
						
					case EMAIL:
						checkAndReplaceClientInfoPlaceholder(textRun, clientInfoParagraphsToRemove, 
								ClientInfo.EMAIL, placeholderText, clientEmail);
						break;
						
					default:
						break;
					}
				}
			}
		}
        
        Collections.sort(clientInfoParagraphsToRemove, Collections.reverseOrder());
        for (int i = 0; i < clientInfoParagraphsToRemove.size(); i++) {
            clientInfoBody.removeParagraph(clientInfoParagraphsToRemove.get(i));
        }
        
        return clientInfoBody;
	}
	
	/**
	 * Check if the client info is available and replace the placeholder with the actual data if it exists. If not, add the 
	 * paragraph on the removal list.
	 * 
	 * @param textRun
	 * @param clientInfoParagraphsToRemove
	 * @param clientInfo
	 * @param placeholderText
	 * @param clientData
	 */
	private static void checkAndReplaceClientInfoPlaceholder(XDDFTextRun textRun, List<Integer> clientInfoParagraphsToRemove, 
			ClientInfo clientInfo, String placeholderText, String clientData)
	{
		if ((clientData != null) && ("" != clientData)) {
			DataAppenderUtils.replacePlaceholder(textRun, placeholderText,
					clientData);
		}
		else 
		{
			clientInfoParagraphsToRemove.add(ClientInfo.COMPANY.getParagraphNumber());
		}
	}
	
	/**
	 * Replace the placeholder text with the actual value.
	 * 
	 * @param textRun
	 * @param placeholderText
	 * @param actualText
	 */
	public static void replacePlaceholder(XDDFTextRun textRun, String placeholderText, String actualText)
	{
		textRun.setText(textRun.getText().replaceFirst(placeholderText, actualText));
	}
	
	/**
	 * Check if there is a placeholder in the text and if it exists then it returns a matcher.
	 * 
	 * @param text
	 * @return
	 */
	public static Matcher getPlaceholderMatcher(String text)
	{
		Pattern pattern = Pattern.compile("placeholder_\\w+", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(text);
	    
	    if (matcher.find()) {
	    	return matcher;
	    }
	    else 
	    {
	    	return null;
		}
	}
	
	/**
	 * Remove the productsIds from the list the charging stations that have cable connector.
	 * 
	 * @param productIds
	 * @return
	 */
	public static List<String> removeStationsWithCableConnector(List<String> productIds)
	{
		return productIds.stream()
				.filter(productId -> hasCableConnector(productId))
				.collect(Collectors.toList());
	}
	
	/**
	 * Remove the productsIds from the list the charging stations that have socket connector.
	 * 
	 * @param productIds
	 * @return
	 */
	public static List<String> removeStationsWithSocketConnector(List<String> productIds)
	{
		return productIds.stream()
			.filter(productId -> !hasCableConnector(productId))
			.collect(Collectors.toList());
	}
	
	/**
	 * Check if the station with the given product is has cable connector or not.
	 * 
	 * @param productId
	 * @return
	 */
	private static boolean hasCableConnector(String productId)
	{
		Pattern pattern = Pattern.compile("(?<=\\w-)+\\w+");
	    Matcher matcher = pattern.matcher(productId);
	    matcher.find();
	    
	    return matcher.group().length() == 5;
	}
	
	/**
	 * Adds a new row to the table that contains the products details.
	 * 
	 * @param table
	 * @param product
	 * @return
	 */
	public static XSLFTable addNewTableRow(XSLFTable table, Product product)
	{
		XSLFTableRow placeholderRow = table.getRows().get(1);
		XSLFTableRow newRow = table.addRow();
		newRow.setHeight(placeholderRow.getHeight());
		
		XSLFTableCell newCell;
		XSLFTableCell placeholderCell;
		
		for(int i = 0; i < placeholderRow.getCells().size(); i++)
		{
			placeholderCell = placeholderRow.getCells().get(i);
			
			newCell = newRow.addCell();
			newCell.setFillColor(Color.WHITE);
			newCell.setVerticalAlignment(VerticalAlignment.MIDDLE);
			
			setCellBorders(placeholderCell, newCell, i, placeholderRow.getCells().size());
			appendCellTexts(newCell, placeholderCell, product);
		}
		
		table.getCTTable().getTrList().remove(0);
		
		return table;
	}

	/**
	 * Append the text for a cell in the row.
	 * 
	 * @param newCell
	 * @param placeholderCell
	 * @param product
	 */
	private static void appendCellTexts(XSLFTableCell newCell, XSLFTableCell placeholderCell, Product product) {
		XSLFTextParagraph newTextParagraph;
		XSLFTextRun newTextRun;
		for(XSLFTextParagraph placeholderParagraph : placeholderCell.getTextParagraphs())
		{
			newTextParagraph = newCell.addNewTextParagraph();
			newTextParagraph.setTextAlign(TextAlign.CENTER);
			
			for(XSLFTextRun placeholderTextRun : placeholderParagraph.getTextRuns())
			{
				newTextRun = newTextParagraph.addNewTextRun();
				appendCellText(placeholderTextRun, newTextRun, product);
			}
		}
	}
	
	/**
	 * Append the text in the textRun.
	 * 
	 * @param placeholderTextRun
	 * @param newTextRun
	 * @param product
	 */
	private static void appendCellText(XSLFTextRun placeholderTextRun, XSLFTextRun newTextRun, Product product)
	{
		StringBuffer sb;
		String power;
		
		switch (ProductInfo.getProductInfo(placeholderTextRun.getRawText())) 
		{
			case CODE:
				newTextRun.setText(product.getProductId());
				break;
			
			case PRODUCT:
				sb = new StringBuffer();
				power = Power.getFormattedPowerString(product.getPower());
				sb.append(product.getModel()).append(" ").append(product.getModelType()).append("\n")
					.append(product.getNumberOfConnectors()).append(" x ").append(power).append(" kW");
				newTextRun.setText(sb.toString());
				break;	
				
			case INPUT:
				sb = new StringBuffer();
				power = Power.getFormattedPowerString(product.getPower());
				
				if(product.getNumberOfConnectors() == 1)
				{
					sb.append("1 cablu");
				}
				else 
				{
					sb.append("2 cabluri");
				}
				
				sb.append("\n").append("x ").append(power).append(" kW").append("\n")
					.append("(").append(Power.getPowerDetails(power)).append(")");
				
				newTextRun.setText(sb.toString());
				break;
				
			case OUTPUT:
				sb = new StringBuffer();
				power = Power.getFormattedPowerString(product.getPower());
				
				if(product.getNumberOfConnectors() == 1)
				{
					if(product.getConnectorType() == "cablu")
					{
						sb.append("1 cablu");
					} 
					else 
					{
						sb.append("1 priză");
					}
						
				}
				else 
				{
					if(product.getConnectorType() == "cablu")
					{
						sb.append("2 cabluri");
					} 
					else 
					{
						sb.append("2 prize");
					}
				}
				
				sb.append(" Type 2").append("\n").append("x ").append(power).append(" kW").append("\n")
					.append("(").append(Power.getPowerDetails(power)).append(")");
				
				newTextRun.setText(sb.toString());
				break;
				
			case PRICE:
				newTextRun.setText(product.getPrice() + " €");
				break;
				
			default:
				break;
		}
		
		
		newTextRun.setFontFamily(placeholderTextRun.getFontFamily());
		newTextRun.setFontSize(placeholderTextRun.getFontSize());
	}
		
	/**
	 * Set up the borders for the new row.
	 * 
	 * @param placeholderCell
	 * @param newCell
	 * @param position
	 * @param maxCells
	 */
	private static void setCellBorders(XSLFTableCell placeholderCell, XSLFTableCell newCell, int position, int maxCells)
	{
		newCell.setBorderColor(BorderEdge.top, placeholderCell.getBorderColor(BorderEdge.top));
		newCell.setBorderStyle(BorderEdge.top, placeholderCell.getBorderStyle(BorderEdge.top));

		if(position > 0)
		{
			newCell.setBorderColor(BorderEdge.left, placeholderCell.getBorderColor(BorderEdge.top));
			newCell.setBorderStyle(BorderEdge.left, placeholderCell.getBorderStyle(BorderEdge.top));
		}

		if(position < maxCells - 1)
		{
			newCell.setBorderColor(BorderEdge.right, placeholderCell.getBorderColor(BorderEdge.top));
			newCell.setBorderStyle(BorderEdge.right, placeholderCell.getBorderStyle(BorderEdge.top));
		}
	}
}
