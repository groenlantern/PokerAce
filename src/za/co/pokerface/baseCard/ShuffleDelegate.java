package za.co.pokerface.baseCard;
import java.util.ArrayList;

/**
 * @author Jean-Pierre Erasmus
 *
 *
 * Functional Interface for delegate usage
 *
 */
public interface ShuffleDelegate {

	ArrayList<Card> shuffleDeck(  ArrayList<Card> deck );

}
