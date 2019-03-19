import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
/**
 * This creates a JPanel of a game called TextTwist, you must 
 *
 * @author Austin Gage, Rose Wilson, Derek McPhail, Cheryl McClean
 * @version 3/18/2019
 */
public class TextTwist extends JPanel implements KeyListener
{
    int width, height;
    static int numWord;
    String s = "";
    String word = "";
    JTextField textField;
    /**
     * Constructor for objects of class KeyboardPanel
     */
    public TextTwist()
    {
        setPreferredSize(new Dimension(832, 622));
        setOpaque(true);
        width = getPreferredSize().width;
        height = getPreferredSize().height;
        setBackground( Color.BLUE );
        setFocusable(true);
        word = getWord();
        textField = new JTextField(word);
        addKeyListener( this );
    }

    public void keyPressed( KeyEvent e ) { }

    public void keyReleased( KeyEvent e ) { }

    public void keyTyped( KeyEvent e ) {
        boolean charInString = false;
        char c = e.getKeyChar();
        for(int i = 0;i < word.length();i++)
        {
            if(word.charAt(i) == c)
                charInString = true;
        }
        if ( c != KeyEvent.CHAR_UNDEFINED && charInString) {
            s = s + c;
            repaint();
            e.consume();
        }
        if(c == '\n')
        {
            //Check to see if word entered is in the list of words
            //if so will print that word to the screen in the appropriate box
            s = "";
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
            img = ImageIO.read(new File("TextTwistBoard.jpg"));
        }
        catch(java.io.IOException e)
        {
            return;
        }
        g.drawImage((Image)img,0,0,null);
        g.drawRect(0,0,250,622);
        g.fillRect(0,0,250,622);
        g.drawString(s,400,300);
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
        JFrame frame = new JFrame("Text Twist!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TextTwist label = new TextTwist();
        frame.add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);

    }
    
    private String getWord()
    {
        return "";
    }

    public static void main(String[] args)
    {
        File file = new File("input.txt");
        Scanner sc;
        try
        {
            sc = new Scanner(file);
        }
        catch(java.io.FileNotFoundException e)
        {
            return;
        }
        String firstStrNumber = JOptionPane.showInputDialog(null, "Enter 1 2 or 3",
            "Input first number", JOptionPane.QUESTION_MESSAGE);
        numWord = Integer.parseInt(firstStrNumber);
        createAndShowGUI();
    }
}
