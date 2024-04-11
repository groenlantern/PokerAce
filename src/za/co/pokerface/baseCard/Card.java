package za.co.pokerface.baseCard;

import za.co.pokerface.baseCard.enums.Suites;

import static org.junit.Assert.assertTrue;

public class Card {
	
	Suites cardSuite; 
	//This interface should only be implemented by an ENUM
	Enum<? extends ICardInfo> cardRank;
	
	/**
	 * 
	 * @param suite
	 * @param cardRank
	 */
	public Card(Suites suite, Enum<? extends ICardInfo> cardRank) {
		assertTrue(cardRank instanceof Enum);
		
		this.cardRank = cardRank;
		this.cardSuite = suite;
	}
	
	public Suites getCardSuite() {
		return cardSuite;
	}
	public void setCardSuite(Suites cardSuite) {
		this.cardSuite = cardSuite;
	}
	public Enum<? extends ICardInfo> getCardRank() {
		return cardRank;
	}
	public void setCardRank(Enum<? extends ICardInfo> cardRank) {
		this.cardRank = cardRank;
	}
	
}
