package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given
    	int a = 100;
    	int b = 22;
    	double expected = 2200;
        //when
    	double actual = payroll.calculatePaycheck(a, b);
    	
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given
    	int a = 10;
    	double expected = 5.75;
        //when
    	double actual = payroll.calculateMileageReimbursement(a);
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given
    	String name = "hi";
    	double wage = 10;
    	String expected = "Hello hi, We are pleased to offer you an hourly wage of 10.0";
        //when
    	String actual = payroll.createOfferLetter(name, wage);
    	System.out.println(actual);
    	System.out.println(expected);
        //then
    	assertEquals(expected, actual);
    }

}