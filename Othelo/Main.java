import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Othelo ot=new Othelo();
        Scanner sc=new Scanner(System.in);
        while(true)
        {
            
            
            ot.doClick(sc.nextInt());
        }
    }
}