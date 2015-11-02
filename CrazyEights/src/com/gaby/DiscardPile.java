package com.gaby;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile {

	/**
	 * the top of the pile is the last card in the list
	 */
	private List<Card> discardPile;

	public DiscardPile() {
		discardPile = new ArrayList<Card>();
	}

	// adds card to pile
	public void addCard(Card card) {
		discardPile.add(card);
	}

	public Card getTopCard() {
		return discardPile.get(discardPile.size() - 1);
	}
	
}