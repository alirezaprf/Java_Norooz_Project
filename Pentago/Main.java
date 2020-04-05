import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
         Board board=new Board();
         
         board.getCounter();
         Scanner sc=new Scanner(System.in);
        
        System.out.println("Welcome Either Use the Console Or Gui\nYou can't use both of them");

   
        boolean isBlackPlaying=true;
        while(!board.isDone())
        {
            System.out.println("\nEnter x(from 1 to 6) y(from 1 to 6) \nrotation Block(from 1 to 4 and negative number for AntiClockWise Roation) ");
            CellType type=isBlackPlaying?CellType.Black:CellType.Red;
            System.out.println(type+" : ");
            int x,y,r;
            x=sc.nextInt();
            y=sc.nextInt();
            r=sc.nextInt();
            board.setByCordinate(y-1, x-1, type);
            if(r==0)
                r=1;
            boolean anti=r>0;
            if(!anti)
                r*=-1;
            board.rotate(r, anti);
            board.print();
            isBlackPlaying=!isBlackPlaying;
        }
        sc.close();
        System.out.println("Done");
        
        
    }
}
