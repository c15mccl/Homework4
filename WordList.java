
/**
 * Class that keeps track of what words have been found
 * by the player
 *
 * @author Austin Gage, Rose Wilson, Derek McPhail, Cheryl McClean,
 *  Mark Eliseo
 * @version 4/8/2019
 */
public class WordList
{
    
    private String word;
    private boolean wordFound;

    /**
     * Constructor for objects of class WordList, wordFound set
     * to false by default
     * @param a String word
     */
    public WordList(String word)
    {
        wordFound = false;
        this.word = word;
    }
    
    /**
     * Accessor method for the word instance variable
     * 
     * @return a String word 
     */
    public String getWord()
    {
        return word;
    }
    
    /**
     * Mutator method that will set the WordFound 
     * instance variable to the supplied one
     * 
     * @param new value of WordFound
     * 
     */
    public void setWordFound(boolean wordFound)
    {
        this.wordFound = wordFound;
    }
    
    /**
     * Accessor method of the WordFound instance variable
     * 
     * @return value of WordFound instance variable
     * 
     */
    public boolean getWordFound()
    {
        return wordFound;
    }
}
