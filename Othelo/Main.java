import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Othelo ot = new Othelo();
        Scanner sc = new Scanner(System.in);
        System.out.println("Either Click The Buttons Or Input Here :\nBlack: ");
        boolean gameIson=true;
        boolean isBot=false;
        while (gameIson) {
            
            String inp[]=sc.nextLine().split(" ");
            int y=Integer.parseInt(inp[0]);
            int x=inp[1].charAt(0)-65;
            ot.doClick(--y*8+x);
            gameIson=ot.getGameStatus();
            isBot=!isBot;
        }
        System.out.println("Done");
        sc.close();
        
        System.exit(0);
    }
}