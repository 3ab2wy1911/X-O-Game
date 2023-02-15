import javax.management.monitor.GaugeMonitor;
import java.util.Scanner;

class player
{
    protected
        String name;
        char symbol;
    public
    player(String n, char s)
    {
        name = n;
        symbol = s;
    }
    char get_symbol()
    {
        return symbol;
    }
    String get_name()
    {
        return name;
    }

}
class Board
{
    protected
    char [][] grid = new char[3][3];
    public
    Board()
    {
        for(int i=0;i<3;i++)
        {
            for(int j = 0 ;j<3;j++)
            {
                grid [i][j]=' ';
            }
        }
    }
    Boolean in_range (int x, int y)
    {
        if ((x<3 & x>=0 ) & (y<3 & y>=0))
            return true;
        System.out.println("Out of Range!!!");
        return false;
    }
    Boolean update_board (int x, int y, char symbol)
    {
        if (grid[x][y] == ' ' & in_range(x,y))
        {
            grid [x][y] = symbol;
            return true;
        }
        System.out.println("Wrong move ,please enter again:");
        return false;
    }
    Boolean is_winner (char symbol)
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
    Boolean is_Draw(char symbol1, char symbol2)
    {
        for(int i= 0 ;i<3;i++)
        {
            for (int j =0 ;j<3; j++)
            {
                if (grid[i][j] ==' ')
                    return false;
            }
        }
        return (!is_winner(symbol1) & !is_winner(symbol2));
    }
    void print_board()
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
class game
{
    private
    Board board = new Board();
    int turn =0 ;
    public
    game()
    {};

    void play()
    {
        System.out.print("Enter first player name:");
        String name;
        Scanner input = new Scanner(System.in);
        name = input.nextLine();
        player p1 = new player(name,'X');
        System.out.print("Enter Second player name:");
        Scanner input2 = new Scanner(System.in);
        name = input2.nextLine();
        player p2 = new player(name,'O');
        board.print_board();
        while (!board.is_Draw(p1.get_symbol(),p2.get_symbol()) & !(board.is_winner(p1.get_symbol()) || board.is_winner(p2.get_symbol())))
        {
            int x,y;
            if (turn ==0) {
                System.out.println( p1.get_name() + " turn!");
                System.out.print("Please enter the x-coordinate of the position you want to put your symbol on:\n");
                Scanner input3 = new Scanner(System.in);
                x = input3.nextInt();
                System.out.print("Please enter the y-coordinates of the position you want to put your symbol on:\n");
                Scanner input4 = new Scanner(System.in);
                y = input4.nextInt();
                while (!board.update_board(x-1, y-1, p1.get_symbol()))
                {
                    System.out.print("Please enter the x-coordinate of the position you want to put your symbol on:\n");
                    Scanner input5 = new Scanner(System.in);
                    x = input5.nextInt();
                    System.out.print("Please enter the y-coordinate of the position you want to put your symbol on:\n");
                    Scanner input6 = new Scanner(System.in);
                    y = input6.nextInt();
                }
                board.print_board();
                turn = 1;
            }
            else if (turn == 1)
            {
                System.out.println( p2.get_name() + " turn!");
                System.out.print("Please enter the x-coordinate of the position you want to put your symbol on:\n");
                Scanner input3 = new Scanner(System.in);
                x = input3.nextInt();
                System.out.print("Please enter the y-coordinates of the position you want to put your symbol on:\n");
                Scanner input4 = new Scanner(System.in);
                y = input4.nextInt();
                while (!board.update_board(x-1, y-1, p2.get_symbol()))
                {
                    System.out.print("Please enter the x-coordinate of the position you want to put your symbol on:\n");
                    Scanner input5 = new Scanner(System.in);
                    x = input5.nextInt();
                    System.out.print("Please enter the y-coordinate of the position you want to put your symbol on:\n");
                    Scanner input6 = new Scanner(System.in);
                    y = input6.nextInt();
                }
                board.print_board();
                turn = 0;
            }
        }
        if (board.is_winner(p1.get_symbol()))
            System.out.println(p1.get_name() + " win !!!");
        else if (board.is_winner(p2.get_symbol()))
            System.out.println(p2.get_name() + " win !!!");
        else
            System.out.println("Tie");
    }
}


public class Main {
    public static void main(String[] args) {
        game Game = new game();
        Game.play();
    }
}
