public enum ClothingType {
	SHIRT(ClothingRole.TOP), PANTS(ClothingRole.BOTTOM), DRESS(ClothingRole.ONE_PIECE), SHOES(ClothingRole.ACCESSORY);

	private ClothingRole role; // instance variable of an enum

	// No public constructors in enums 
	ClothingType(ClothingRole role) {
		this.role = role;
	}

	public ClothingRole getRole() {
		return role;
	}
}

enum ClothingRole {
	TOP, BOTTOM, ONE_PIECE, ACCESSORY;
}