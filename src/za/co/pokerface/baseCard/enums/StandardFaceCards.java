package za.co.pokerface.baseCard.enums;
import java.awt.image.BufferedImage;

import za.co.pokerface.baseCard.ICardInfo;

/**
 * 
 * @author Jean-Pierre Erasmus
 *
 */
public enum StandardFaceCards implements ICardInfo {
	JACK("J", 11, 11){ 
		@Override
		public CardImages cardImage() {
			return CardImages.JACK_CARD1;
		}
	}, 
	QUEEN("Q", 12, 12){ 
		@Override
		public CardImages cardImage() {
			return CardImages.QUEEN_CARD1;
		}
	}, 
	KING("K", 13, 13){ 
		@Override
		public CardImages cardImage() {
			return CardImages.KING_CARD2;
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
	StandardFaceCards(String symbol, int rank, int rank_secondary) { 
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
