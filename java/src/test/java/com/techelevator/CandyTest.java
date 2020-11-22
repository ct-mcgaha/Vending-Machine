package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class CandyTest {

	@Test
<<<<<<< HEAD
	public void candy_munch_munch_yum() {
		Candy candy = new Candy();
		String expected = "Munch Munch, Yum!";
		
=======
	public void does_candy_return_purchase_method() {
		Candy candy = new Candy();
		String expected = "Munch Munch, Yum!";
		 
>>>>>>> caec782721d1817dbbcc32fa4aebf117531d1bad
		String actual = candy.purchaseMessage();
		
		assertEquals(expected, actual);
	}

}
