package com.gaby;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//creates shuffled deck
public class ShuffledDeck {
	private Deck shuffledDeck;
	
	//constructor; gets sorted deck (deck), generates random int 0-51 to assign cards 
		//to index of randomized deck(randomlist)...randomly. 
		public ShuffledDeck(Deck deck) {
			List<Card> list = deck.getDeck();
			List<Card> randomList = new ArrayList<Card>();
			while(randomList.size()<52){
				int rand = (int) (Math.random() * 52);
				Card card = list.get(rand);
				//skips over card if its already been used
				if (card.isUsed()) {
					continue;
				}
				//else, sets card to 'used', adds it to list
				card.setUsed(true);
				randomList.add(card);

			}
			shuffledDeck = new Deck(randomList);
		}

	//prints out all cards in randomized deck, and total number (FOR TESTING)
	public void check() {
		int totalshuffledCards = 0;
		for (Iterator<Card> iterator = shuffledDeck.getDeck().iterator(); iterator
				.hasNext();) {
			Card card = iterator.next();
			card.print();
			totalshuffledCards++;
		}
		System.out.println("Total cards in shuffled deck: "+totalshuffledCards);

	}
	//checks if all cards present in deck, returns boolean, method used in RunGame
	public boolean checkAll() {
		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < 13; k++) {

				Card newcard = shuffledDeck.getCard(Deck.number[k],
						Deck.suit[i]);
				if (newcard == null) {
					return false;
				}
			}
		}
		return true;
	}

	// gets top card from stockpile
	public Card getTopCard(){
		Card topCard = shuffledDeck.getTopCard();
		return topCard;
		
	}

	public List<Card> dealCards() {
		List<Card> list = new ArrayList<Card>();
		for(int i = 0; i<7; i++){
			Card newCard = getTopCard();
			list.add(newCard);
		}
		return list;
	}

	public void removeCard(Card topCard) {
		shuffledDeck.removeCard(topCard);
		
	}

	
}
