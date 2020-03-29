import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Othelo {

    private JFrame frame;
    private int[][] gameBoard;
    private boolean playerTurn=true;
    //if true black moves else white does
    private ActionListener ButtonsAction;




    /**
     * gameBorad Containg Game's Data as int
     * 0 which is the initial State of every cell = empty
     * 1 means black
     * 2 means white 
     */

    public Othelo() {
        gameBoard=new int[8][8];
        
        gameBoard[3][3]=2;
        gameBoard[4][4]=2;
        
        gameBoard[3][4]=1;
        gameBoard[4][3]=1;

        ButtonsAction=Set_Button_Actions();
        

        frame = new JFrame("Othelo");
        SetProperties();

    }
    
    private ActionListener Set_Button_Actions() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                Integer cmd=new Integer(e.getActionCommand());
                int i=cmd.intValue()/8;
                int j=cmd.intValue()%8;
                gameBoard[i][j]=playerTurn?1:2;
                printBoard();

                playerTurn=!playerTurn;

            }

        };
    }

    private void printBoard()
    {
        //Debug
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                char p=gameBoard[i][j]==1?'☻':' ';
                p=gameBoard[i][j]==2?'☺':p;
                System.out.print(p +" | ");
            }
            System.out.println("\n--------------------------------");
        }
        //Debug
        
    }
    
    public void SetProperties() {
        frame.setSize(800, 800);
        Draw();
        frame.setLayout(new GridLayout(8, 8));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void Draw() {
        for (int i = 0; i < 64; i++) {

            JButton b = new JButton();
            b.setVisible(true);
            b.setBackground(Color.GRAY);
            b.setForeground(b.getBackground());
            b.setActionCommand(Integer.toString(i));
            
            b.addActionListener(ButtonsAction);
            frame.add(b);
        }
    }


    
}