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
 * Converter methods for usage of Kata Poker Master library
 * 
 * @author Jean-Pierre Erasmus
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

		validateFindFirstKata(cardSuit, cardNumber);
		
		return new kata.pokerhand.Card( (Figure)cardNumber.get(0), (Suit)cardSuit.get(0));
	}

	/**
	 * This method aims to find the equiv. enums from kata matched to the poer ace internal enums. 
	 * 
	 * @param cardSuit
	 * @param cardNumber
	 */
	private static void validateFindFirstKata(ArrayList<Enum<?>> cardSuit, ArrayList<Enum<?>> cardNumber) {
		assertTrue(!cardSuit.isEmpty());
		assertTrue(!cardNumber.isEmpty());
		
		/**
		 * Find the first suit mapping that is a Kata Suit
		 * If elem 0 is not kata, remove and test next elem. 
		 */
		for (Enum<?> enmO : cardSuit) { 
			try { 
				assertTrue(cardSuit.get(0) instanceof Suit);
				break;
			} catch (Throwable thex) { 
				cardSuit.remove(0);
			}
		}
		//Same logic for card numbers as for suit - find first kata 
		for (Enum<?> enmO : cardNumber) { 
			try { 
				assertTrue(cardNumber.get(0) instanceof Figure);
				break;
			} catch (Throwable thex) { 
				cardNumber.remove(0);
			}
		}
		
		assertTrue(!cardSuit.isEmpty());
		assertTrue(!cardNumber.isEmpty());
		
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
