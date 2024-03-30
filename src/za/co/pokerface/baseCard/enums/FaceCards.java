package za.co.pokerface.baseCard.enums;
import java.awt.image.BufferedImage;

import za.co.pokerface.baseCard.ICardInfo;

/**
 * 
 * @author Jean-Pierre Erasmus
 *
 */
public enum FaceCards implements ICardInfo {
	JACK("J", 11, 11), 
	QUEEN("Q", 12, 12), 
	KING("K", 13, 13);

	public String symbol;
	public int rank;
	public int rank_secondary;
	
	/**
	 * 
	 * @param symbol
	 * @param rank 
	 */
	FaceCards(String symbol, int rank, int rank_secondary) { 
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
	
	
}
