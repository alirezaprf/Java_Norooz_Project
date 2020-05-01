import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


public class Othelo {
    
    private JFrame frame;
    private int[][] gameBoard;
    private boolean playerTurn = true;
    // if true black moves else white does
    private ActionListener ButtonsAction;
    private ArrayList<JButton> buttons;
    private boolean gameIsone=true;

    private boolean lastPassed=false;

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
        SetProperties();//set Frame Properties

        // white initial position
        setGameBoard(3, 3, 2);
        setGameBoard(4, 4, 2);
        // Black initial position
        setGameBoard(3, 4, 1);
        setGameBoard(4, 3, 1);

    }
    /**
     * 
     * @return a boolean indicating Game Status
     */
    public boolean getGameStatus()
    {
        return gameIsone;
    }
    // Actions Performed By Each Button
    private ActionListener Set_Button_Actions() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Integer cmd = new Integer(e.getActionCommand());
                int i = cmd.intValue() / 8;
                int j = cmd.intValue() % 8;

                int playerType = playerTurn ? 1 : 2;
                //checking if the desierd cell is valid
                if (isValid(playerType, i, j) || lastPassed)
                {
                    if(!lastPassed)
                    {

                        setGameBoard(i, j, playerType);
                        ApplyBoard(playerType,i,j);
                        playerTurn = !playerTurn;
                        
                    }
                    short valids=0;
                    short whites=0,blacks=0;
                    playerType = playerTurn ? 1 : 2;


                   

                    for (int y = 0; y < 8; y++) {
                        for (int x = 0; x < 8; x++) {
                            //counting blacks and whites 
                            if(gameBoard[y][x]!=0)
                                {
                                    if(gameBoard[y][x]==1)
                                        blacks++;
                                    else
                                        whites++;

                                    buttons.get(y*8+x).setText("");
                                    continue;
                                }

                            if(isValid(playerType, y, x))
                            {
                                valids++;
                                buttons.get(y*8+x).setForeground(Color.GREEN);
                                buttons.get(y*8+x).setText("\u2714");
                                
                            }
                            else{
                                buttons.get(y*8+x).setText("");
                            }
                                
                            
                        }
                    }
                    //checking wheteher  gameboard is full or not and check if 
                    // both players have no option 
                    if(blacks+whites>=64 || (valids==0 && lastPassed))
                    {
                        //done 
                        gameIsone=false;
                        frame.setTitle("finished "+ blacks+" : Black" + "-----  " +whites+" : Whites");
                        System.out.println("finished "+ blacks+" : Black" + "-----  " +whites+" : Whites");
                        return;
                    }
                   
                    if(playerTurn)
                    {
                        frame.setTitle("Black");
                        System.out.println("Black :");
                    }
                    else{
                        frame.setTitle("White");
                        System.out.println("White :");
                    }
                     //there is no option for player    
                    if(valids==0)
                    {
                        System.out.println("Pass");
                        playerTurn=!playerTurn;
                        JButton source=(JButton)e.getSource();
                        lastPassed=true;
                        source.doClick();
                        
                        //return;
                    }else
                        lastPassed=false;
                        
                        if(!lastPassed)
                        printBoard();
                }
                
                

                

            }

        };
    }

    // Printing The Board On console
    private void printBoard() {
        // Debug
        System.out.println("");
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                char p = gameBoard[i][j] == 1 ? '☻' : ' ';
                p = gameBoard[i][j] == 2 ? '☺' : p;
                System.out.print(p + " | ");
            }
            System.out.println("\n________________________________");
        }
        System.out.println();
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
    /**
     * 
     * @param type type of player 1(black) 2(white)
     * @param i y
     * @param j x
     * @return
     */
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



   
     /**
     * Apply Board Is Using Same Logic as IsValid()
     * Checking 8 directions and adding changes to list
     * then it applies changes all togher
     * @param type type of player 1(black) 2(white)
     * @param i y
     * @param j x
     * @return
     */
    public void ApplyBoard(final int type,final int i,final int j){
        
        int OtherType = type == 1 ? 2 : 1;
        int x = j - 1, y = i;
        ArrayList<ArrayList<Integer>> changes=new ArrayList<>();
        
        
        
        int index=0;
        
        changes.add(new ArrayList<>()); // forech direction up down left right and combination
        changes.add(new ArrayList<>());
        changes.add(new ArrayList<>());
        changes.add(new ArrayList<>());
        changes.add(new ArrayList<>());
        changes.add(new ArrayList<>());
        changes.add(new ArrayList<>());
        changes.add(new ArrayList<>());
        
        // <=== direction

        while (x > -1 && gameBoard[y][x] == OtherType) {
            changes.get(index).add(new Integer(y*8+x));
            x--;
        }
        if (!(x > -1 && gameBoard[y][x] == type && x != j - 1))
            changes.get(index).clear();


        index++;
        
        // ==> direction
        x = j + 1;
        while (x < 8 && gameBoard[y][x] == OtherType) {
            changes.get(index).add(new Integer(y*8+x));
            x++;
            
        }
        if (!(x < 8 && gameBoard[y][x] == type && x != j + 1))
            changes.get(index).clear();


        index++;
        // up direction 
        x=j;
        y=i-1;
        
        while(y >-1 && gameBoard[y][x]==OtherType){
            changes.get(index).add(new Integer(y*8+x));
            y--;
        }
        if (!(y > -1 && gameBoard[y][x] == type && y != i - 1))
            changes.get(index).clear();
        
        index++;
        //down direction
        x=j;
        y=i+1;
        
        while(y < 8 && gameBoard[y][x]==OtherType){
            changes.get(index).add(new Integer(y*8+x));
            y++;
        }
        if (!(y < 8 && gameBoard[y][x] == type && y != i + 1))
            changes.get(index).clear();
        
        
        
        
        index++;
        // up left
        x = j - 1;
        y = i - 1;

        while (x > -1 && y > -1 && gameBoard[y][x] == OtherType) {
            changes.get(index).add(new Integer(y*8+x));
            x--;
            y--;
        }
        if (!(x > -1 && y > -1 && gameBoard[y][x] == type && x != j - 1 && y != i - 1))
            changes.get(index).clear();



        
        index++;
        //up right

        x = j + 1;
        y = i - 1;

        while (x < 8 && y > -1 && gameBoard[y][x] == OtherType) {
            changes.get(index).add(new Integer(y*8+x));
            x++;
            y--;
        }
        if (!(x < 8 && y > -1 && gameBoard[y][x] == type && x != j + 1 && y != i - 1))
            changes.get(index).clear();


        index++;
        //down left

        x = j - 1;
        y = i + 1;

        while (x > -1 && y < 8 && gameBoard[y][x] == OtherType) {
            changes.get(index).add(new Integer(y*8+x));
            x--;
            y++;
        }
        if (!(x > -1 && y < 8 && gameBoard[y][x] == type && x != j - 1 && y != i + 1))
            changes.get(index).clear();

        
        
        index++;
        //down right
        x = j + 1;
        y = i + 1;

        while (x < 8 && y < 8 && gameBoard[y][x] == OtherType) {
            changes.get(index).add(new Integer(y*8+x));
            x++;
            y++;
        }
        if (!(x < 8 && y < 8 && gameBoard[y][x] == type && x != j + 1 && y != i + 1))
            changes.get(index).clear();


        for (int k = 0; k < changes.size(); k++) {
            for (int k2 = 0; k2 < changes.get(k).size(); k2++) {
                Integer c=changes.get(k).get(k2);
                setGameBoard(c.intValue()/8, c.intValue()%8, type);
            }
        }

       

    }

    //fast debug with aoutomatic test
    /**
     * 
     * @param index index of list that contains BUttons
     */
    public void doClick(int index)
    {
        buttons.get(index).doClick();
    }



}