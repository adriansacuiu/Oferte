package entities.DTO;

public enum RequestType {
	
	HOMELINE("target/ppt/input/input home line.pptx");
	
	private String inputFile;

	private RequestType(String inputFile) {
		this.inputFile = inputFile;
	}

	public String getInputFile() {
		return inputFile;
	}
}
