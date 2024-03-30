package za.co.pokerface.standardDeck.util;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import kata.pokerhand.bean.Figure;
import kata.pokerhand.bean.Suit;
import za.co.pokerface.baseCard.Card;
import za.co.pokerface.baseCard.enums.CardValueMapping;
import za.co.pokerface.baseCard.enums.IMapperEnum;
import za.co.pokerface.baseCard.enums.SuitMapping;

/**
 * 
 * Converter methods for usage of Poker Master library
 * 
 * @author F5156165
 *
 */
public abstract class PokerMasterCardGenerator {

	/**
	 * 
	 * @param origCard
	 * @return
	 * @throws Exception 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static kata.pokerhand.Card buildCard(za.co.pokerface.baseCard.Card origCard) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, Exception {
		assertTrue(origCard.getCardRank() instanceof Enum);
		
		ArrayList<Enum<?>> cardSuit = IMapperEnum.getExternalFromInternal( origCard.getCardSuite(), SuitMapping.CLUB_MAP );
		ArrayList<Enum<?>> cardNumber = IMapperEnum.getExternalFromInternal( origCard.getCardRank(), CardValueMapping.ACE_MAP );

		validateFirst(cardSuit, cardNumber);
		
		return new kata.pokerhand.Card( (Figure)cardNumber.get(0), (Suit)cardSuit.get(0));
	}

	/**
	 * 
	 * @param cardSuit
	 * @param cardNumber
	 */
	private static void validateFirst(ArrayList<Enum<?>> cardSuit, ArrayList<Enum<?>> cardNumber) {
		assertTrue(!cardSuit.isEmpty());
		assertTrue(!cardNumber.isEmpty());

		assertTrue(cardSuit.get(0) instanceof Suit);
		assertTrue(cardNumber.get(0) instanceof Figure);
	}
	
	/**
	 * 
	 * @param number
	 * @param suit
	 * @return
	 */
	public static kata.pokerhand.Card buildCard(Object number, Object suit)  {
		assertTrue(number != null);
		assertTrue(suit != null);
				
		return  new kata.pokerhand.Card( (Figure)number, (Suit)suit);
	}
	
	/**
	 * 
	 * @param number
	 * @param suit
	 * @return
	 */
	public static kata.pokerhand.Card buildCard(Figure number, Suit suit)  {
		assertTrue(number != null);
		assertTrue(suit != null);
				
		return  new kata.pokerhand.Card( number, suit);
	}
	
	/**
	 * 
	 * @param origHand
	 * @return
	 * @throws Exception 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static kata.pokerhand.Card[] getKataHand(ArrayList<za.co.pokerface.baseCard.Card> origHand) 
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, Exception { 
		kata.pokerhand.Card[] kataHandArray = new kata.pokerhand.Card[ origHand.size() ];
		int x = 0;
		
		for (Card cardObj : origHand) { 
			kata.pokerhand.Card kataCard = PokerMasterCardGenerator.buildCard( cardObj );
			kataHandArray[x++] = kataCard;
		}
		
		return kataHandArray;
	
	}
	
	
}
