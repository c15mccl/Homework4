import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
/**
 * This creates a JPanel of a game called TextTwist, a dialogue box will
 * pop up asking what difficulty, then the board will pop up
 *
 * @author Austin Gage, Rose Wilson, Derek McPhail, Cheryl McClean,
 *  Mark Eliseo
 * @version 3/18/2019
 */
public class TextTwist extends JPanel implements KeyListener, MouseListener
{
    int width, height;
    static int numWord;
    String s = "";
    String word = "";
    ArrayList<String> arr = new ArrayList<String>();
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
        addKeyListener( this );
    }

    public void mouseExited(MouseEvent e) { }

    public void mouseEntered(MouseEvent e) { }

    public void mouseReleased(MouseEvent e) { }

    public void mouseClicked(MouseEvent e)
    {
        //THIS METHOD SHOULD CHECK WHERE THE MOUSE WAS 
        //CLICKED AND IF IT WAS IN THE TWIST BUTTON
        //CALL A METHOD THAT WILL 
    }

    public void mousePressed(MouseEvent e) { }

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
        if ( c != KeyEvent.CHAR_UNDEFINED && charInString && s.length() < word.length()) {
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
        g.setColor(Color.black);
        Font font = new Font("Comic Sans", Font.BOLD, 60);
        g.setFont(font);
        g.drawString(s,300,250);
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
        boolean difficultySet = false;
        Scanner sc = new Scanner(System.in);
        File file = new File("easy.txt");
        while(!difficultySet)
        {
            String difficulty = JOptionPane.showInputDialog(null, "Enter easy, medium, or hard",
                    "Input word", JOptionPane.QUESTION_MESSAGE);

            file = new File(difficulty.toLowerCase() + ".txt");
            try
            {
                sc = new Scanner(file);

                difficultySet = true;
            }
            catch(java.io.FileNotFoundException e)
            {
            }
        }
        word = sc.nextLine().toLowerCase();
        arr.add(word);
        while(sc.hasNext())
        {
            arr.add(sc.nextLine().toLowerCase());
        }
        
        return word;
    }


    public static void main(String[] args)
    {

        createAndShowGUI();
    }
}
