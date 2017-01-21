public class Clothing implements Comparable<Clothing> {

	double msrp, cost;
	ClothingType clothingType; 
	ClothingColor color;
	ClothingSize size;
	ClothingCondition condition;

	public Clothing(double msrp, double cost, ClothingType clothingType, ClothingColor color, ClothingSize size, ClothingCondition condition) {

		this.msrp = msrp;
		this.cost = cost;
		this.clothingType = clothingType;
		this.color = color; 
		this.size = size;
		this.condition = condition;

	}

	public int compareTo(Clothing c) {
		// compareTo returns arbritrarily signed numbers
		if (this.clothingType.compareTo(c.clothingType) <= -1) {
			return -1;
		} else if (this.clothingType.compareTo(c.clothingType) >= 1) {
			return 1;
		} else {
			if (this.color.compareTo(c.color) <= -1) {
				return -1;
			} else if (this.color.compareTo(c.color) >= 1) {
				return 1;
			} else {
				return 0; 
			}
		}
	}
}

enum ClothingColor {
	RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE;
}

enum ClothingSize {
	XSMALL, SMALL, MEDIUM, LARGE, XLARGE;
}

enum ClothingCondition {
	NEW, GOOD, DAMAGED, UNSELLABLE; 
}