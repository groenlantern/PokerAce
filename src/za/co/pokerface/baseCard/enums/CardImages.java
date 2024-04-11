package za.co.pokerface.baseCard.enums;

import java.util.Arrays;
import java.util.Optional;

import com.google.common.base.Strings;

/**
 * This enum is used to contain all ASCII art images with placeholder to replace numbers/rank and suit
 * 
 * Placeholders are hard coded within the static fields and images for ease of setup/config
 * 
 * @author Jean-Pierre Erasmus
 *
 *	Shadow Image
 *	ACE_CARD("Ace Card", "A", new String[] 
               {"┌─────────╖" ,  
			    "│N        ║" ,
			    "│X        ║" ,
			    "│         ║" ,
			    "│    X    ║" ,
			    "│         ║" ,
			    "│        N║" ,
			    "│        X║" , 
				"╘═════════╝"} ),
 */
public enum CardImages  {
	
	
	ACE_CARD("Ace Card", "A", new String[] 
			                   {"╭─────────╮" ,  
							    "│N        │" ,
							    "│X        │" ,
							    "│         │" ,
							    "│    X    │" ,
							    "│         │" ,
							    "│        N│" ,
							    "│        X│" , 
							    "╰─────────╯"}, "N" ),

	DOS_CARD("Two Card", "2", new String[]
							  {"╭─────────╮" ,
							   "│N   X    │" ,
							   "│X        │" ,
							   "│         │" ,
							   "│         │" , 
							   "│         │" ,
							   "│        N│" ,
							   "│    X   X│" ,
							   "╰─────────╯"}, "N" ),

	THREE_CARD("Three Card", "3", new String[]
								  {"╭─────────╮" , 
								   "│N   X    │" ,
								   "│X        │" ,
								   "│         │" ,
								   "│    X    │" ,
								   "│         │" ,
								   "│        N│" ,
								   "│    X   X│" ,
								   "╰─────────╯"}, "N" ), 

	FOUR_CARD("Four Card", "4", new String[]
			  {"╭─────────╮" ,
			   "│N X   X  │" ,
			   "│X        │" ,
			   "│         │" ,
			   "│         │" , 
			   "│         │" ,
			   "│        N│" ,
			   "│  X   X X│" ,
			   "╰─────────╯"}, "N" ),

	FIVE_CARD("Five Card", "5", new String[]
			  {"╭─────────╮" ,
			   "│N X   X  │" ,
			   "│X        │" ,
			   "│         │" ,
			   "│    X    │" , 
			   "│         │" ,
			   "│        N│" ,
			   "│  X   X X│" ,
			   "╰─────────╯"} , "N"),

	SIX_CARD("Six Card", "6", new String[]
			  {"╭─────────╖" ,
			   "│N X   X  │" ,
			   "│X        │" ,
			   "│         │" ,
			   "│  X   X  │" , 
			   "│         │" ,
			   "│        N│" ,
			   "│  X   X X│" ,
			   "╰─────────╯"} , "N"),

	SEVEN_CARD("Seven Card", "7", new String[]
					  {"╭─────────╮" ,
					   "│N X   X  │" ,
					   "│X        │" ,
					   "│    X    │" ,
					   "│  X   X  │" , 
					   "│         │" ,
					   "│        N│" ,
					   "│  X   X X│" ,
					   "╰─────────╯"}, "N" ),

	EIGHT_CARD("Eight Card", "8", new String[]
        			  {"╭─────────╮" ,
					   "│N X   X  │" ,
					   "│X        │" ,
					   "│    X    │" ,
					   "│  X   X  │" , 
					   "│    X    │" ,
					   "│        N│" ,
					   "│  X   X X│" ,
					   "╰─────────╯"}, "N" ),         
	
	NINE_CARD("Nine Card", "9", new String[]
			  {"╭─────────╮" ,
			   "│N X   X  │" ,
			   "│X        │" ,
			   "│  X   X  │" ,
			   "│    X    │" , 
			   "│  X   X  │" ,
			   "│        N│" ,
			   "│  X   X X│" ,
			   "╰─────────╯"}, "N" ),         

	TEN_CARD("Ten Card", "10", new String[]
					  {"┌─────────╮" ,
					   "│NNX   X  │" ,
					   "│X   X    │" ,
					   "│  X   X  │" ,
					   "│         │" , 
					   "│  X   X  │" ,
					   "│    X  NN│" ,
					   "│  X   X X│" ,
					   "╰─────────╯"}, "NN" ),         
	
	FACE_CARD("Face Card", "$", new String[]
			  {"╭─────────╮" ,
			   "│N        │" ,
			   "│X        │" ,
			   "│         │" ,
			   "│    X    │" , 
			   "│         │" ,
			   "│        N│" ,
			   "│        X│" ,
			   "╰─────────╯"}, "N" )
,         
	

BACK_CARD("Card BACK", "@", new String[]
		  {"╭─────────╮" ,		   
		   "│v♠v♠v♠v♠v│" ,		   
		   "│v♣_,-._♣v│" ,
		   "│%/ \\_/ \\%│" ,
		   "│♦>-(_)-<♦│" ,
		   "│%\\_/ \\_/%│" ,
		   "│v♣%`-'%♣v│" ,		   
		   "│♥v♥v♥v♥v♥│" ,        
		   "╰─────────╯"}, " " )
,         
 
	LABEL_CARD("Label/Joker Card", "JOKER", new String[]
			  {"╭─────────╮" ,
			   "│JOKER    │" ,
			   "│         │" ,
			   "│         │" ,
			   "│  JOKER  │" , 
			   "│         │" ,
			   "│         │" ,
			   "│    JOKER│" ,
			   "╰─────────╯"},"JOKER" ),

	KING_CARD1("King Card 1", "K", new String[]
			  		 {"╭─────────╮" ,
					  "│K  \\\\Θ// │" ,
					  "│X  |0 0Ʌ │" ,
					  "│   \\ Ɛ ╫ │" ,
					  "│~~~~X~~~~│" ,
					  "│ ╫ 3 \\   │" ,
					  "│ V0 0|  K│" ,
					  "│ //Θ\\\\  X│" ,
			  		  "╰─────────╯"} ),

	KING_CARD2("King Card 2", "K", new String[]
				   {"╭─────────╮" ,
	  				"│K  \\ɅɅɅ/ │" ,
	  				"│X  |ό όƐ │" ,
	  				"│   ( ¿ ║ │" ,
	  				"│~~~~X~~~~│" ,
	  				"│ ║ ? )   │" ,
	  				"│ 3ϙ ϙ|  K│" ,
	  				"│ /VVV\\  X│" ,
				    "╰─────────╯"} ),
	
	QUEEN_CARD1("Queen Card 1", "Q", new String[]
					   {"╭─────────╮" ,
						"│Q  @@@\\\\ │" ,
						"│X  (ό ό% │" ,
						"│  @\\ ₔ ╤ │" ,
						"│~~~~X~~~~│" ,
						"│ ╧ ₑ \\@  │" ,
						"│ %ϙ ϙ)  Q│" ,
						"│ \\\\@@@  X│" ,
						"╰─────────╯"} ),


	QUEEN_CARD2("Queen Card 2", "Q", new String[]
				  {"╭─────────╮" ,
				   "│Q  /Ʌ0\\\\ │" ,
				   "│X  {ϙ ϙ\\ │" ,
				   "│  $( d \\ │" ,
				   "│~~~~X~~~~│" ,
				   "│ \\ p )$  │" ,
				   "│ \\ό ό}  Q│" ,
				   "│ \\\\0V/  X│" ,
		   		   "╰─────────╯"} ),

	JACK_CARD1("Jack Card 1", "J", new String[]
		  		 {"╭─────────╮" ,
				  "│J  <###: │" ,
				  "│X  (ό ϙ╫ │" ,
				  "│   ( d Ɛ │" ,
				  "│~~~~X~~~~│" ,
				  "│ 3 p )   │" ,
				  "│ ╫ό ϙ)  J│" ,
				  "│ :###>  X│" , 
		  		  "╰─────────╯"} ),

	JACK_CARD2("Jack Card 2", "J", new String[]
	  		 		{"╭─────────╮" ,
	  				 "│J  /WWW\\ │" ,
	  				 "│X  |0 ό< │" ,
	  				 "│  @( Ɛ ║ │" ,
	  				 "│~~~~X~~~~│" ,
	  				 "│ ║ 3 )@  │" ,
	  				 "│ >ϙ 0|  J│" ,
	  				 "│ \\MMM/  X│" ,
	  		 		 "╰─────────╯"} ),

	JOKER_CARD("Joker Card", "Joker", new String[]
			  {"╭─────────╮" , 
			   "│J   o    │" ,
			   "│O  /*\\   │" ,
			   "│K /_*_\\ J│" ,
			   "│E ('o') O│" ,
			   "│R {^*^} K│" ,
			   "│  [ * ] E│" ,
			   "│  d```b R│" ,
			   "╰─────────╯"} ), 

	;
 
          
	private String label;
	private String defaultRank;
	private String[] asciiTemplate;
	private String rank_placeholder;
	
	/**
	 * 
	 * @param label
	 * @param defaultRank
	 * @param asciiTemplate
	 */
	CardImages(String label, String defaultRank, String[] asciiTemplate) {
		this.label = label;
		this.setDefaultRank(defaultRank);
		this.setAsciiTemplate(asciiTemplate);
		this.rank_placeholder ="NULL";
	}

	/**
	 * 
	 * @param label
	 * @param defaultRank
	 * @param asciiTemplate
	 * @param rank_placeholder
	 */
	CardImages(String label, String defaultRank, String[] asciiTemplate, String rank_placeholder) {
		this.label = label;
		this.setDefaultRank(defaultRank);
		this.setAsciiTemplate(asciiTemplate);
		this.rank_placeholder = rank_placeholder;
	}

	@Override
	public String toString() {
		return label;
	}

	public String getLabel() {
		return label;
	}

	public String getDefaultRank() {
		return defaultRank;
	}

	private void setDefaultRank(String defaultRank) {
		this.defaultRank = defaultRank;
	}

	public String[] getAsciiTemplate() {
		return asciiTemplate;
	}

	public static String SUIT_PLACEHOLDER = "X";
	
	/**
	 * Get the template with rank and suit inserted
	 * @param rank
	 * @param suit
	 * @return
	 */
	public String[] getAsciiTemplate(Optional<String> rank, String suit) {
				
		String rankPadded =  Strings.padStart( ((rank.isEmpty())? defaultRank : rank.get()), this.rank_placeholder.length(), ' ')
				.substring(0,this.rank_placeholder.length());
		String suitPadded =  Strings.padStart( (suit == null? "" : suit), SUIT_PLACEHOLDER.length(), ' ').substring(0,SUIT_PLACEHOLDER.length());
		
		return Arrays.stream(asciiTemplate)
			        .map(s -> s.replaceAll(this.rank_placeholder, rankPadded)
			        		   .replaceAll(SUIT_PLACEHOLDER, suitPadded))
			        .toArray(String[]::new);
		
	}


	/**
	 * 
	 * @param suit
	 * @return
	 */
	public String[] getAsciiTemplate(String suit) {
						
		return getAsciiTemplate(Optional.of(defaultRank), suit);
	}

	private void setAsciiTemplate(String[] asciiTemplate) {
		this.asciiTemplate = asciiTemplate;
	}


}
