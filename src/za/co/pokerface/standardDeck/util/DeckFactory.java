package za.co.pokerface.standardDeck.util;

import java.util.ArrayList;

import za.co.pokerface.baseCard.Card;
import za.co.pokerface.baseCard.enums.DeckTypes;

/**
 * 
 * @author Jean-Pierre Erasmus
 *
 */
public class DeckFactory {

	/**
	 * 
	 * @param selectedDeck
	 * @return
	 */
	public static ArrayList<Card> getNewDeck(DeckTypes selectedDeck) {
		ArrayList<Card> newDeck;
		switch (selectedDeck) {
			case STANDARD: newDeck = selectedDeck.getDeckDefinition().getDeck();
						   break;
			default: newDeck = selectedDeck.getDeckDefinition().getDeck();
		}
		return newDeck;
	}

}
