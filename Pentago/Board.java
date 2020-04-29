import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board {
    private Block blocks[];
    /**
     * 
     * aligned as b1 | b2 
     *            b3 | b4
     * 
     * 
     */
    private short counter = 0;// counter of not empty cells
    private CellType Winner;
    private JFrame frame;
    private boolean victory = false;
    private boolean isBlackPlaying = true;// to identify Type of Player
    private ArrayList<JButton> buttns;
    private int BlockToRotate = 1;

    public Board() {
        frame = new JFrame("Pentago");
        buttns = new ArrayList<JButton>();
        SetProperties();

        blocks = new Block[4];
        blocks[0] = new Block();
        blocks[1] = new Block();
        blocks[2] = new Block();
        blocks[3] = new Block();
    }

    private ActionListener JOptionPaneDilaogActions(int index, JLabel jLabel) {

        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BlockToRotate = index;
                jLabel.setText(" You Chose : " + Integer.toString(BlockToRotate));

                jLabel.setForeground(Color.red);

            }
        };
    }

    public void SetProperties() {
        frame.setSize(800, 800);

        frame.setLayout(new GridLayout(2, 2, 50, 30));

        for (int i = 0; i < 4; i++) {
            JPanel jPanel = new JPanel();
            jPanel.setLayout(new GridLayout(3, 3, 10, 10));
            jPanel.setBackground(Color.decode("#66ffff"));
            jPanel.setVisible(true);
            for (int j = 0; j < 9; j++) {

                JButton button = new RoundButton(Color.green);
                button.setBackground(Color.white);
                int Y = j / 3 + (i / 2) * 3, X = j % 3 + (i % 2) * 3;
                button.setActionCommand(Integer.toString(Y) + " " + Integer.toString(X));

                buttns.add(button);
                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        int y, x;
                        String cmds[] = e.getActionCommand().split(" ");
                        y = Integer.parseInt(cmds[0]);
                        x = Integer.parseInt(cmds[1]);
                        CellType type = isBlackPlaying ? CellType.Black : CellType.Red;
                        if (isEmpty(y, x) && !victory) {
                            setByCordinate(y, x, type);
                            Color clr = type == CellType.Black ? Color.black : Color.red;
                            ((JButton) e.getSource()).setBackground(clr);

                            System.out.println("\n Now Rotate it \n");
                            String[] bu = { "ClockWise", "AntiClockWise" };
                            /**
                             * Graphic for Rotate Dialog
                             */
                            JPanel rtPanel = new JPanel();
                            rtPanel.setLayout(new GridLayout(2, 2, 5, 5));
                            JLabel jlabel = new JLabel("You Chose : "+Integer.toString(BlockToRotate));
                            JButton jb;
                            ///////////////////////////////////////////////////////////////////
                            jb = new JButton("1");
                            jb.addActionListener(JOptionPaneDilaogActions(1, jlabel));
                            rtPanel.add(jb);

                            jb = new JButton("2");
                            jb.addActionListener(JOptionPaneDilaogActions(2, jlabel));
                            rtPanel.add(jb);

                            jb = new JButton("3");
                            jb.addActionListener(JOptionPaneDilaogActions(3, jlabel));
                            rtPanel.add(jb);

                            jb = new JButton("4");
                            jb.addActionListener(JOptionPaneDilaogActions(4, jlabel));
                            rtPanel.add(jb);

                            JComponent[] jComponents = { rtPanel, jlabel };
                            int chose = JOptionPane.showOptionDialog(frame, jComponents, "title",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, bu, null);

                            
                            if(chose==JOptionPane.YES_OPTION)
                            {
                                rotate(BlockToRotate, true);
                            }
                            else
                            {
                                rotate(BlockToRotate, false);
                            }
                            
                            print();
                            
                            isBlackPlaying = !isBlackPlaying;
                            
                            if(isBlackPlaying)
                                frame.setTitle("Black");
                            else
                                frame.setTitle("Red");
                            if (CheckVictory()) {
                                victory=true;
                                frame.setTitle("Finished "+Winner+" Won");
                                System.out.println("Finished "+Winner+" Won");
                                print();
                                //System.exit(0);
                            }
                            if(counter==36)
                            {
                                victory=true;
                                System.out.println("\nFinished as A Tie");
                                frame.setTitle("Tie :/");
                            }
                        }
                    }
                });

                jPanel.add(button);
                button.setVisible(true);
            }
            frame.add(jPanel);
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
    public void rotate(int BlockNumber,boolean ClockWise)
    {
        
        if(ClockWise)
        {
            blocks[BlockNumber-1].ClockWiseRotation();
        }
        else
        {
            blocks[BlockNumber-1].AntiClockRotation();
        }
        RotateButtons(BlockNumber-1);
    }
    /**
     * 
     * @param index 0 to 3 index of block
     */
    public void RotateButtons(int index)
    {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn=buttns.get(index*9+i*3+j);
                CellType target=blocks[index].getCells()[i][j];
                if(target==CellType.Empty)
                {
                   btn.setBackground(Color.white); 
                }else if(target==CellType.Black)
                    btn.setBackground(Color.black);
                else{
                    btn.setBackground(Color.red);
                }
                
            }
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
                Winner=getCell(i, 2);
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
                Winner=getCell(2, i);
                return true;
            }
        }
        CellType[] arrTypes={getCell(0, 0),getCell(1, 1),getCell(2, 2),getCell(3, 3),
            getCell(4, 4),getCell(5, 5)};

        
        if(isFiveAligned(arrTypes))
            {
                Winner=arrTypes[2];
                return true;
            }

            CellType[] REVERSE_arrTypes={getCell(0, 5),getCell(1, 4),getCell(2, 3),getCell(3, 2),
                getCell(4, 1),getCell(5, 0)};

            

        if(isFiveAligned(REVERSE_arrTypes))
            {
                Winner=REVERSE_arrTypes[2];    
                return true;
            }


            if(isFiveAligned(new CellType[]{getCell(0, 1),
                getCell(1, 2),
                getCell(2, 3),
                getCell(3, 4),
                getCell(4, 5),
                CellType.None
            })) {
                Winner=getCell(3, 4);
                return true;
                }
    
            if(isFiveAligned(new CellType[]{getCell(1, 0),
                getCell(2, 1),
                getCell(3, 2),
                getCell(4, 3),
                getCell(5, 4),
                CellType.None
            })) {
                Winner=getCell(4, 3);
                return true;
            }

            if(isFiveAligned(new CellType[]{
                getCell(4, 0),
                getCell(3, 1),
                getCell(2, 2),
                getCell(1, 3),
                getCell(0, 4),
                CellType.None
                
            })){
                Winner=getCell(2, 2);
                return true;}

            if(isFiveAligned(new CellType[]{
                getCell(5, 1),
                getCell(4, 2),
                getCell(3, 3),
                getCell(2, 4),
                getCell(1, 5),
                CellType.None
                
            })){
                Winner=getCell(3, 3);
                return true;}

            
                


        

        return false;
    }
    
    /**
     * 
     * @return if the game is Done Or not
     */
    public boolean isDone()
    {
        return victory;
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