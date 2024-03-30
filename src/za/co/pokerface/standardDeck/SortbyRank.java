package za.co.pokerface.standardDeck;
import java.util.Comparator;

import za.co.pokerface.baseCard.Card;
import za.co.pokerface.baseCard.ICardInfo;

/**
	 * 
	 * @author Jean-Pierre Erasmus
	 *
	 */
public class SortbyRank implements Comparator<Card> {
	public int compare(Card a, Card b) {
		return Integer.compare(  ((ICardInfo)  a.getCardRank()).getRank(), ((ICardInfo)  b.getCardRank()).getRank());
	}
}

