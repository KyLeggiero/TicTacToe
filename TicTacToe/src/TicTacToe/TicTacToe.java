package TicTacToe;

/* Plays Tic Tac Toe
 * 
 * Version: 1.3.0
 * Originally created by Supuhstar on 2009/12/23
 * Latest version finished on 2010/02/06
 * 
 * Copyright Blue Husky Studios, � 2009-2010 under Creative Commons 3.0 (CC-BY-SA)
 */

import java.util.*;

public class TicTacToe
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
    boolean move = true, owner, winner = false, game = true;
    String mark1, mark2, board;
    int where1 = 0, where2 = 0, size, count, turns, p1wins = 0, p2wins = 0;
    System.out.println ("\n" + 
                        " T | I | C \n" +
                        "---+---+---\n" +
                        " T | A | C \n" +
                        "---+---+---\n" +
                        " T | O | E \n" +
                        "\nWelcome to Blue Husky's Tic-Tac-Toe. Please follow the instructions and enjoy your game.\n");
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
      System.out.println ("What is your mark, Player 2?");
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
            
            if (where1 >= (size * size))
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
        else
        {
          while (true)
          {
            System.out.print("Where do you want to place your \"" + mark2 + "\", Player 2?");
            where2 = scan.nextInt();
            where1 = 0;
            while (where2 > (size - 1))
            {
              where2 = (where2 - (size));
              where1++;
            }
            
            if (where1 >= (size * size))
              System.err.println("I'm sorry, but that's just not a place where1 you can put your " + mark2 + ". Try somewhere1 else. :3");
            else if (xo[where1][where2] == mark1 || xo[where1][where2] == mark2)
              System.err.println("I'm sorry, but there is already a mark there. Try somewhere1 else. :3");
            else
            {
              xo[where1][where2] = mark2;
              break;
            }
          }
        }
        //END PLAYER TURN
        
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
        move = true;
        where1 = 0;
      }
      else
        break;
    }
    System.out.println("That was fun! Can't wait till next time! :3 ");
  }
}
