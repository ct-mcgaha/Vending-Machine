package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class GumTest {

	@Test
	public void chew_chew_yum() {
		Gum gum = new Gum();
		
		String expected = "Chew Chew, Yum!";
		String actual = gum.purchaseMessage();
		
		assertEquals(expected, actual);
	}

}
