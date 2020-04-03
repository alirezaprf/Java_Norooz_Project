
public class Board {
   private Block b[];
    /**
     * 
     * aligned as
     b1 | b2
     b3 | b4


     */

   public Board()
   {
    b=new Block[4];
    b[0]=new Block();
    b[1]=new Block();
    b[2]=new Block();
    b[3]=new Block();
   }


   /**
    * 
    * @param y y from 0 to 5
    * @param x x from 0 to 5
    * @param type Cell Type
    */
   public void setByCordinate(int y,int x,CellType type)
   {
    if(y>2)
    {
        if(x>2)
        {
            b[3].setByindex((y-3)*3 + x-3, type);
        }
        else
        {
            b[2].setByindex((y-3)*3 + x, type);
        }
    }
    else
    {
        if(x>2)
        {
            b[1].setByindex((y)*3 + x-3, type);
        }
        else
        {
            b[0].setByindex((y)*3 + x, type);
        }
    }
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
            b[BlockNumber-1].ClockWiseRotation();
        }
        else
        {
            b[BlockNumber-1].AntiClockRotation();
        }
    }
    /**
     * 
     * @param index index of Board from 1 to 4
     * @return Array of That BLock
     */
    public CellType[][] getArrayByIndex(int index)
    {
        return b[index-1].getCells();
    }




}