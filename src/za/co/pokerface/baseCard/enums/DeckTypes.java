package za.co.pokerface.baseCard.enums;
import za.co.pokerface.baseCard.DeckOfCards;
import za.co.pokerface.standardDeck.StandardDeck;

/**
 * 
 * @author Jean-Pierre Erasmus
 *
 */
public enum DeckTypes {
	STANDARD(new StandardDeck());

	private DeckOfCards deck;
 
	
	/**
	 * 
	 * @param symbol
	 */
	DeckTypes(DeckOfCards deck) { 
		this.deck = deck;
	}


	public DeckOfCards getDeckDefinition() {
		return deck;
	}
	
}
