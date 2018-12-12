package util;

/**
 * @author Adrian
 *
 */
public class CarUtils {
	
	
	/**
	 * Get the optimal charging power of the charging station based on the car's charging rate.
	 * 
	 * @param chargeRate
	 * @return
	 */
	public static double getOptimalChargeRate(double chargeRate)
	{
		double result;
		
		if(chargeRate <= 3.7)
		{
			result = 3.7;
		}
		
		else if(chargeRate <= 7.4)
		{
			result = 7.4;
		}
		else if(chargeRate <= 11)
		{
			result = 11;
		}
		
		else {
			result = 22;
		}
		
		return result;
	}
}
