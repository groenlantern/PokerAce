package za.co.pokerface.standardDeck;
import java.util.Comparator;

import za.co.pokerface.baseCard.Card;

/**
 * 
 * @author F5156165
 *
 */
public class SortbySuite implements Comparator<Card> {
	public int compare(Card a, Card b) {
		return Integer.compare(a.getCardSuite().getRank(),b.getCardSuite().getRank()) ;
	}
}