package za.co.pokerface.standardDeck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import za.co.pokerface.baseCard.Card;
import za.co.pokerface.baseCard.DeckOfCards;
import za.co.pokerface.baseCard.ICardInfo;
import za.co.pokerface.baseCard.ShuffleDelegate;
import za.co.pokerface.baseCard.enums.DeckTypes;
import za.co.pokerface.baseCard.enums.StandardFaceCards;
import za.co.pokerface.baseCard.enums.StandardPipCards;
import za.co.pokerface.baseCard.enums.Suites;

/**
 * Defines and builds a standard deck of 52 cards - no jokers
 * @author Jean-Pierre Erasmus
 *
 */
public class StandardDeck extends DeckOfCards {

	
	/**
	 * Build method to initiate a standard deck of cards
	 */
	@Override
	public void buildDeck() {
		 
		HashSet<Card> pipCardsTmp = new HashSet<>();
		HashSet<Card> faceCardsTmp = new HashSet<>();
		
		for (Suites suiteObj : Suites.values()) { 
			//Adds numbered cards A-10
			for (Enum<? extends ICardInfo> cardObj: StandardPipCards.values()) { 
				pipCardsTmp.add( new Card(suiteObj,cardObj));
			}		
		
			//Add Face cards Jack, Queen, King	
			for (Enum<? extends ICardInfo> cardObj: StandardFaceCards.values()) { 
				faceCardsTmp.add(new Card(suiteObj,cardObj));
			}
		
		}
		
		this.setPipCards(pipCardsTmp);
		this.setFaceCards(faceCardsTmp);
		
	}

	/**
	 * Setup shuffle algorithm
	 */
	@Override
	public ShuffleDelegate getShuffleMethod() { 
		return new ShuffleDelegate() {
            @Override
            public ArrayList<Card> shuffleDeck( ArrayList<Card> deck) {
            	Collections.shuffle( deck );
            	
            	return deck;
            }
        };		
	}

	@Override
	public String getDeckName() {
		return "Standard Deck";
	}

	@Override
	public String getDescription() {
		return "Standard Deck of 52 Cards used in Poker, no Jokers allowed.";
	}

	@Override
	public DeckTypes getDeckType() {
		return DeckTypes.STANDARD;
	}
	    
    
}
