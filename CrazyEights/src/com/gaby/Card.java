package com.gaby;

public class Card {

	public String number;
	public String suit;
	private boolean used;

	public Card(String number, String suit) {
		this.number = number;
		this.suit = suit;
		used = false;
	}

	// returns false(used), if card has been added to random deck
	public boolean isUsed() {
		return used;
	}

	// sets card to 'used' once it's added to random deck
	public void setUsed(boolean used) {
		this.used = used;
	}

	// method to print card
	public void print() {
		System.out.println(number + " of " + suit);
	}

	// checks if card is a particular card
	public boolean isThis(String number, String suit) {
		if (number.equals(this.number) && (suit.equals(this.suit))) {
			return true;
		}
		return false;
	}

	// checks if number matches 
	public boolean isNumber(String number) {
		return this.number.equals(number);
	}

	// checks for a suit match with top card of discard pile
	public boolean isSuit(Card topCard) {
		return topCard.suit.equals(suit);
	}

	// checks for a suit match with declared suit (in case of an 8)
	public boolean isSuit(String declaredSuit) {
		return declaredSuit.equals(suit);
	}

	// checks for a number match with top card of discard pile
	public boolean isNumber(Card topCard) {
		return topCard.number.equals(number);
	}

}
