package com.gaby;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player {

	public String playerName;
	private List<Card> playerHand;

	// constructor
	public Player(String playerName) {
		this.playerName = playerName;
		playerHand = new ArrayList<Card>();
	}

	// getter
	public List<Card> getPlayerHand() {
		return playerHand;
	}

	// setter
	public void setPlayerHand(List<Card> playerHand) {
		this.playerHand = playerHand;
	}

	// adds card to hand
	public void addCard(Card card) {
		playerHand.add(card);
	}

	// removes card from hand
	public void removeCard(Card card) {
		playerHand.remove(card);
	}

	public void printHand() {
		System.out.println("\n"+playerName+ "'s hand:");
		for (Iterator<Card> iterator = playerHand.iterator(); iterator
				.hasNext();) {
			Card card = iterator.next();
			card.print();
		}

	}

	// rules for discarding: card must match suit or number of topCard, unless
	// topCard is an 8, in which case you may discard any card within opponent's
	// stated suit, or another 8.
	public Card discard(Card topCard) {
		
		//no discard if player has no cards, obv.
		if (playerHand.size() < 1) {
			return null;
		}

		//if top discard card is an 8, sets new suit at random
		if (topCard.isNumber("8")) {
			String declaredSuit = Deck.getRandomSuit();
			
			// iterates through player's hand, card matching declared suit is played (removed from hand, discarded)
			for (Iterator<Card> iterator = playerHand.iterator(); iterator
					.hasNext();) {
				Card card = iterator.next();
				if (card.isSuit(declaredSuit)) {
					playerHand.remove(card);
					return card;
				}
			}
			return null;
		}
		
		// if top card not an 8, iterates through hand and plays card matching the suit of the top card
		for (Iterator<Card> iterator = playerHand.iterator(); iterator
				.hasNext();) {
			Card card = iterator.next();
			if (card.isSuit(topCard)) {
				playerHand.remove(card);
				return card;
			}
		}
		
		// if top card not an 8, and no card in the suit has been found, iterates through hand and plays card matching the NUMBER of the top card
		for (Iterator<Card> iterator = playerHand.iterator(); iterator
				.hasNext();) {
			Card card = iterator.next();
			if (card.isNumber(topCard)){
				playerHand.remove(card);
				return card;
			}
		}
		return null;
		
	}
}
