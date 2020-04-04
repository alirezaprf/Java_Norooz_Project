
public class Block {
    private CellType array[][] = new CellType[3][3];
    private int first_loop_start = 0; // y
    private int first_loop_end = 3;
    private int second_loop_start = 0; // x
    private int second_loop_end = 3;
    private int rotation = 0;
    private boolean reverse=false;
    




    public Block() {
        for (int i = 0; i < 9; i++)
            this.setByindex(i, CellType.Empty);
    
        
    
        }

    /**
     * 
     * @param index is a index from 0 to 8
     * @param type  Cell Type from CellType Enum
     */
    public void setByindex(int index, CellType type) {
        int c = 0;
        for (int i = first_loop_start; i != first_loop_end; i += i < first_loop_end ? 1 : -1) {
            for (int j = second_loop_start; j != second_loop_end; j += j < second_loop_end ? 1 : -1) {
                
                
                if(c==index)
                {
                    if(reverse)
                    array[j][i]=type;
                    else    
                    array[i][j]=type;
    
                    return;
                }
                c++;
                
            }
        }

    }

    // debug function
    // printing the Board
    public void Print() {
        for (int i = first_loop_start; i != first_loop_end; i += i < first_loop_end ? 1 : -1) {
            for (int j = second_loop_start; j != second_loop_end; j += j < second_loop_end ? 1 : -1) {
                if(reverse)
                System.out.print(array[j][i]+"-");
                else    
                System.out.print(array[i][j]+"-");
            }
            System.out.println();
        }
    }

    /**
     * 
     * @return Cells Array
     */
    public CellType[][] getCells() {
        CellType[][] arr=new CellType[3][3];

        int y=0,x=0;
        for (int i = first_loop_start; i != first_loop_end; i += i < first_loop_end ? 1 : -1) {
            x=0;
            for (int j = second_loop_start; j != second_loop_end; j += j < second_loop_end ? 1 : -1) {
                
                if(reverse)
                arr[y][x]=(array[j][i]);
                else    
                arr[y][x]=(array[i][j]);
                x++;
            }
            y++;
        }

        return arr;
    }

    // getFirst_loop_start()
    public int _i() {
        return first_loop_start;
    }

    // getFirst_loop_end()
    public int _iEnd() {
        return first_loop_end;
    }

    // getSecond_loop_start
    public int _j() {
        return second_loop_start;
    }

    // getSecond_loop_end
    public int _jEnd() {
        return second_loop_end;
    }
    public boolean getReverse()
    {
        return reverse;
    }


    ///Rotating Block Clock Direction
    public void ClockWiseRotation() {
        rotation++;
        
        rotation = rotation % 4;
        rotator();//apply rotation
    }
    ///Rotating Block ClockWise Direction
    public void AntiClockRotation()
    {
        rotation--;
        if(rotation==-1)
            rotation+=4;
            
        rotation = rotation % 4;
        rotator();
    }
    
    /**
     *  middle one is always constant during all rotation
     * @return middle cell of a block
     */
    public CellType getMiddle()
    {
        return array[1][1];
    }
    public CellType getCell(int y,int x)
    {
        return getCells()[y][x];
    }
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        Block block=(Block)obj;
        
        return array[1][1]!=CellType.Empty && array[1][1]==block.getMiddle();
    }
    ///applier of rotations
    private void rotator()
    {
        switch (rotation) {
            case 0:
                first_loop_start=0;
                first_loop_end=3;
                second_loop_start=0;
                second_loop_end=3;
                reverse=false;
                break;
        
            case 1:
            reverse=true;
            first_loop_start=0;
            first_loop_end=3;
            second_loop_start=2; // 3 ====> 1 //
            second_loop_end=-1;
                break;
                
            case 2:
            first_loop_start=2;
            first_loop_end=-1;
            second_loop_start=2;
            second_loop_end=-1;
            reverse=false;
                break;
            case 3:
            first_loop_start=2;
            first_loop_end=-1;
            second_loop_start=0;
            second_loop_end=3;
            reverse=true;
                break;

        }
    }

}