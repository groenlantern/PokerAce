package za.co.pokerface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import za.co.pokerface.baseCard.Card;
import za.co.pokerface.baseCard.DeckOfCards;
import za.co.pokerface.baseCard.ICardInfo;
import za.co.pokerface.baseCard.IComboHand;
import za.co.pokerface.baseCard.enums.CardImages;
import za.co.pokerface.baseCard.enums.DeckTypes;
import za.co.pokerface.baseCard.enums.EvaluatorLibrary;
import za.co.pokerface.baseCard.enums.PokerHandMapping;
import za.co.pokerface.baseCard.enums.PokerType;
import za.co.pokerface.standardDeck.exceptions.NoDeckFoundException;
import za.co.pokerface.standardDeck.exceptions.NoEvaluatorFoundException;
import za.co.pokerface.standardDeck.exceptions.NoHandFoundException;
import za.co.pokerface.standardDeck.util.CardPrinter;
import za.co.pokerface.standardDeck.util.DeckFactory;
import za.co.pokerface.standardDeck.util.ThirdPartyConvertor;

/**
 * THe Poker Ace is a small a small application designed to be able to build a
 * deck based on a predefined deck config The deck can be shuffled A hand of
 * cards can be drawn from the deck and evaluated to identify the hand as 1 of 9
 * poker hands in order.
 * 
 * THe design of the application is such that all aspects can be extended and or
 * overwritten to facilitate custom decks and or rules.
 * 
 * THe app uses a 3rd party library to identify the poker hands. Library used :
 * PokerHand Kata- This is the PokerHand kata
 * (http://codingdojo.org/kata/PokerHands) implemented in Java by Arthur
 * Latimier.
 * 
 * Pokerhand Ace The face of Poker
 * 
 * @author Jean-Pierre Erasmus
 * @email groenlantern@gmail.com
 *
 *        THis application is console based and runs as a small app taking in
 *        user input to select from a few options. [S]huffle [D]raw [R]eplace
 *        [E]valuate [Q]uit/Exit
 * 
 *        The input evaluator will also except the words in either upper or
 *        lowercase
 * 
 */
public class Main {

	// Variables
	static final PokerType gameType = PokerType.STANDARDPOKER;
	static DeckTypes selectedDeck = null;
	static Optional<ArrayList<Card>> newDeck = null;
	static Optional<ArrayList<Card>> hand = null;
	// 3rd Party evaluator storage
	static Object rdPrtyHand = null;
	static PokerHandMapping handValue = PokerHandMapping.NONE;
	static EvaluatorLibrary handEvaluator = EvaluatorLibrary.KATA;

	/**
	 * Main method to run
	 * 
	 * Will run until app is quit.
	 * 
	 * Input will except the letters [*] as well as the word in lowercase or
	 * uppercase
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
		newDeck = Optional.of(DeckFactory.getNewDeck(selectedDeck));
		hand = Optional.of(new ArrayList<>());

		if (newDeck.isEmpty())
			throw new NoDeckFoundException("Exception: Empty Deck");

		printWelcome(selectedDeck, newDeck.get());

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
		if (userChat == null) return;
		
		//Shuffle deck
		if ( userChat.toLowerCase().equals("s".toLowerCase())
				|| userChat.toLowerCase().contains("shuffle".toLowerCase())) {
			shuffle();
		}

		//Sort Deck
		if (userChat.toLowerCase().contains("sort by rank".toLowerCase())) {
			sortByRank();
		} else if (userChat.toLowerCase().equals("sr".toLowerCase())
				|| userChat.toLowerCase().contains("sort".toLowerCase())) {
			sort();
		}

		//Draw cards 
		if (userChat.toLowerCase().equals("d".toLowerCase())
				|| userChat.toLowerCase().contains("draw".toLowerCase())) {
			drawCards();

		}

		//Show hand 
		if (userChat.toLowerCase().equals("sh".toLowerCase())
				|| userChat.toLowerCase().contains("show".toLowerCase())) {
			showCards();

		}

		//Evaluate deck
		if (userChat.toLowerCase().equals("e".toLowerCase())
				|| userChat.toLowerCase().contains("evaluate".toLowerCase())) {
			evalHand();
		}

		//Return cards
		if (userChat.toLowerCase().equals("r".toLowerCase())
				|| userChat.toLowerCase().startsWith("r ".toLowerCase()) 
				|| userChat.toLowerCase().contains("return".toLowerCase())) {
			
			replaceCards(userChat);

		}

		//Quit app
		if (userChat.toLowerCase().equals("q".toLowerCase())
				|| userChat.toLowerCase().contains("quit".toLowerCase())
				|| userChat.toLowerCase().contains("exit".toLowerCase())) {
			System.out.println("Bye, see ya later!");
			System.out.println("");
			System.exit(0);
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
	private static void replaceCards(String userChat) throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, Exception {		
		validateDeckHand();
		
		ArrayList<Integer> cardsToReturn = getSpecifiedCnt(userChat);

		if (cardsToReturn != null && cardsToReturn.size() > 0)  
			returnCardsIndiv( cardsToReturn );
		else 
			DeckOfCards.returnCards(newDeck.get(), hand.get(), getCnt(userChat));
		
		setup3dPartyHand();
		
		showCards();
	}

	/**
	 * 
	 * @param userChat
	 * @return
	 */
	private static int getCnt(String userChat) {
		int returnCnt = (hand.isPresent()?hand.get().size():0);
				
		 Pattern p = Pattern.compile("(^|\\s)([0-9]+)($|\\s)");
		 Matcher matcher = p.matcher(userChat);
				  		 				
		try { 
			returnCnt = Integer.valueOf( (matcher.find())?matcher.group(0).trim().strip():"X");
		} catch (Exception e) {

			returnCnt = (hand.isPresent()?hand.get().size():0);
		}
		
		return returnCnt;
	}

	/**
	 * 
	 * @param userChat
	 * @return
	 */
	private static ArrayList<Integer> getSpecifiedCnt(String userChat) {
		Pattern pCards = Pattern.compile("(((?i)C[0-9][0-9]?)+)\\s?");
		Pattern pNumber = Pattern.compile("(((?i)[0-9][0-9]?)+)\\s?");

		Matcher matcher = pCards.matcher(userChat);
		
		ArrayList<Integer> cardsToReturn = new ArrayList<>();
		
		try { 
			while ( matcher.find() ) { 
				Matcher matcherNumber = pNumber.matcher( matcher.group(0) );

				if ( matcherNumber.find() )
					cardsToReturn.add( Integer.valueOf( matcherNumber.group(0).trim().strip()) );
			}
		} catch (Exception e) {}
		
		return cardsToReturn;
	}

	  
/**
 * 
 * @param cardsToReturn
 * @throws InstantiationException
 * @throws IllegalAccessException
 * @throws InvocationTargetException
 * @throws NoSuchMethodException
 * @throws Exception
 */
	private static void returnCardsIndiv(ArrayList<Integer> cardsToReturnIndx) throws InstantiationException, IllegalAccessException, InvocationTargetException,
	NoSuchMethodException, Exception {
 
		for (Card cardObj : getCardsToRemove( cardsToReturnIndx )) { 
			DeckOfCards.returnCard(newDeck.get(), hand.get(), cardObj );
		}
		
	}

	/**
	 * 
	 * @param cardsToReturnIndx
	 * @param cardsToReturn
	 * @throws NoHandFoundException 
	 * @throws NoDeckFoundException 
	 */
	private static ArrayList<Card> getCardsToRemove(ArrayList<Integer> cardsToReturnIndx) throws NoDeckFoundException, NoHandFoundException {
		ArrayList<Card> cardsToReturn = new ArrayList<>();
		
		for (Integer cardNo : cardsToReturnIndx) {
			if ( cardNo <= hand.get().size())
				cardsToReturn.add( DeckOfCards.getCardTemp(newDeck.get(), hand.get(), (cardNo - 1) ) );
		 
		}
		
		return cardsToReturn;
	}

	/**
	 * 
	 */
	private static void validateDeckHand() throws NoDeckFoundException, NoHandFoundException {
		if (newDeck == null || newDeck.isEmpty())
			throw new NoDeckFoundException("Exception: Empty Deck");
		if (hand == null || hand.isEmpty())
			throw new NoHandFoundException("Exception: Empty Hand");
	}

	/**
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws Exception
	 * @throws NoEvaluatorFoundException
	 */
	private static void setup3dPartyHand() throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, Exception, NoEvaluatorFoundException {
		if ( hand == null || hand.isEmpty() || hand.get().size() < 1) { 
			rdPrtyHand = null;
		} else { 
			rdPrtyHand = ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).loadHand(hand.get());
		}
	}

	/**
	 * Evaluate the hand for poker value
	 * 
	 * @throws Exception
	 */
	private static void evalHand() throws Exception {
		System.out
				.println("Evaluating hand by value for " + gameType.getDeckType().getDeckDefinition().getDescription());
 		
		if (hand == null || rdPrtyHand == null) {
			System.out.println("Your hand is Empty");
		} else {

			handValue = PokerHandMapping.getHandValue(rdPrtyHand);
			String handLabel = ((IComboHand) handValue.getReferenceInternal()).getLabel();

			/**
			 * Print the Users hand identity as evaluated by the chosen library
			 */
			printPokerHandName(handLabel);
		}
	}

	/**
	 * Print the poker hand description/label
	 * 
	 * @param cardHandDescription
	 */
	private static void printPokerHandName(String cardHandDescription) {
		System.out.println("Your hand value is a " + cardHandDescription);
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

		if (newDeck.isEmpty())
			throw new NoDeckFoundException("Exception: Empty Deck");
		if (hand.isEmpty())
			throw new NoHandFoundException("Exception: Empty Hand");

		DeckOfCards.drawCards(newDeck.get(), gameType.getDrawSize(), hand.get());

		if (hand.isEmpty())
			throw new NoHandFoundException("Exception: Empty Hand");

		setup3dPartyHand();

		showCards();
	}

	/**
	 * Show hand
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws Exception
	 */
	private static void showCards() throws InstantiationException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, Exception {
	
		if (newDeck.isEmpty())
			throw new NoDeckFoundException("Exception: Empty Deck");
		if (hand.isEmpty())
			throw new NoHandFoundException("Exception: Empty Hand");

		CardPrinter printer =new CardPrinter(hand);
		
		printer.printFullHand();
		
		System.out.println("");
		
		printer.printHandFan();
		
		System.out.println("");
	}

	/**
	 * Shuffle the deck
	 * 
	 * @throws NoDeckFoundException
	 * 
	 */
	private static void shuffle() throws NoDeckFoundException {
		System.out.println("Shuffeling ... Shuffeling ... Shuffeling ...  ");
		System.out.println("");

		if (newDeck.isEmpty())
			throw new NoDeckFoundException("Exception: Empty Deck");

		selectedDeck.getDeckDefinition().shuffle(newDeck.get());
	}

	/**
	 * Standard deck sort the deck
	 * 
	 * @throws NoDeckFoundException
	 * 
	 */
	private static void sort() throws NoDeckFoundException {
		System.out.println("Sort ... Sort ... Sort ...  ");
		System.out.println("");

		if (newDeck.isEmpty())
			throw new NoDeckFoundException("Exception: Empty Deck");

		newDeck = Optional.of(DeckOfCards.sort(newDeck.get()));
	}

	/**
	 * Deck sort the deck by rank only
	 * 
	 * @throws NoDeckFoundException
	 * 
	 */
	private static void sortByRank() throws NoDeckFoundException {
		System.out.println("Sort ... Sort ... Sort by Rank...  ");
		System.out.println("");

		if (newDeck.isEmpty())
			throw new NoDeckFoundException("Exception: Empty Deck");

		newDeck = Optional.of(DeckOfCards.sortRankOnly(newDeck.get()));
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
				"[S] - Shuffle Deck; [D] - Draw Cards; [E} - Evaluate Hand; [R] - Return Cards; [SR] - Sort Deck; [Q] - Exit/Quit; ");
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