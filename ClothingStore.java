import java.util.ArrayList;
import java.util.*;

public class ClothingStore {

	Map<Clothing, Double> prices; // HashMap
	Map<Clothing, Integer> inventory; // HashMap
	Map<List<Clothing>, Double> discounts; // HashMap

	public ClothingStore() {

		prices = new HashMap<Clothing, Double>();
		inventory = new HashMap<Clothing, Integer>();
		discounts = new HashMap<List<Clothing>, Double>();
	}

	public void add(Clothing c, int number, Double price) {
		if (inventory.containsKey(c)) {
			inventory.put(c, number + inventory.get(c));
		} else {
			prices.put(c, price);
			inventory.put(c, number);
		}
	}

	void addSale(List<Clothing> combo, Double discount) {
		if (discounts.containsKey(combo)) {
			discounts.put(combo, discount);
		} else {
			discounts.put(combo, discount);
		}
	}

	int inStock(Clothing c) {
		return inventory.get(c);
	}

	Double sale(List<Clothing> shoppingCart) throws InsufficientStockException, NoSuchClothingItemException {

		Double cartTotal;
		Map<Clothing, Integer> cartCount = new HashMap<Clothing, Integer>();

		for(Clothing c : shoppingCart) {

			// If any item not in ineventory, throw InsufficientStockException
			if (!inventory.containsKey(c)) { 
				throw new InsufficientStockException(c, 0, 1, "Error: Insufficient stock");
			}

			// If any item not in prices map, throw NoSuchClothingItemException
			if (!prices.containsKey(c)) {
				throw new NoSuchClothingItemException(c, "Error: No such clothing item found");
			}

			// If the cart doesn't have the item, add to cart after checking 2 conditions above
			if (!cartCount.containsKey(c)) {
				cartCount.put(c, 1);
			} else {
				cartCount.put(c, cartCount.get(c)++);
			}
		}

		// If store doesn't have enough of that item, throw InsufficientStockException
		for (Clothing c : cartCount.keySet()) { 
			if (cartCount.get(c) > inventory.get(c)) {
				throw new InsufficientStockException(c, inventory.get(c), cartCount.get(c), "Error: Insufficient stock");
			}
		}

		// After verifying all items have a price and are in stock, for each piece of clothing:
		// a) Reduce level of inventory of that piece by 1
		// b) Add the price of the clothing to a sum

		ArrayList<Clothing> itemsInCart = new ArrayList<Clothing>();
		for(Clothing c : shoppingCart) {
			inventory.put(c, inventory.get(c)--); // Updates inventory
			cartTotal = cartTotal + prices.get(c); // Adds price to sum
			itemsInCart.add(c); 
		}

		// Checks for all possible combinations of clothing items in cart and applies discounts
		Combine checkForSale = new Combine(itemsInCart); 
		itemsInCart = checkForSale.nextComb();
		for(Clothing c : itemsInCart) {
			if (discounts.containsKey(c)) {
				cartTotal = cartTotal - discounts.get(c);
			}
		}

		return cartTotal;
	}
}

class InsufficientStockException extends Exception {
	Clothing missingItem;
	int itemsLeft; 
	int itemsDesired;

	InsufficientStockException(Clothing missingItem, int itemsLeft, int itemsDesired, String errorMessage) {
		super(errorMessage); // Calls superclass to display error message
		this.missingItem = missingItem;
		this.itemsLeft = itemsLeft;
		this.itemsDesired = itemsDesired;
	}
}

class NoSuchClothingItemException extends Exception { 
	Clothing itemSought; 

	NoSuchClothingItemException(Clothing itemSought, String errorMessage) {
		super(errorMessage);
		this.itemSought = itemSought;
	}
}