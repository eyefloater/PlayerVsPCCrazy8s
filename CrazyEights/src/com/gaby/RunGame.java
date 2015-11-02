package com.gaby;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class RunGame {

	public static void main(String[] args) {

		System.out.println("WELCOME TO FULLY AUTOMATED CRAZY EIGHTS!");
		// creates deck
		Deck deck = new Deck();

		// prints out deck (FOR TESTING)
		// deck.check();
		// creates shuffled deck
		ShuffledDeck shuffledDeck = new ShuffledDeck(deck);

		// prints out shuffled deck (FOR TESTING)
		// shuffledDeck.check();
		// says whether deck is full or not
		if (shuffledDeck.checkAll()) {
			System.out.println("We have a full deck.\n");
		} else {
			System.out.println("We don't have a full deck.\n");

		}
		// names players
		String playerOneName = "";
		String playerTwoName = "Jeff the Computer";

		// instantiates two players
		Player playerOne = new Player(playerOneName);
		Player playerTwo = new Player(playerTwoName);

		// deal cards from shuffled deck
		List<Card> playerOneList = shuffledDeck.dealCards();
		playerOne.setPlayerHand(playerOneList);

		List<Card> playerTwoList = shuffledDeck.dealCards();
		playerTwo.setPlayerHand(playerTwoList);

		// print out Players' hands FOR TESTING
		// playerOne.printHand();
		// playerTwo.printHand();

		// begins discard pile from top stockpile card
		DiscardPile discardPile = new DiscardPile();
		Card topCard = shuffledDeck.getTopCard();
		discardPile.addCard(topCard);
		Card tCard = discardPile.getTopCard();

		System.out.println("Enter your name, human:");
		Scanner scan = new Scanner(System.in);
		playerOneName = scan.nextLine();
		System.out
				.println("\nGreetings, " + playerOneName + ". Let's begin!\n");

		// initializes wild card suit randomly
		Random rand = new Random();
		int randomNumber = rand.nextInt(4);
		String declaredSuit;
		switch (randomNumber) {
		case 0:
			declaredSuit = "Diamonds";
		case 1:
			declaredSuit = "Spades";
		case 2:
			declaredSuit = "Clubs";
		case 3:
			declaredSuit = "Hearts";
		default:
			declaredSuit = "Hearts";

		}

		// <>>>>>>>>>><><***** MAIN GAME LOOP ****><>><><><><>
		while (true) {
			System.out.println(playerOneName + "'s turn:");

			/*
			 * // prints player one's hand System.out.println("\n" +
			 * playerOneName + "'s hand:");
			 * 
			 * for (int i = 0; i < playerOneList.size(); i++) {
			 * System.out.println((i + 1) + ". " + playerOneList.get(i).number +
			 * " of " + playerOneList.get(i).suit); }
			 */

			System.out.println("");

			// gets top card in stockpile
			topCard = shuffledDeck.getTopCard();

			// ends game and runs scoring if there are no cards left in
			// stockpile
			if (topCard == null) {
				playerGameOver(playerOneName, playerTwoName, playerOneList,
						playerTwoList);
				break;
			}

			// }

			// prints player one's hand
			System.out.println("\n" + playerOneName + "'s hand:");

			for (int i = 0; i < playerOneList.size(); i++) {
				System.out.println((i + 1) + ". " + playerOneList.get(i).number
						+ " of " + playerOneList.get(i).suit);
			}

			// prints top card in discard pile
			System.out.println("\nThe top card in the discard pile is the "
					+ tCard.number + " of " + tCard.suit + ".");

			System.out.println("");
			System.out.println("Type the number of a card you may discard,");
			System.out.println("or press (0) to draw.");

			// Player 1 - DISCARDING OR DRAWING
			while (true) {
				int choice = scan.nextInt() - 1;
				// makes sure input is not out of bounds of the size of the hand
				if (choice > playerOneList.size()) {
					printSelectionError();
				} else if (choice == -1) {
					// if player can't discard, DRAW a card from stockpile, adds
					// it to hand
					System.out.print(playerOneName + " drew the "
							+ topCard.number + " of " + topCard.suit + ".\n");
					playerOne.addCard(topCard);
					break;
				} else {
					// or discards
					Card dCard = playerOneList.get(choice);

					if (dCard.number.equals(tCard.number)
							| dCard.suit.equals(tCard.suit)
							| (tCard.number.equals("8") & dCard.suit
									.equals(declaredSuit))) {
						addToDiscardPile(playerOneName, discardPile, dCard);
						playerOne.discard(dCard);

						if (dCard.number.equals("8")) {
							declaredSuit = declaringWildSuit(scan, declaredSuit);
						}
						break;
					} else {
						System.out.println("Invalid choice, choose again.");
					}
				}

			}

			// checks if player won by discarding their last card
			if (playerOneList.size() < 1) {
				playerGameOver(playerOneName, playerTwoName, playerOneList,
						playerTwoList);
				break;
			}

			// COMPUTER PLAYER - DISCARDING OR DRAWING
			System.out.println("\nComputer's turn.\n");
			// delay for added DRAMA
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// prints player two's hand COMMENTED OUT FOR HUMAN VS COMPUTER GAME
			// playerTwo.printHand();

			// gets new top card from stockpile
			topCard = shuffledDeck.getTopCard();

			// ends game and runs scoring if there are no cards left in
			// stockpile
			if (topCard == null) {
				playerGameOver(playerOneName, playerTwoName, playerOneList,
						playerTwoList);
				break;
			}

			// gets card on top of discard pile, prints it
			tCard = discardPile.getTopCard();
			System.out.print("Top card on the discard pile is the "
					+ tCard.number + " of " + tCard.suit + ".\n");

			// delay
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("\nThinking...\n");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// player two discards.
			boolean found = false;
			for (Iterator<Card> iterator = playerTwoList.iterator(); iterator
					.hasNext();) {
				Card pTwoDiscardCard = iterator.next();
				if (pTwoDiscardCard.number.equals(tCard.number)
						|| pTwoDiscardCard.suit.equals(tCard.suit)
						|| (tCard.number.equals("8") && pTwoDiscardCard.suit
								.equals(declaredSuit))) {
					found = true;
					computerDiscard(playerTwoName, playerTwo, discardPile,
							pTwoDiscardCard);
					break;
				}
			}
			// otherwise, computer DRAWS a card from stockpile,
			// adds it to their hand
			if (found == false) {
				computerDrawCard(shuffledDeck, playerTwo, topCard);
			}
		}
	}

	private static void computerDrawCard(ShuffledDeck shuffledDeck,
			Player playerTwo, Card topCard) {
		System.out.println("Drawing a card...\n");
		playerTwo.addCard(topCard);
		shuffledDeck.removeCard(topCard);
	}

	private static String declaringWildSuit(Scanner scan, String declaredSuit) {
		System.out.println("\nSelect a suit for your wild card.\n");
		System.out.println("1. Diamonds");
		System.out.println("2. Clubs");
		System.out.println("3. Spades");
		System.out.println("4. Hearts");
		int suitChoice = scan.nextInt();
		switch (suitChoice) {
		case 1:
			declaredSuit = "Diamonds";
		case 2:
			declaredSuit = "Clubs";
		case 3:
			declaredSuit = "Spades";
		case 4:
			declaredSuit = "Hearts";
		}
		return declaredSuit;
	}

	private static void computerDiscard(String playerTwoName, Player playerTwo,
			DiscardPile discardPile, Card pTwoDiscardCard) {
		discardPile.addCard(pTwoDiscardCard);
		playerTwo.removeCard(pTwoDiscardCard);
		System.out.print(playerTwoName + " discarded the "
				+ pTwoDiscardCard.number + " of " + pTwoDiscardCard.suit
				+ ".\n");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void playerGameOver(String playerOneName,
			String playerTwoName, List<Card> playerOneList,
			List<Card> playerTwoList) {
		System.out.println("\nNo cards left in deck. GAME OVER.");
		int playerOneScore = Scoring.getScore(playerOneList);
		int playerTwoScore = Scoring.getScore(playerTwoList);
		System.out.println(playerOneName + "'s score is " + playerOneScore);
		System.out.println(playerTwoName + "'s score is " + playerTwoScore);
	}

	private static void printSelectionError() {
		System.out.println("Error. Please select a valid number.");
		System.out.print("Type the number of the card you want to discard ");
		System.out.print("or press (0) to pass");
	}

	private static void addToDiscardPile(String playerOneName,
			DiscardPile discardPile, Card dCard) {
		// adds new discarded card to discard pile
		discardPile.addCard(dCard);

		// prints player's discard
		System.out.println(playerOneName + " discarded the " + dCard.number
				+ " of " + dCard.suit + ".\n");
	}
}
