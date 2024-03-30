package za.co.pokerface.baseCard.enums;
import java.awt.image.BufferedImage;

import za.co.pokerface.baseCard.ICardInfo;


/**
 * 
 * @author Jean-Pierre Erasmus
 *
 */
public enum Suites implements ICardInfo {
	CLUBS("♣", 4), 
	DIAMONDS("♦", 2), 
	HEARTS("♥", 1), 
	SPADES("♠", 3);

	public String symbol;
	public int rank;
 
	
	/**
	 * 
	 * @param symbol
	 */
	Suites(String symbol, int rank) { 
		this.symbol = symbol;
		this.rank = rank; 
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
		return rank;
	}

	@Override
	public int getRank_secondary() {
		return 0;
	}
	
	
}
