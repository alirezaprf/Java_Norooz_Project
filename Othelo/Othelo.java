import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Othelo {

    private JFrame frame;
    private int[][] gameBoard;
    private boolean playerTurn = true;
    // if true black moves else white does
    private ActionListener ButtonsAction;
    private ArrayList<JButton> buttons;

    /**
     * gameBorad Containg Game's Data as int 0 which is the initial State of every
     * cell = empty 1 means black 2 means white
     */

    /**
     * 
     * @param i    y
     * @param j    x
     * @param type type of board
     */
    public void setGameBoard(int i, int j, int type) {
        gameBoard[i][j] = type;
        if (type == 2)
            buttons.get(i * 8 + j).setBackground(Color.WHITE);
        else if (type == 1) {
            buttons.get(i * 8 + j).setBackground(Color.BLACK);
        }

    }

    public Othelo() {
        gameBoard = new int[8][8];

        ButtonsAction = Set_Button_Actions();
        buttons = new ArrayList<JButton>();
        frame = new JFrame("Othelo");
        SetProperties();

        // white initial position
        setGameBoard(3, 3, 2);
        setGameBoard(4, 4, 2);
        // Black initial position
        setGameBoard(3, 4, 1);
        setGameBoard(4, 3, 1);

    }

    // Actions Performed By Each Button
    private ActionListener Set_Button_Actions() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                Integer cmd = new Integer(e.getActionCommand());
                int i = cmd.intValue() / 8;
                int j = cmd.intValue() % 8;

                int playerType = playerTurn ? 1 : 2;

                if (isValid(playerType, i, j))
                {
                    setGameBoard(i, j, playerType);
                    playerTurn = !playerTurn;
                    if(playerTurn)
                    {
                        frame.setTitle("Black");
                        System.out.println("Black :");
                    }
                    else
                        frame.setTitle("White");
                        System.out.println("White :");
                }

                printBoard();

                

            }

        };
    }

    // Printing The Board On console
    private void printBoard() {
        // Debug
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                char p = gameBoard[i][j] == 1 ? '☻' : ' ';
                p = gameBoard[i][j] == 2 ? '☺' : p;
                System.out.print(p + " | ");
            }
            System.out.println("\n--------------------------------");
        }
        // Debug

    }

    /**
     * Defualt Frame Properties set
     */
    public void SetProperties() {
        frame.setSize(800, 800);
        Draw();
        frame.setLayout(new GridLayout(8, 8));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // adding buttons to the frame
    public void Draw() {
        for (int i = 0; i < 64; i++) {

            JButton b = new JButton();
            b.setVisible(true);
            b.setBackground(Color.GRAY);
            b.setForeground(b.getBackground());
            b.setActionCommand(Integer.toString(i));

            b.addActionListener(ButtonsAction);
            frame.add(b);
            buttons.add(b);
        }
    }

    public boolean isValid(final int type,final int i,final int j) {
        if (gameBoard[i][j] != 0)
            return false;

        int OtherType = type == 1 ? 2 : 1;

        int x = j - 1, y = i;
        // <=== direction

        while (x > -1 && gameBoard[y][x] == OtherType) {
            x--;
        }
        if (x > -1 && gameBoard[y][x] == type && x != j - 1)
            return true;

        // ==> direction
        x = j + 1;
        while (x < 8 && gameBoard[y][x] == OtherType) {
            x++;
        }
        if (x < 8 && gameBoard[y][x] == type && x != j + 1)
            return true;


        // up direction 
        x=j;
        y=i-1;
        
        while(y >-1 && gameBoard[y][x]==OtherType){
            y--;
        }
        if (y > -1 && gameBoard[y][x] == type && y != i - 1)
            return true;
        

        //down direction
        x=j;
        y=i+1;
        
        while(y < 8 && gameBoard[y][x]==OtherType){
            y++;
        }
        if (y < 8 && gameBoard[y][x] == type && y != i + 1)
            return true;
        
        // up left
        x = j - 1;
        y = i - 1;

        while (x > -1 && y > -1 && gameBoard[y][x] == OtherType) {
            x--;
            y--;
        }
        if (x > -1 && y > -1 && gameBoard[y][x] == type && x != j - 1 && y != i - 1)
            return true;



        

        //up right

        x = j + 1;
        y = i - 1;

        while (x < 8 && y > -1 && gameBoard[y][x] == OtherType) {
            x++;
            y--;
        }
        if (x < 8 && y > -1 && gameBoard[y][x] == type && x != j + 1 && y != i - 1)
            return true;



        //down left

        x = j - 1;
        y = i + 1;

        while (x > -1 && y < 8 && gameBoard[y][x] == OtherType) {
            x--;
            y++;
        }
        if (x > -1 && y < 8 && gameBoard[y][x] == type && x != j - 1 && y != i + 1)
            return true;


        //down right
        x = j + 1;
        y = i + 1;

        while (x < 8 && y < 8 && gameBoard[y][x] == OtherType) {
            x++;
            y++;
        }
        if (x < 8 && y < 8 && gameBoard[y][x] == type && x != j + 1 && y != i + 1)
            return true;


                
        
        
        // checking in 8 directions

        return false;
    }

}