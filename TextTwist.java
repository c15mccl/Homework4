import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
/**
 * This creates a JPanel of a game called TextTwist, a dialogue box will
 * pop up asking what difficulty, then the board will pop up
 *
 * @author Austin Gage, Rose Wilson, Derek McPhail, Cheryl McClean,
 *  Mark Eliseo
 * @version 3/18/2019
 */
public class TextTwist extends JPanel implements  MouseListener
{
    int width, height;
    Integer score=0;
    String s = "";
    String word = "";
    ArrayList<String> arr = new ArrayList<String>();
    String difficulty;
    Random rand = new Random();
    boolean textPainted = false;
    /**
     * Constructor for objects of class KeyboardPanel
     */
    public TextTwist()
    {
        setPreferredSize(new Dimension(850, 650));
        setOpaque(true);
        width = getPreferredSize().width;
        height = getPreferredSize().height;
        setBackground( new Color(0,204,255) );
        setFocusable(true);
        word = getWord();
        addMouseListener(this);
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

    @Override
    public void paintComponent( Graphics g ) { 
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        if(difficulty.equals("easy"))
            makeEasyBoard(g);
        else if(difficulty.equals("medium"))
            makeMediumBoard(g);
        else if(difficulty.equals("hard"))
            makeHardBoard(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        for(int i = 0;i < 6;i++)
        {
            g2.drawRect(300 + i*90,75,90,90);
        }
        Font font = new Font("Comic Sans", Font.BOLD, 40);
        g.setFont(font);
        String randWord = "";
        String word1 = word;
        
        if(!textPainted)
        {
            for(int i = 0;i < 6;i++)
            {
                int num = rand.nextInt(word1.length());
                randWord += word1.substring(num,num+1);
                int len = word1.length();
                word1 = word1.substring(0,num) + word1.substring(num+1,len);
            }
            textPainted = true;
        }
        for(int i = 0;i < 6;i++)
        {
            g2.drawOval(350 + i*70,200,70,70);
            g.drawString(randWord.substring(i,i+1),350 + i*70 + 25 ,250);
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
    }

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
    }

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
