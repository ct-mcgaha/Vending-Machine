package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String END_PROGRAM = "Quit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			END_PROGRAM };

	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT,
			PURCHASE_MENU_FINISH_TRANSACTION };
	private static final String NO_BUTTON = "That's not an option.";

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	Scanner userInput = new Scanner(System.in);
	int dollars = 0;
	int currentMoney = 0;
	BigDecimal cm = new BigDecimal("0.00");
	BigDecimal orderPrice = new BigDecimal("0.00");
	BigDecimal coinChange = new BigDecimal("0.00");
	BigDecimal quartersInADollar = new BigDecimal("4.00");
	DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();

	public void run() throws IOException {
		List<VendingMachine> items = new ArrayList<>();
		items = readFile();
		try (vmLogger testLog = new vmLogger("purchaseLog.txt")) {
			boolean shouldLoop = true;
			while (shouldLoop) {
				String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
				if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
					System.out.println(items);
				} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
					shouldLoop = false;
					while (!shouldLoop) {
						String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
						if (purchaseChoice.equals(PURCHASE_MENU_FEED_MONEY)) {
							System.out.println("Please insert full dollar amounts: $1, $5, $10, or $20 >>> ");
							int dollars = Integer.parseInt(userInput.nextLine());
							currentMoney += dollars;
							BigDecimal bigDollars = new BigDecimal(dollars);
							cm = cm.add(bigDollars);
							System.out.println("Current Money: $" + cm);
							testLog.write(dateTime.format(now) + "   " + PURCHASE_MENU_FEED_MONEY + "   $" + bigDollars
									+ "  $" + cm);
						} else if (purchaseChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
							System.out.println("Please choose the slot number item you'd like to purchase: ");
							System.out.println(items);
							String keyChoice = userInput.nextLine().toUpperCase();
							boolean found = false;
							for (VendingMachine slot : items) {
								if (slot.getSlot().equals(keyChoice)) {
									found = true;
									if (slot.getQuantity() > 0) {
										cm = cm.subtract(slot.getPrice());
										if (cm.intValue() > 0) {
											System.out.println(slot.getName() + ", $" + slot.getPrice() + ", $" + cm
													+ ", " + slot.purchaseMessage());
											slot.setQuantity(slot.getQuantity() - 1);
											testLog.write(dateTime.format(now) + "   " + slot.getName() + " "
													+ slot.getSlot());
										} else {
											System.out.println("Not enough money!");

										}

									} else {
										System.out.println("Out Of Stock!");
									}
									// testLog.write(dateTime.format(now) + PURCHASE_MENU_SELECT_PRODUCT);
								}

							}
							if (!found) {
								System.out.println(NO_BUTTON);
							}

						} else if (purchaseChoice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
							System.out.println("Thanks for using this ol' vending machine! Your change is: ");
							coinChange = (cm.subtract(orderPrice));
							System.out.println(coinChange);
							BigDecimal bigSingle = new BigDecimal("1.00");
							int quarter = 0;
							BigDecimal bigQuarter = new BigDecimal("0.25");
							int dime = 0;
							BigDecimal bigDime = new BigDecimal("0.10");
							int nickel = 0;
							BigDecimal bigNickel = new BigDecimal("0.05");
							BigDecimal zero = new BigDecimal("0.00");
							BigDecimal dollarChange = coinChange.remainder(bigSingle);
							BigDecimal quarterChange = coinChange.remainder(bigQuarter);
							BigDecimal dimeChange = coinChange.remainder(bigDime);
							BigDecimal nickelChange = coinChange.remainder(bigNickel);
							
								if (dollarChange.equals(zero)) {
									dollarChange.equals(bigQuarter.add(quartersInADollar));
								}
								if (quarterChange.equals(zero)) {
									quarter++;
									coinChange.subtract(bigQuarter);
								}
								if (dimeChange.equals(zero)) {
									dime++;
									coinChange.subtract(bigDime);
								}
								if (nickelChange.equals(zero)) {
									nickel++;
									coinChange.subtract(bigNickel);
								}
							//}
							System.out.println("your change is: " + quarter + " quarters " + dime + " dimes and "
									+ nickel + " nickels");
							shouldLoop = true;
							testLog.write(dateTime.format(now) + "   CHANGE:   " + coinChange);
							// System.out.println(coinChange);
						}
//				// do purchase
//				VendingMachine.purchase();
					}
				} else if (choice.equals(END_PROGRAM)) {
					System.out.println("Thank you!");
					System.exit(1);
				}
			}
		}
	}

	// while loop for change that keeps subtracting while there is still change
	// total

	public List<VendingMachine> readFile() {
		List<VendingMachine> invList = new ArrayList<>();

		try {
			File inventory = new File("vendingmachine.csv");
			Scanner fileScanner = new Scanner(inventory);
			while (fileScanner.hasNextLine()) {
				String invData = fileScanner.nextLine();
				String[] splitInvData = invData.split("\\|");
				String slot = splitInvData[0];
				String name = splitInvData[1];
				BigDecimal price = new BigDecimal(splitInvData[2]);
				String type = splitInvData[3];

				if (type.equals("Candy")) {
					Candy candy = new Candy(slot, name, price, 5);
					invList.add(candy);
				}
				if (type.equals("Chip")) {
					Chips chips = new Chips(slot, name, price, 5);
					invList.add(chips);
				}
				if (type.equals("Gum")) {
					Gum gum = new Gum(slot, name, price, 5);
					invList.add(gum);
				}
				if (type.equals("Drink")) {
					Drinks drinks = new Drinks(slot, name, price, 5);
					invList.add(drinks);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}

		// System.out.println(invList); // for debugging purposes only

		return invList;
	}

	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

}