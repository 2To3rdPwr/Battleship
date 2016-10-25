
/**
 * Aircraft Carrier objects
 * 
 * @author Jake Sage 
 * @version 9/24/15
 */
public class Carrier
{
    private boolean horizontal, a = true, b = true, c = true, d = true, e = true;
    private int numA, letA, numB, letB, numC, letC, numD, letD, numE, letE;  //number and letter chosen coordinates, respectively;
    /**
     * Constructor for battleships
     * This also constructs the rest of the ship
     * Ship should be tested for placement possible BEFORE object declaration
     */
    public Carrier(boolean h, int numCoord, int letCoord)
    {
        horizontal = h;
        numA = numCoord;
        letA = letCoord;
        if(horizontal == true)
        {
            letE = letD = letC = letB = letA;
            numB = numA + 1;
            numC = numA + 2;
            numD = numA + 3;
            numE = numA + 4;
        }
        else
        {
            numE = numD = numC = numB = numA;
            letB = letA + 1;
            letC = letA + 2;
            letD = letA + 3;
            letE = letA + 4;
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
        else if(let == letD && num == numD)
            d = false;
        else if(let == letE && num == numE)
            e = false;        
    }
    /**
     * This method is called whenever a hit is scored
     * it tests for sinks, and counts each hit against the ship
     * parameters are letter and number coords of the hit
     * it returns True for sink of the ship
     */
    public boolean sunk(int let, int num)
    {
        if((a == b) && (b == c) && (c == d) && (d == e) && (e == false))
        {
            a = b = c = d = e = true;
            return true;
        }
        else
            return false;
    }
}
