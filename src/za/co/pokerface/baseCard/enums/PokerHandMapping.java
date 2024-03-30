package za.co.pokerface.baseCard.enums;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import kata.pokerhand.Hand;
import kata.pokerhand.bean.Combination;
import za.co.pokerface.baseCard.Card;
import za.co.pokerface.baseCard.ICardInfo;
import za.co.pokerface.baseCard.IComboHand;
import za.co.pokerface.standardDeck.exceptions.NoHandFoundException;
import za.co.pokerface.standardDeck.util.PokerMasterCardGenerator;

/**
 * Map Internal Standard poker hand to Kata Poker hand, exposes evaluator methods from kata for each hand. 
 * 
 * @author Jean-Pierre Erasmus
 *
 */
public enum PokerHandMapping implements IMapperEnum {

	HIGH_CARD(PokerHand.HIGH_CARD, Combination.HIGH_CARD) {
		@Override
		public boolean isMapped(Object... hand) { 
			if (!validateHandObject(hand)) return false;
			
			return ((Hand) hand[0]).isHighCard();
		}

	},
	PAIR(PokerHand.PAIR, Combination.PAIR){
		@Override
		public boolean isMapped(Object... hand) { 
			if (!validateHandObject(hand)) return false;
			
			return ((Hand) hand[0]).isPair();
		}

	},

	TWO_PAIRS(PokerHand.TWO_PAIRS, Combination.TWO_PAIRS){
		@Override
		public boolean isMapped(Object... hand) { 
			if (!validateHandObject(hand)) return false;
			
			return ((Hand) hand[0]).isTwoPair();
		}

	},

	THREE_OF_A_KIND(PokerHand.THREE_OF_A_KIND, Combination.THREE_OF_A_KIND){
		@Override
		public boolean isMapped(Object... hand) { 
			if (!validateHandObject(hand)) return false;
			
			return ((Hand) hand[0]).isThreeOfKind();
		}

	},

	STRAIGHT(PokerHand.STRAIGHT, Combination.STRAIGHT){
		@Override
		public boolean isMapped(Object... hand) { 
			if (!validateHandObject(hand)) return false;
			
			return ((Hand) hand[0]).isStraight();
		}

	},

	FLUSH(PokerHand.FLUSH, Combination.FLUSH){
		@Override
		public boolean isMapped(Object... hand) { 
			if (!validateHandObject(hand)) return false;
			
			return ((Hand) hand[0]).isFlush();
		}

	},

	FULL_HOUSE(PokerHand.FULL_HOUSE, Combination.FULL_HOUSE){
		@Override
		public boolean isMapped(Object... hand) { 
			if (!validateHandObject(hand)) return false;
			
			return ((Hand) hand[0]).isFullHouse();
		}

	},

	FOUR_OF_A_KIND(PokerHand.FOUR_OF_A_KIND, Combination.FOUR_OF_A_KIND){
		@Override
		public boolean isMapped(Object... hand) { 
			if (!validateHandObject(hand)) return false;
			
			return ((Hand) hand[0]).isFourOfKind();
		}

	},

	STRAIGHT_FLUSH(PokerHand.STRAIGHT_FLUSH, Combination.STRAIGHT_FLUSH){
		@Override
		public boolean isMapped(Object... hand) { 
			if (!validateHandObject(hand)) return false;
			
			return ((Hand) hand[0]).isStraightFlush();
		}

	}, 
	
	NONE(null,null) { 
		@Override
		public boolean isMapped(Object... hand) { 
			return false;
		}
	};

	private PokerHand referenceInternal;
	private Enum<?> referenceExternal;

	/**
	 * 
	 * @param internalReference
	 * @param extReference
	 */
	PokerHandMapping(PokerHand internalReference, Enum<?> extReference) {
		this.referenceInternal = internalReference;
		this.referenceExternal = extReference;
	}

	private static boolean validateHandObject(Object... hand) {
		if (hand == null) return false;
		if (hand.length < 1) return false;
		if (hand.length > 1) return false;
		if (!(hand[0] instanceof Hand)) return false;
		
		return true;
	}
	
	@Override
	public Enum<? extends ICardInfo> getReferenceInternal() {
		return referenceInternal;
	}

	@Override
	public Enum<?> getReferenceExternal() {
		return referenceExternal;
	}
	
	/**
	 * Get Enum instance that maps to parameter enum passed in, matched to internal reference enum
	 */
	@Override
	public Enum<? extends IMapperEnum> getFromInternalRef(Enum<? extends ICardInfo> internalRef) throws Exception { 
		for (PokerHandMapping mapping: PokerHandMapping.values()) { 
			if ( mapping.getReferenceInternal().equals(internalRef)) return mapping;
		}
		
		throw new NoHandFoundException("Invalid Internal Reference Enum");
	}

	@Override
	public Exception getException() {
		return new NoHandFoundException("");
	}
 
	
	/**
	 * 
	 * @param hand
	 * @param kataHand
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws Exception
	 */
	public static Hand loadKataHand(ArrayList<Card> hand)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, Exception {

		kata.pokerhand.Card[] kataHandArray = new kata.pokerhand.Card[  hand.size() ];

		int x = 0;
		
		for (Card cardObj : hand) { 
			kata.pokerhand.Card kataCard = PokerMasterCardGenerator.buildCard( cardObj );
			kataHandArray[x++] = kataCard;
		}

		return new Hand( kataHandArray );					
		
	}

	/**
	 * 
	 * @param kataHand
	 * @return
	 * @throws Exception
	 */
	public static PokerHandMapping getHandValue(Hand kataHand) throws Exception {
		PokerHandMapping handValue = null;
		
		//Use a treemap as it is sorted by key
		TreeMap<Integer, IComboHand> handsMap = new TreeMap<>();
		//Load treemap, key is the ranking of the hand values
		for ( IComboHand handOj : PokerHand.values()) { 
			handsMap.put( handOj.getRank(), handOj);
		}
		 
		//Get the current hand value based of the hand rankings
		for (Integer key : handsMap.keySet()) {
		        @SuppressWarnings("unchecked")
				PokerHandMapping handMapper = (PokerHandMapping) PokerHandMapping.NONE.getFromInternalRef( 
		        		(Enum<? extends ICardInfo>) handsMap.get(key) );
		        
		        //First mapped value is hand value
		        if (handMapper.isMapped(kataHand)) { 
		        	handValue = handMapper;
		        	break;
		        }
		}
		
		return handValue;
	}

}
