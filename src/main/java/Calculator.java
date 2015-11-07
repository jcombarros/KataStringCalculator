import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.List;


public class Calculator {

	private static final Object EMPTY_STRING = "";

	public int add(String numbers){
		if(numbers == null || numbers.equals(EMPTY_STRING)){
			return 0;
		}
		List<String> values = Arrays.asList(numbers.split(","));
		if(values.size() > 2){
			throw new IllegalArgumentException("The amount of numbers entered, is invalid.");
		}
		int result = 0;
		for (String value : values) {
			int intValue = Integer.parseInt(value);
			result = result += intValue;
		}
		return result;
	} 

}
