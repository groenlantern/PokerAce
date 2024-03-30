package za.co.pokerface.baseCard;

import java.awt.image.BufferedImage;

/**
 * 
 * @author Jean-Pierre Erasmus 
 * 
 * This interface should only be implemented by ENUM's
 *
 */
public interface ICardInfo {
	public BufferedImage getImage();
	public BufferedImage getIcon();
	public String getSymbol();
	public int getRank();
	public int getRank_secondary();
	
}
