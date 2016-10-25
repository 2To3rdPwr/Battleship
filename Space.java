
/**
 * Space class for the Battleship board
 * Space Objects are used for the Offensive View for the board.
 * 
 * @author Jake Sage 
 * @version 9/23/15
 */

public class Space
{
    private String space = ".";           //Shows the current view
    private String redStore = ".";        //stores the view shown on Red's turn
    private String blueStore = ".";       //stores the view shown on blue's turn
    /**
     * constructor for Space objects
     */
    public Space()
    {}
    /**
     * Changes the offensive view for Red's turn
     */
    public void redTurn()
    {
        space = redStore;
    }
    /**
     * Changes the offensive view for blue's turn
     */
    public void blueTurn()
    {
        space = blueStore;
    }
    /**
     * tests and displays results of Red's attack
     * returns boolean for hit/miss
     */
    public boolean redGuess(Map map)
    {
        if (map.redAttack() == true)
        {
            redStore = "X";
            return true;
        }
        else
        {
            redStore = "O";
            return false;
        }
    }
    /**
     * tests and displays the results of Blue's attack
     */
    public boolean blueGuess(Map map)
    {
        if (map.blueAttack() == true)
        {
            blueStore = "X";
            return true;
        }
        else
        {
            blueStore = "O";
            return false;
        }
    }
    public String get()
    {
        return space;
    }
}
