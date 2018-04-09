import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * This is the Menu class. It provides a home screen to the applet prior to the
 * running of the game.
 */
public class Menu {
	

	/**
	 * Creates a welcoming text and a play button when applet is in menu state.
	 */
	public void render(Graphics g)
	{
		
		Font fnt0 = new Font("Garamond", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.black);
		g.drawString("PETEY the PANDA", 25,250);
		Font fnt1 = new Font("Garamond", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Press S to Start", 10, 30);
		
	}

	
}