
public class Board {
   private Block blocks[];
    /**
     * 
     * aligned as
     b1 | b2
     b3 | b4


     */
    private short counter=0;//counter of not empty cells
   public Board()
   {
    blocks=new Block[4];
    blocks[0]=new Block();
    blocks[1]=new Block();
    blocks[2]=new Block();
    blocks[3]=new Block();
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

    public int getCounter()
    {
        return counter;
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
         * 4 possible wins 
         * 1=>2
         * 1=>3
         * 2=>4
         * 3=>4
         */

        if(blocks[0].equals(blocks[1]))
        {
            //1=>2
            if(blocks[0].getCells()[1][2]==blocks[0].getMiddle() && 
            blocks[0].getCells()[1][2]==blocks[1].getCells()[1][0] )
            {return (blocks[0].getCells()[1][0]==blocks[0].getMiddle()
                || blocks[1].getCells()[1][2]==blocks[1].getMiddle());
            } 

            //to check if 5 of same type is aligned

        }
        else if(blocks[0].equals(blocks[2]))
        {

        } 
        else if(blocks[1].equals(blocks[3]))
        {

        } 
        else if(blocks[2].equals(blocks[3]))
        {

            //3=>4
            if(blocks[2].getCells()[1][2]==blocks[2].getMiddle() && 
            blocks[2].getCells()[1][2]==blocks[3].getCells()[1][0] )
            {return (blocks[2].getCells()[1][0]==blocks[2].getMiddle()
                || blocks[3].getCells()[1][2]==blocks[3].getMiddle());
            } 

            //to check if 5 of same type is aligned
            

        } 
        


        return false;
    }
   

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
                System.out.print(" | ");
            }
            System.out.println();
            if(i==2)
            System.out.println("--------------");
        }
    }
    


}