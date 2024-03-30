package za.co.pokerface.baseCard.enums;

import kata.pokerhand.bean.Figure;
import za.co.pokerface.baseCard.ICardInfo;
import za.co.pokerface.standardDeck.exceptions.NoPIPFoundException;

/**
 * @author Jean-Pierre Erasmus
 *
 */
public enum CardValueMapping implements IMapperEnum {

	ACE_MAP(PipCards.ACE, Figure.ACE),
	TWO_MAP(PipCards.DUECE, Figure.TWO),
	THREE_MAP(PipCards.TREY, Figure.THREE),
	FOUR_MAP(PipCards.FOUR, Figure.FOUR),
	FIVE_MAP(PipCards.FIVE, Figure.FIVE),
	SIX_MAP(PipCards.SIX, Figure.SIX),
	SEVEN_MAP(PipCards.SEVEN, Figure.SEVEN),
	EIGHT_MAP(PipCards.EIGHT, Figure.EIGHT),
	NINE_MAP(PipCards.NINE, Figure.NINE),
	TEN_MAP(PipCards.TEN, Figure.TEN),
	JACK_MAP(FaceCards.JACK, Figure.JACK),
	QUEEN_MAP(FaceCards.QUEEN, Figure.QUEEN),
	KING_MAP(FaceCards.KING, Figure.KING);
	
	private Enum<? extends ICardInfo> referenceCardInternal;
	private Enum<?> referenceCardExternal;

	/**
	 * 
	 * @param internalReference
	 * @param extReference
	 */
	CardValueMapping(Enum<? extends ICardInfo> internalReference, Enum<?> extReference) {
		this.referenceCardInternal = internalReference;
		this.referenceCardExternal = extReference;
	}

	@Override
	public Enum<? extends ICardInfo> getReferenceInternal() {
		return referenceCardInternal;
	}

	@Override
	public Enum<?> getReferenceExternal() {
		return referenceCardExternal;
	}

	@Override
	public Exception getException() {
		return new NoPIPFoundException("");
	}

	@Override
	public boolean isMapped(Object... hand) {
		return true;
	}

	@Override
	public Enum<? extends IMapperEnum> getFromInternalRef(Enum<? extends ICardInfo> internalRef) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
