package za.co.pokerface.baseCard.enums;

/**
 * 
 * @author Jean-Pierre Erasmus
 *
 */
public enum PokerType  {

	STANDARDPOKER("Standard Poker", DeckTypes.STANDARD, 5),
	FOURHANDDRAWPOKER("4 Hand Poker", DeckTypes.FOURHAND, 4);
	
	private String label;
	private DeckTypes deckType;
	private int drawSize;
	
/**
 * 
 * @param label
 * @param deck
 * @param drawSize
 */
	PokerType(String label, DeckTypes deck, int drawSize) {
		this.label = label;
		this.drawSize = drawSize;
		this.deckType = deck;
	}

	@Override
	public String toString() {
		return label;
	}

	public String getLabel() {
		return label;
	}

	public DeckTypes getDeckType() {
		return deckType;
	}

	public int getDrawSize() {
		return drawSize;
	}

}
