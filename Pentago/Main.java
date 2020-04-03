import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Board board=new Board();
        Scanner sc=new Scanner(System.in);
        while(true)
        {
            int x,y;
            x=sc.nextInt();
            y=sc.nextInt();
            int itype=sc.nextInt();
            CellType type=itype%2==0?CellType.Red:CellType.Black;
            board.setByCordinate(y, x, type);
            board.print();
        }
        
        
    }
}