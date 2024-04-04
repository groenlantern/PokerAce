package za.co.pokerface.baseCard.enums;
import java.awt.image.BufferedImage;

import za.co.pokerface.baseCard.ICardInfo;

/**
 * 
 * @author Jean-Pierre Erasmus
 *
 */
public enum StandardPipCards implements ICardInfo {
	ACE("Á", 1, 14) { 
		@Override
		public CardImages cardImage() {
			return CardImages.ACE_CARD;
		}
	}, 
	DUECE("2", 2, 2){ 
		@Override
		public CardImages cardImage() {
			return CardImages.DOS_CARD;
		}
	},  
	TREY("3", 3, 3){ 
		@Override
		public CardImages cardImage() {
			return CardImages.THREE_CARD;
		}
	}, 
	FOUR("4", 4, 4){ 
		@Override
		public CardImages cardImage() {
			return CardImages.FOUR_CARD;
		}
	}, 
	FIVE("5", 5, 5){ 
		@Override
		public CardImages cardImage() {
			return CardImages.FIVE_CARD;
		}
	}, 
	SIX("6", 6, 6){ 
		@Override
		public CardImages cardImage() {
			return CardImages.SIX_CARD;
		}
	}, 
	SEVEN("7", 7, 7){ 
		@Override
		public CardImages cardImage() {
			return CardImages.SEVEN_CARD;
		}
	}, 
	EIGHT("8", 8, 8){ 
		@Override
		public CardImages cardImage() {
			return CardImages.EIGHT_CARD;
		}
	}, 
	NINE("9", 9, 9){ 
		@Override
		public CardImages cardImage() {
			return CardImages.NINE_CARD;
		}
	}, 
	TEN("10", 10, 10){ 
		@Override
		public CardImages cardImage() {
			return CardImages.TEN_CARD;
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
	StandardPipCards(String symbol, int rank, int rank_secondary) { 
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
