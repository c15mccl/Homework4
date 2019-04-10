import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
/**
 * This creates a JPanel of a game called TextTwist, a dialogue box will
 * pop up asking what difficulty, then the board will pop up and can be played
 *
 * @author Austin Gage, Rose Wilson, Derek McPhail, Cheryl McClean,
 *  Mark Eliseo
 * @version 3/18/2019
 */
public class TextTwist extends JPanel implements  MouseListener
{
    private int width, height;
    private Integer score=0;
    private String word = "";
    private ArrayList<WordList> arr = new ArrayList<WordList>();
    private ArrayList<String> chars = new ArrayList<String>();
    private ArrayList<Boolean> charUsed = new ArrayList<Boolean>();
    private String currentGuess = "";
    private String difficulty;
    private Random rand = new Random();
    private boolean textPainted = false;
    private boolean clearPressed = false;
    private boolean gameWon = false;

    /**
     * Constructor for objects of class KeyboardPanel
     */
    public TextTwist()
    {
        for(int i = 0;i < 6;i++)
        {
            charUsed.add(false);
        }
        setPreferredSize(new Dimension(850, 650));
        setOpaque(true);
        width = getPreferredSize().width;
        height = getPreferredSize().height;
        setBackground( new Color(0,204,255) );
        setFocusable(true);
        word = getWord();
        addMouseListener(this);
    }
    
    /**
     * Empty methods that had to be overriden to use MouseListener
     * 
     */
    public void mouseExited(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    
    /**
     * This method checks to see where the mouse was clicked, and calls
     * the appropriate method, like if twist is pressed or if enter is pressed
     * @param the MouseEvent that started the method
     */
    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        //TWIST BUTTON PRESSED
        if(x <=450 && x >= 310 && y <= 400 && y>=350) 
        {
            textPainted = false;
            repaint();
        }

        //CLEAR BUTTON PRESSED 
        if(x <=600 && x >= 460 && y <= 400 && y>=350) 
        {
            clearPressed = true;
            repaint();
        }

        //ENTER BUTTON PRESSED 
        if(x <=750 && x >= 610 && y <= 400 && y>=350) 
        {
            guessWord();
            currentGuess = "";
            repaint();
        }

        //FIRST CIRCLE 
        if(x <=420 && x >= 350 && y <= 270 && y>=200) 
        {
            if(!charUsed.get(0))
            {
                currentGuess += chars.get(0);
                charUsed.set(0,true);
            }
            repaint();
        }
        //SECOND CIRCLE
        if(x <=490 && x >= 420 && y <= 270 && y>=200) 
        {
            if(!charUsed.get(1))
            {
                currentGuess += chars.get(1);
                charUsed.set(1,true);
            }
            repaint();
        }
        //THIRD CIRCLE
        if(x <=560 && x >= 490 &&  y <= 270 && y>=200) 
        {
            if(!charUsed.get(2))
            {
                currentGuess += chars.get(2);
                charUsed.set(2,true);
            }
            repaint();
        }
        //FOURTH CIRCLE
        if(x <=620 && x >= 560 && y <= 270 && y>=200) 
        {
            if(!charUsed.get(3))
            {
                currentGuess += chars.get(3);
                charUsed.set(3,true);
            }
            repaint();
        }
        //FIFTH CIRCLE
        if(x <=690 && x >= 620 && y <= 270 && y>=200) 
        {
            if(!charUsed.get(4))
            {
                currentGuess += chars.get(4);
                charUsed.set(4,true);
            }
            repaint();
        }
        //SIXTH CIRCLE
        if(x <= 760 && x >= 690 && y <= 270 && y>=200) 
        {
            if(!charUsed.get(5))
            {
                currentGuess += chars.get(5);
                charUsed.set(5,true);
            }
            repaint();
        }

    }

    /**
     * Method that paints the JPanel
     * 
     * @param a Graphics object g
     * 
     */
    @Override
    public void paintComponent( Graphics g ) { 
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g.setColor(Color.WHITE);

        if(!gameWon)
        {
            if(difficulty.equals("easy"))
                makeEasyBoard(g);
            else if(difficulty.equals("medium"))
                makeMediumBoard(g);
            else if(difficulty.equals("hard"))
                makeHardBoard(g);

            for(int i = 0;i < 6;i++)
            {
                g2.drawRect(300 + i*90,75,90,90);
            }

            if(clearPressed)
            {
                currentGuess = "";
                for(int i = 0;i < charUsed.size();i++)
                {
                    charUsed.set(i,false);
                }
                clearPressed = false;
            }

            Font font = new Font("Comic Sans", Font.BOLD, 40);
            g.setFont(font);
            String word1 = word;
            int num = 0;
            if(!textPainted)
            {
                chars.clear();
                for(int i = 0;i < 6;i++)
                {
                    num = rand.nextInt(word1.length());
                    chars.add(word1.substring(num,num+1));
                    int len = word1.length();
                    word1 = word1.substring(0,num) + word1.substring(num+1,len);
                }
                if(currentGuess.length() > 0)
                {
                    String guess = currentGuess;
                    for(int i = 0;i < 6;i++)
                    {
                        
                        if(guess.contains(chars.get(i)))
                        {
                            int index = guess.indexOf(chars.get(i));
                            charUsed.set(i,true);
                            guess = guess.substring(0,index) + guess.substring(index+1,guess.length());
                        }
                        else
                            charUsed.set(i,false);
                    }
                }
                //}
                textPainted = true;
            }
            for(int i = 0;i < 6;i++)
            {
                g2.drawOval(350 + i*70,200,70,70);
                g2.setColor(new Color(0,204,255));
                g2.fillOval(350 + i*70,200,70,70);
                g2.setColor(Color.WHITE);
                g.drawString(chars.get(i),350 + i*70 + 25 ,250);
            }

            if(currentGuess.length() != 0)
            {
                for(int i = 0;i < charUsed.size();i++)
                {
                    if(charUsed.get(i))
                    {
                        g2.fillOval(350 + i*70,200,70,70);
                    }
                    if(i < currentGuess.length())
                        g2.drawString(currentGuess.substring(i,i+1),300 + i*90 + 40,125);
                }

            }
            g2.drawRect(310,350,140,50);
            g2.drawString("Twist",325,390);

            g2.drawRect(460,350,140,50);
            g2.drawString("Clear",475,390);

            g2.drawRect(610,350,140,50);
            g2.drawString("Enter",625,390);

            g2.drawString("Score",325,500);
            g2.drawString(score.toString(),325,550);
        }
        else
        {
            g2.setStroke(new BasicStroke(4));
            g2.drawRect(100,100,500,400);
            g2.setColor(new Color(0,204,255));
            g2.fillRect(100,100,500,400);
            g2.setColor(Color.WHITE);
            Font font = new Font("Comic Sans", Font.BOLD, 40);
            g2.setFont(font);
            g2.drawString("WINNER!!", 150,150); 
            g2.drawString("If you want to play again,",125,300);
            g2.drawString("restart the program",150,450);
        }

    }

    /**
     * Paints the boxes the words go in on the left of the JPanel and
     * the words that have been found by the player if the difficulty 
     * selected is "easy"
     * @param a Graphics object g
     */
    private void makeEasyBoard(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(10,10,130,50);
        g2.drawRect(10,60,110,50);
        g2.drawRect(10,110,90,50);
        g2.drawRect(10,160,90,50);
        g2.drawRect(10,210,90,50);
        g2.drawRect(10,260,70,50);
        g2.drawRect(10,310,70,50);
        g2.drawRect(10,360,70,50);
        Font font = new Font("Comic Sans", Font.BOLD,24);
        g2.setFont(font);
        for(int i = 0;i < arr.size();i++)
        {
            if(arr.get(i).getWordFound())
            {
                g2.drawString(arr.get(i).getWord(),30,50*(i+1)-10);
            }
        }
    }

    /**
     * Paints the boxes the words go in on the left of the JPanel and
     * the words that have been found by the player if the difficulty 
     * selected is "medium"
     * @param a Graphics object g
     */
    private void makeMediumBoard(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(10,10,130,40);
        g2.drawRect(10,50,110,40);
        g2.drawRect(10,90,110,40);
        g2.drawRect(10,130,110,40);
        g2.drawRect(10,170,110,40);
        g2.drawRect(10,210,110,40);
        g2.drawRect(10,250,90,40);
        g2.drawRect(10,290,90,40);
        g2.drawRect(10,330,90,40);
        g2.drawRect(10,370,90,40);
        g2.drawRect(10,410,90,40);
        g2.drawRect(10,450,70,40);
        g2.drawRect(10,490,70,40);
        g2.drawRect(10,530,70,40);
        g2.drawRect(10,570,70,40);
        Font font = new Font("Comic Sans", Font.BOLD,24);
        g2.setFont(font);
        for(int i = 0;i < arr.size();i++)
        {
            if(arr.get(i).getWordFound())
            {
                g2.drawString(arr.get(i).getWord(),30,5+40*(i+1));
            }
        }
    }

    /**
     * Paints the boxes the words go in on the left of the JPanel and
     * the words that have been found by the player if the difficulty 
     * selected is "hard"
     * @param a Graphics object g
     */
    private void makeHardBoard(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(10,10,130,30);
        g2.drawRect(10,40,110,30);
        g2.drawRect(10,70,110,30);
        g2.drawRect(10,100,110,30);
        g2.drawRect(10,130,110,30);
        g2.drawRect(10,160,110,30);
        g2.drawRect(10,190,110,30);
        g2.drawRect(10,220,110,30);
        g2.drawRect(10,250,110,30);
        g2.drawRect(10,280,110,30);
        g2.drawRect(10,310,90,30);
        g2.drawRect(10,340,90,30);
        g2.drawRect(10,370,90,30);
        g2.drawRect(10,400,90,30);
        g2.drawRect(10,430,90,30);
        g2.drawRect(10,460,90,30);
        g2.drawRect(10,490,70,30);
        g2.drawRect(10,520,70,30);
        g2.drawRect(10,550,70,30);
        g2.drawRect(10,580,70,30);
        g2.drawRect(10,610,70,30);
        Font font = new Font("Comic Sans", Font.BOLD,24);
        g2.setFont(font);
        for(int i = 0;i < arr.size();i++)
        {
            if(arr.get(i).getWordFound())
            {
                g2.drawString(arr.get(i).getWord(),30,5+30*(i+1));
            }
        }
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
    
    /**
     * Method called when the "enter" button is pressed 
     * that checks if the current guess submitted
     * by the player is one of the words stored in ArrayList 
     * arr, if it is it will set that word equal to true and 
     * it will be printed to the JPanel, it also checks if
     * all words are found and if so will end the game the next time 
     * the board is painted.
     * 
     */
    private void guessWord()
    {
        for(int i = 0;i < charUsed.size();i++)
        {
            charUsed.set(i,false);
        }
        if(currentGuess.length() < 3)
            return;
        for(int i = 0;i < arr.size();i++)
        {
            String getWord = arr.get(i).getWord();
            if(i < charUsed.size())
                charUsed.set(i,false);
            if(getWord.equals(currentGuess) && !arr.get(i).getWordFound())
            {
                arr.get(i).setWordFound(true);
                score += 10 * arr.get(i).getWord().length();
            }
        }
        //SETS GAMEWON TO TRUE BUT UNLESS ALL WORDS ARE SET TO TRUE,
        //IS SET BACK TO FALSE
        gameWon = true;
        for(int i = 0;i < arr.size();i++)
        {
            if(!arr.get(i).getWordFound())
            {
                gameWon = false;
            }
        }
    }

    /**
     * This method asks for what difficulty the player would 
     * like to play by asking in a JOptionPane to type
     * "easy","medium", or "hard". Will repeatedly ask until one
     * of those Strings, this is not case sensitive. It will then
     * fill an arrayList of the words that were selected that can be made
     * from 6 letters and will return the longest word for use in different
     * methods
     * 
     * @return the first index of the ArrayList, which is the longest word
     * that uses all characters
     */
    private String getWord()
    {
        boolean difficultySet = false;
        Scanner sc = new Scanner(System.in);
        File file = new File("easy.txt");
        while(!difficultySet)
        {
            difficulty = JOptionPane.showInputDialog(null, "Enter easy, medium, or hard",
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
        arr.add(new WordList(word));
        while(sc.hasNext())
        {
            arr.add(new WordList(sc.nextLine().toLowerCase()));
        }

        return word;
    }

    
    /**
     * Main method that will call createAndShowGUI() and will
     * run that method
     * 
     * @param Command Line arguments
     * 
     */
    public static void main(String[] args)
    {
        createAndShowGUI();
    }
}
