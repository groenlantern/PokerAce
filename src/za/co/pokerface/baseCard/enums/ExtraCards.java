package za.co.pokerface.baseCard.enums;
import java.awt.image.BufferedImage;

import za.co.pokerface.baseCard.ICardInfo;

/**
 * 
 * @author Jean-Pierre Erasmus
 *
 */
public enum ExtraCards implements ICardInfo {
	JOKER("Joker", 15, 15){ 
		@Override
		public CardImages cardImage() {
			return CardImages.JOKER_CARD;
		}
	};

	public String symbol;
	public int rank;
	public int rank_secondary;
	
	/**
	 * 
	 * @param symbol
	 * @param rank 
	 */
	ExtraCards(String symbol, int rank, int rank_secondary) { 
		this.symbol = symbol;
		this.rank = rank;
		this.rank_secondary = rank_secondary;
	}

	@Override
	public String getSymbol() {		
		return this.symbol;
	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedImage getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRank() {
		return this.rank;
	}

	@Override
	public int getRank_secondary() {
		return rank_secondary;
	}

	@Override
	public CardImages cardImage() {
		return CardImages.FACE_CARD;
	}
	
	
}
