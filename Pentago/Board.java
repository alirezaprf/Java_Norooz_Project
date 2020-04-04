import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Board {
    private Block blocks[];
    /**
     * 
     * aligned as b1 | b2 b3 | b4
     * 
     * 
     */
    private short counter = 0;// counter of not empty cells

    private JFrame frame;

    private boolean isBlackPlaying=true;//to identify Type of Player 
    
    
    public Board() {
        frame = new JFrame("Pentago");
        SetProperties();

        blocks = new Block[4];
        blocks[0] = new Block();
        blocks[1] = new Block();
        blocks[2] = new Block();
        blocks[3] = new Block();
    }
    

    public void SetProperties() {
        frame.setSize(800, 800);
        frame.setLayout(new GridLayout(6, 6));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                JButton button = new JButton(Integer.toString(i * 6 + j));
                button.setVisible(true);
                button.setActionCommand(Integer.toString(i)+" "+Integer.toString(j));
                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        int y,x;
                        String cmds[]=e.getActionCommand().split(" ");
                        y=Integer.parseInt(cmds[0]);
                        x=Integer.parseInt(cmds[1]);
                        CellType type=isBlackPlaying?CellType.Black:CellType.Red;
                        if(isEmpty(y, x))
                        {
                            setByCordinate(y, x, type);
                            print();
                            isBlackPlaying=!isBlackPlaying;
                            if(CheckVictory())
                            {
                                System.exit(0);
                            }
                        }
                    }
                });

                frame.add(button);
        }
    }
    
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



   /**
    * 
    * @param y y from 0 to 5
    * @param x x from 0 to 5
    * @param type Cell Type
    */
   public void setByCordinate(int y,int x,CellType type)
   {
       
       counter++;
    if(y>2)
    {
        if(x>2)
        {

            blocks[3].setByindex((y-3)*3 + x-3, type);
        }
        else
        {
            blocks[2].setByindex((y-3)*3 + x, type);
        }
    }
    else
    {
        if(x>2)
        {
            blocks[1].setByindex((y)*3 + x-3, type);
        }
        else
        {
            blocks[0].setByindex((y)*3 + x, type);
        }
    }
   }

   /**
    * 
    * @param y
    * @param x
    * @return wheter the cell is Emty or not
    */
   public boolean isEmpty(int y,int x)
   {
       int index=0;
        if(y>2)
            index+=2;

        if(x>2)
            index++;

        return blocks[index].getCells()[y%3][x%3]==CellType.Empty;
   }

    /**
     * 
     * @param BlockNumber index of block from 1 to 4
     * @param ClocWise true = clockWise Rotation false = AntiClockWise  
     * 
     */
    public void rotate(int BlockNumber,boolean ClocWise)
    {
        if(ClocWise)
        {
            blocks[BlockNumber-1].ClockWiseRotation();
        }
        else
        {
            blocks[BlockNumber-1].AntiClockRotation();
        }
    }
    /**
     * 
     * @param index index of Board from 1 to 4
     * @return Array of That BLock
     */
    public CellType[][] getArrayByIndex(int index)
    {
        return blocks[index-1].getCells();
    }

    /**
     * 
     * @param y 0 to 5
     * @param x 0 to 5
     * @return cell type of that
     */
    public CellType getCell(int y,int x)
    {
        if(y>2)
    {
        if(x>2)
        {

            return blocks[3].getCell(y-3,x-3);
        }
        else
        {
            return  blocks[2].getCell(y-3,x);
        }
    }
    else
    {
        if(x>2)
        {
            return blocks[1].getCell(y,x-3);
        }
        else
        {
            return blocks[0].getCell(y,x);
        }
    }
    } 

    public int getCounter()
    {
        return counter;
    }
    

    private boolean isFiveAligned(CellType... cells)
    {
        for (int i = 2; i < cells.length-1; i++) {
            if(cells[i]!=cells[i-1] || cells[i]==CellType.Empty)
                return false;
        }

        return cells[0]==cells[1] || cells[5]==cells[1];
    }

    public boolean CheckVictory()
    {

        /**
         *  [1] | [2]
         *  ---------
         *  [3] | [4]
         * 
         */

        /**
         * 18 possible wins 
         * 
         */

        for (int i = 0; i < 6; i++) {
            if(isFiveAligned(
            getCell(i, 0),
            getCell(i, 1),
            getCell(i, 2),
            getCell(i, 3),
            getCell(i, 4),
            getCell(i, 5)      )){
                return true;
            }
        }

        for (int i = 0; i < 6; i++) {
            if(isFiveAligned(
            getCell(0,i),
            getCell(1,i),
            getCell(2,i),
            getCell(3,i),
            getCell(4,i),
            getCell(5,i)      )){
                return true;
            }
        }
        CellType[] arrTypes={getCell(0, 0),getCell(1, 1),getCell(2, 2),getCell(3, 3),
            getCell(4, 4),getCell(5, 5)};

        
        if(isFiveAligned(arrTypes))
            return true;

            CellType[] REVERSE_arrTypes={getCell(0, 5),getCell(1, 4),getCell(2, 3),getCell(3, 2),
                getCell(4, 1),getCell(5, 0)};


                
        if(isFiveAligned(REVERSE_arrTypes))
            return true;


        return false;
    }
   
    /**
     * Printing The board On Console
     */
    public void print()
    {
        System.out.println();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int index=0;
                if(j>2)
                    index++;
                if(i>2)
                    index+=2;
                char c='○';
                if(blocks[index].getCells()[i%3][j%3]==CellType.Black)
                    c='B';
                if(blocks[index].getCells()[i%3][j%3]==CellType.Red)
                c='R';
                System.out.print(c+" ");
                if(j==2)
                System.out.print("| ");
            }
            System.out.println();
            if(i==2)
            System.out.println("-------------");
        }
    }
    


}