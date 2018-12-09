package test;
import java.util.ArrayList;
import java.util.List;

import entities.Car;
import entities.ChargeRate;
import entities.Client;
import entities.DTO.Request;
import services.ppt.DataAppender;


public class SimpleTest {

	public static void main(String[] args) throws Exception {
		String inputFileName = "target/ppt/input.pptx";
		String outputFileName = "target/ppt/output.pptx";
		
        DataAppender dataAppender = new DataAppender(inputFileName, outputFileName); 
        Client client = new Client("Siemens SRL", "Andrei", "Gheorge", "+40 754 432 245", "andreigheorghe@yahoo.com", "");
       
        Car car = new Car("Volkswage", "E-Golf", "https://ev-database.uk/car/1087/Volkswagen-e-Golf#charge-table");
        List<ChargeRate> chargeRates = new ArrayList<ChargeRate>(2);
        chargeRates.add(new ChargeRate(7.2));
        car.setChargeRates(chargeRates);
        
        Request request = new Request(client, null, 0, car, null);
        
        dataAppender.processPPT(request);
	}
}
