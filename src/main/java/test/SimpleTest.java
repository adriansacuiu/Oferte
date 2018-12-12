package test;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.Car;
import entities.ChargeRate;
import entities.Client;
import entities.DTO.Request;
import entities.DTO.RequestType;
import services.ppt.PPTProcessor;


public class SimpleTest {

	public static void main(String[] args) throws Exception {
		test();
	}

	private static void test() throws Exception {
		String outputFileName = "target/ppt/output/output home line.pptx";
		
        PPTProcessor dataAppender = new PPTProcessor(outputFileName); 
        Client client = new Client("Siemens SRL", "Andrei", "Gheorge", "+40 754 432 245", "andreigheorghe@yahoo.com", "");
       
        Car car = new Car("Volkswagen", "E-Golf", "https://ev-database.uk/car/1087/Volkswagen-e-Golf#charge-table");
        List<ChargeRate> chargeRates = new ArrayList<ChargeRate>(2);
        chargeRates.add(new ChargeRate(7.2));
        car.setChargeRates(chargeRates);
        
        Request request = new Request(client, null, 0, car, RequestType.HOMELINE, null);
        
        dataAppender.processPPT(request);
	}
	
	public static void printMatches(String text, String regex) {
	    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(text);
	    
	    // Check all occurrences
	    if (matcher.find()) {
	        System.out.print("Start index: " + matcher.start());
	        System.out.print(" End index: " + matcher.end());
	        System.out.println(" Found: " + matcher.group());
	    }
	    
	    else {
			System.out.println("Not found.");
		}
	}
}
