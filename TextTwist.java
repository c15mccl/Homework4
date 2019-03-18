import java.util.ArrayList;
import java.io.File;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
/**
 * This creates a JPanel of the 
 *
 * @author Austin Gage, Rose Wilson, Derek McPhail, Cheryl McClean
 * @version 3/18/2019
 */
public class TextTwist extends JPanel implements KeyListener
{
    int width, height;
    String s = "";
    /**
     * Constructor for objects of class KeyboardPanel
     */
    public TextTwist()
    {
        setPreferredSize(new Dimension(1024, 900));
        setOpaque(true);
        width = getPreferredSize().width;
        height = getPreferredSize().height;
        setBackground( Color.BLUE );
        setFocusable(true);

        addKeyListener( this );
    }

    public void keyPressed( KeyEvent e ) { }

    public void keyReleased( KeyEvent e ) { }

    public void keyTyped( KeyEvent e ) {
        char c = e.getKeyChar();
        if ( c != KeyEvent.CHAR_UNDEFINED ) {
            s = s + c;
            repaint();
            e.consume();
        }
    }

    @Override
    public void paintComponent( Graphics g ) { 
        super.paintComponent(g);
        g.setColor( Color.blue );
        BufferedImage img;
        
        try
        {
            img = ImageIO.read(new File("TextTwistBoardEdit.jpg"));
        }
        catch(java.io.IOException e)
        {
            return;
        }
        g.drawImage((Image)img,25,25,null);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("Rainbow");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TextTwist label = new TextTwist();
        frame.add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args)
    {
        createAndShowGUI();
    }
}
