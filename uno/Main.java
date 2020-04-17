import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.PrintStream;
import java.lang.System;

public class Main{
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        

        int playersSize=2;
        ArrayList<Card> rep=new ArrayList<>();
        ArrayList<Player> players=new ArrayList<Player>();
        
        for (int i = 1; i <= 4; i++) {
            Colors color=Colors.color(i);
            rep.add(new NormalCard(color, CardType.numeric, 0));
            for (int k = 0; k < 2; k++) {
                
                for (int j = 1; j < 10; j++) {
                    rep.add(new NormalCard(color, CardType.numeric, j));
                }
                rep.add(new NormalCard(color, CardType.skip));
                rep.add(new NormalCard(color, CardType.reverse));
                rep.add(new NormalCard(color, CardType.draw));
            }
        }
        for (int i = 0; i < 4; i++) {
            rep.add(new WildCard(CardType.wild));
            rep.add(new WildCard(CardType.wildDraw));
        }
        Random random=new Random();
        for (int i = 0; i < playersSize; i++) {
            Player p=new Player();
            for (int j = 0; j < 7; j++) {
                p.addCard(rep);
            }
            players.add(p);
            
        }





        int randi=random.nextInt(rep.size());
        Card current=rep.get(randi);
        rep.remove(randi);

        

        int drawPenalty=0;
        boolean skip=false;


        turnControl tc=new turnControl(players.size());
        while(true)
        {
            current.Print();
            System.out.println("\n\n\n");
            for (int i = 0; i < players.size(); i++) {
                players.get(i).printCards();
                System.out.println("\n____________________________________________");
            }
            int turn=tc.turn();
            System.out.println(":" + turn+" :");
            sc.nextLine();
            //get turns


            if(drawPenalty>0)
            {
                if(drawPenalty==2)
                {
                    //current card is draw
                    int canDropDraw=players.get(turn).canDropDraw();

                    if(canDropDraw!=-1)
                    {
                        players.get(turn).drop(canDropDraw);
                        drawPenalty+=2;
                        
                        tc.tchange();
                        continue;
                        
                    }
                }
                if(drawPenalty==4)
                {
                    int canDropWildDraw=players.get(turn).canDropWildDraw();
                    if(canDropWildDraw!=-1)
                    {
                        players.get(turn).drop(canDropWildDraw);
                        drawPenalty+=4;
                        tc.tchange();
                        continue;
                        
                    }
                }
                for (int i = 0; i < drawPenalty; i++) {
                    players.get(turn).addCard(rep);
                } 


            }
            if(skip)
            {
                skip=false;
                tc.tchange();
                continue;
            }
            // if(turn==0)
            // {
            //     //Main Player of the Game
            // }
            // else
            {
                //computer dicision
                int cardIndex=players.get(turn).canDrop(current);
                if(cardIndex!=-1)
                {
                    System.out.println("you can drop");
                    Card droppeCard=players.get(turn).getCards().get(cardIndex);
                    droppeCard.action();
                    switch(droppeCard.getType())
                    {
                        case wildDraw:
                        drawPenalty=2;
                        skip=true;
                        break;
                        
                        case draw:
                        drawPenalty=2;
                        skip=true;
                        break;
                        
                        case reverse:
                        tc.reverse();
                        break;
                        
                        case skip:
                        tc.tchange();
                        break;



                    default:
                        break;
                        
                    }
                    current=droppeCard;
                    players.get(turn).drop(cardIndex);
                }
                else
                {
                    System.out.println("you can't drop any");

                }


                
                
            }

            //change turns
            tc.tchange();



            sc.nextLine();

        }
        
        

    }
}