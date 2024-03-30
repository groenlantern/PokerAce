package za.co.pokerface.baseCard.enums;

import kata.pokerhand.bean.Suit;
import za.co.pokerface.baseCard.ICardInfo;
import za.co.pokerface.standardDeck.exceptions.NoSuitFoundException;

/**
 * @author Jean-Pierre Erasmus
 *
 */
public enum SuitMapping implements IMapperEnum {

	DIAMOND_MAP(Suites.DIAMONDS, Suit.DIAMOND),
	CLUB_MAP(Suites.CLUBS, Suit.CLUB),
	SPADE_MAP(Suites.SPADES, Suit.SPADE),
	HEART_MAP(Suites.HEARTS, Suit.HEART);

	private Suites referenceSuitInternal;
	private Enum<?> referenceSuitExternal;

	/**
	 * 
	 * @param internalReference
	 * @param extReference
	 */
	SuitMapping(Suites internalReference, Enum<?> extReference) {
		this.referenceSuitInternal = internalReference;
		this.referenceSuitExternal = extReference;
	}

	@Override
	public Enum<? extends ICardInfo> getReferenceInternal() {
		return referenceSuitInternal;
	}

	@Override
	public Enum<?> getReferenceExternal() {
		return referenceSuitExternal;
	}

	@Override
	public Exception getException() {
		return new NoSuitFoundException("");
	}

	@Override
	public boolean isMapped(Object... hand) {
		return true;
	}

	@Override
	public Enum<? extends IMapperEnum> getFromInternalRef(Enum<? extends ICardInfo> internalRef) throws Exception {
		return null;
	}

}
