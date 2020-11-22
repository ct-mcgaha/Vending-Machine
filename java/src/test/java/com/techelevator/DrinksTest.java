package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class DrinksTest {

	@Test
	public void drinks_glug_glug_yum() {
		Drinks drinks = new Drinks();
		
		String expected = "Glug Glug, Yum!";
		String actual = drinks.purchaseMessage();
		
		assertEquals(expected, actual);
	}

}
