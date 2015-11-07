import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Calculator {
	
	private String numbers;
	private List<String> separators;

	private static final String EMPTY_STRING = "";
	private static final String COMMA = ",";
	private static final String NEW_LINE = "\n";
	private static final String SPLIT_LINE = "|";
	
	public Calculator(){
		separators = new ArrayList<String>();
		resetSeparators();
	}

	public List<String> getSeparators() {
		return separators;
	}

	public void setSeparators(List<String> separators) {
		this.separators = separators;
	}
	
	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	private void resetSeparators(){
		separators.clear();
		separators.add(COMMA);
		separators.add(NEW_LINE);
	}
	
	private String separatorsToString(){
		StringBuilder separatorsStringBuilder = new StringBuilder();
		String  separatorsString = "";
		for (String separator : separators) {
			separatorsStringBuilder.append(separator);
			separatorsStringBuilder.append(SPLIT_LINE);
		}
		
		separatorsString = separatorsStringBuilder.toString();
		if(!EMPTY_STRING.equals(separatorsString)){
			separatorsString = separatorsString.substring(0, separatorsString.length() - 1);
		}
		return separatorsString.toString();
		
	}

	public int add(String numbers){
		this.numbers = numbers;
		if(numbers == null || numbers.equals(EMPTY_STRING)){
			return 0;
		}
		if(!isValidInput()){
			throw new IllegalArgumentException("Illegal format");
		}
		
		List<String> values = Arrays.asList(this.numbers.split(separatorsToString()));
		int result = 0;
		for (String value : values) {
			int intValue = Integer.parseInt(value);
			result = result += intValue;
		}
		
		resetSeparators();
		return result;
	}
	
	private boolean isValidInput(){
		if(this.numbers.startsWith("//")){
			separators.add(this.numbers.substring(2, 3));
			this.numbers = this.numbers.substring(4, this.numbers.length());
		}
		
		
		boolean isValid = true;
		List<String> values = Arrays.asList(this.numbers.split(""));
		
		boolean lastValueIsNumber = false;
		for (String value : values) {
			//Valid characters
			if(!(separators.contains(value) || isNumber(value))){
				isValid = false;
			}
			
			//Valid format
			if(!lastValueIsNumber){
				if(separators.contains(value)){
					isValid = false;
				}
			}
			lastValueIsNumber = isNumber(value);
		}
		return isValid;
	}
	
	private boolean isNumber(String value){
		try{
			Integer.parseInt(value);
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}

}
