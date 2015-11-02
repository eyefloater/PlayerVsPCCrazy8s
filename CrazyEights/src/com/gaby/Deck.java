package com.gaby;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

//creates initial full, sorted deck, later shuffled to create RandomizedDeck
public class Deck {
	public static String[] suit;
	public static String[] number;
	/**
	 * the top card is index 0
	 */
	private List<Card> deck;

	public Deck() {

		deck = new ArrayList<Card>();

		suit = new String[4];
		suit[0] = "Diamonds";
		suit[1] = "Clubs";
		suit[2] = "Spades";
		suit[3] = "Hearts";

		number = new String[13];
		number[0] = "Ace";
		number[1] = "2";
		number[2] = "3";
		number[3] = "4";
		number[4] = "5";
		number[5] = "6";
		number[6] = "7";
		number[7] = "8";
		number[8] = "9";
		number[9] = "10";
		number[10] = "Jack";
		number[11] = "Queen";
		number[12] = "King";

		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < 13; k++) {

				deck.add(new Card(number[k], suit[i]));
			}
		}
	}

	public Deck(List<Card> randomList) {
		deck = randomList;
	}

	// prints out all cards in deck (FOR TESTING)
	public void check() {
		int totalSortedCards = 0;
		for (Iterator<Card> iterator = deck.iterator(); iterator.hasNext();) {
			Card card = iterator.next();
			card.print();
			totalSortedCards++;
		}
		System.out.println("Total cards in sorted deck: " + totalSortedCards);

	}

	// getter for Deck
	public List<Card> getDeck() {
		return deck;
	}

	// checks for individual card from deck (for testing if randomizd deck if
	// complete
	public Card getCard(String number, String suit) {
		for (Iterator<Card> iterator = deck.iterator(); iterator.hasNext();) {
			Card card = iterator.next();
			if (card.isThis(number, suit)) {
				return card;
			}

		}
		return null;
	}
	
	//method returns top card from deck
	public Card getTopCard() {
		if (deck.size() > 0) {
			Card topCard = deck.get(0);
			deck.remove(0);
			return topCard;
		}
		return null;
	}

	//method returns a random suit
	public static String getRandomSuit(){
		Random rand = new Random();
		int randomNumber = rand.nextInt(4);
		return suit[randomNumber];
	}
	
	public void removeCard(Card topCard) {
		deck.remove(topCard);
		
	}

}