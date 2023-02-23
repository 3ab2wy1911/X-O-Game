import javax.management.monitor.GaugeMonitor;
import java.util.Scanner;
abstract class Board {
    protected
    int n;
    char grid[][] = new char [n][n];

    public


    abstract boolean update_board(int x,char symbol);

    abstract boolean is_winner(char symbol);
    abstract boolean is_draw(char symbol1,char symbol2) ;
    abstract void display_board();
}
abstract class Player
{
    protected
    String name;
    char symbol;
    public
   abstract char get_symbol();
    abstract String get_name();

}
abstract class Game {
    private
    XO_Board board;
    int turn;

    public

    abstract void play();
}

class XO_Player extends Player
{
    protected
        String name;
        char symbol;
    public
    XO_Player(String n, char s)    // Assigning the name and the Symbol of the player.
    {
        name = n;
        symbol = s;
    }
    public
    char get_symbol()
    {
        return symbol;
    }   //Returning the symbol.
    String get_name()
    {
        return name;
    }   //Returning the name.

}
class XO_Board extends Board
{
    protected
    char [][] grid = new char[3][3];
    public
    XO_Board()
    {
        n =3;
        char x='1';
        for(int i=0;i<3;i++)
        {
            for(int j = 0 ;j<3;j++)
            {
                grid [i][j]=x;  //Assigning the grid positions from 1 to 9
                x++;
            }
        }
    }
    public
    boolean in_range (int input)    // Function to prevent User entering inputs out of range.
    {
        if (input<10 & input>0 )
            return true;
        System.out.println("Out of Range!!!");
        return false;
    }
    public
    boolean update_board(int input, char symbol)   //Function that updates the grid if the input is correct otherwise it will retake the input again.
    {
        for(int i =0 ;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                if(grid[i][j]==(char)(input+'0'))
                {
                    grid [i][j] = symbol;
                    return true;
                }
            }
        }
        System.out.println("Wrong move ,please enter again:");
        return false;
    }
    public
    boolean is_winner (char symbol)    // Check if there is a winner
    {
        for (int i=0;i < 3;i++) {
            int c=0;
            for (int j = 0; j < 3; j++)
            {
                if (grid [i][j] == symbol)
                {
                    c++;
                }
                if(c==3)
                {
                    return true;
                }
            }
        }
        for (int i=0;i < 3;i++) {
            int c=0;
            for (int j = 0; j < 3; j++)
            {
                if (grid [j][i] == symbol)
                {
                    c++;
                }
                if(c==3)
                {
                    return true;
                }
            }
        }
        int c=0;
        for (int i=0;i < 3;i++) {
            if (grid [i][i] == symbol)
            {
                c++;
            }
            if(c==3)
            {
                return true;
            }
        }
        c=0;
        for (int i=0;i < 3;i++) {
            if (grid [i][2-i] == symbol)
            {
                c++;
            }
            if(c==3)
            {
                return true;
            }
        }
        return false;
    }
    public
    boolean is_draw(char symbol1, char symbol2) //Check if it's draw.
    {
        for(int i= 0 ;i<3;i++)
        {
            for (int j =0 ;j<3; j++)
            {
                if (grid[i][j] !='X' )
                    if(grid [i][j] != 'O')
                        return false;
            }
        }
        return (!is_winner(symbol1) & !is_winner(symbol2));
    }
    public
    void display_board()  // Printing the board.
    {
        for (int i=0;i<3;i++)
        {
            for (int j =0 ;j<3;j++)
            {
                System.out.print(" | "+grid[i][j]);
            }

            System.out.println(" |");
        }
    }
}
class XO_Game extends Game
{
    private
    XO_Board board = new XO_Board();
    int turn =0 ;
    public
    XO_Game()
    {};
public
    void play()
    {
        // Taking players names and assigning their symbols.
        System.out.print("Enter first player name:");
        String name;
        Scanner input1 = new Scanner(System.in);
        name = input1.nextLine();
        XO_Player p1 = new XO_Player(name,'X');
        System.out.print("Enter Second player name:");
        Scanner input2 = new Scanner(System.in);
        name = input2.nextLine();
        XO_Player p2 = new XO_Player(name,'O');
        board.display_board();
        // Condition to check is the game still in progress or not.
        while (!board.is_draw(p1.get_symbol(),p2.get_symbol()) & !(board.is_winner(p1.get_symbol()) || board.is_winner(p2.get_symbol())))
        {
            int input;
            if (turn ==0) {
                System.out.println( p1.get_name() + " turn!");
                System.out.print("Please enter the position you want to put your symbol on:\n");
                Scanner input3 = new Scanner(System.in);
                input = input3.nextInt();
                while (!board.update_board(input, p1.get_symbol()) || !board.in_range(input))
                {
                    System.out.print("Please enter the  position you want to put your symbol on:\n");
                    Scanner input5 = new Scanner(System.in);
                    input = input5.nextInt();
                }
                board.display_board();
                turn = 1;
            }
            else if (turn == 1)
            {
                System.out.println( p2.get_name() + " turn!");
                System.out.print("Please enter the position you want to put your symbol on:\n");
                Scanner input3 = new Scanner(System.in);
                input = input3.nextInt();
                while (!board.update_board(input, p2.get_symbol()) || !board.in_range(input))
                {
                    System.out.print("Please enter the position you want to put your symbol on:\n");
                    Scanner input5 = new Scanner(System.in);
                    input = input5.nextInt();
                }
                board.display_board();
                turn = 0;
            }
        }
        // Checking the result of the game.
        if (board.is_winner(p1.get_symbol()))
            System.out.println(p1.get_name() + " win !!!");
        else if (board.is_winner(p2.get_symbol()))
            System.out.println(p2.get_name() + " win !!!");
        else
            System.out.println("Tie");
    }
}


public class main{
    public static void main(String[] args) {
        XO_Game game = new XO_Game();
        game.play();
    }
}
