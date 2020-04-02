
public class Main {
    public static void main(String[] args) {
        Block b=new Block();
        for(int i=0;i<9;i++)
        {
            if(i%4==0)
            {
                b.setByindex(i, CellType.Black);
            }
            else
            {
                b.setByindex(i, CellType.Red);
            }
        }
        b.Print();
        System.out.println("==============================");
        b.ClockRotation();
        b.Print();
        System.out.println("==============================");
        b.ClockRotation();
        b.Print();
        System.out.println("==============================");
        b.ClockRotation();
        b.Print();
        System.out.println("==============================");
        b.ClockRotation();
        b.Print();
        System.out.println("==============================");
        b.ClockRotation();
        
    }

}