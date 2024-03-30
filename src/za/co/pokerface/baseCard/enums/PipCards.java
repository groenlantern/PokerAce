package za.co.pokerface.baseCard.enums;
import java.awt.image.BufferedImage;

import za.co.pokerface.baseCard.ICardInfo;

/**
 * 
 * @author Jean-Pierre Erasmus
 *
 */
public enum PipCards implements ICardInfo {
	ACE("A", 1, 14), 
	DUECE("2", 2, 2), 
	TREY("3", 3, 3),
	FOUR("4", 4, 4),
	FIVE("5", 5, 5),
	SIX("6", 6, 6),
	SEVEN("7", 7, 7),
	EIGHT("8", 8, 8),
	NINE("9", 9, 9),
	TEN("10", 10, 10);

	public String symbol;
	public int rank;
	public int rank_secondary;
	
	/**
	 * 
	 * @param symbol
	 * @param rank 
	 */
	PipCards(String symbol, int rank, int rank_secondary) { 
		this.symbol = symbol;
		this.rank = rank;
		this.rank_secondary = rank_secondary;
	}

	/**
	 * 
	 * @return
	 */
	public int getRank_secondary() {
		return rank_secondary;
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
	
	
}
