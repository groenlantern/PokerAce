package za.co.pokerface.baseCard;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import za.co.pokerface.baseCard.enums.DeckTypes;
import za.co.pokerface.standardDeck.SortbyRank;
import za.co.pokerface.standardDeck.SortbySuiteAndRank;
import za.co.pokerface.standardDeck.exceptions.NoDeckFoundException;
import za.co.pokerface.standardDeck.exceptions.NoHandFoundException;

/**
 * Base for a deck of cards definition
 * 
 * @author Jean-Pierre Erasmus
 * groenlantern@gmail.com
 *
 */
public abstract class DeckOfCards {
		 
	/**
	 * PIP - Number cards
	 * Extra - Jokers etc. when applicable
	 * 
	 */
	
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
			if (deck.size() > 0)
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

		if (isEmpty(deck, hand)) return deck;

		return  returnCards( deck, hand,handSize );
	}

	/**
	 * 
	 * @param deck
	 * @param hand
	 * @return
	 */
	private static boolean isEmpty(ArrayList<Card> deck, ArrayList<Card> hand) {
		if (deck == null || deck.isEmpty() || hand == null || hand.isEmpty()) {
			return true;
		}
		
		return false;
	}
	

	/**
	 * 
	 * @param deck
	 * @param hand
	 * @param numberOfCards
	 * @return
	 */
	public static ArrayList<Card> returnCards(ArrayList<Card> deck, ArrayList<Card> hand, int numberOfCards) {
		 
		if (isEmpty(deck, hand)) return deck;
		
		if (numberOfCards > hand.size()) numberOfCards = hand.size();
		
		System.out.println("Returning " + numberOfCards + " cards to deck...");
		
		for (int x = 0; x< numberOfCards; x++) {
			if ( hand.size() < 1) break;
			
			addCardBackToDeck(deck, hand, 0);
		}
				
		return deck;
	}

	/**
	 * 
	 * @param deck
	 * @param hand
	 * @param numberOfCards
	 * @return
	 */
	public static ArrayList<Card> returnCard(ArrayList<Card> deck, ArrayList<Card> hand, Card cardObj) {
		 
		if (isEmpty(deck, hand)) return deck;

		if (!hand.contains( cardObj)) { 
			System.out.println("No card to return");
			
			return deck;
		}
		
		System.out.println("Returning " + ((ICardInfo) cardObj.getCardRank()).getSymbol() +  cardObj.getCardSuite().symbol + " card to deck...");
		
		addCardBackToDeck(deck, hand, cardObj);
				
		return deck;
	}

	/**
	 * 
	 * @param deck
	 * @param hand
	 * @param indexNo
	 * @return
	 * @throws NoDeckFoundException 
	 * @throws NoHandFoundException 
	 */
	public static Card getCardTemp(ArrayList<Card> deck, ArrayList<Card> hand, int indexNo) throws NoDeckFoundException, NoHandFoundException {
		 
		if (isEmpty(deck, hand)) throw new NoDeckFoundException("No Deck/Hand");
		
		if (indexNo >= hand.size()) throw new NoHandFoundException("Card not found in Hand") ;
		
		if (  !deck.contains( hand.get(indexNo))) return hand.get(indexNo) ;
		
		throw new NoHandFoundException("Card not found in Hand");
	}

	/**
	 * 
	 * @param deck
	 * @param hand
	 * @param indexNo
	 */
	private static void addCardBackToDeck(ArrayList<Card> deck, ArrayList<Card> hand, Card cardObj) {
		if (  !deck.contains( cardObj ))  
				deck.add( cardObj  );
		
		if (  hand.contains( cardObj ))
			hand.remove(cardObj);
	}

	/**
	 * 
	 * @param deck
	 * @param hand
	 * @param indexNo
	 */
	private static void addCardBackToDeck(ArrayList<Card> deck, ArrayList<Card> hand, int indexNo) {
		if (  !deck.contains( hand.get(indexNo))) { 
			deck.add( hand.remove(indexNo) );
		}
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
