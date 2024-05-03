package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

    DeliveryDriver deliveryDriver;
    String name = "Joe";
    
    @Mock
    CellPhone phone;
    
    @Mock
    Car car;
    
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	
    	deliveryDriver= new DeliveryDriver(name, car, phone);
    }

    @Test
    void itShouldWasteTime() {
    	boolean expected = true;
        //given
    	when(phone.browseCatMemes()).thenReturn(true);
        //when
    	boolean actual = deliveryDriver.wasteTime();
        //then
    	assertEquals(expected,actual);
    }

    @Test
    void itShouldRefuel() {
    	boolean expected = true;
    	int octane = 85;
        //given
    	when(car.fillTank(octane)).thenReturn(true);
        //when
    	boolean actual = deliveryDriver.refuel(octane);
        //then
    	assertEquals(expected,actual);
    }

    @Test
    void itShouldContactCustomer() {
    	boolean expected = true;
    	String phoneNum = "6084312828";
        //given
    	when(phone.call(phoneNum)).thenReturn(true);
        //when
    	boolean actual = deliveryDriver.contactCustomer(phoneNum);
        //then
    	assertEquals(expected,actual);
    }

}