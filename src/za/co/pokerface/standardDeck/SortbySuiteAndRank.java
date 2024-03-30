package za.co.pokerface.standardDeck;
import java.util.Comparator;

import za.co.pokerface.baseCard.Card;

/**
 * 
 * @author Jean-Pierre Erasmus
 *
 */
public class SortbySuiteAndRank implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        int suitCompare = new SortbySuite().compare(o1, o2);  	        		
        
        return (suitCompare != 0)? suitCompare : new SortbyRank().compare(o1, o2);
    }
}