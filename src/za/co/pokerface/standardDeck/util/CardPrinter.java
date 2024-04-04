package za.co.pokerface.standardDeck.util;

import java.util.ArrayList;
import java.util.Optional;

import com.google.common.base.Strings;

import za.co.pokerface.baseCard.Card;
import za.co.pokerface.baseCard.ICardInfo;

/**
 * 
 * @author Jean-Pierre Erasmus
 * 
 * Print card images  
 *
 *
 *	 
	//♦ │ │ ♠ │ │ ♥ │  │ ♣ │
	Stream.of(aceCard).forEach(line-> System.out.println(line.replaceAll("N", "A").replaceAll("X", "♣")) );	
	Stream.of(threyCard).forEach(line-> System.out.println(line.replaceAll("N", "A").replaceAll("X", "♣")) );
	Stream.of(dosCard).forEach(line-> System.out.println(line.replaceAll("N", "A").replaceAll("X", "♣")) );	
 */
public class CardPrinter {
	
	public final static int WIDTH_MULTIPLIER = 3; 
	
	private Optional<ArrayList<Card>> hand = null;
	private int height = 0;
	private int crdCnt = 0;
	private int fullWidth = 0;
	private int halfWidth = 0;
	private int inverseStart = 0;
	
	/**
	 * 
	 */
	public CardPrinter( Optional<ArrayList<Card>> currentHand ) { 
		this.hand = currentHand;

		height = (hand.isPresent() && hand.get().size() > 0)? ((ICardInfo) hand.get().get(0).getCardRank()).cardImage().getAsciiTemplate().length:0;
		crdCnt =  (hand.isPresent())?hand.get().size():0;
		
		fullWidth = (hand.isPresent() && hand.get().size() > 0)?((ICardInfo) hand.get().get(0).getCardRank()).cardImage().getAsciiTemplate()[0].length():0;
		halfWidth = (fullWidth / WIDTH_MULTIPLIER);
		inverseStart = fullWidth - halfWidth;	
		
	}
	
	/**
	 * Print spread hand using deck defined rank values
	 * Top card last 
	 */
	public void printHandFan() { 
		for (int row=0; row < height; row++) { 
			System.out.println("");
			
			for (int cardIx=0; cardIx < crdCnt; cardIx++) {
					
				int width = getCardPrintWidth(cardIx);
			
				Optional<String> rank = (hand.isPresent() && hand.get().size() > 0)? Optional.of(((ICardInfo) hand.get().get(cardIx).getCardRank()).getSymbol()):Optional.of("0");
				String suit = (hand.isPresent() && hand.get().size() > 0)? ((ICardInfo) hand.get().get(cardIx).getCardSuite()).getSymbol():"$";
			
				String cardImageRow = (hand.isPresent() && hand.get().size() > 0)? ((ICardInfo) hand.get().get(cardIx).getCardRank()).cardImage().getAsciiTemplate(rank,suit)[row]:"";
			
				System.out.print(cardImageRow.substring(0,width));
			}
		}

		printCardNumbers();		
	}

	/**
	 * 
	 */
	public void printCardNumbers() {
		System.out.println("");
		for (int cardIx=0; cardIx < crdCnt; cardIx++) {
					
				int width = getCardPrintWidth(cardIx);
				int padwidth = (width / 2) + 1;
				
				String cardNUmber = Strings.padStart(( "(" + (cardIx + 1) + ")"), padwidth, ' ' );
				
				cardNUmber = Strings.padEnd(cardNUmber, width, ' ');

				if (width > cardNUmber.length()) width = cardNUmber.length();
				
				System.out.print(cardNUmber.substring(0,width));
			}
		
			System.out.println("");
	}

	/**
	 * 
	 * @param cardIx
	 * @return
	 */
	private int getCardPrintWidth(int cardIx) {
		int width = ( (cardIx + 1) < crdCnt ) ?halfWidth:fullWidth;
		return width;
	}


	/**
	 * Print deck using template defaults
	 * top card right / last 
	 */
	public void printHandFanDefault() {
		System.out.println("");
		for (int row=0; row < height; row++) { 
		
			for (int cardIx=0; cardIx < crdCnt; cardIx++) { 
				int width = getCardPrintWidth(cardIx);
			
				String suit = (hand.isPresent() && hand.get().size() > 0)? ((ICardInfo) hand.get().get(cardIx).getCardSuite()).getSymbol():"  ";
			
				String cardImageRow = (hand.isPresent() && hand.get().size() > 0)? ((ICardInfo) hand.get().get(cardIx).getCardRank()).cardImage().getAsciiTemplate(suit)[row]:"";
			
				System.out.print(cardImageRow.substring(0,width));
			}
			System.out.println("");		
		}
	}

	/**
	 * Print deck top card first 
	 */
	public void printHandFanInverse() {
		System.out.println("");
		
		for (int row=0; row < height; row++) { 
		
			for (int cardIx=0; cardIx < crdCnt; cardIx++) { 
				int width = ( cardIx == 0 ) ?0:inverseStart;
				Optional<String> rank = (hand.isPresent() && hand.get().size() > 0)? Optional.of(((ICardInfo) hand.get().get(cardIx).getCardRank()).getSymbol()):Optional.of("0");
				
				String suit = (hand.isPresent() && hand.get().size() > 0)? ((ICardInfo) hand.get().get(cardIx).getCardSuite()).getSymbol():"$";
				String cardImageRow = (hand.isPresent() && hand.get().size() > 0)? ((ICardInfo) hand.get().get(cardIx).getCardRank()).cardImage().getAsciiTemplate(rank,suit)[row]:"";
								
				System.out.print(cardImageRow.substring(width, cardImageRow.length()));
			}
			System.out.println("");					
		
		}
	}
	
	/**
	 * 
	 * @param hand
	 */
	public  void printFullHand( ) {
		if (hand ==null|| hand.isEmpty() || hand.get() == null || hand.get().size() < 1) return;
		
		// Print the current hand of cards
		System.out.print("Your hand: ");

		for (Card cardObj : hand.get()) {
			System.out
					.print(" " + ((ICardInfo) cardObj.getCardRank()).getSymbol() + cardObj.getCardSuite().getSymbol());
		}
	}

	
}
