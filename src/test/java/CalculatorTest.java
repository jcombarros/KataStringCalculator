import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CalculatorTest {
	
	private Calculator calculator;
	
	@Before
	public void before(){
		calculator = new Calculator();
	}
	
	@Test
	public void emptyStringInputTest(){
		assertEquals(0, calculator.add(""));
	}
	
	@Test
	public void oneNumberStringInputTest(){
		assertEquals(1, calculator.add("1"));
	}
	
	@Test
	public void twoNumbersStringInputTest(){
		assertEquals(2, calculator.add("1,1"));
	}
	
	@Test
	public void nullStringInputTest(){
		assertEquals(0, calculator.add(null));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void charactersStringInputTest(){
		calculator.add("a");
	}
	
	@Test
	public void threeNumberesStringInputTest(){
		assertEquals(3, calculator.add("1,1,1"));
	}
	
	@Test
	public void newLineSeparatorTest(){
		assertEquals(3, calculator.add("1\n1,1"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void newLineSeparatorInvalidFormatTest(){
		calculator.add("1,\n");
	}
	
	@Test
	public void changeSeparatorTest(){
		assertEquals(3, calculator.add("//[;]\n1;2"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void negativeNumberInputTest(){
		calculator.add("1,-1,1");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void negativeSimbolInputTest(){
		calculator.add("1,-,1");
	}
	
	@Test
	public void BigNumbersInputTest(){
		assertEquals(2, calculator.add("1,1001,1"));
	}
	
	@Test
	public void BigNumbers2InputTest(){
		assertEquals(1002, calculator.add("1,1000,1"));
	}
	
	@Test
	public void BigNumbers3InputTest(){
		assertEquals(1002, calculator.add("//[;]\n1;1000;1"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void BigNumbers4InputTest(){
		assertEquals(2, calculator.add("//[;]\n1;-1001;1"));
	}
	
	
	@Test
	public void multipleSeparatorTest(){
		assertEquals(3, calculator.add("//[qqq]\n1qqq2"));
	}

}
