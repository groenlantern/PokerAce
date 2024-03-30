/**
 * 
 */
package za.co.pokerface.test;

import static org.junit.Assert.assertNotEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import kata.pokerhand.Hand;
import kata.pokerhand.bean.Figure;
import kata.pokerhand.bean.Suit;
import za.co.pokerface.baseCard.Card;
import za.co.pokerface.baseCard.DeckOfCards;
import za.co.pokerface.baseCard.enums.DeckTypes;
import za.co.pokerface.baseCard.enums.EvaluatorLibrary;
import za.co.pokerface.baseCard.enums.PokerHandMapping;
import za.co.pokerface.baseCard.enums.PokerType;
import za.co.pokerface.standardDeck.util.DeckFactory;
import za.co.pokerface.standardDeck.util.ThirdPartyConvertor;

/**
 * Unit tests related to the shuffeling drawing anf evaluating of cards and hands and decks
 * 
 * @author Jean-Pierre Erasmus
 *
 */
public class MainTest {
	
	/**
	 * Config data for text
	 */
	static final PokerType gameType = PokerType.STANDARDPOKER;
	static DeckTypes selectedDeck = null;
	static ArrayList<Card> newDeck = null;
	static ArrayList<Card> hand = new ArrayList<>();
	// 3rd Party evaluator storage
	static Hand kataHand = null;
	static PokerHandMapping handValue = PokerHandMapping.NONE;
	static EvaluatorLibrary handEvaluator = EvaluatorLibrary.KATA;
	
	/**
	 * Setup test data
	 */
	@BeforeClass
	public static void setUpClass() {
		// code executed before all test methods
		selectedDeck = gameType.getDeckType();
		newDeck = DeckFactory.getNewDeck(selectedDeck);

	}

	/**
	 * Test method for new Deck Creation
	 */
	@Test
	public void testDeckSize() {
		newDeck = DeckFactory.getNewDeck(selectedDeck);
		assert (newDeck.size() == selectedDeck.getDeckDefinition().getFullDeckSize());
	}

	/**
	 * Test method for new SHuffeling
	 */
	@Test
	public void testDeckShuffle() {
		@SuppressWarnings("unchecked")
		ArrayList<Card> oldDeck = (ArrayList<Card>) newDeck.clone();
		selectedDeck.getDeckDefinition().shuffle(newDeck);

		assertNotEquals(oldDeck, newDeck);
	}

	/**
	 * Test method for card draw
	 * 
	 * @throws Exception
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void testCardDrawStandardDeck() throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, Exception {
		int deckSize = newDeck.size();

		DeckOfCards.drawCards(newDeck, gameType.getDrawSize(), hand);

		assert (hand.size() == gameType.getDrawSize());

		int deckSizeNew = newDeck.size();

		assert (deckSize == deckSizeNew + gameType.getDrawSize());
	}

	/**
	 * Test method for returning card
	 */
	@Test
	public void testReturnHand() {
		newDeck = DeckFactory.getNewDeck(selectedDeck);

		assert (newDeck.size() == selectedDeck.getDeckDefinition().getFullDeckSize());

		DeckOfCards.drawCards(newDeck, gameType.getDrawSize(), hand);

		assert (newDeck.size() == (selectedDeck.getDeckDefinition().getFullDeckSize() - gameType.getDrawSize()));

		DeckOfCards.returnCards(newDeck, hand);

		assert (newDeck.size() == selectedDeck.getDeckDefinition().getFullDeckSize());

	}

	/**
	 * Test hand evaluation
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEvalue() throws Exception {
		testStraightFlush();

		assert (handValue.equals(PokerHandMapping.STRAIGHT_FLUSH));

		testStraight();

		assert (handValue.equals(PokerHandMapping.STRAIGHT));

		testThreeKind();

		assert (handValue.equals(PokerHandMapping.THREE_OF_A_KIND));

		testFullHouseKind();

		assert (handValue.equals(PokerHandMapping.FULL_HOUSE));

		testFourKind();
		
		assert (handValue.equals(PokerHandMapping.FOUR_OF_A_KIND));

		testHigh();

		assert (handValue.equals(PokerHandMapping.HIGH_CARD));

		testPair();

		assert (handValue.equals(PokerHandMapping.PAIR));

		testTwoPair();

		assert (handValue.equals(PokerHandMapping.TWO_PAIRS));

		testFlush();

		assert (handValue.equals(PokerHandMapping.FLUSH));

	}

	/**
	 * 
	 * @throws Exception
	 */
	private void testStraightFlush() throws Exception {
		List<kata.pokerhand.Card> kataHandList = new ArrayList<>();

		kata.pokerhand.Card kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.JACK, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.TEN, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.NINE, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.EIGHT, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.SEVEN, Suit.CLUB);
		kataHandList.add(kataCard);

		kata.pokerhand.Card[] kataHandArray = new kata.pokerhand.Card[kataHandList.size()];
		kataHandList.toArray(kataHandArray);

		kataHand = new Hand(kataHandArray);

		handValue = PokerHandMapping.getHandValue(kataHand);
	}

	/**
	 * 
	 * @throws Exception
	 */
	private void testFlush() throws Exception {
		List<kata.pokerhand.Card> kataHandList = new ArrayList<>();

		kata.pokerhand.Card kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.TWO, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.TEN, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.FOUR, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.EIGHT, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.ACE, Suit.CLUB);
		kataHandList.add(kataCard);

		kata.pokerhand.Card[] kataHandArray = new kata.pokerhand.Card[kataHandList.size()];
		kataHandList.toArray(kataHandArray);

		kataHand = new Hand(kataHandArray);

		handValue = PokerHandMapping.getHandValue(kataHand);
	}


	/**
	 * 
	 * @throws Exception
	 */
	private void testStraight() throws Exception {
		List<kata.pokerhand.Card> kataHandList = new ArrayList<>();

		kata.pokerhand.Card kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.JACK, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.TEN, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.NINE, Suit.HEART);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.EIGHT, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.SEVEN, Suit.CLUB);
		kataHandList.add(kataCard);

		kata.pokerhand.Card[] kataHandArray = new kata.pokerhand.Card[kataHandList.size()];
		kataHandList.toArray(kataHandArray);

		kataHand = new Hand(kataHandArray);

		handValue = PokerHandMapping.getHandValue(kataHand);
	}

	/**
	 * 
	 * @throws Exception
	 */
	private void testThreeKind() throws Exception {
		List<kata.pokerhand.Card> kataHandList = new ArrayList<>();

		kata.pokerhand.Card kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.JACK, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.TEN, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.JACK, Suit.HEART);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.EIGHT, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.JACK, Suit.DIAMOND);
		kataHandList.add(kataCard);

		kata.pokerhand.Card[] kataHandArray = new kata.pokerhand.Card[kataHandList.size()];
		kataHandList.toArray(kataHandArray);

		kataHand = new Hand(kataHandArray);

		handValue = PokerHandMapping.getHandValue(kataHand);
	}

	/**
	 * 
	 * @throws Exception
	 */
	private void testFullHouseKind() throws Exception {
		List<kata.pokerhand.Card> kataHandList = new ArrayList<>();

		kata.pokerhand.Card kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.JACK, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.TEN, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.JACK, Suit.HEART);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.TEN, Suit.SPADE);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.JACK, Suit.DIAMOND);
		kataHandList.add(kataCard);

		kata.pokerhand.Card[] kataHandArray = new kata.pokerhand.Card[kataHandList.size()];
		kataHandList.toArray(kataHandArray);

		kataHand = new Hand(kataHandArray);

		handValue = PokerHandMapping.getHandValue(kataHand);
	}


	/**
	 * 
	 * @throws Exception
	 */
	private void testFourKind() throws Exception {
		List<kata.pokerhand.Card> kataHandList = new ArrayList<>();

		kata.pokerhand.Card kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.JACK, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.TEN, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.TEN, Suit.HEART);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.TEN, Suit.SPADE);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.TEN, Suit.DIAMOND);
		kataHandList.add(kataCard);

		kata.pokerhand.Card[] kataHandArray = new kata.pokerhand.Card[kataHandList.size()];
		kataHandList.toArray(kataHandArray);

		kataHand = new Hand(kataHandArray);

		handValue = PokerHandMapping.getHandValue(kataHand);
	}


	/**
	 * 
	 * @throws Exception
	 */
	private void testPair() throws Exception {
		List<kata.pokerhand.Card> kataHandList = new ArrayList<>();

		kata.pokerhand.Card kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.JACK, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.TEN, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.JACK, Suit.HEART);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.EIGHT, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.QUEEN, Suit.DIAMOND);
		kataHandList.add(kataCard);

		kata.pokerhand.Card[] kataHandArray = new kata.pokerhand.Card[kataHandList.size()];
		kataHandList.toArray(kataHandArray);

		kataHand = new Hand(kataHandArray);

		handValue = PokerHandMapping.getHandValue(kataHand);
	}

	/**
	 * 
	 * @throws Exception
	 */
	private void testTwoPair() throws Exception {
		List<kata.pokerhand.Card> kataHandList = new ArrayList<>();

		kata.pokerhand.Card kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.JACK, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.TEN, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.JACK, Suit.HEART);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.EIGHT, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.EIGHT, Suit.DIAMOND);
		kataHandList.add(kataCard);

		kata.pokerhand.Card[] kataHandArray = new kata.pokerhand.Card[kataHandList.size()];
		kataHandList.toArray(kataHandArray);

		kataHand = new Hand(kataHandArray);

		handValue = PokerHandMapping.getHandValue(kataHand);
	}

	/**
	 * 
	 * @throws Exception
	 */
	private void testHigh() throws Exception {
		List<kata.pokerhand.Card> kataHandList = new ArrayList<>();

		kata.pokerhand.Card kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.JACK, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.TWO, Suit.CLUB);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.KING, Suit.HEART);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.EIGHT, Suit.DIAMOND);
		kataHandList.add(kataCard);

		kataCard = (kata.pokerhand.Card) ThirdPartyConvertor.ThirdPartyConvertorFactory(handEvaluator).buildCard(Figure.FIVE, Suit.SPADE);
		kataHandList.add(kataCard);

		kata.pokerhand.Card[] kataHandArray = new kata.pokerhand.Card[kataHandList.size()];
		kataHandList.toArray(kataHandArray);

		kataHand = new Hand(kataHandArray);

		handValue = PokerHandMapping.getHandValue(kataHand);
	}

}
