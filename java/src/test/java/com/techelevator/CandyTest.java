package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class CandyTest {

	@Test
	public void candy_munch_munch_yum() {
		Candy candy = new Candy();
		String expected = "Munch Munch, Yum!";
		
		String actual = candy.purchaseMessage();
		
		assertEquals(expected, actual);
	}

}
