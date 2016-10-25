
/**
 * Class for DESTROYER objects
 * 
 * @author Jake Sage
 * @version 9/25/15
 */
public class Destroyer
{
    private boolean horizontal, a = true, b = true, c = true;
    private int numA, letA, numB, letB, numC, letC;  //number and letter chosen coordinates, respectively;
    /**
     * Constructor for destroyers
     * This also constructs the rest of the ship
     * Ship should be tested for placement possible BEFORE object declaration
     */
    public Destroyer(boolean h, int numCoord, int letCoord)
    {
        horizontal = h;
        numA = numCoord;
        letA = letCoord;
        if(horizontal == true)
        {
            letC = letB = letA;
            numB = numA + 1;
            numC = numA + 2;
        }
        else
        {
            numC = numB = numA;
            letB = letA + 1;
            letC = letA + 2;
        }
    }
    public void hit(int let, int num)
    {
        if(let == letA && num == numA)
            a = false;
        else if(let == letB && num == numB)
            b = false;
        else if(let == letC && num == numC)
            c = false;
    }
    /**
     * This method is called whenever a hit is scored
     * it tests for sinks, and counts each hit against the ship
     * parameters are letter and number coords of the hit
     * it returns True for sink of the ship
     */
    public boolean sunk(int let, int num)
    {
        if((a == b) && (b == c ) && (c == false))
        {
            a = b = c = true;
            return true;
        }
        else
            return false;
    }
}
