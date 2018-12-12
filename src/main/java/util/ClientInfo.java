package util;

/**
 * @author Adrian
 *
 */
public enum ClientInfo {
	COMPANY(0), NAME(1), PHONE(2), EMAIL(3), CAR, POWER, POWER2, SOURCE;
	
	private int paragraphNumber;
	
	private ClientInfo() {
	}
	
	private ClientInfo(int paragraphNumber)
	{
		this.paragraphNumber = paragraphNumber;
	}
	
	@Override
	public String toString() 
	{
		return name().toLowerCase();
	}
	
	public static ClientInfo getClientInfo(String clientInfo)
	{
		return ClientInfo.valueOf(clientInfo.replace("placeholder_", "").toUpperCase());
	}

	public int getParagraphNumber() {
		return paragraphNumber;
	}
}
