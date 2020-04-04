import java.util.Scanner;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        
        Board board=new Board();
        Scanner sc=new Scanner(System.in);
        

        while(!board.CheckVictory())
        {
            int x,y;
            x=sc.nextInt();
            y=sc.nextInt();
            int itype=sc.nextInt();
            CellType type=itype%2==0?CellType.Red:CellType.Black;
            board.setByCordinate(y, x, type);
            board.print();
        }
        System.out.println("Done");
        
        
    }
}