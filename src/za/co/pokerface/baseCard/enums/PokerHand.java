package za.co.pokerface.baseCard.enums;

import java.awt.image.BufferedImage;

import za.co.pokerface.baseCard.IComboHand;

/**
 * 
 * @author Jean-Pierre Erasmus
 *
 */
public enum PokerHand implements IComboHand {

	HIGH_CARD("High Cards",9),
	PAIR("One Pair",8),
	TWO_PAIRS("Two Pair",7),
	THREE_OF_A_KIND("Three of a Kind",6),
	STRAIGHT("Straight",5),
	FLUSH("Flush",4),
	FULL_HOUSE("Full House",3),
	FOUR_OF_A_KIND("Four of a Kind",2),
	STRAIGHT_FLUSH("Straight Flush",1);

	private String label;
	private int rank;
	
	/**
	 * 
	 * @param label
	 * @param rank
	 */
	PokerHand(String label, int rank) {
		this.label = label;
		this.rank = rank;
	}

	@Override
	public String toString() {
		return label;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public int getRank() {
		return rank;
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
	public String getSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRank_secondary() {
		// TODO Auto-generated method stub
		return 0;
	}


}
