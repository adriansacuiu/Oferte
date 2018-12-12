package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xddf.usermodel.text.XDDFTextParagraph;
import org.apache.poi.xddf.usermodel.text.XDDFTextRun;
import org.apache.poi.xslf.usermodel.XMLSlideShow;

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
}
