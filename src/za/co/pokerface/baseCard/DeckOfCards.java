package za.co.pokerface.baseCard;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import za.co.pokerface.baseCard.enums.DeckTypes;
import za.co.pokerface.standardDeck.SortbyRank;
import za.co.pokerface.standardDeck.SortbySuiteAndRank;

public abstract class DeckOfCards {
		 
	HashSet<Card> pipCards;
	HashSet<Card> faceCards;
	HashSet<Card> extraCards;
	ShuffleDelegate shuffleAlgorithm;
	
	/**
	 * 
	 */
	public DeckOfCards() { 
		buildDeck();
	}
	
	public HashSet<Card> getPipCards() {
		return pipCards;
	}
	public void setPipCards(HashSet<Card> pipCards) {
		this.pipCards = pipCards;
	}
	public HashSet<Card> getFaceCards() {
		return faceCards;
	}
	public void setFaceCards(HashSet<Card> faceCards) {
		this.faceCards = faceCards;
	}
	public HashSet<Card> getExtraCards() {
		return extraCards;
	}
	public void setExtraCards(HashSet<Card> extraCards) {
		this.extraCards = extraCards;
	}
	
	public abstract void buildDeck();
	
	public abstract ShuffleDelegate getShuffleMethod();
	public abstract String getDeckName();
	public abstract String getDescription();
	public abstract DeckTypes getDeckType();
	
	/**
	 * 
	 * @param deck
	 * @return
	 */
    public ArrayList<Card> shuffle( ArrayList<Card> deck ) { 
    	return getShuffleMethod().shuffleDeck( deck );
    }
        
    
	/**
	 * 
	 * @return
	 */
	public int getFullDeckSize() { 
		return 	((pipCards != null?pipCards.size():0) + 
					(faceCards != null?faceCards.size():0) + 
						(extraCards != null?extraCards.size():0));
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Card> getDeck() {
		ArrayList<Card> deck = new ArrayList<>();
		
		deck.addAll( pipCards != null?pipCards:new ArrayList<>() );	
		deck.addAll( faceCards != null?faceCards:new ArrayList<>());
		deck.addAll( extraCards != null?extraCards:new ArrayList<>());
		
		return deck;
	}
	
	/**
	 * 
	 * @param deck
	 * @param handSize
	 * @return
	 */
	public static ArrayList<Card> drawCards(ArrayList<Card> deck, int handSize, ArrayList<Card> hand) {		
		for (int x = 0; x<handSize; x++) {
			hand.add( deck.remove(0) );
			
		}
		
		return hand;
	}
	
	/**
	 * 
	 * @param deck
	 * @param hand
	 * @return
	 */
	public static ArrayList<Card> returnCards(ArrayList<Card> deck, ArrayList<Card> hand) {
		int handSize = hand.size();
		
		for (int x = 0; x< handSize; x++) { 
			if ( !deck.contains( hand.get(0))) { 
				deck.add( hand.remove(0) );
			}
		}
				
		return deck;
	}
	
 
	/**
	 * 
	 * @param deck
	 * @return
	 */
	public static ArrayList<Card> sort(ArrayList<Card> deck) {
		return new ArrayList<Card>(  deck.stream()
									.sorted( new SortbySuiteAndRank() )
										.collect( Collectors.toList()));
	}
	

	/**
	 * 
	 * @param deck
	 * @return
	 */
	public static ArrayList<Card> sortRankOnly(ArrayList<Card> deck) {
		return new ArrayList<Card>(  deck.stream()
									.sorted( new SortbyRank() )
										.collect( Collectors.toList()));
	}
			
}
