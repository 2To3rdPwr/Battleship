
/**
 * Map object for the Battleship game
 * Map objects are the spaces on the DEFENSIVE view of the game
 * 
 * @author Jake Sage
 * @version 9/23/15
 */
public class Map
{
    private String space = ".";
    private String redStore = ".";
    private String blueStore = ".";
    /**
     * Constructor for Map objects
     */
    public Map()
    {}
    /**
     * Changes the defensive view for Red's turn
     */
    public void redTurn()
    {
        space = redStore;
    }
    public void blueTurn()
    {
        space = blueStore;
    }
    /**
     * returns a boolean for Red's attack and changes
     * Blue's board accordingly
     */
    public boolean redAttack()
    {
        if(blueStore.equals("#"))
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
    public boolean blueAttack()
    {
        if(redStore.equals("#"))
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
     * places a "ship" piece for Red
     */
    public void redSet()
    {
        redStore = "#";
    }
    public void blueSet()
    {
        blueStore = "#";
    }
    public String get()
    {
        return space;
    }
}
