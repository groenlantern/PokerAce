package za.co.pokerface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import kata.pokerhand.Hand;
import za.co.pokerface.baseCard.Card;
import za.co.pokerface.baseCard.DeckOfCards;
import za.co.pokerface.baseCard.ICardInfo;
import za.co.pokerface.baseCard.IComboHand;
import za.co.pokerface.baseCard.enums.DeckTypes;
import za.co.pokerface.baseCard.enums.PokerHandMapping;
import za.co.pokerface.baseCard.enums.PokerType;
import za.co.pokerface.standardDeck.util.DeckFactory;

/**
 * THe Poker Ace is a small a small application designed to be able to build a deck based on a predefined deck config
 * The deck can be shuffled 
 * A hand of cards can be drawn from the deck and evaluated to identify 
 * the hand as 1 of 9 poker hands in order. 
 * 
 * THe design of the application is such that all aspects can be extended and or overwritten to facilitate
 * custom decks and or rules. 
 * 
 * THe app uses a 3rd party library to identify the poker hands. Library used : 
 * PokerHand Kata- This is the PokerHand kata (http://codingdojo.org/kata/PokerHands) implemented in Java by Arthur Latimier.
 * 
 * Pokerhand Ace The face of Poker
 * 
 * @author Jean-Pierre Erasmus
 * @email groenlantern@gmail.com
 *
 * THis application is console based and runs as a small app taking in user input to select from a few options. 
 * [S]huffle
 * [D]raw
 * [R]eplace
 * [E]valuate
 * [Q]uit/Exit
 * 
 * The input evaluator will also except the words in either upper or lowercase
 * 
 */
public class Main {

	//Variables
	static final PokerType gameType = PokerType.STANDARDPOKER;
	static DeckTypes selectedDeck = null;
	static ArrayList<Card> newDeck = null;
	static ArrayList<Card> hand = new ArrayList<>();
	// 3rd Party evaluator storage
	static Hand kataHand = null;
	static PokerHandMapping handValue = PokerHandMapping.NONE;

	/**
	 * Main method to run
	 * 
	 * Will run until app is quit. 
	 * 
	 * Input will except the letters [*] as well as the word in lowercase or uppercase
	 * 
	 * @param args
	 * @throws Exception
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, Exception {

		// create deck of cards
		selectedDeck = gameType.getDeckType();
		newDeck = DeckFactory.getNewDeck(selectedDeck);

		printWelcome(selectedDeck, newDeck);

		// Process user input
		while (true) {
			String userChat = readInput();

			processUserInput(userChat);

		}

	}

	/**
	 * 
	 * @param userChat
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws Exception
	 */
	private static void processUserInput(String userChat) throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, Exception {
		if (userChat != null && (userChat.toLowerCase().equals("s".toLowerCase())
				|| userChat.toLowerCase().contains("shuffle".toLowerCase()))) {
			shuffle();
		}

		if (userChat != null && (userChat.toLowerCase().equals("d".toLowerCase())
				|| userChat.toLowerCase().contains("draw".toLowerCase()))) {
			drawCards();

		}
		if (userChat != null && (userChat.toLowerCase().equals("e".toLowerCase())
				|| userChat.toLowerCase().contains("evaluate".toLowerCase()))) {
			evalHand();
		}

		if (userChat != null && (userChat.toLowerCase().equals("r".toLowerCase())
				|| userChat.toLowerCase().contains("return".toLowerCase()))) {
			returnCards();

		}

		if (userChat != null && (userChat.toLowerCase().equals("q".toLowerCase())
				|| userChat.toLowerCase().contains("quit".toLowerCase())
				|| userChat.toLowerCase().contains("exit".toLowerCase()))) {
			System.out.println("Bye, see ya later!");
			System.out.println("");
			System.exit(0);
		}

	}

	/**
	 * Return cards to deck 
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws Exception
	 */
	private static void returnCards() throws InstantiationException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, Exception {
		System.out.println("Returning cards to deck...");

		DeckOfCards.returnCards(newDeck, hand);
		kataHand = null;
	}

	/**
	 * Evaluate the hand for poker value
	 * 
	 * @throws Exception
	 */
	private static void evalHand() throws Exception {
		System.out
				.println("Evaluating hand by value for " + gameType.getDeckType().getDeckDefinition().getDescription());

		if (hand == null || kataHand == null) {
			System.out.println("Your hand is Empty");
		} else {

			handValue = PokerHandMapping.getHandValue(kataHand);

			System.out.println("Your hand value is a " + ((IComboHand) handValue.getReferenceInternal()).getLabel());

		}
	}

	/**
	 * Draw a hand of cards
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws Exception
	 */
	private static void drawCards() throws InstantiationException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, Exception {
		System.out.println("Drawing " + gameType.getDrawSize() + " cards from the top");
		System.out.println("");

		DeckOfCards.drawCards(newDeck, gameType.getDrawSize(), hand);
		kataHand = PokerHandMapping.loadKataHand(hand);

		// Print the current hand of cards
		System.out.print("Your hand: ");

		for (Card cardObj : hand) {
			System.out
					.print(" " + ((ICardInfo) cardObj.getCardRank()).getSymbol() + cardObj.getCardSuite().getSymbol());
		}

		System.out.println("");
		System.out.println("");
	}

	/**
	 * Shuffle the deck
	 * 
	 */
	private static void shuffle() {
		System.out.println("Shuffeling ... Shuffeling ... Shuffeling ...  ");
		System.out.println("");

		selectedDeck.getDeckDefinition().shuffle(newDeck);
	}

	/**
	 * 
	 * @param selectedDeck
	 * @param newDeck
	 */
	private static void printWelcome(DeckTypes selectedDeck, ArrayList<Card> newDeck) {
		// Welcome note
		System.out.println("Welcome to Poker Ace the Face of Poker");
		System.out.println("We are using a " + selectedDeck.getDeckDefinition().getDeckName() + " of " + newDeck.size()
				+ " cards.");
		System.out.println("");
		System.out.println("");
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	private static String readInput() throws IOException {
		System.out.println("");
		System.out.println("");

		printMenu();

		String userChat = readLine(">> ");
		return userChat;
	}

	/**
	 * Main menu 
	 * 
	 * add sort deck
	 * 
	 */
	private static void printMenu() {
		System.out.println("What would you like to do today ?  ");
		System.out.println(
				"[S] - Shuffle Deck; [D] - Draw Cards; [E} - Evaluate Hand; [R] - Return Cards; [Q] - Exit/Quit; ");
	}

	/**
	 * 
	 * @param format
	 * @return
	 * @throws IOException
	 */
	private static String readLine(String format) throws IOException {
		if (System.console() != null) {
			return System.console().readLine(format);
		}

		System.out.print(String.format(format));

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		return reader.readLine();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}