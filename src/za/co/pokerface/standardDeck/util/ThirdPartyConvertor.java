package za.co.pokerface.standardDeck.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import za.co.pokerface.baseCard.Card;
import za.co.pokerface.baseCard.enums.EvaluatorLibrary;
import za.co.pokerface.standardDeck.exceptions.NoEvaluatorFoundException;

/**
 * 
 * Interface to define methods needed to use 3rd party card hand evaluator library includes
 * 
 * @author Jean-Pierre Erasmus
 *
 */
public interface ThirdPartyConvertor {

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
	Object buildCard(za.co.pokerface.baseCard.Card origCard) throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, Exception;

	/**
	 * 
	 * @param number
	 * @param suit
	 * @return
	 */
	Object buildCard(Object number, Object suit);

	/**
	 * This method aims to find the equiv. enums from kata matched to the poer ace internal enums. 
	 * 
	 * @param cardSuit
	 * @param cardNumber
	 */
	void validateFindFirst(ArrayList<Enum<?>> cardSuit, ArrayList<Enum<?>> cardNumber);

	/**
	 * 
	 */
	Object loadHand(ArrayList<Card> hand) throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, Exception;

	
	/**
	 * Factory Method to create implementation based on selected 3rd party library
	 * 
	 * @param evauluatorUtils
	 * @return
	 * @throws NoEvaluatorFoundException
	 */
	public static ThirdPartyConvertor ThirdPartyConvertorFactory(EvaluatorLibrary handEvaluator)throws NoEvaluatorFoundException {
		ThirdPartyConvertor evauluatorUtils = null;
		
		if (handEvaluator != null && handEvaluator.equals(EvaluatorLibrary.KATA)) { 
			evauluatorUtils = new PokerMasterCardGenerator();
		} else { 
			throw new NoEvaluatorFoundException("System Error : Invalid Evaluator");
		}
		
		return evauluatorUtils;
	}

}
