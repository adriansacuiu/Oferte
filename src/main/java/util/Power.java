package util;

public enum Power {
	mono37 (3.7, 230, 16),
	mono74 (7.4, 230, 32), 
	tri11 (11, 380, 16), 
	tri22 (22, 380, 32);
	
	private double power;
	private int voltage;
	private int current;
	
	private Power(double power, int voltage, int current) {
		this.power = power;
		this.voltage = voltage;
		this.current = current;
	}
	
	@Override
	public String toString()
	{
		return this.voltage + " V x " + this.current + " A";
	}
	
	public static String getPowerDetails(String power)
	{
		String result;
		
		switch (power) {
		case "3.7":
			result = mono37.toString();
			break;

		case "7.4":
			result = mono74.toString();
			break;
			
		case "11":
			result = tri11.toString();
			break;
		case "22":
			result = tri22.toString();
			break;
			
		default:
			result = "";
			break;
		}
		
		return result;
	}
	
	public static String getFormattedPowerString(double power)
	{
		String result;
		
		if(power > 10)
		{
			result = String.valueOf((int)power);
		}
		
		else {
			result = String.valueOf(power);
		}
		
		return result;
	}
}
