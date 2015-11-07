import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IllegalFormatException;
import java.util.List;


public class Calculator {

	private static final String EMPTY_STRING = "";
	private static final String COMMA = ",";
	private static final String NEW_LINE = "\n";
	
	private static final List<String> SEPARATORS = Collections.unmodifiableList(
		    new ArrayList<String>() {{
		        add(COMMA);
		        add(NEW_LINE);
		    }});

	public int add(String numbers){
		if(numbers == null || numbers.equals(EMPTY_STRING)){
			return 0;
		}
		if(!isValidInput(numbers)){
			throw new IllegalArgumentException("Illegal format");
		}
		
		List<String> values = Arrays.asList(numbers.split(",|\n"));
		int result = 0;
		for (String value : values) {
			int intValue = Integer.parseInt(value);
			result = result += intValue;
		}
		return result;
	}
	
	private boolean isValidInput(String numbers){
		boolean isValid = true;
		List<String> values = Arrays.asList(numbers.split(""));
		
		boolean lastValueIsNumber = false;
		for (String value : values) {
			//Valid characters
			if(!(SEPARATORS.contains(value) || isNumber(value))){
				isValid = false;
			}
			
			//Valid format
			if(!lastValueIsNumber){
				if(SEPARATORS.contains(value)){
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
