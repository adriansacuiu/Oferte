package services.ppt.appender;

import entities.DTO.Request;

public interface PPTDataAppender {
	
	/**
	 * Process the ppt's body.
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void proccesPPTBody(Request request) throws Exception;
}
