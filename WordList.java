
/**
 * Write a description of class WordList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WordList
{
    // instance variables - replace the example below with your own
    private String word;
    private boolean wordFound;

    /**
     * Constructor for objects of class WordList
     */
    public WordList(String word)
    {
        wordFound = false;
        this.word = word;
    }
    
    public String getWord()
    {
        return word;
    }

    public void setWordFound(boolean wordFound)
    {
        this.wordFound = wordFound;
    }
    
    
    public boolean getWordFound()
    {
        return wordFound;
    }
}
