#include <bits/stdc++.h>
using namespace std;
class player
{
protected:
    string name;
    char symbol;
public:
    player(string n, char s)
    {
        name = n;
        symbol = s;
    }

    void get_move(int & x, int & y)
    {

    }

    char get_symbol()
    {
        return symbol;
    }
    string get_name()
    {
        return name;
    }

};

class Board
{
protected:
    char grid[3][3];
public:
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
    bool in_range (int x, int y)
    {
        if ((x<3 and x>=0 ) and (y<3 and y>=0))
            return true;
        cout<<"Out of Range!!!";
        return false;
    }
   bool update_board (int x, int y, char symbol)
   {
        if (grid[x][y] == ' ' and in_range(x,y))
        {
            grid [x][y] = symbol;
            return true;
        }
        cout<<"Wrong move ,please enter again:";
       return false;
   }
   bool is_winner (char symbol)
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
   bool is_Draw(char symbol1, char symbol2)
   {
        for(int i= 0 ;i<3;i++)
        {
            for (int j =0 ;j<3; j++)
            {
                if (grid[i][j] ==' ')
                    return false;
            }
        }
        return (!is_winner(symbol1) and !is_winner(symbol2));
   }
   void print_board()
   {
        for (int i=0;i<3;i++)
        {
            for (int j =0 ;j<3;j++)
            {
                cout<<" | "<<grid[i][j];
            }

            cout<<" |"<<endl;
           // cout<<" _____________"<<endl;
        }
   }
};

class game
{
private:
    Board board;
    int turn =0 ;
public:
    game()
    {
    };

    void play()
    {
        cout<<"Enter first player name:";
        string name;
        cin>>name;
        player p1(name,'X');
        cout<<"Enter Second player name:";
        cin>>name;
        player p2 (name,'O');
        while (!board.is_Draw(p1.get_symbol(),p2.get_symbol()) and !(board.is_winner(p1.get_symbol()) or board.is_winner(p2.get_symbol())))
        {
            cout<<"Please enter the coordinates of the position you want to put your symbol on:\n";
            board.print_board();
            int x,y;
            if (turn ==0) {
                cout << p1.get_name() << " turn!";
                cin >> x >> y;
                while (!board.update_board(x-1, y-1, p1.get_symbol()))
                {
                    cin>> x >>y;
                }
                board.print_board();
                turn = 1;
            }
            else if (turn == 1)
            {
                cout<<p2.get_name()<<" turn!";
                cin>>x>>y;
                while (!board.update_board(x-1, y-1, p2.get_symbol()))
                {
                    cin>> x >>y;
                }
                board.print_board();
                turn = 0;
            }
        }
        if (board.is_winner(p1.get_symbol()))
            cout<<p1.get_name()<<" win !!!";
        else if (board.is_winner(p2.get_symbol()))
            cout<<p2.get_name()<<" win !!!";
        else
            cout<<"Tie!";
    }
};

int main()
{
   game Game;
   Game.play();

}
