# Applet
/*
Jumping Panda
by Jenn, Quinn, Neha, and Halanna
based on The Chronium Authors (2013)
made available for use with a BSD license
https://github.com/wayou/t-rex-runner
*/

public class Level extends Applet implements Runnable, KeyListener {

    public Image offscreen;
    public Graphics d;
    private Thread th;
    public void init(){
        th = new Thread(this);
        th.start();
        offscreen = createImage(480,800);
        d = offscreen.getGraphics();
        initObjects(); //do we have this?
    }

    public void run(){}  // run is the method an Applet automatically calls after init() is done, for me it basically contains the game
    //loop which works just fine apart from the camera and is ran 60 times per second using Thread.sleep()

    // offSetY is set here as the player moves up or down (the camera only needs to scroll on the y-axis)
    //as offSetY = player.y -750; The player is supposed to always be 50 pixels from the bottom of the screen which is 800 pixels high.
    //The purpose of offSetY is to provide the delta of the players position on y axis since the last update in order to
    //determine in which direction and how much we need to move the screen.

    repaint() // calls update(), which is where the truly relevant part starts.


    public void update(Graphics g){
        d.clearRect(0, 0, 480, 800); // clears the offscreen area of whatever was there last time update() was called
        d.drawImage(background, 0, 0, this); // draws the background image on the offscreen area
        g.translate(0, -offSetY); // translate to change coordinates according to player movement, offSetY is determined by player position
        //in the game loop and is getting the "correct" value (as many units as the player has moved on the Y axis so far)
        paint(d);
        g.drawImage(offscreen, 0, 0, this); // after paint() has drawn everything offscreen, the graphics object g draws that image on the screen
    }

    public void paint(Graphics d){
        d.translate(0, -offSetY); // this is where things get complicated, I'll discuss this translate in more detail below the code
        d.drawImage(background, 0, 0, this); // I'm not entirely sure why the background is drawn both here and in the "on screen" Graphics object,
        //it's inherited from the double buffer example. Removing it seems to have no effect on anything.

        // I have a for-loop drawing all my game objects here. I've tested this separately and it works just fine without the translates
        //(e.g. if I'm moving the objects down instead of moving the "screen".

    }
}
