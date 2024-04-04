package za.co.pokerface.baseCard.enums;

import kata.pokerhand.bean.Figure;
import za.co.pokerface.baseCard.ICardInfo;
import za.co.pokerface.standardDeck.exceptions.NoPIPFoundException;

/**
 * @author Jean-Pierre Erasmus
 *
 */
public enum CardValueMapping implements IMapperEnum {

	ACE_MAP(StandardPipCards.ACE, Figure.ACE),
	TWO_MAP(StandardPipCards.DUECE, Figure.TWO),
	THREE_MAP(StandardPipCards.TREY, Figure.THREE),
	FOUR_MAP(StandardPipCards.FOUR, Figure.FOUR),
	FIVE_MAP(StandardPipCards.FIVE, Figure.FIVE),
	SIX_MAP(StandardPipCards.SIX, Figure.SIX),
	SEVEN_MAP(StandardPipCards.SEVEN, Figure.SEVEN),
	EIGHT_MAP(StandardPipCards.EIGHT, Figure.EIGHT),
	NINE_MAP(StandardPipCards.NINE, Figure.NINE),
	TEN_MAP(StandardPipCards.TEN, Figure.TEN),
	JACK_MAP(StandardFaceCards.JACK, Figure.JACK),
	QUEEN_MAP(StandardFaceCards.QUEEN, Figure.QUEEN),
	KING_MAP(StandardFaceCards.KING, Figure.KING),
	JOKER_MAP(ExtraCards.JOKER, null);
	
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
