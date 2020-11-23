package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class CandyTest {

	
	public void does_candy_return_purchase_method() {
		Candy candy = new Candy();
		String expected = "Munch Munch, Yum!";
		 
		String actual = candy.purchaseMessage();
		
		assertEquals(expected, actual);
	}

}
