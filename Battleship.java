

/**
 * Play a game of battleship!
 * 1-2 players
 * Play against the random computer or a friend!
 * 
 * @author Jake Sage
 * @version 9/23/15
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Battleship
{
    public static void main(String[]args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Number of players: ");
        String alpha[] = alphabet();
        int players = Integer.parseInt(in.nextLine());
        int shipsRemRed = 5;
        int shipsRemBlue = 5;
        boolean hit = false;
        boolean sink = false;
        boolean red = true; //indicates whether it is RED's turn or not
        boolean gameOver = false; //flags the end of the game
        boolean userInput = true; //set to True before user inputs moves/ship placements.
        String letter = "";
        String waiting = " h ";
        String input = "k";
        //creating the offensive board
        //Accessing spaces is off.get(1-10).get(A-J).function()
        ArrayList<ArrayList<Space>> off = new ArrayList<ArrayList<Space>>(0);
        for(int i = 0; i <= 10; i++)
        {
            off.add(new ArrayList<Space>(0));
            for(int j = 0; j <= 10; j++)
            {
                off.get(i).add(new Space());
            }
        }
        //creating the defensive board
        ArrayList<ArrayList<Map>> def = new ArrayList<ArrayList<Map>>(0);
        for(int i = 0; i <= 10; i++)
        {
            def.add(new ArrayList<Map>(0));
            for(int j = 0; j <= 10; j++)
            {
                def.get(i).add(new Map());
            }
        }
        
            //off and def are offensive & defensive boards respectively
            //referanced as board.get(num).get(let)
        
        printBoard(off);    
        printBoard(def, 0);
        
        if(players == 2)
        {
             // Players set up their MAP boards starting with red
             //RED's setup
             System.out.println("RED's turn for ship placement.");
             System.out.println("Make sure BLUE cannot see the screen.");
             System.out.println("Press 'Enter' to continue");
             waiting = in.nextLine();
             printBoard(def, 0);
            
            
            
             //battleship placement
             System.out.println("Place your BATTLESHIP (4 spaces long)");
             userInput = true;
             boolean horizontal = true;
             int let = 0, num = 0;
             letter = "z";
             while(userInput == true)
             {
                 System.out.println("Is your BATTLESHIP (V)ertical or (H)orizontal?");
                 String vh = in.nextLine();
                 if(vh.equalsIgnoreCase("h"))
                 {
                     horizontal = true;
                     userInput = false;
                 }
                 else if(vh.equalsIgnoreCase("v"))
                 {
                     horizontal = false;
                     userInput = false;
                 }
                 else
                     System.out.println("Not a possible selection. Try again.");
             }
             userInput = true;
             while(userInput == true)
             {
                 if(horizontal == true)
                     System.out.println("Input the LEFTMOST coordinate of your ship");
                 else
                     System.out.println("Input the UPMOST coordinate of your ship");
                 input = in.nextLine();
                 if(check(input, alpha)==true)
                 {
                     letter = input.substring(0,1);
                     num = Integer.parseInt(input.substring(1));
                 }
                 else
                    letter = "q";
                 if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                 {
                    let = 1;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("b"))
                 {
                    let = 2;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("c"))
                 {
                    let = 3;
                    userInput = false;
                 }
                  else if (letter.equalsIgnoreCase("d"))
                  {
                    let = 4;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("e"))
                 {
                    let = 5;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("f"))
                 {
                    let = 6;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("g"))
                 {
                    let = 7;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("h"))
                 {
                    let = 8;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("i"))
                 {
                    let = 9;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("j"))
                 {
                    let = 10;
                    userInput = false;
                 }
                 if(!((num >= 1) && (num <= 10)))
                    userInput = true;
                    
                    
                 if((let > 7 && horizontal == false) || (num > 7 && horizontal == true))
                 {
                     System.out.println("Your ship would not be on the board.");
                     userInput = true;
                 }
                 if(userInput == true)
                    System.out.println("Your input is out of range. Try again.");
             }
             BShip BSRed = new BShip (horizontal, num, let);
             if(horizontal == true)
             {
                 for(int i = 0; i <= 3; i++)
                 {
                     def.get(num + i).get(let).redSet();
                 }
             }
             else
             {
                 for(int i = 0; i <= 3; i++)
                 {
                     def.get(num).get(let + i).redSet();
                 }
             }
             redTurn(off, def);
             printBoard(def, 0);
            
            
             //Aircraft Carrier placement
             System.out.println("Place your AIRCRAFT CARRIER (5 spaces long)");
             userInput = true;
             horizontal = true;
             let = 0;
             num = 0;
             
             while(userInput == true)
             {
                 System.out.println("Is your AIRCRAFT CARRIER (V)ertical or (H)orizontal?");
                 String vh = in.nextLine();
                 if(vh.equalsIgnoreCase("h"))
                 {
                     horizontal = true;
                     userInput = false;
                 }
                 else if(vh.equalsIgnoreCase("v"))
                 {
                     horizontal = false;
                     userInput = false;
                 }
                 else
                     System.out.println("Not a possible selection. Try again.");
             }
             userInput = true;
             while(userInput == true)
             {
                 if(horizontal == true)
                     System.out.println("Input the LEFTMOST coordinate of your ship");
                 else
                     System.out.println("Input the UPMOST coordinate of your ship");
                 input = in.nextLine();
                 if(check(input, alpha)==true)
                 {
                     letter = input.substring(0,1);
                     num = Integer.parseInt(input.substring(1));
                 }
                 else
                    letter = "q";
                 if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                 {
                    let = 1;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("b"))
                 {
                    let = 2;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("c"))
                 {
                    let = 3;
                    userInput = false;
                 }
                  else if (letter.equalsIgnoreCase("d"))
                  {
                    let = 4;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("e"))
                 {
                    let = 5;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("f"))
                 {
                    let = 6;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("g"))
                 {
                    let = 7;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("h"))
                 {
                    let = 8;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("i"))
                 {
                    let = 9;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("j"))
                 {
                    let = 10;
                    userInput = false;
                 }
                 if(!((num >= 1) && (num <= 10)))
                    userInput = true;
                 
                    
                 if((let > 6 && horizontal == false) || (num > 6 && horizontal == true))//testing placement validity
                 {
                     System.out.println("Your ship would not be on the board.");
                     userInput = true;
                 }
                 else if(horizontal == true)
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num + 1).get(let).get().equals("#") || def.get(num + 2).get(let).get().equals("#") || def.get(num + 3).get(let).get().equals("#") || def.get(num + 4).get(let).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 else
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num).get(let + 1).get().equals("#") || def.get(num).get(let + 2).get().equals("#") || def.get(num).get(let + 3).get().equals("#") || def.get(num).get(let + 4).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 if(userInput == true)
                    System.out.println("Your input is out of range. Try again.");
             }
             Carrier ACRed = new Carrier (horizontal, num, let);
             if(horizontal == true)
             {
                 for(int i = 0; i <= 4; i++)
                 {
                     def.get(num + i).get(let).redSet();
                 }
             }
             else
             {
                 for(int i = 0; i <= 4; i++)
                 {
                     def.get(num).get(let + i).redSet();
                 }
             }
             redTurn(off, def);
             printBoard(def, 0);
            
            
             //Destroyer placement
             System.out.println("Place your DESTROYER (3 spaces long)");
             userInput = true;
             horizontal = true;
             let = 0;
             num = 0;
             
             while(userInput == true)
             {
                 System.out.println("Is your DESTROYER (V)ertical or (H)orizontal?");
                 String vh = in.nextLine();
                 if(vh.equalsIgnoreCase("h"))
                 {
                     horizontal = true;
                     userInput = false;
                 }
                 else if(vh.equalsIgnoreCase("v"))
                 {
                     horizontal = false;
                     userInput = false;
                 }
                 else
                     System.out.println("Not a possible selection. Try again.");
             }
             userInput = true;
             while(userInput == true)
             {
                 if(horizontal == true)
                     System.out.println("Input the LEFTMOST coordinate of your ship");
                 else
                     System.out.println("Input the UPMOST coordinate of your ship");
                 input = in.nextLine();
                 if(check(input, alpha)==true)
                 {
                     letter = input.substring(0,1);
                     num = Integer.parseInt(input.substring(1));
                 }
                 else
                    letter = "q";
                 if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                 {
                    let = 1;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("b"))
                 {
                    let = 2;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("c"))
                 {
                    let = 3;
                    userInput = false;
                 }
                  else if (letter.equalsIgnoreCase("d"))
                  {
                    let = 4;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("e"))
                 {
                    let = 5;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("f"))
                 {
                    let = 6;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("g"))
                 {
                    let = 7;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("h"))
                 {
                    let = 8;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("i"))
                 {
                    let = 9;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("j"))
                 {
                    let = 10;
                    userInput = false;
                 }
                 if(!((num >= 1) && (num <= 10)))
                    userInput = true;
                 
                    
                 if((let > 8 && horizontal == false) || (num > 8 && horizontal == true))//testing placement validity
                 {
                     System.out.println("Your ship would not be on the board.");
                     userInput = true;
                 }
                 else if(horizontal == true)
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num + 1).get(let).get().equals("#") || def.get(num + 2).get(let).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 else
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num).get(let + 1).get().equals("#") || def.get(num).get(let + 2).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 if(userInput == true)
                    System.out.println("Your input is out of range. Try again.");
             }
             Destroyer DRed = new Destroyer (horizontal, num, let);
             if(horizontal == true)
             {
                 for(int i = 0; i <= 2; i++)
                 {
                     def.get(num + i).get(let).redSet();
                 }
             }
             else
             {
                 for(int i = 0; i <= 2; i++)
                 {
                     def.get(num).get(let + i).redSet();
                 }
             }
             redTurn(off, def);
             printBoard(def, 0);
            
             //Submarine placement
             System.out.println("Place your SUBMARINE (3 spaces long)");
             userInput = true;
             horizontal = true;
             let = 0;
             num = 0;
             
             while(userInput == true)
             {
                 System.out.println("Is your SUBMARINE (V)ertical or (H)orizontal?");
                 String vh = in.nextLine();
                 if(vh.equalsIgnoreCase("h"))
                 {
                     horizontal = true;
                     userInput = false;
                 }
                 else if(vh.equalsIgnoreCase("v"))
                 {
                     horizontal = false;
                     userInput = false;
                 }
                 else
                     System.out.println("Not a possible selection. Try again.");
             }
             userInput = true;
             while(userInput == true)
             {
                 if(horizontal == true)
                     System.out.println("Input the LEFTMOST coordinate of your ship");
                 else
                     System.out.println("Input the UPMOST coordinate of your ship");
                 input = in.nextLine();
                 if(check(input, alpha)==true)
                 {
                     letter = input.substring(0,1);
                     num = Integer.parseInt(input.substring(1));
                 }
                 else
                    letter = "q";
                 if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                 {
                    let = 1;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("b"))
                 {
                    let = 2;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("c"))
                 {
                    let = 3;
                    userInput = false;
                 }
                  else if (letter.equalsIgnoreCase("d"))
                  {
                    let = 4;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("e"))
                 {
                    let = 5;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("f"))
                 {
                    let = 6;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("g"))
                 {
                    let = 7;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("h"))
                 {
                    let = 8;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("i"))
                 {
                    let = 9;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("j"))
                 {
                    let = 10;
                    userInput = false;
                 }
                 if(!((num >= 1) && (num <= 10)))
                    userInput = true;
                 
                    
                 if((let > 8 && horizontal == false) || (num > 8 && horizontal == true))//testing placement validity
                 {
                     System.out.println("Your ship would not be on the board.");
                     userInput = true;
                 }
                 else if(horizontal == true)
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num + 1).get(let).get().equals("#") || def.get(num + 2).get(let).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 else
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num).get(let + 1).get().equals("#") || def.get(num).get(let + 2).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 if(userInput == true)
                    System.out.println("Your input is out of range. Try again.");
             }
             Sub SMRed = new Sub (horizontal, num, let);
             if(horizontal == true)
             {
                 for(int i = 0; i <= 2; i++)
                 {
                     def.get(num + i).get(let).redSet();
                 }
             }
             else
             {
                 for(int i = 0; i <= 2; i++)
                 {
                     def.get(num).get(let + i).redSet();
                 }
             }
             redTurn(off, def);
             printBoard(def, 0);
            
             //PT Boat placement
             System.out.println("Place your PT BOAT (2 spaces long)");
             userInput = true;
             horizontal = true;
             let = 0;
             num = 0;
           
             while(userInput == true)
             {
                 System.out.println("Is your PT BOAT (V)ertical or (H)orizontal?");
                 String vh = in.nextLine();
                 if(vh.equalsIgnoreCase("h"))
                 {
                     horizontal = true;
                     userInput = false;
                 }
                 else if(vh.equalsIgnoreCase("v"))
                 {
                     horizontal = false;
                     userInput = false;
                 }
                 else
                     System.out.println("Not a possible selection. Try again.");
             }
             userInput = true;
             while(userInput == true)
             {
                 if(horizontal == true)
                     System.out.println("Input the LEFTMOST coordinate of your ship");
                 else
                     System.out.println("Input the UPMOST coordinate of your ship");
                 input = in.nextLine();
                 if(check(input, alpha)==true)
                 {
                     letter = input.substring(0,1);
                     num = Integer.parseInt(input.substring(1));
                 }
                 else
                    letter = "q";
                 if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                 {
                    let = 1;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("b"))
                 {
                    let = 2;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("c"))
                 {
                    let = 3;
                    userInput = false;
                 }
                  else if (letter.equalsIgnoreCase("d"))
                  {
                    let = 4;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("e"))
                 {
                    let = 5;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("f"))
                 {
                    let = 6;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("g"))
                 {
                    let = 7;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("h"))
                 {
                    let = 8;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("i"))
                 {
                    let = 9;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("j"))
                 {
                    let = 10;
                    userInput = false;
                 }
                 if(!((num >= 1) && (num <= 10)))
                    userInput = true;
                 
                    
                 if((let > 9 && horizontal == false) || (num > 9 && horizontal == true))//testing placement validity
                 {
                     System.out.println("Your ship would not be on the board.");
                     userInput = true;
                 }
                 else if(horizontal == true)
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num + 1).get(let).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 else
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num).get(let + 1).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 if(userInput == true)
                    System.out.println("Your input is out of range. Try again.");
             }
             PTBoat PTRed = new PTBoat (horizontal, num, let);
             if(horizontal == true)
             {
                 for(int i = 0; i <= 1; i++)
                 {
                     def.get(num + i).get(let).redSet();
                 }
             }
             else
             {
                 for(int i = 0; i <= 1; i++)
                 {
                     def.get(num).get(let + i).redSet();
                 }
             }
             redTurn(off, def);
             printBoard(def, 0);
            
            
            
            
             System.out.println("Press 'ENTER' and give the computer to BLUE");
             waiting = in.nextLine();
             clearBoard();
             System.out.println("BLUE's turn for ship placement");
             System.out.println("Make sure RED cannot see the screen");
             System.out.println("Press 'ENTER' to continue");
             waiting = in.nextLine();
             blueTurn(off, def);
             printBoard(def, 0);
             //battleship placement
             System.out.println("Place your BATTLESHIP (4 spaces long)");
             userInput = true;
             horizontal = true;
             let = 0;
             num = 0;
           
             while(userInput == true)
             {
                 System.out.println("Is your BATTLESHIP (V)ertical or (H)orizontal?");
                 String vh = in.nextLine();
                 if(vh.equalsIgnoreCase("h"))
                 {
                     horizontal = true;
                     userInput = false;
                 }
                 else if(vh.equalsIgnoreCase("v"))
                 {
                     horizontal = false;
                     userInput = false;
                 }
                 else
                     System.out.println("Not a possible selection. Try again.");
             }
             userInput = true;
             while(userInput == true)
             {
                 if(horizontal == true)
                     System.out.println("Input the LEFTMOST coordinate of your ship");
                 else
                     System.out.println("Input the UPMOST coordinate of your ship");
                 input = in.nextLine();
                 if(check(input, alpha)==true)
                 {
                     letter = input.substring(0,1);
                     num = Integer.parseInt(input.substring(1));
                 }
                 else
                    letter = "q";
                 if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                 {
                    let = 1;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("b"))
                 {
                    let = 2;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("c"))
                 {
                    let = 3;
                    userInput = false;
                 }
                  else if (letter.equalsIgnoreCase("d"))
                  {
                    let = 4;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("e"))
                 {
                    let = 5;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("f"))
                 {
                    let = 6;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("g"))
                 {
                    let = 7;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("h"))
                 {
                    let = 8;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("i"))
                 {
                    let = 9;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("j"))
                 {
                    let = 10;
                    userInput = false;
                 }
                 if(!((num >= 1) && (num <= 10)))
                    userInput = true;
                    
                    
                 if((let > 7 && horizontal == false) || (num > 7 && horizontal == true))
                 {
                     System.out.println("Your ship would not be on the board.");
                     userInput = true;
                 }
                 if(userInput == true)
                    System.out.println("Your input is out of range. Try again.");
             }
             BShip BSBlue = new BShip (horizontal, num, let);
             if(horizontal == true)
             {
                 for(int i = 0; i <= 3; i++)
                 {
                     def.get(num + i).get(let).blueSet();
                 }
             }
             else
             {
                 for(int i = 0; i <= 3; i++)
                 {
                     def.get(num).get(let + i).blueSet();
                 }
             }
             blueTurn(off, def);
             printBoard(def, 0);
            
            
             //Aircraft Carrier placement
             System.out.println("Place your AIRCRAFT CARRIER (5 spaces long)");
             userInput = true;
             horizontal = true;
             let = 0;
             num = 0;
          
             while(userInput == true)
             {
                 System.out.println("Is your AIRCRAFT CARRIER (V)ertical or (H)orizontal?");
                 String vh = in.nextLine();
                 if(vh.equalsIgnoreCase("h"))
                 {
                     horizontal = true;
                     userInput = false;
                 }
                 else if(vh.equalsIgnoreCase("v"))
                 {
                     horizontal = false;
                     userInput = false;
                 }
                 else
                     System.out.println("Not a possible selection. Try again.");
             }
             userInput = true;
             while(userInput == true)
             {
                 if(horizontal == true)
                     System.out.println("Input the LEFTMOST coordinate of your ship");
                 else
                     System.out.println("Input the UPMOST coordinate of your ship");
                 input = in.nextLine();
                 if(check(input, alpha)==true)
                 {
                     letter = input.substring(0,1);
                     num = Integer.parseInt(input.substring(1));
                 }
                 else
                    letter = "q";
                 if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                 {
                    let = 1;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("b"))
                 {
                    let = 2;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("c"))
                 {
                    let = 3;
                    userInput = false;
                 }
                  else if (letter.equalsIgnoreCase("d"))
                  {
                    let = 4;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("e"))
                 {
                    let = 5;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("f"))
                 {
                    let = 6;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("g"))
                 {
                    let = 7;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("h"))
                 {
                    let = 8;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("i"))
                 {
                    let = 9;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("j"))
                 {
                    let = 10;
                    userInput = false;
                 }
                 if(!((num >= 1) && (num <= 10)))
                    userInput = true;
                 
                    
                 if((let > 6 && horizontal == false) || (num > 6 && horizontal == true))//testing placement validity
                 {
                     System.out.println("Your ship would not be on the board.");
                     userInput = true;
                 }
                 else if(horizontal == true)
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num + 1).get(let).get().equals("#") || def.get(num + 2).get(let).get().equals("#") || def.get(num + 3).get(let).get().equals("#") || def.get(num + 4).get(let).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 else
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num).get(let + 1).get().equals("#") || def.get(num).get(let + 2).get().equals("#") || def.get(num).get(let + 3).get().equals("#") || def.get(num).get(let + 4).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 if(userInput == true)
                    System.out.println("Your input is out of range. Try again.");
             }
             Carrier ACBlue = new Carrier (horizontal, num, let);
             if(horizontal == true)
             {
                 for(int i = 0; i <= 4; i++)
                 {
                     def.get(num + i).get(let).blueSet();
                 }
             }
             else
             {
                 for(int i = 0; i <= 4; i++)
                 {
                     def.get(num).get(let + i).blueSet();
                 }
             }
             blueTurn(off, def);
             printBoard(def, 0);
            
            
             //Destroyer placement
             System.out.println("Place your DESTROYER (3 spaces long)");
             userInput = true;
             horizontal = true;
             let = 0; 
             num = 0;
             
             while(userInput == true)
             {
                 System.out.println("Is your DESTROYER (V)ertical or (H)orizontal?");
                 String vh = in.nextLine();
                 if(vh.equalsIgnoreCase("h"))
                 {
                     horizontal = true;
                     userInput = false;
                 }
                 else if(vh.equalsIgnoreCase("v"))
                 {
                     horizontal = false;
                     userInput = false;
                 }
                 else
                     System.out.println("Not a possible selection. Try again.");
             }
             userInput = true;
             while(userInput == true)
             {
                 if(horizontal == true)
                     System.out.println("Input the LEFTMOST coordinate of your ship");
                 else
                     System.out.println("Input the UPMOST coordinate of your ship");
                 input = in.nextLine();
                 if(check(input, alpha)==true)
                 {
                     letter = input.substring(0,1);
                     num = Integer.parseInt(input.substring(1));
                 }
                 else
                    letter = "q";
                 if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                 {
                    let = 1;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("b"))
                 {
                    let = 2;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("c"))
                 {
                    let = 3;
                    userInput = false;
                 }
                  else if (letter.equalsIgnoreCase("d"))
                  {
                    let = 4;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("e"))
                 {
                    let = 5;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("f"))
                 {
                    let = 6;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("g"))
                 {
                    let = 7;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("h"))
                 {
                    let = 8;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("i"))
                 {
                    let = 9;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("j"))
                 {
                    let = 10;
                    userInput = false;
                 }
                 if(!((num >= 1) && (num <= 10)))
                    userInput = true;
                 
                    
                 if((let > 8 && horizontal == false) || (num > 8 && horizontal == true))//testing placement validity
                 {
                     System.out.println("Your ship would not be on the board.");
                     userInput = true;
                 }
                 else if(horizontal == true)
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num + 1).get(let).get().equals("#") || def.get(num + 2).get(let).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 else
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num).get(let + 1).get().equals("#") || def.get(num).get(let + 2).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 if(userInput == true)
                    System.out.println("Your input is out of range. Try again.");
             }
             Destroyer DBlue = new Destroyer (horizontal, num, let);
             if(horizontal == true)
             {
                 for(int i = 0; i <= 2; i++)
                 {
                     def.get(num + i).get(let).blueSet();
                 }
             }
             else
             {
                 for(int i = 0; i <= 2; i++)
                 {
                     def.get(num).get(let + i).blueSet();
                 }
             }
             blueTurn(off, def);
             printBoard(def, 0);
            
             //Submarine placement
             System.out.println("Place your SUBMARINE (3 spaces long)");
             userInput = true;
             horizontal = true;
             let = 0;
             num = 0;
 
             while(userInput == true)
             {
                 System.out.println("Is your SUBMARINE (V)ertical or (H)orizontal?");
                 String vh = in.nextLine();
                 if(vh.equalsIgnoreCase("h"))
                 {
                     horizontal = true;
                     userInput = false;
                 }
                 else if(vh.equalsIgnoreCase("v"))
                 {
                     horizontal = false;
                     userInput = false;
                 }
                 else
                     System.out.println("Not a possible selection. Try again.");
             }
             userInput = true;
             while(userInput == true)
             {
                 if(horizontal == true)
                     System.out.println("Input the LEFTMOST coordinate of your ship");
                 else
                     System.out.println("Input the UPMOST coordinate of your ship");
                 input = in.nextLine();
                 if(check(input, alpha)==true)
                 {
                     letter = input.substring(0,1);
                     num = Integer.parseInt(input.substring(1));
                 }
                 else
                    letter = "q";
                 if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                 {
                    let = 1;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("b"))
                 {
                    let = 2;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("c"))
                 {
                    let = 3;
                    userInput = false;
                 }
                  else if (letter.equalsIgnoreCase("d"))
                  {
                    let = 4;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("e"))
                 {
                    let = 5;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("f"))
                 {
                    let = 6;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("g"))
                 {
                    let = 7;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("h"))
                 {
                    let = 8;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("i"))
                 {
                    let = 9;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("j"))
                 {
                    let = 10;
                    userInput = false;
                 }
                 if(!((num >= 1) && (num <= 10)))
                    userInput = true;
                 
                    
                 if((let > 8 && horizontal == false) || (num > 8 && horizontal == true))//testing placement validity
                 {
                     System.out.println("Your ship would not be on the board.");
                     userInput = true;
                 }
                 else if(horizontal == true)
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num + 1).get(let).get().equals("#") || def.get(num + 2).get(let).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 else
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num).get(let + 1).get().equals("#") || def.get(num).get(let + 2).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 if(userInput == true)
                    System.out.println("Your input is out of range. Try again.");
             }
             Sub SMBlue = new Sub (horizontal, num, let);
             if(horizontal == true)
             {
                 for(int i = 0; i <= 2; i++)
                 {
                     def.get(num + i).get(let).blueSet();
                 }
             }
             else
             {
                 for(int i = 0; i <= 2; i++)
                 {
                     def.get(num).get(let + i).blueSet();
                 }
             }
             blueTurn(off, def);
             printBoard(def, 0);
            
             //PT Boat placement
             System.out.println("Place your PT BOAT (2 spaces long)");
             userInput = true;
             horizontal = true;
             let = 0;
             num = 0;
        
             while(userInput == true)
             {
                 System.out.println("Is your PT BOAT (V)ertical or (H)orizontal?");
                 String vh = in.nextLine();
                 if(vh.equalsIgnoreCase("h"))
                 {
                     horizontal = true;
                     userInput = false;
                 }
                 else if(vh.equalsIgnoreCase("v"))
                 {
                     horizontal = false;
                     userInput = false;
                 }
                 else
                     System.out.println("Not a possible selection. Try again.");
             }
             userInput = true;
             while(userInput == true)
             {
                 if(horizontal == true)
                     System.out.println("Input the LEFTMOST coordinate of your ship");
                 else
                     System.out.println("Input the UPMOST coordinate of your ship");
                 input = in.nextLine();
                 if(check(input, alpha)==true)
                 {
                     letter = input.substring(0,1);
                     num = Integer.parseInt(input.substring(1));
                 }
                 else
                    letter = "q";
                 if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                 {
                    let = 1;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("b"))
                 {
                    let = 2;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("c"))
                 {
                    let = 3;
                    userInput = false;
                 }
                  else if (letter.equalsIgnoreCase("d"))
                  {
                    let = 4;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("e"))
                 {
                    let = 5;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("f"))
                 {
                    let = 6;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("g"))
                 {
                    let = 7;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("h"))
                 {
                    let = 8;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("i"))
                 {
                    let = 9;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("j"))
                 {
                    let = 10;
                    userInput = false;
                 }
                 if(!((num >= 1) && (num <= 10)))
                    userInput = true;
                 
                    
                 if((let > 9 && horizontal == false) || (num > 9 && horizontal == true))//testing placement validity
                 {
                     System.out.println("Your ship would not be on the board.");
                     userInput = true;
                 }
                 else if(horizontal == true)
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num + 1).get(let).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 else
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num).get(let + 1).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 if(userInput == true)
                    System.out.println("Your input is out of range. Try again.");
             }
             PTBoat PTBlue = new PTBoat (horizontal, num, let);
             if(horizontal == true)
             {
                 for(int i = 0; i <= 1; i++)
                 {
                     def.get(num + i).get(let).blueSet();
                 }
             }
             else
             {
                 for(int i = 0; i <= 1; i++)
                 {
                     def.get(num).get(let + i).blueSet();
                 }
             }
             blueTurn(off, def);
             printBoard(def, 0);
            
             /**
             * End of ship placement
             */
             //Game Starts and players take turns
             while (gameOver == false)
             {
                System.out.println("Press 'ENTER' and hand the computer off to RED");
                waiting = in.nextLine();
                clearBoard();
                System.out.println("RED's Turn");
                System.out.println("Press 'ENTER' to continue");
                waiting = in.nextLine();
                if(hit == true)
                {
                    if(sink == true)
                    {
                        System.out.println("One of your ships was sunk last turn");
                        System.out.println(shipsRemRed + " ships remaining");
                        sink = false;
                        hit = false;
                    }
                    else
                    {
                        System.out.println("BLUE scored a hit on you last turn");
                        hit = false;
                    }
                }
                let = 0;
                num = 0;
                red = true;
                while (red == true)//all of RED's turn within this while statement
                {
                    redTurn(off, def);
                    printBoard(off);
                    printBoard(def, 0);
                    System.out.println("Input coordinates you wish to attack");
                    input = in.next();
                    if(check(input, alpha)==true)
                    {
                        letter = input.substring(0,1);
                        num = Integer.parseInt(input.substring(1));
                    }
                    if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                    {
                        let = 1;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("b"))
                    {
                        let = 2;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("c"))
                    {
                        let = 3;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("d"))
                    {
                        let = 4;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("e"))
                    {
                        let = 5;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("f"))
                    {
                        let = 6;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("g"))
                    {
                        let = 7;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("h"))
                    {
                        let = 8;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("i"))
                    {
                        let = 9;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("j"))
                    {
                        let = 10;
                        red = false;
                    }
                    if(!((num >= 1) && (num <= 10)))
                    {
                        red = true;
                    }
                    blueTurn(off, def);
                    if(((!red == true) && (def.get(num).get(let).get().equals("O"))||(def.get(num).get(let).get().equals("X"))))
                    {
                        System.out.println("You have already attacked that space");
                        red = true;
                    }
                    if(red == true)
                        System.out.println("Invalid Input. Try Again");
                }
                if(off.get(num).get(let).redGuess(def.get(num).get(let)) == true)
                {
                    hit = true;
                }
                redTurn(off, def);
                printBoard(off);
                printBoard(def, 0);
                if(hit == true)
                {
                    System.out.println("Hit!");
                    ACBlue.hit(let, num);
                    BSBlue.hit(let, num);
                    DBlue.hit(let, num);
                    SMBlue.hit(let, num);
                    PTBlue.hit(let, num);
                }
                if(ACBlue.sunk(let, num)==true)
                {
                    System.out.println("You sunk BLUE's AIRCRAFT CARRIER!");
                    sink = true;
                    shipsRemBlue--;
                    System.out.println(shipsRemBlue + " ships left to sink!");
                }
                if(BSBlue.sunk(let, num)==true)
                {
                    System.out.println("You sunk BLUE's BATTLESHIP!");
                    sink = true;
                    shipsRemBlue--;
                    System.out.println(shipsRemBlue + " ships left to sink!");
                }
                if(DBlue.sunk(let, num)==true)
                {
                    System.out.println("You sunk BLUE's DESTROYER!");
                    sink = true;
                    shipsRemBlue--;
                    System.out.println(shipsRemBlue + " ships left to sink!");
                }
                if(SMBlue.sunk(let, num)==true)
                {
                    System.out.println("You sunk BLUE's SUBMARINE!");
                    sink = true;
                    shipsRemBlue--;
                    System.out.println(shipsRemBlue + " ships left to sink!");
                }
                if(PTBlue.sunk(let, num)==true)
                {
                    System.out.println("You sunk BLUE's PT Boat!");
                    sink = true;
                    shipsRemBlue--;
                    System.out.println(shipsRemBlue + " ships left to sink!");
                }
                if(shipsRemBlue == 0)
                {
                    gameOver = true;
                    System.out.println("RED wins!");
                }                
                /**
                 * Blue's Turn
                 */
                if(gameOver == false)
                {
                    System.out.println("Press 'Enter' and pass the computer off to BLUE");
                    waiting = in.nextLine();
                    clearBoard();
                    System.out.println("BLUE's turn");
                    System.out.println("Press 'ENTER' to continue");
                    waiting = in.nextLine();
                    if(hit == true)
                    {
                        if(sink == true)
                        {
                            System.out.println("One of your ships was sunk last turn");
                            System.out.println(shipsRemBlue + " ships remaining");
                            sink = false;
                            hit = false;
                        }
                        else
                        {
                            System.out.println("RED scored a hit on you last turn");
                            hit = false;
                        }
                    }
                    let = 0;
                    num = 0;
                    red = false;
                    while (red == false)//all of BLUE's turn within this while statement
                    {
                        blueTurn(off, def);
                        printBoard(off);
                        printBoard(def, 0);
                        System.out.println("Input coordinates you wish to attack");
                        input = in.next();
                        if(check(input, alpha)==true)
                        {
                            letter = input.substring(0,1);
                            num = Integer.parseInt(input.substring(1));
                        }
                        if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                        {
                            let = 1;
                            red = true;
                        }
                        else if (letter.equalsIgnoreCase("b"))
                        {
                            let = 2;
                            red = true;
                        }
                        else if (letter.equalsIgnoreCase("c"))
                        {
                            let = 3;
                            red = true;
                        }
                        else if (letter.equalsIgnoreCase("d"))
                        {
                            let = 4;
                            red = true;
                        }
                        else if (letter.equalsIgnoreCase("e"))
                        {
                            let = 5;
                            red = true;
                        }
                        else if (letter.equalsIgnoreCase("f"))
                        {
                            let = 6;
                            red = true;
                        }
                        else if (letter.equalsIgnoreCase("g"))
                        {
                            let = 7;
                            red = true;
                        }
                        else if (letter.equalsIgnoreCase("h"))
                        {
                            let = 8;
                            red = true;
                        }
                        else if (letter.equalsIgnoreCase("i"))
                        {
                            let = 9;
                            red = true;
                        }
                        else if (letter.equalsIgnoreCase("j"))
                        {
                            let = 10;
                            red = true;
                        }
                        if(!((num >= 1) && (num <= 10)))
                        {
                            red = false;
                        }
                        redTurn(off, def);
                        if((def.get(num).get(let).get().equals("O"))||(def.get(num).get(let).get().equals("X")))
                        {
                            System.out.println("You have already attacked that space");
                            red = false;
                        }
                        if(red == false)
                            System.out.println("Invalid Input. Try Again");
                    }
                    if(off.get(num).get(let).blueGuess(def.get(num).get(let)) == true)
                    {
                        hit = true;
                    }
                    blueTurn(off, def);
                    printBoard(off);
                    printBoard(def, 0);
                    if(hit == true)
                    {
                        System.out.println("Hit!");
                        ACRed.hit(let, num);
                        BSRed.hit(let, num);
                        DRed.hit(let, num);
                        SMRed.hit(let, num);
                        PTRed.hit(let, num);
                    }
                    if(ACRed.sunk(let, num)==true)
                    {
                        System.out.println("You sunk RED's AIRCRAFT CARRIER!");
                        sink = true;
                        shipsRemRed--;
                        System.out.println(shipsRemRed + " ships left to sink!");
                    }
                    if(BSRed.sunk(let, num)==true)
                    {
                        System.out.println("You sunk Red's BATTLESHIP!");
                        sink = true;
                        shipsRemRed--;
                        System.out.println(shipsRemRed + " ships left to sink!");
                    }
                    if(DRed.sunk(let, num)==true)
                    {
                        System.out.println("You sunk RED's DESTROYER!");
                        sink = true;
                        shipsRemRed--;
                        System.out.println(shipsRemRed + " ships left to sink!");
                    }
                    if(SMRed.sunk(let, num)==true)
                    {
                        System.out.println("You sunk RED's SUBMARINE!");
                        sink = true;
                        shipsRemRed--;
                        System.out.println(shipsRemRed + " ships left to sink!");
                    }
                    if(PTRed.sunk(let, num)==true)
                    {
                        System.out.println("You sunk RED's PT Boat!");
                        sink = true;
                        shipsRemRed--;
                        System.out.println(shipsRemRed + " ships left to sink!");
                    }
                    if(shipsRemRed == 0)
                    {
                        gameOver = true;
                        System.out.println("BLUE wins!");
                    }
                }
             }
        }
        else if(players == 1)
        {
             //RED is the Player
             //BLUE is the AI
             //red's turn
             /**
              * PLAYER ship placement
              */
             printBoard(def, 0);
             boolean attacking = false, sinking = false;    
            
             //battleship placement
             System.out.println("Place your BATTLESHIP (4 spaces long)");
             userInput = true;
             boolean horizontal = true;
             int let = 0, num = 0;
             while(userInput == true)
             {
                 System.out.println("Is your BATTLESHIP (V)ertical or (H)orizontal?");
                 String vh = in.nextLine();
                 if(vh.equalsIgnoreCase("h"))
                 {
                     horizontal = true;
                     userInput = false;
                 }
                 else if(vh.equalsIgnoreCase("v"))
                 {
                     horizontal = false;
                     userInput = false;
                 }
                 else
                     System.out.println("Not a possible selection. Try again.");
             }
             userInput = true;
             while(userInput == true)
             {
                 if(horizontal == true)
                     System.out.println("Input the LEFTMOST coordinate of your ship");
                 else
                     System.out.println("Input the UPMOST coordinate of your ship");
                 input = in.nextLine();
                 if(check(input, alpha)==true)
                 {
                     letter = input.substring(0,1);
                     num = Integer.parseInt(input.substring(1));
                 }
                 else
                    letter = "q";
                 if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                 {
                    let = 1;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("b"))
                 {
                    let = 2;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("c"))
                 {
                    let = 3;
                    userInput = false;
                 }
                  else if (letter.equalsIgnoreCase("d"))
                  {
                    let = 4;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("e"))
                 {
                    let = 5;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("f"))
                 {
                    let = 6;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("g"))
                 {
                    let = 7;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("h"))
                 {
                    let = 8;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("i"))
                 {
                    let = 9;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("j"))
                 {
                    let = 10;
                    userInput = false;
                 }
                 if(!((num >= 1) && (num <= 10)))
                    userInput = true;
                    
                    
                 if((let > 7 && horizontal == false) || (num > 7 && horizontal == true))
                 {
                     System.out.println("Your ship would not be on the board.");
                     userInput = true;
                 }
                 if(userInput == true)
                    System.out.println("Your input is out of range. Try again.");
             }
             BShip BSRed = new BShip (horizontal, num, let);
             if(horizontal == true)
             {
                 for(int i = 0; i <= 3; i++)
                 {
                     def.get(num + i).get(let).redSet();
                 }
             }
             else
             {
                 for(int i = 0; i <= 3; i++)
                 {
                     def.get(num).get(let + i).redSet();
                 }
             }
             redTurn(off, def);
             printBoard(def, 0);
            
            
             //Aircraft Carrier placement
             System.out.println("Place your AIRCRAFT CARRIER (5 spaces long)");
             userInput = true;
             horizontal = true;
             let = 0;
             num = 0;
             
             while(userInput == true)
             {
                 System.out.println("Is your AIRCRAFT CARRIER (V)ertical or (H)orizontal?");
                 String vh = in.nextLine();
                 if(vh.equalsIgnoreCase("h"))
                 {
                     horizontal = true;
                     userInput = false;
                 }
                 else if(vh.equalsIgnoreCase("v"))
                 {
                     horizontal = false;
                     userInput = false;
                 }
                 else
                     System.out.println("Not a possible selection. Try again.");
             }
             userInput = true;
             while(userInput == true)
             {
                 if(horizontal == true)
                     System.out.println("Input the LEFTMOST coordinate of your ship");
                 else
                     System.out.println("Input the UPMOST coordinate of your ship");
                 input = in.nextLine();
                 if(check(input, alpha)==true)
                 {
                     letter = input.substring(0,1);
                     num = Integer.parseInt(input.substring(1));
                 }
                 else
                    letter = "q";
                 if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                 {
                    let = 1;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("b"))
                 {
                    let = 2;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("c"))
                 {
                    let = 3;
                    userInput = false;
                 }
                  else if (letter.equalsIgnoreCase("d"))
                  {
                    let = 4;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("e"))
                 {
                    let = 5;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("f"))
                 {
                    let = 6;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("g"))
                 {
                    let = 7;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("h"))
                 {
                    let = 8;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("i"))
                 {
                    let = 9;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("j"))
                 {
                    let = 10;
                    userInput = false;
                 }
                 if(!((num >= 1) && (num <= 10)))
                    userInput = true;
                 
                    
                 if((let > 6 && horizontal == false) || (num > 6 && horizontal == true))//testing placement validity
                 {
                     System.out.println("Your ship would not be on the board.");
                     userInput = true;
                 }
                 else if(horizontal == true)
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num + 1).get(let).get().equals("#") || def.get(num + 2).get(let).get().equals("#") || def.get(num + 3).get(let).get().equals("#") || def.get(num + 4).get(let).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 else
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num).get(let + 1).get().equals("#") || def.get(num).get(let + 2).get().equals("#") || def.get(num).get(let + 3).get().equals("#") || def.get(num).get(let + 4).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 if(userInput == true)
                    System.out.println("Your input is out of range. Try again.");
             }
             Carrier ACRed = new Carrier (horizontal, num, let);
             if(horizontal == true)
             {
                 for(int i = 0; i <= 4; i++)
                 {
                     def.get(num + i).get(let).redSet();
                 }
             }
             else
             {
                 for(int i = 0; i <= 4; i++)
                 {
                     def.get(num).get(let + i).redSet();
                 }
             }
             redTurn(off, def);
             printBoard(def, 0);
            
            
             //Destroyer placement
             System.out.println("Place your DESTROYER (3 spaces long)");
             userInput = true;
             horizontal = true;
             let = 0;
             num = 0;
             
             while(userInput == true)
             {
                 System.out.println("Is your DESTROYER (V)ertical or (H)orizontal?");
                 String vh = in.nextLine();
                 if(vh.equalsIgnoreCase("h"))
                 {
                     horizontal = true;
                     userInput = false;
                 }
                 else if(vh.equalsIgnoreCase("v"))
                 {
                     horizontal = false;
                     userInput = false;
                 }
                 else
                     System.out.println("Not a possible selection. Try again.");
             }
             userInput = true;
             while(userInput == true)
             {
                 if(horizontal == true)
                     System.out.println("Input the LEFTMOST coordinate of your ship");
                 else
                     System.out.println("Input the UPMOST coordinate of your ship");
                 input = in.nextLine();
                 if(check(input, alpha)==true)
                 {
                     letter = input.substring(0,1);
                     num = Integer.parseInt(input.substring(1));
                 }
                 else
                    letter = "q";
                 if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                 {
                    let = 1;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("b"))
                 {
                    let = 2;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("c"))
                 {
                    let = 3;
                    userInput = false;
                 }
                  else if (letter.equalsIgnoreCase("d"))
                  {
                    let = 4;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("e"))
                 {
                    let = 5;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("f"))
                 {
                    let = 6;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("g"))
                 {
                    let = 7;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("h"))
                 {
                    let = 8;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("i"))
                 {
                    let = 9;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("j"))
                 {
                    let = 10;
                    userInput = false;
                 }
                 if(!((num >= 1) && (num <= 10)))
                    userInput = true;
                 
                    
                 if((let > 8 && horizontal == false) || (num > 8 && horizontal == true))//testing placement validity
                 {
                     System.out.println("Your ship would not be on the board.");
                     userInput = true;
                 }
                 else if(horizontal == true)
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num + 1).get(let).get().equals("#") || def.get(num + 2).get(let).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 else
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num).get(let + 1).get().equals("#") || def.get(num).get(let + 2).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 if(userInput == true)
                    System.out.println("Your input is out of range. Try again.");
             }
             Destroyer DRed = new Destroyer (horizontal, num, let);
             if(horizontal == true)
             {
                 for(int i = 0; i <= 2; i++)
                 {
                     def.get(num + i).get(let).redSet();
                 }
             }
             else
             {
                 for(int i = 0; i <= 2; i++)
                 {
                     def.get(num).get(let + i).redSet();
                 }
             }
             redTurn(off, def);
             printBoard(def, 0);
            
             //Submarine placement
             System.out.println("Place your SUBMARINE (3 spaces long)");
             userInput = true;
             horizontal = true;
             let = 0;
             num = 0;
             
             while(userInput == true)
             {
                 System.out.println("Is your SUBMARINE (V)ertical or (H)orizontal?");
                 String vh = in.nextLine();
                 if(vh.equalsIgnoreCase("h"))
                 {
                     horizontal = true;
                     userInput = false;
                 }
                 else if(vh.equalsIgnoreCase("v"))
                 {
                     horizontal = false;
                     userInput = false;
                 }
                 else
                     System.out.println("Not a possible selection. Try again.");
             }
             userInput = true;
             while(userInput == true)
             {
                 if(horizontal == true)
                     System.out.println("Input the LEFTMOST coordinate of your ship");
                 else
                     System.out.println("Input the UPMOST coordinate of your ship");
                 input = in.nextLine();
                 if(check(input, alpha)==true)
                 {
                     letter = input.substring(0,1);
                     num = Integer.parseInt(input.substring(1));
                 }
                 else
                    letter = "q";
                 if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                 {
                    let = 1;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("b"))
                 {
                    let = 2;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("c"))
                 {
                    let = 3;
                    userInput = false;
                 }
                  else if (letter.equalsIgnoreCase("d"))
                  {
                    let = 4;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("e"))
                 {
                    let = 5;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("f"))
                 {
                    let = 6;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("g"))
                 {
                    let = 7;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("h"))
                 {
                    let = 8;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("i"))
                 {
                    let = 9;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("j"))
                 {
                    let = 10;
                    userInput = false;
                 }
                 if(!((num >= 1) && (num <= 10)))
                    userInput = true;
                 
                    
                 if((let > 8 && horizontal == false) || (num > 8 && horizontal == true))//testing placement validity
                 {
                     System.out.println("Your ship would not be on the board.");
                     userInput = true;
                 }
                 else if(horizontal == true)
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num + 1).get(let).get().equals("#") || def.get(num + 2).get(let).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 else
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num).get(let + 1).get().equals("#") || def.get(num).get(let + 2).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 if(userInput == true)
                    System.out.println("Your input is out of range. Try again.");
             }
             Sub SMRed = new Sub (horizontal, num, let);
             if(horizontal == true)
             {
                 for(int i = 0; i <= 2; i++)
                 {
                     def.get(num + i).get(let).redSet();
                 }
             }
             else
             {
                 for(int i = 0; i <= 2; i++)
                 {
                     def.get(num).get(let + i).redSet();
                 }
             }
             redTurn(off, def);
             printBoard(def, 0);
            
             //PT Boat placement
             System.out.println("Place your PT BOAT (2 spaces long)");
             userInput = true;
             horizontal = true;
             let = 0;
             num = 0;
           
             while(userInput == true)
             {
                 System.out.println("Is your PT BOAT (V)ertical or (H)orizontal?");
                 String vh = in.nextLine();
                 if(vh.equalsIgnoreCase("h"))
                 {
                     horizontal = true;
                     userInput = false;
                 }
                 else if(vh.equalsIgnoreCase("v"))
                 {
                     horizontal = false;
                     userInput = false;
                 }
                 else
                     System.out.println("Not a possible selection. Try again.");
             }
             userInput = true;
             while(userInput == true)
             {
                 if(horizontal == true)
                     System.out.println("Input the LEFTMOST coordinate of your ship");
                 else
                     System.out.println("Input the UPMOST coordinate of your ship");
                 input = in.nextLine();
                 if(check(input, alpha)==true)
                 {
                     letter = input.substring(0,1);
                     num = Integer.parseInt(input.substring(1));
                 }
                 else
                    letter = "q";
                 if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                 {
                    let = 1;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("b"))
                 {
                    let = 2;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("c"))
                 {
                    let = 3;
                    userInput = false;
                 }
                  else if (letter.equalsIgnoreCase("d"))
                  {
                    let = 4;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("e"))
                 {
                    let = 5;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("f"))
                 {
                    let = 6;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("g"))
                 {
                    let = 7;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("h"))
                 {
                    let = 8;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("i"))
                 {
                    let = 9;
                    userInput = false;
                 }
                 else if (letter.equalsIgnoreCase("j"))
                 {
                    let = 10;
                    userInput = false;
                 }
                 if(!((num >= 1) && (num <= 10)))
                    userInput = true;
                 
                    
                 if((let > 9 && horizontal == false) || (num > 9 && horizontal == true))//testing placement validity
                 {
                     System.out.println("Your ship would not be on the board.");
                     userInput = true;
                 }
                 else if(horizontal == true)
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num + 1).get(let).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 else
                 {
                     if(def.get(num).get(let).get().equals("#") || def.get(num).get(let + 1).get().equals("#"))
                     {
                         System.out.println("Your ship intersects a previously placed ship");
                         userInput = true;
                     }
                 }
                 if(userInput == true)
                    System.out.println("Your input is out of range. Try again.");
             }
             PTBoat PTRed = new PTBoat (horizontal, num, let);
             if(horizontal == true)
             {
                 for(int i = 0; i <= 1; i++)
                 {
                     def.get(num + i).get(let).redSet();
                 }
             }
             else
             {
                 for(int i = 0; i <= 1; i++)
                 {
                     def.get(num).get(let + i).redSet();
                 }
             }
             redTurn(off, def);
             printBoard(def, 0);
             /**
              * END PLAYER ship placement
              */
             /**
              * AI ship placement
              */
             new java.util.Random();
             blueTurn(off, def);
             //battleship
             num = ((int)(Math.random()*7)) + 1;
             let = (int)(Math.random()*7) + 1;
             if(Math.random() > 0.5)
             {
                horizontal = true;
                def.get(num).get(let).blueSet();
                def.get(num+1).get(let).blueSet();
                def.get(num+2).get(let).blueSet();
                def.get(num+3).get(let).blueSet();
             }
             else
             {
                horizontal = false;
                def.get(num).get(let).blueSet();
                def.get(num).get(let+1).blueSet();
                def.get(num).get(let+2).blueSet();
                def.get(num).get(let+3).blueSet();
             }
             BShip BSBlue = new BShip(horizontal, num, let);
             //Air carrier
             blueTurn(off, def);
             boolean aiGO = true;
             boolean a = true, b = true, c = true, d = true, e = true;
             while(aiGO == true)
             {
                num = (int)(Math.random()*6) + 1;
                let = (int)(Math.random()*6) + 1;
                
                if(num == 6 || let == 6)
                {
                    aiGO = true;
                    a = false;
                }
                else if(Math.random() > 0.5)
                {
                    horizontal = true;
                    a = def.get(num).get(let).get().equals(".");
                    b = def.get(num+1).get(let).get().equals(".");
                    c = def.get(num+2).get(let).get().equals(".");
                    d = def.get(num+3).get(let).get().equals(".");
                    e = def.get(num+4).get(let).get().equals(".");
                }
                else
                {
                    horizontal = false;
                    a = def.get(num).get(let).get().equals(".");
                    b = def.get(num).get(let+1).get().equals(".");
                    c = def.get(num).get(let+2).get().equals(".");
                    d = def.get(num).get(let+3).get().equals(".");
                    e = def.get(num).get(let+4).get().equals(".");
                }
                if(a == b && b == c && c == d && d == e && e == true)
                    aiGO = false;
                else
                    aiGO = true;
             }
             if(horizontal == true)
             {
                def.get(num).get(let).blueSet();
                def.get(num+1).get(let).blueSet();
                def.get(num+2).get(let).blueSet();
                def.get(num+3).get(let).blueSet();
                def.get(num+4).get(let).blueSet();
             }
             else
             {
                def.get(num).get(let).blueSet();
                def.get(num).get(let+1).blueSet();
                def.get(num).get(let+2).blueSet();
                def.get(num).get(let+3).blueSet();
                def.get(num).get(let+4).blueSet();
             }
             Carrier ACBlue = new Carrier(horizontal, num, let);
             //destroyer
             blueTurn(off, def);
             aiGO = true;
              while(aiGO == true)
             {
                num = (int)(Math.random()*8) + 1;
                let = (int)(Math.random()*8) + 1;
                if(num == 8 || let == 8)
                {
                    aiGO = true;
                    a = false;
                }
                else if(Math.random() > 0.5)
                {
                    horizontal = true;
                    a = def.get(num).get(let).get().equals(".");
                    b = def.get(num+1).get(let).get().equals(".");
                    c = def.get(num+2).get(let).get().equals(".");
                }
                else
                {
                    horizontal = false;
                    a = def.get(num).get(let).get().equals(".");
                    b = def.get(num).get(let+1).get().equals(".");
                    c = def.get(num).get(let+2).get().equals(".");
                }
                if(a == b && b == c && c == true)
                    aiGO = false;
                else
                    aiGO = true;
             }
             if(horizontal == true)
             {
                def.get(num).get(let).blueSet();
                def.get(num+1).get(let).blueSet();
                def.get(num+2).get(let).blueSet();
             }
             else
             {
                def.get(num).get(let).blueSet();
                def.get(num).get(let+1).blueSet();
                def.get(num).get(let+2).blueSet();
             }
             Destroyer DBlue = new Destroyer(horizontal, num, let);
             //submarine
             blueTurn(off, def);
             aiGO = true;
              while(aiGO == true)
             {
                num = (int)(Math.random()*8) + 1;
                let = (int)(Math.random()*8) + 1;
                if(num == 8 || let == 8)
                {
                    aiGO = true;
                    a = false;
                }
                else if(Math.random() > 0.5)
                {
                    horizontal = true;
                    a = def.get(num).get(let).get().equals(".");
                    b = def.get(num+1).get(let).get().equals(".");
                    c = def.get(num+2).get(let).get().equals(".");
                }
                else
                {
                    horizontal = false;
                    a = def.get(num).get(let).get().equals(".");
                    b = def.get(num).get(let+1).get().equals(".");
                    c = def.get(num).get(let+2).get().equals(".");
                }
                if(a == b && b == c && c == true)
                    aiGO = false;
                else
                    aiGO = true;
             }
             if(horizontal == true)
             {
                def.get(num).get(let).blueSet();
                def.get(num+1).get(let).blueSet();
                def.get(num+2).get(let).blueSet();
             }
             else
             {
                def.get(num).get(let).blueSet();
                def.get(num).get(let+1).blueSet();
                def.get(num).get(let+2).blueSet();
             }
             Sub SMBlue = new Sub(horizontal, num, let);
             //PT boat
             aiGO = true;
             blueTurn(off, def);
              while(aiGO == true)
             {
                num = (int)(Math.random()*9) + 1;
                let = (int)(Math.random()*9) + 1;
                if(num == 9 || let == 9)
                {
                    aiGO = true;
                    a = false;
                }
                else if(Math.random() > 0.5)
                {
                    horizontal = true;
                    a = def.get(num).get(let).get().equals(".");
                    b = def.get(num+1).get(let).get().equals(".");
                }
                else
                {
                    horizontal = false;
                    a = def.get(num).get(let).get().equals(".");
                    b = def.get(num).get(let+1).get().equals(".");
                }
                if(a == b && b == true)
                    aiGO = false;
                else
                    aiGO = true;
             }
             if(horizontal == true)
             {
                def.get(num).get(let).blueSet();
                def.get(num+1).get(let).blueSet();
             }
             else
             {
                def.get(num).get(let).blueSet();
                def.get(num).get(let+1).blueSet();
             }
             PTBoat PTBlue = new PTBoat(horizontal, num, let);
             /**
              * end AI ship placement.
              */
             /**
              * RED's turn
              */
             boolean searching = false;
             int aiAttackTimer = 0;
             int aiAttLet = 0;
             int aiAttNum = 0;
             while (gameOver == false)
             {
                clearBoard();
                if(hit == true)
                {
                    if(sink == true)
                    {
                        System.out.println("One of your ships was sunk last turn");
                        System.out.println(shipsRemRed + " ships remaining");
                        sink = false;
                        hit = false;
                    }
                    else
                    {
                        System.out.println("RODNEY scored a hit on you last turn");
                        hit = false;
                    }
                }
                let = 0;
                num = 0;
                red = true;
                while (red == true)//all of RED's turn within this while statement
                {
                    redTurn(off, def);
                    printBoard(off);
                    printBoard(def, 0);
                    System.out.println(shipsRemBlue + " ships left to sink");
                    System.out.println("You have " + shipsRemRed + " ships remaining.");
                    System.out.println("Input coordinates you wish to attack");
                    input = in.next();
                    if(check(input, alpha)==true)
                    {
                        letter = input.substring(0,1);
                        num = Integer.parseInt(input.substring(1));
                    }
                    if (letter.equalsIgnoreCase("a")) //this if statement could be optimized using string->char->int conversion
                    {
                        let = 1;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("b"))
                    {
                        let = 2;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("c"))
                    {
                        let = 3;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("d"))
                    {
                        let = 4;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("e"))
                    {
                        let = 5;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("f"))
                    {
                        let = 6;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("g"))
                    {
                        let = 7;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("h"))
                    {
                        let = 8;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("i"))
                    {
                        let = 9;
                        red = false;
                    }
                    else if (letter.equalsIgnoreCase("j"))
                    {
                        let = 10;
                        red = false;
                    }
                    if(!((num >= 1) && (num <= 10)))
                    {
                        red = true;
                    }
                    blueTurn(off, def);
                    if((def.get(num).get(let).get().equals("O"))||(def.get(num).get(let).get().equals("X")))
                    {
                        System.out.println("You have already attacked that space");
                        red = true;
                    }
                    if(red == true)
                        System.out.println("Invalid Input. Try Again");
                }
                if(off.get(num).get(let).redGuess(def.get(num).get(let)) == true)
                {
                    hit = true;
                }
                redTurn(off, def);
                printBoard(off);
                printBoard(def, 0);
                if(hit == true)
                {
                    System.out.println("Hit!");
                    ACBlue.hit(let, num);
                    BSBlue.hit(let, num);
                    DBlue.hit(let, num);
                    SMBlue.hit(let, num);
                    PTBlue.hit(let, num);
                }
                if(ACBlue.sunk(let, num)==true)
                {
                    System.out.println("You sunk RODNEY's AIRCRAFT CARRIER!");
                    sink = true;
                    shipsRemBlue--;
                    System.out.println(shipsRemBlue + " ships left to sink!");
                }
                if(BSBlue.sunk(let, num)==true)
                {
                    System.out.println("You sunk RODNEY's BATTLESHIP!");
                    sink = true;
                    shipsRemBlue--;
                    System.out.println(shipsRemBlue + " ships left to sink!");
                }
                if(DBlue.sunk(let, num)==true)
                {
                    System.out.println("You sunk RODNEY's DESTROYER!");
                    sink = true;
                    shipsRemBlue--;
                    System.out.println(shipsRemBlue + " ships left to sink!");
                }
                if(SMBlue.sunk(let, num)==true)
                {
                    System.out.println("You sunk RODNEY's SUBMARINE!");
                    sink = true;
                    shipsRemBlue--;
                    System.out.println(shipsRemBlue + " ships left to sink!");
                }
                if(PTBlue.sunk(let, num)==true)
                {
                    System.out.println("You sunk RODNEY's PT Boat!");
                    sink = true;
                    shipsRemBlue--;
                    System.out.println(shipsRemBlue + " ships left to sink!");
                }
                if(shipsRemBlue == 0)
                {
                    gameOver = true;
                    System.out.println("You win!");
                }  
                /**
                 * AI RODNEY's turn
                 */
                hit = false;
                sink = false;
                sinking = false;
                if(gameOver == false)
                {
                    sinking = false;
                    aiGO = true;
                    redTurn(off, def);
                    /**
                     * The folowing is RODNEY's random guessing behavior
                     * RODNEY will pick a random space and guess there
                     * if RODNEY gets a hit, attacking = true, and next 
                     * turn, RODNEY will begin ATTACKING behavior
                     */
                    if(sinking == false && attacking == false && searching == false)
                    {
                        while (aiGO == true)
                        {
                            num = (int)(Math.random() * 10) + 1;
                            let = (int)(Math.random() * 10) + 1;
                            if(def.get(num).get(let).get().equals(".") || def.get(num).get(let).get().equals("#"))
                            {
                                aiGO = false;
                            }
                        }
                        if(off.get(num).get(let).blueGuess(def.get(num).get(let)) == true)
                        {
                            hit = true;
                            searching = true;
                            aiAttackTimer = 4;
                            aiAttLet = let;
                            aiAttNum = num;
                        }
                        sinking = true;
                    }
                    /**
                     * The following is ai RODNEY's SEARCHING behavior
                     * boolean searching = true will flag this behavior
                     * During this behavior, RODNEY will search around the
                     * aiAtt coordinates saved from the hit, using the 
                     * aiAttackTimer as direction.
                     * 
                     * This aiAttackTimer system will be used in RODNEY's other behaviors
                     * 4 = guess (aiAttLet, aiAttNum + 1)
                     * 3 = guess (aiAttLet + 1, aiAttNum)
                     * 2 = guess (aiAttLet, aiAttNum - 1)
                     * 1 = guess (aiAttLet - 1, aiAttNum)
                     * 
                     * the AiAttackTimer will decrement with each miss,
                     * and when RODNEY get's a hit, aiAttLet and
                     * aiAttNum will be updated to the hit, but 
                     * aiAttackTimer will keep it's value to be used in
                     * the ATTACKING behavior.
                     * If SEARCHING somehow fails, and aiAttackTimer reaches
                     * 0, RODNEY will return to random guessing behavior.
                     */
                    if(searching == true && sinking == false)
                    {
                        //ensures safe searching around boarders
                        if(aiAttackTimer == 4 && aiAttNum == 10)
                            aiAttackTimer --;
                        if(aiAttackTimer == 3 && aiAttLet == 10)
                            aiAttackTimer --;
                        if(aiAttackTimer == 2 && aiAttNum == 1)
                            aiAttackTimer --;
                        if(aiAttackTimer == 1 && aiAttLet == 1)
                            aiAttackTimer --;
                        if(aiAttackTimer == 4)
                        {
                            if(off.get(aiAttNum + 1).get(aiAttLet).blueGuess(def.get(aiAttNum+1).get(aiAttLet)) == true)
                            {
                                hit = true;
                                attacking = true;
                                searching = false;
                                aiAttNum = aiAttNum+1;
                            }
                            else
                                aiAttackTimer--;
                        }
                        else if(aiAttackTimer == 3)
                        {
                            if(off.get(aiAttNum).get(aiAttLet+1).blueGuess(def.get(aiAttNum).get(aiAttLet+1)) == true)
                            {
                                hit = true;
                                attacking = true;
                                searching = false;
                                aiAttLet = aiAttLet+1;
                            }
                            else
                                aiAttackTimer--;
                        }
                        else if(aiAttackTimer == 2)
                        {
                            if(off.get(aiAttNum-1).get(aiAttLet).blueGuess(def.get(aiAttNum-1).get(aiAttLet)) == true)
                            {
                                hit = true;
                                attacking = true;
                                searching = false;
                                aiAttNum = aiAttNum-1;
                            }
                            else
                                aiAttackTimer--;
                        }
                        else if(aiAttackTimer == 1)
                        {
                            if(off.get(aiAttNum).get(aiAttLet-1).blueGuess(def.get(aiAttNum).get(aiAttLet-1)) == true)
                            {
                                hit = true;
                                attacking = true;
                                searching = false;
                                aiAttLet = aiAttLet-1;
                            }
                            else
                                aiAttackTimer--;
                        }
                        else
                            searching = false;
                        num = aiAttNum;
                        let = aiAttLet;
                        sinking = true;
                    }
                    /**
                     * RODNEY's ATTACKING behavior
                     * This behavior is rather straightforeward.
                     * Because aiAttackTimer does not change if 
                     * there was a hit during searching, this 
                     * behavior will have a very similar structure
                     * to searching. The main difference will be
                     * in it's miss behavior. 
                     * 
                     * If RODNEY encounters a miss while ATTACKING, 
                     * aiAttackTimer will be inverted (4 -> 2, 3 -> 1, 2 -> 4, 1 -> 3),       this reversing behavior occurs at the end of the turn
                     * and RODNEY will travel back down his hits                              where the miss was encountered
                     * using a 
                     * while(def.get(aiAttNum).get(aiAttLet).get().equals("X"))
                     * containing if statements that continue the traversal 
                     * according to aiAttackTimer
                     * 
                     * If a ship "#" is encountered during backpedaling,
                     * RODNEY will move back one space so that next turn
                     * it can attack as usual (this is possible because of 
                     * the fact that aiAttackCounter has been reset)
                     * 
                     * If a miss "O" or empty water "." is encountered, normal behavior resumes
                     * 
                     * If a sink is recieved, normal behavior resumes.
                     */
                    if(attacking == true && sinking == false)
                    {
                        if(aiAttackTimer == 4)
                        {
                            if(aiAttNum <10 && off.get(aiAttNum + 1).get(aiAttLet).blueGuess(def.get(aiAttNum+1).get(aiAttLet)) == true)
                            {
                                hit = true;
                                aiAttNum = aiAttNum+1;
                            }
                            else
                            {
                                aiAttackTimer = 2;
                                while(def.get(aiAttNum-1).get(aiAttLet).get().equals("X"))
                                {
                                    aiAttNum --;
                                }
                            }
                        }
                        else if(aiAttackTimer == 3)
                        {
                            if(aiAttLet < 10 && off.get(aiAttNum).get(aiAttLet+1).blueGuess(def.get(aiAttNum).get(aiAttLet+1)) == true)
                            {
                                hit = true;
                                aiAttLet = aiAttLet+1;
                            }
                            else
                            {
                                aiAttackTimer = 1;
                                while(def.get(aiAttNum).get(aiAttLet-1).get().equals("X"))
                                {
                                    aiAttLet --;
                                }
                            }
                        }
                        else if(aiAttackTimer == 2)
                        {
                            if(aiAttNum > 1 && off.get(aiAttNum-1).get(aiAttLet).blueGuess(def.get(aiAttNum-1).get(aiAttLet)) == true)
                            {
                                hit = true;
                                aiAttNum = aiAttNum-1;
                            }
                            else
                            {
                                aiAttackTimer = 4;
                                while(def.get(aiAttNum+1).get(aiAttLet).get().equals("X"))
                                {
                                    aiAttNum ++;
                                }
                            }
                        }
                        else if(aiAttackTimer == 1)
                        {
                            if(aiAttLet > 1 && off.get(aiAttNum).get(aiAttLet-1).blueGuess(def.get(aiAttNum).get(aiAttLet-1)) == true)
                            {
                                hit = true;
                                aiAttLet = aiAttLet-1;
                            }
                            else
                            {
                                aiAttackTimer = 3;
                                while(def.get(aiAttNum).get(aiAttLet+1).get().equals("X"))
                                {
                                    aiAttLet ++;
                                }
                            }
                        }
                        else
                            attacking = false;
                        sinking = true;
                        num = aiAttNum;
                        let = aiAttLet;
                    }
                    if(hit == true)
                    {
                        ACRed.hit(let, num);
                        BSRed.hit(let, num);
                        DRed.hit(let, num);
                        SMRed.hit(let, num);
                        PTRed.hit(let, num);
                    }
                    if(ACRed.sunk(let, num)==true)
                    {
                        sink = true;
                        shipsRemRed--;
                        attacking = false;
                        searching = false;
                    }
                    if(BSRed.sunk(let, num)==true)
                    {
                        sink = true;
                        shipsRemRed--;
                        attacking = false;
                        searching = false;
                    }
                    if(DRed.sunk(let, num)==true)
                    {
                        sink = true;
                        shipsRemRed--;
                        attacking = false;
                        searching = false;
                    }
                    if(SMRed.sunk(let, num)==true)
                    {
                        sink = true;
                        shipsRemRed--;
                        attacking = false;
                        searching = false;
                    }
                    if(PTRed.sunk(let, num)==true)
                    {
                        sink = true;
                        shipsRemRed--;
                        attacking = false;
                        searching = false;
                    }
                    if(shipsRemRed == 0)
                    {
                        gameOver = true;
                        clearBoard();
                        System.out.println("Congratulations! You have just been beaten by Jake's brainchild RODNEY!");
                        System.out.println("Thanks for playing!");
                    }
                    sinking = true;
                }
             }
        }
        else
        {
            System.out.println("The game is only 2 players :o");
        }
    }
    /**
     * prints the board
     */
    public static void printBoard(ArrayList<ArrayList<Space>> board) //defensive
    {
        System.out.println("         Radar        ");
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        System.out.printf("A %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(1).get(), board.get(2).get(1).get(), board.get(3).get(1).get(), board.get(4).get(1).get(), board.get(5).get(1).get(), board.get(6).get(1).get(), board.get(7).get(1).get(), board.get(8).get(1).get(), board.get(9).get(1).get(), board.get(10).get(1).get());
        System.out.printf("B %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(2).get(), board.get(2).get(2).get(), board.get(3).get(2).get(), board.get(4).get(2).get(), board.get(5).get(2).get(), board.get(6).get(2).get(), board.get(7).get(2).get(), board.get(8).get(2).get(), board.get(9).get(2).get(), board.get(10).get(2).get());
        System.out.printf("C %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(3).get(), board.get(2).get(3).get(), board.get(3).get(3).get(), board.get(4).get(3).get(), board.get(5).get(3).get(), board.get(6).get(3).get(), board.get(7).get(3).get(), board.get(8).get(3).get(), board.get(9).get(3).get(), board.get(10).get(3).get());
        System.out.printf("D %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(4).get(), board.get(2).get(4).get(), board.get(3).get(4).get(), board.get(4).get(4).get(), board.get(5).get(4).get(), board.get(6).get(4).get(), board.get(7).get(4).get(), board.get(8).get(4).get(), board.get(9).get(4).get(), board.get(10).get(4).get());
        System.out.printf("E %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(5).get(), board.get(2).get(5).get(), board.get(3).get(5).get(), board.get(4).get(5).get(), board.get(5).get(5).get(), board.get(6).get(5).get(), board.get(7).get(5).get(), board.get(8).get(5).get(), board.get(9).get(5).get(), board.get(10).get(5).get());
        System.out.printf("F %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(6).get(), board.get(2).get(6).get(), board.get(3).get(6).get(), board.get(4).get(6).get(), board.get(5).get(6).get(), board.get(6).get(6).get(), board.get(7).get(6).get(), board.get(8).get(6).get(), board.get(9).get(6).get(), board.get(10).get(6).get());
        System.out.printf("G %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(7).get(), board.get(2).get(7).get(), board.get(3).get(7).get(), board.get(4).get(7).get(), board.get(5).get(7).get(), board.get(6).get(7).get(), board.get(7).get(7).get(), board.get(8).get(7).get(), board.get(9).get(7).get(), board.get(10).get(7).get());
        System.out.printf("H %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(8).get(), board.get(2).get(8).get(), board.get(3).get(8).get(), board.get(4).get(8).get(), board.get(5).get(8).get(), board.get(6).get(8).get(), board.get(7).get(8).get(), board.get(8).get(8).get(), board.get(9).get(8).get(), board.get(10).get(8).get());
        System.out.printf("I %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(9).get(), board.get(2).get(9).get(), board.get(3).get(9).get(), board.get(4).get(9).get(), board.get(5).get(9).get(), board.get(6).get(9).get(), board.get(7).get(9).get(), board.get(8).get(9).get(), board.get(9).get(9).get(), board.get(10).get(9).get());
        System.out.printf("J %s %s %s %s %s %s %s %s %s %s\n\n", board.get(1).get(10).get(), board.get(2).get(10).get(), board.get(3).get(10).get(), board.get(4).get(10).get(), board.get(5).get(10).get(), board.get(6).get(10).get(), board.get(7).get(10).get(), board.get(8).get(10).get(), board.get(9).get(10).get(), board.get(10).get(10).get());
    }
    public static void printBoard(ArrayList<ArrayList<Map>> board, int ye) //offensive
    {
        System.out.println("      Your Ships      ");
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        System.out.printf("A %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(1).get(), board.get(2).get(1).get(), board.get(3).get(1).get(), board.get(4).get(1).get(), board.get(5).get(1).get(), board.get(6).get(1).get(), board.get(7).get(1).get(), board.get(8).get(1).get(), board.get(9).get(1).get(), board.get(10).get(1).get());
        System.out.printf("B %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(2).get(), board.get(2).get(2).get(), board.get(3).get(2).get(), board.get(4).get(2).get(), board.get(5).get(2).get(), board.get(6).get(2).get(), board.get(7).get(2).get(), board.get(8).get(2).get(), board.get(9).get(2).get(), board.get(10).get(2).get());
        System.out.printf("C %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(3).get(), board.get(2).get(3).get(), board.get(3).get(3).get(), board.get(4).get(3).get(), board.get(5).get(3).get(), board.get(6).get(3).get(), board.get(7).get(3).get(), board.get(8).get(3).get(), board.get(9).get(3).get(), board.get(10).get(3).get());
        System.out.printf("D %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(4).get(), board.get(2).get(4).get(), board.get(3).get(4).get(), board.get(4).get(4).get(), board.get(5).get(4).get(), board.get(6).get(4).get(), board.get(7).get(4).get(), board.get(8).get(4).get(), board.get(9).get(4).get(), board.get(10).get(4).get());
        System.out.printf("E %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(5).get(), board.get(2).get(5).get(), board.get(3).get(5).get(), board.get(4).get(5).get(), board.get(5).get(5).get(), board.get(6).get(5).get(), board.get(7).get(5).get(), board.get(8).get(5).get(), board.get(9).get(5).get(), board.get(10).get(5).get());
        System.out.printf("F %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(6).get(), board.get(2).get(6).get(), board.get(3).get(6).get(), board.get(4).get(6).get(), board.get(5).get(6).get(), board.get(6).get(6).get(), board.get(7).get(6).get(), board.get(8).get(6).get(), board.get(9).get(6).get(), board.get(10).get(6).get());
        System.out.printf("G %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(7).get(), board.get(2).get(7).get(), board.get(3).get(7).get(), board.get(4).get(7).get(), board.get(5).get(7).get(), board.get(6).get(7).get(), board.get(7).get(7).get(), board.get(8).get(7).get(), board.get(9).get(7).get(), board.get(10).get(7).get());
        System.out.printf("H %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(8).get(), board.get(2).get(8).get(), board.get(3).get(8).get(), board.get(4).get(8).get(), board.get(5).get(8).get(), board.get(6).get(8).get(), board.get(7).get(8).get(), board.get(8).get(8).get(), board.get(9).get(8).get(), board.get(10).get(8).get());
        System.out.printf("I %s %s %s %s %s %s %s %s %s %s\n", board.get(1).get(9).get(), board.get(2).get(9).get(), board.get(3).get(9).get(), board.get(4).get(9).get(), board.get(5).get(9).get(), board.get(6).get(9).get(), board.get(7).get(9).get(), board.get(8).get(9).get(), board.get(9).get(9).get(), board.get(10).get(9).get());
        System.out.printf("J %s %s %s %s %s %s %s %s %s %s\n\n", board.get(1).get(10).get(), board.get(2).get(10).get(), board.get(3).get(10).get(), board.get(4).get(10).get(), board.get(5).get(10).get(), board.get(6).get(10).get(), board.get(7).get(10).get(), board.get(8).get(10).get(), board.get(9).get(10).get(), board.get(10).get(10).get());
    }
    public static void clearBoard()
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    public static void redTurn(ArrayList<ArrayList<Space>> board, ArrayList<ArrayList<Map>> map)
    {
        for(int i = 0; i < board.size(); i++)
        {
            for(int j = 0; j < board.get(i).size(); j++)
            {
                board.get(i).get(j).redTurn();
            }
        }
        for(int i = 0; i < map.size(); i++)
        {
            for(int j = 0; j < map.get(i).size(); j++)
            {
                map.get(i).get(j).redTurn();
            }
        }
    }
    public static void blueTurn(ArrayList<ArrayList<Space>> board, ArrayList<ArrayList<Map>> map)
    {
        for(int i = 0; i < board.size(); i++)
        {
            for(int j = 0; j < board.get(i).size(); j++)
            {
                board.get(i).get(j).blueTurn();
            }
        }
        for(int i = 0; i < map.size(); i++)
        {
            for(int j = 0; j < map.get(i).size(); j++)
            {
                map.get(i).get(j).blueTurn();
            }
        }
    }
    public static boolean check(String s, String[] alpha)
    {
        boolean p = false;
        for(int i = 0; i < 10; i++)
        {
            if(s.substring(0,1).equalsIgnoreCase(alpha[i]))
                p = true;
        }
        if(isInt(s.substring(1))==false)
            p = false;
        return p;
    }
    public static boolean isInt(String s)
    {
        try
        {
            int i = Integer.parseInt(s);
            return true;
        }
        catch(NumberFormatException er)
        {
            return false;
        }
    }
    /**
     * This method will be used to optimize input later
     */
    static String[] alphabet()
    {
        String[] alphabet = new String[10];
        for(int i = 97;i<=106;i++)
        {
            String character = Character.toString((char)i);
            alphabet[i-97] = character;            
        }
        return alphabet;
    }
}
