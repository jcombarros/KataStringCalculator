import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Calculator {
	
	private String numbers;
	private List<String> separators;

	private static final String EMPTY_STRING = "";
	private static final String COMMA = ",";
	private static final String NEW_LINE = "\n";
	private static final String SPLIT_LINE = "|";
	private static final int ZERO_NUMBER = 0;
	private static final int BIG_NUMBER = 1000;
	
	
	private static final String DELIMITTERS_PATTERN = "\\/\\/(\\[([^0-9]+)\\])+\n(.*)";
	private static final String DELIMITTERS_END = "\\]";
	
	private static final String NUMBERS_PATTERN_BEGGINGING = "[0-9]+([";
	private static final String NUMBERS_PATTERN_END = "]+[0-9]+)*";
	
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

		if(!(isValidInput() && checkSeparators())){
			throw new IllegalArgumentException("Illegal format");
		}
		
		boolean withNegativeNumbers = false;
		List<String> negativeNumbers = new ArrayList<String>();
		
		List<String> values = Arrays.asList(this.numbers.split(separatorsToString()));
		int result = 0;
		for (String value : values) {
			int intValue = Integer.parseInt(value);
			if(intValue <= BIG_NUMBER){
				result = result += intValue;
			}
			if(intValue < ZERO_NUMBER){
				withNegativeNumbers = true;
				negativeNumbers.add(value);
			}
		}
		
		if(withNegativeNumbers){
			throw new IllegalArgumentException(new StringBuilder("Negatives not allowed: ").append(negativeNumbers.toString()).toString());
		}
		
		resetSeparators();
		return result;
	}
	
	private boolean isValidInput(){
		String separatorsString = separatorsToString();
		Pattern pattern = Pattern.compile(new StringBuilder(NUMBERS_PATTERN_BEGGINGING).append(separatorsString).append(NUMBERS_PATTERN_END).toString());
		Matcher matcher;
		matcher = pattern.matcher(numbers);
	    if(!matcher.matches()){
	    	return false;
	    }
	    return true;
	}
	
	private boolean checkSeparators(){
		Pattern pattern = Pattern.compile(DELIMITTERS_PATTERN);
		Matcher matcher;

		if(this.numbers.startsWith("//")){
			matcher = pattern.matcher(numbers);
		    if(!matcher.matches()){
		    	return false;
		    }
		    String[] numberParts = numbers.split(NEW_LINE);
		    String separatorString = numberParts[0];
			separatorString = separatorString.substring(2, separatorString.length());
			List<String> delimiters = Arrays.asList(separatorString.split(DELIMITTERS_END));
		    for (String delimiter : delimiters) {
		    	separators.add(delimiter.substring(1, delimiter.length()));
			}
			
			this.numbers = numberParts[1];
		}
		
		return true;
		
	}

}
