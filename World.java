import java.applet.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

public class World extends Applet implements KeyListener

{
	private static final long serialVersionUID = 1L;
	public static Ellipse2D.Double Petey;
	public static Rectangle.Double Bamboo;
	public static Rectangle.Double Person;
	public static Rectangle.Double Ground;
	public static boolean ingame = false;
	public static int counter = 0;
	private Image dbImage;
	private Graphics dbg;

	private Menu menu;
/**
*Creates two states of the app: a menu state when the user runs the game and a game state when 
* the user hits play on the menu.
*/



	/**
	 * this paints the screen off the screen and then paints it on the applet screen so that there
 * is no lag
	 * Takes in the parameter g which loads in the graphics and processes them for use
	 */
	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);

	}

	/**Allows 
	* the switching between Menu and Game state. If game state is true, then the game 
* graphics are painted
	*/
		public void paintComponent(Graphics g) 
	{
			 if(!ingame)
			{
					setSize(500,500);
				menu.render(g); 
			} 

		super.paint(g);
		setSize(500, 500);
		Graphics2D g2 = (Graphics2D) g;
		
	
		
		g2.setColor(Color.GREEN);
		g2.setColor(new Color(27,111,28,200));
		g2.fill(Ground);
		
		Font fnt0 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt0);
		if (ingame)
		{
	
			PlayGame(g2);
			g.drawString("Petey ate " + counter +" bamboos!", 10, 20);
			if(counter ==0)
			g.drawString("Press Space to Jump", 10, 60);
			
			if (counter == 0)
			{
				g.drawString("Level One", 10, 40);
			}
			else if (counter == 11)
			{
				g.drawString("Level Two", 10, 40);
			}
			else if (counter == 21)
			{
				g.drawString("Level Three", 10, 40);
			}
			
			
		}
		
		
		
		Image panda = getImage(getCodeBase(), "panda.png");
		
		 g.drawImage(panda, (int)(Petey.getX()), (int)(Petey.getY()), 75, 75, this);

		Image person = getImage(getCodeBase(), "Person.png");
		 g.drawImage(person, (int)(Person.getX()), (int)(Person.getY()), 75,75, this);

	Image bamboo = getImage(getCodeBase(), "bamboo.png");
		 g.drawImage(bamboo, (int)(Bamboo.getX()), (int)(Bamboo.getY()), 75,75, this);

	
	
		
			}



	/**
	 * constantly repaints the screen in order to reduce buffering time and flickering
	 */
	public void update(Graphics g) {
		paint(g);
	}

	/**
	 * initializes the Ground, Petey the panda, Person, and bamboo as well as the sky
	 */
	public void init() {
		{
		menu = new Menu();
		
	}
		
		
		addKeyListener(this);
		setBackground(new Color(126, 192, 238, 250));

		Ground = new Rectangle.Double(0, 400, 500, 100);
		Petey = new Ellipse2D.Double(25, 370, 30, 30);
		Person = new Rectangle.Double(400, 370, 30, 30);
		Bamboo = new Rectangle.Double(450, 370, 30, 30);

	}

	/**
	 * starts the game
	 */
	public void GameInit() {
		ingame = true;
		repaint();
	}

	/**
	 * if the space key is pressed the panda will move up and stay up until the space key is released
	 * if the s key is pressed the game starts
	 * takes in parameter of a specified key
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE: {
			Petey.setFrame(25, 290, 30, 30);
			repaint();
			break;
		}
		}

		switch (e.getKeyCode()) {
		case KeyEvent.VK_S: {
			ingame = true;
			GameInit();
			break;
		}
		}

	}

	/**
	 * if space is released the panda drops from its height
	 */
	@Override
	public void keyReleased(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE: {
			Petey.setFrame(25, 370, 30, 30);
			repaint();
			break;
		}

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}


/**
 * When the Game starts
 * The bamboo and the person are "moving" towards Petey
 * If Petey touches a bamboo, he eats it
 * If a Person touches Petey, Petey dies
 * More People and bamboos keep respawning when they die/move offscreen
 * when Petey eats 11 bamboos, he goes to level two (which makes everything move faster)
 *  when Petey eats 21 bamboos, he goes to level three 
 */
	public void PlayGame(Graphics2D g2d) {

		if (Math.abs(Petey.getX() - Person.getX()) < 2
				&& Math.abs(Petey.getY() - Person.getY()) < 2) {
			Petey.setFrame(1000, 370, 30, 30);
			ingame = false;
			System.out.println("YOU LOSE");
			System.exit(0);

		}

		if (Math.abs(Petey.getX() - Bamboo.getX()) < 2
				&& Math.abs(Petey.getY() - Bamboo.getY()) < 2) {
			Bamboo.setRect(510 - (int) Math.random() * 10, 370, 30, 30);
			counter++;
			
		}
		if (Bamboo.x < - 30)
		{
		Bamboo.setRect(510 - (int) Math.random() * 10, 370, 30, 30);
		}
		if (Person.x < -30) {
			Person.setRect(540 + (int) Math.random() * 10, 370, 30, 30);
		}
		
	
		
		if (counter <=10)
		{
		Person.setRect(Person.x - 2, 370, 30, 30);
		Bamboo.setRect(Bamboo.x - 1.5, 370, 30, 30);
		}
		else if ( counter <=20)
		{
			Person.setRect(Person.x - 2.5, 370, 30, 30);
			Bamboo.setRect(Bamboo.x - 2, 370, 30, 30);
		}
		else if (counter >= 21)
		{
			Person.setRect(Person.x - 3, 370, 30, 30);
			Bamboo.setRect(Bamboo.x - 2.5, 370, 30, 30);
		}
		repaint();
		stop();

	}

}

