package TicTacToeComp;

/* Plays Tic Tac Toe, with a computer player
 * 
 * Version: 2.1.0
 * Originally created by Supuhstar on 2009/12/23
 * Latest version finished on 2010/01/16
 * 
 * Copyright Blue Husky Studios, � 2009 under Creative Commons 3.0 (CC-BY-SA)
 */

import java.util.*;

public class TicTacToeComp
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
    boolean move, owner, winner = false, game = true, blocked = false;
    String mark1, mark2, board;
    int where1 = 0, where2 = 0, size, count, turns, p1wins = 0, p2wins = 0;
    move = (rand.nextInt(10) > 5);
    
    System.out.println ("\n" + 
                        " T | I | C \n" +
                        "---+---+---\n" +
                        " T | A | C \n" +
                        "---+---+---\n" +
                        " T | O | E \n" +
                        "\nWelcome to Blue Husky's single-player Tic-Tac-Toe. Please follow the instructions and enjoy your game.\n");
    while (true)
    {
      System.out.println ("What is your mark, Player 1?");
      mark1 = scan.next();
      
      if (mark1.length() == 1)
        break;
      else
        System.err.println ("I'm sorry, but that's just too long. Could you try again?");
    }
    
    while (true)
    {
      System.out.println ("What do you want my mark to be?");
      mark2 = scan.next();
      
      if (mark2.length() == 1)
        break;
      else
        System.err.println ("I'm sorry, but that's just too long. Could you try again?");
    }
    
    while (true)
    {
      System.out.println ("How wide is the square?");
      size = scan.nextInt();
      while (size < 1)
      {
        System.err.println ("That's way too small. Try something bigger.");
        size = scan.nextInt();
      }
      
      String[][] xo = new String[size][size];
      
      for (int i=0; i < size; i++)
      {
        for (int j=0; j < size; j++)
          xo[i][j] = ("" + ((i * size) + j));
      }
      
      
      for (turns=0; turns <= (size * size); turns++)
      {
        if (move)
          owner = true;
        else
          owner = false;
        
        board = "";
        if (move)
        {
          for (int i=0; i != size; i++)
          {
            for (int j=0; j < size; j++)
            {
              if (xo[i][j].length() == 1)
                board += (" " + xo[i][j] + " ");
              else if (xo[i][j].length() == 2)
                board += (xo[i][j] + " ");
              else
                board += (xo[i][j]);
              if (j < (size - 1))
                board += ("|");
            }
            board +=("\n");
            
            if (i < (size - 1))
            {
              for (int j=0; j < size; j++)
              {
                board += ("---");
                if (j < size - 1)
                  board += ("+");
              }
              board +=("\n");
            }
          }
        }
        System.out.print(board);
        
        //BEGIN PLAYER TURN
        if (move)
        {
          while (true)
          {
            System.out.print("Where do you want to place your \"" + mark1 + "\", Player 1?");
            where2 = scan.nextInt();
            where1 = 0;
            while (where2 > (size - 1))
            {
              where2 = (where2 - (size));
              where1++;
            }
            
            if (where1 >= (size))
              System.err.println("I'm sorry, but that's just not a place where1 you can put your " + mark1 + ". Try somewhere1 else. :3");
            else if (xo[where1][where2] == mark2 || xo[where1][where2] == mark1)
              System.err.println("I'm sorry, but there is already a mark there. Try somewhere1 else. :3");
            else
            {
              xo[where1][where2] = mark1;
              break;
            }
          }
        }
        //END PLAYER TURN
        
        //BEGIN COMPUTER TURN
        if (!move)
        {
          /* WIN:
           * OOO
           */
          for(int x = 0; x < size; x++) 
          {
            if (blocked)
              break;
            
            count = 0;
            
            for(int y = 1; y < size; y++)
            {
              if (xo[x][0] != mark2)
                break;
              if (xo[x][size - 1] != mark2)
                break;
              
              if (xo[x][y] != mark2 && xo[x][y] != mark2 && xo[x][y] != mark1)
              {
                xo[x][y] = mark2;
                blocked = true;
                break;
              }
            }
          }
          
          /* WIN:
           * OOO
           */
          for(int x = 0; x < size; x++) 
          {
            if (blocked)
              break;
            
            count = 0;
            
            for(int y = 0; y < size; y++)
            {
              if (xo[x][y].equals(mark2))
                count++;
              else
                break;
              
              if (count == (size - 1) && xo[x][size - 1] != mark2 && xo[x][size - 1] != mark1)
              {
                xo[x][size - 1] = mark2;
                blocked = true;
                break;
              }
            }
          }
          
          /* WIN:
           * OOO
           */
          for(int x = 0; x < size; x++) 
          {
            if (blocked)
              break;
            
            count = 0;
            
            for(int y = 1; y < size; y++)
            {
              if (xo[x][y].equals(mark2))
                count++;
              else
                break;
              
              if (count == (size - 1) && xo[x][0] != mark2 && xo[x][0] != mark1)
              {
                xo[x][0] = mark2;
                blocked = true;
                break;
              }
            }
          }
          
          /* WIN:
           * O
           * O
           * O
           */
          for(int x = 0; x < size; x++) 
          {
            if (blocked)
              break;
            
            count = 0;
            
            for(int y = 1; y < size; y++)
            {
              if (xo[(y - 1)][x].equals(mark2))
                count++;
              
              if (xo[0][x] != mark2)
                break;
              
              if (count == (size - 1) && xo[y][x] != mark2 && xo[y][x] != mark1)
              {
                xo[y][x] = mark2;
                blocked = true;
                break;
              }
            }
          }
          
          /* WIN:
           * O
           * O
           * O
           */
          for(int x = 0; x < size; x++) 
          {
            if (blocked)
              break;
            
            count = 0;
            
            for(int y = (size - 1); y >= 0; y--)
            {
              if (xo[y][x].equals(mark2))
                count++;
              
              if (xo[y][x] != mark2)
                break;
              
              if (count == (size - 1) && xo[y - 1][x] != mark2 && xo[y - 1][x] != mark1)
              {
                xo[y - 1][x] = mark2;
                blocked = true;
                break;
              }
            }
          }
          
          /* WIN:
           * O
           * O
           * O
           */
          for(int x = 0; x < size; x++) 
          {
            if (blocked)
              break;
            
            count = 0;
            
            for(int y = 1; y < size; y++)
            {
              if (xo[0][x] != mark2)
                break;
              if (xo[size - 1][x] != mark2)
                break;
              if (xo[y][x] != mark2 && xo[y][x] != mark2 && xo[y][x] != mark1)
              {
                xo[y][x] = mark2;
                blocked = true;
                break;
              }
            }
          }
          
          count = 0;
          
          /* WIN:
           * O
           *  O
           *   O
           */
          for(int x = 0; x < size; x++)
          {
            if (blocked)
              break;
            
            if (xo[x][x] != mark2)
              count++;
            if (xo[x][x] != mark1)
              break;
            
            if (x == (size - 2) && xo[size - 1][size - 1] != mark2 && xo[size - 1][size - 1] != mark1)
            {
              xo[size - 1][size - 1] = mark2;
              blocked = true;
              break;
            }
          }
          
          
          /* WIN:
           * O
           *  O
           *   O
           */
          for(int x = 1; x < size; x++)
          {
            if (blocked)
              break;
            
            if (xo[x][x] != mark2)
              break;
            
            if (x == (size - 1) && xo[0][0] != mark2 && xo[0][0] != mark1)
            {
              xo[0][0] = mark2;
              blocked = true;
              break;
            }
          }
          
          count = 0;
          
          /* WIN:
           *   O
           *  O
           * O
           */
          for(int x = 0, y = (size - 1); x >= 0; x++)
          {
            if (blocked)
              break;
            
            if (xo[y][x].equals(mark2))
              count++;
            if (xo[y][x] != mark2)
              break;
            
            if (count == (size - 1) && xo[y - 1][x + 1] != mark2 && xo[y - 1][x + 1] != mark1)
            {
              xo[y - 1][x + 1] = mark2;
              blocked = true;
              break;
            }
            y--;
          }
          
          /* WIN:
           *   O
           *  O
           * O
           */
          for(int x = (size - 1), y = 0; x >= 0; x--)
          {
            if (blocked)
              break;
            
            if (xo[y][x].equals(mark2))
              count++;
            if (xo[y][x] != mark2)
              break;
            
            if (count == (size - 1) && xo[y + 1][x - 1] != mark2 && xo[y + 1][x + - 1] != mark1)
            {
              xo[y + 1][x - 1] = mark2;
              blocked = true;
              break;
            }
            y++;
          }
          
          count = 0;
          
          /* BLOCK:
           * XOX
           */
          for(int x = 0; x < size; x++) 
          {
            if (blocked)
              break;
            
            count = 0;
            
            for(int y = 1; y < size; y++)
            {
              if (xo[x][0] != mark1)
                break;
              if (xo[x][size - 1] != mark1)
                break;
              
              if (xo[x][y] != mark1 && xo[x][y] != mark1 && xo[x][y] != mark2)
              {
                xo[x][y] = mark2;
                blocked = true;
                break;
              }
            }
          }
          
          /* BLOCK:
           * XXO
           */
          for(int x = 0; x < size; x++) 
          {
            if (blocked)
              break;
            
            count = 0;
            
            for(int y = 0; y < size; y++)
            {
              if (xo[x][y].equals(mark1))
                count++;
              else
                break;
              
              if (count == (size - 1) && xo[x][size - 1] != mark1 && xo[x][size - 1] != mark2)
              {
                xo[x][size - 1] = mark2;
                blocked = true;
                break;
              }
            }
          }
          
          /* BLOCK:
           * OXX
           */
          for(int x = 0; x < size; x++) 
          {
            if (blocked)
              break;
            
            count = 0;
            
            for(int y = 1; y < size; y++)
            {
              if (xo[x][y].equals(mark1))
                count++;
              else
                break;
              
              if (count == (size - 1) && xo[x][0] != mark1 && xo[x][0] != mark2)
              {
                xo[x][0] = mark2;
                blocked = true;
                break;
              }
            }
          }
          
          /* BLOCK:
           * X
           * X
           * O
           */
          for(int x = 0; x < size; x++) 
          {
            if (blocked)
              break;
            
            count = 0;
            
            for(int y = 1; y < size; y++)
            {
              if (xo[(y - 1)][x].equals(mark1))
                count++;
              
              if (xo[0][x] != mark1)
                break;
              
              if (count == (size - 1) && xo[y][x] != mark1 && xo[y][x] != mark2)
              {
                xo[y][x] = mark2;
                blocked = true;
                break;
              }
            }
          }
          
          /* BLOCK:
           * O
           * X
           * X
           */
          for(int x = 0; x < size; x++) 
          {
            if (blocked)
              break;
            
            count = 0;
            
            for(int y = (size - 1); y >= 0; y--)
            {
              if (xo[y][x].equals(mark1))
                count++;
              
              if (xo[y][x] != mark1)
                break;
              
              if (count == (size - 1) && xo[y - 1][x] != mark1 && xo[y - 1][x] != mark2)
              {
                xo[y - 1][x] = mark2;
                blocked = true;
                break;
              }
            }
          }
          
          /* BLOCK:
           * X
           * O
           * X
           */
          for(int x = 0; x < size; x++) 
          {
            if (blocked)
              break;
            
            count = 0;
            
            for(int y = 1; y < size; y++)
            {
              if (xo[0][x] != mark1)
                break;
              if (xo[size - 1][x] != mark1)
                break;
              if (xo[y][x] != mark1 && xo[y][x] != mark1 && xo[y][x] != mark2)
              {
                xo[y][x] = mark2;
                blocked = true;
                break;
              }
            }
          }
          
          count = 0;
          
          /* BLOCK:
           * X
           *  X
           *   O
           */
          for(int x = 0; x < size; x++)
          {
            if (blocked)
              break;
            
            if (xo[x][x] != mark1)
              count++;
            if (xo[x][x] != mark2)
              break;
            
            if (x == (size - 2) && xo[size - 1][size - 1] != mark1 && xo[size - 1][size - 1] != mark2)
            {
              xo[size - 1][size - 1] = mark2;
              blocked = true;
              break;
            }
          }
          
          
          /* BLOCK:
           * O
           *  X
           *   X
           */
          for(int x = 1; x < size; x++)
          {
            if (blocked)
              break;
            
            if (xo[x][x] != mark1)
              break;
            
            if (x == (size - 1) && xo[0][0] != mark1 && xo[0][0] != mark2)
            {
              xo[0][0] = mark2;
              blocked = true;
              break;
            }
          }
          
          count = 0;
          
          /* BLOCK:
           *   O
           *  X
           * X
           */
          for(int x = 0, y = (size - 1); x >= 0; x++)
          {
            if (blocked)
              break;
            
            if (xo[y][x].equals(mark1))
              count++;
            if (xo[y][x] != mark1)
              break;
            
            if (count == (size - 1) && xo[y - 1][x + 1] != mark1 && xo[y - 1][x + 1] != mark2)
            {
              xo[y - 1][x + 1] = mark2;
              blocked = true;
              break;
            }
            y--;
          }
          
          /* BLOCK:
           *   X
           *  X
           * O
           */
          for(int x = (size - 1), y = 0; x >= 0; x--)
          {
            if (blocked)
              break;
            
            if (xo[y][x].equals(mark1))
              count++;
            if (xo[y][x] != mark1)
              break;
            
            if (count == (size - 1) && xo[y + 1][x - 1] != mark1 && xo[y + 1][x + - 1] != mark2)
            {
              xo[y + 1][x - 1] = mark2;
              blocked = true;
              break;
            }
            y++;
          }
          
          count = 0;
          
          //IF NO BLOCK IS POSSIBLE
          if (!blocked)
          {
            for (int i=0; i < (size * size); i++)
            {
              int rand1 = rand.nextInt(size);
              int rand2 = rand.nextInt(size);
              
              if (xo[size / 2][size / 2] != mark1 && xo[size / 2][size / 2] != mark2)
              {
                xo[size / 2][size / 2] = mark2;
                break;
              }
              
              else if (xo[rand1][rand2] != mark1 && xo[rand1][rand2] != mark2)
              {
                xo[rand1][rand2] = mark2;
                break;
              }
            }
          }
          blocked = false;
        }
        //END COMPUTER TURN
        
        //BEGIN WIN CHECKING
        for(int x = 0; x < size; x++) //Check -
        {
          count = 0;
          
          for(int y = 0; y < size; y++)
          {
            if (xo[x][y].equals(mark1))
              count++;
            
            if (count == size)
            {
              move = true;
              winner = true;
              game = false;
              break;
            }
          }
          if (winner)
            break;
        }
        
        for(int x = 0; x < size; x++) //Check -
        {
          count = 0;
          
          for(int y = 0; y < size; y++)
          {
            if (xo[x][y].equals(mark2))
              count++;
            
            if (count == size)
            {
              move = false;
              winner = true;
              game = false;
              break;
            }
          }
          if (winner)
            break;
        }
        
        for(int x = 0; x < size; x++) //Check |
        {
          count = 0;
          
          for(int y = 0; y < size; y++)
          {
            if (xo[y][x].equals(mark1))
              count++;
            
            if (count == size)
            {
              move = true;
              winner = true;
              game = false;
              break;
            }
          }
          if (winner)
            break;
        }
        
        
        for(int x = 0; x < size; x++) //Check |
        {
          count = 0;
          
          for(int y = 0; y < size; y++)
          {
            if (xo[y][x].equals(mark2))
              count++;
            
            if (count == size)
            {
              move = false;
              winner = true;
              game = false;
              break;
            }
          }
          if (winner)
            break;
        }
        
        count = 0;
        
        for(int x = 0; x < size; x++) // Check \
        {
          if (xo[x][x].equals(mark1))
            count++;
          
          if (count == size)
          {
            move = true;
            winner = true;
            game = false;
            break;
          }
          
          if (winner)
            break;
        }
        
        count = 0;
        
        for(int x = 0; x < size; x++) // Check \
        {
          if (xo[x][x].equals(mark2))
            count++;
          
          if (count == size)
          {
            move = false;
            winner = true;
            game = false;
            break;
          }
          
          if (winner)
            break;
        }
        
        count = 0;
        
        for(int x = (size - 1), y = 0; x >= 0; x--) // Check /
        {
          if (xo[y][x].equals(mark1))
          {
            count++;
          }
          
          if (count == size)
          {
            move = true;
            winner = true;
            game = false;
            break;
          }
          if (winner)
            break;
          y++;
        }
        
        count = 0;
        
        for(int x = (size - 1), y = 0; x >= 0; x--) // Check /
        {
          if (xo[y][x].equals(mark2))
          {
            count++;
          }
          
          if (count == size)
          {
            move = false;
            winner = true;
            game = false;
            break;
          }
          if (winner)
            break;
          y++;
        }
        //END WIN CHECKING
        
        move = !move;
        
        //BEGIN FINAL STATEMENT
        if (turns == ((size * size) - 1) || !game)
        {
          for (int i=0; i < size; i++)
          {
            for (int j=0; j < (size); j++)
            {
              if (xo[i][j].length() == 1)
                System.out.print (" " + xo[i][j] + " ");
              else if (xo[i][j].length() == 2)
                System.out.print (xo[i][j] + " ");
              else if (xo[i][j].length() == 3)
                System.out.print (xo[i][j]);
              if (j < (size - 1))
                System.out.print ("|");
              
            }
            System.out.print("\n");
            
            if (i < (size - 1))
            {
              for (int j=0; j < size; j++)
              {
                System.out.print ("---");
                if (j < size - 1)
                  System.out.print ("+");
              }
              System.out.print("\n");
            }
          }
          System.out.print("\n");
          
          
          if (turns == ((size * size) - 1) && !winner)
            System.out.println("Sorry... no one wins. :/");
          else if (!move)
          {
            System.out.println(mark1 + "'s win!");
            p1wins++;
          }
          else
          {
            System.out.println(mark2 + "'s win!");
            p2wins++;
          }
          
          System.out.println("\n SCORE: \n" + 
                             "P1 | P2\n" + 
                             "---+---");
          if (p1wins < 9)
            System.out.print(" " + p1wins + " ");
          else if (p1wins < 99)
            System.out.print(" " + p1wins);
          else
            System.out.print(p1wins);
          System.out.print("|");
          if (p2wins < 9)
            System.out.print(" " + p2wins + " ");
          else if (p2wins < 99)
            System.out.print(" " + p2wins);
          else
            System.out.print(p2wins);
          System.out.println("\n   |\n");
          break;
        }
        //END FINAL STATEMENT
      }
      System.out.println("Would you like to play again?");
      String yn = scan.next();
      if (yn.charAt(0) == 'y' || yn.charAt(0) == 'Y')
      {
        winner = false;
        game = true;
        where1 = 0;
      }
      else
        break;
    }
    System.out.println("That was fun! Can't wait till next time! :3 ");
  }
}
