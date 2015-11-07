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

}
