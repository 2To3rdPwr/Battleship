
/**
 * Class for PT boats objects
 * 
 * @author Jake Sage
 * @version 9/25/15
 */
public class PTBoat
{
    private boolean horizontal, a = true, b = true;
    private int numA, letA, numB, letB;  //number and letter chosen coordinates, respectively;
    /**
     * Constructor for PT boats
     * This also constructs the rest of the ship
     * Ship should be tested for placement possible BEFORE object declaration
     */
    public PTBoat(boolean h, int numCoord, int letCoord)
    {
        horizontal = h;
        numA = numCoord;
        letA = letCoord;
        if(horizontal == true)
        {
            letB = letA;
            numB = numA + 1;
        }
        else
        {
            numB = numA;
            letB = letA + 1;
        }
    }
    public void hit(int let, int num)
    {
        if(let == letA && num == numA)
            a = false;
        else if(let == letB && num == numB)
            b = false;        
    }
    /**
     * This method is called whenever a hit is scored
     * it tests for sinks, and counts each hit against the ship
     * parameters are letter and number coords of the hit
     * it returns True for sink of the ship
     */
    public boolean sunk(int let, int num)
    {
        if((a == b) && (b == false))
        {
            a = b = true;
            return true;
        }
        else
            return false;
    }
}
