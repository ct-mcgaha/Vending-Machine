package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChipsTest {

	@Test
	public void candy_munch_munch_yum() {
		Chips chips = new Chips();
		String expected = "Crunch Crunch, Yum!";
		
		String actual = chips.purchaseMessage();
		
		assertEquals(expected, actual);

}
}
