import java.util.*;

public class ClothingStoreTest {

	public static void main(String[] args) {

		// ---------------------------------------- Testing ClothingType.java ------------------------------------

		System.out.println("Testing ClothingType.java");

		// Can't make a new one, use dot operator to access clothing types
		ClothingType clothingTypeTest = ClothingType.PANTS; 
		System.out.println(clothingTypeTest.getRole());

		// ---------------------------------------- Testing Clothing.java ----------------------------------------

		System.out.println("Testing Clothing.java");
		
		Clothing purpleSweater = new Clothing(60.00, 25.00, ClothingType.SHIRT, ClothingColor.PURPLE, ClothingSize.MEDIUM, ClothingCondition.NEW);
		Clothing redDress = new Clothing(49.99, 15.00, ClothingType.DRESS, ClothingColor.RED, ClothingSize.XSMALL, ClothingCondition.NEW);
		Clothing greenSweater = new Clothing(60.00, 25.00, ClothingType.SHIRT, ClothingColor.GREEN, ClothingSize.XLARGE, ClothingCondition.NEW);
		Clothing blueDress = new Clothing(49.99, 15.00, ClothingType.DRESS, ClothingColor.BLUE, ClothingSize.XSMALL, ClothingCondition.NEW);

		System.out.println("Comparing purple sweater to red dress:" + purpleSweater.compareTo(redDress));
		System.out.println("Comparing purple sweater to green sweater:" + purpleSweater.compareTo(greenSweater));

		// ---------------------------------------- Testing ClothingStore.java ------------------------------------

		System.out.println("Testing ClothingStore.java");

		ClothingStore clothingStore = new ClothingStore(); // Create the clothing store
		clothingStore.add(purpleSweater, 22, 25.00); // Add items to the store
		clothingStore.add(redDress, 13, 15.00);
		clothingStore.add(greenSweater, 13, 25.00);
		clothingStore.add(blueDress, 9, 25.00); 

		// Create a BOGO-Free discount for two dresses
		List<Clothing> clothingSales = new ArrayList<Clothing>(); 
		clothingSales.add(redDress);
		clothingSales.add(blueDress);

		// Add discount to the store's list
		clothingStore.addSale(clothingSales, 15.00); 

		// Testing inStock() method
		System.out.println("There are " + clothingStore.inStock(redDress) + " red dresses in stock");

		List<Clothing> myCart = new ArrayList<Clothing>(); // Create a shopping cart
		myCart.add(redDress);
		myCart.add(blueDress);
		myCart.add(purpleSweater);
		myCart.add(greenSweater);

		// Testing sale() method -- Comment out one of the clothingStore.add() items to see 
		// the different exceptions
		try { 
			System.out.println("Your cart total was " + clothingStore.sale(myCart)); 
		} 
		catch (InsufficientStockException insufficientStockException) {
			System.out.println("ERROR: Insufficient stock available for item");
		}
		catch (NoSuchClothingItemException noSuchClothingException) {
			System.out.println("ERROR: No such clothing item available");
		}
	}
}