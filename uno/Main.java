import java.util.ArrayList;
import java.util.Random;

public class Main{
    public static void main(String[] args) {
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
                int index=random.nextInt(rep.size());
                p.addCard(rep.get(index));
                rep.remove(index);
                
            }
            players.add(p);
            
        }





        int randi=random.nextInt(rep.size());
        Card current=rep.get(randi);
        rep.remove(randi);
        System.out.println("--------------------------------------");
        System.out.println(current);
        System.out.println(players.size());
        System.out.println("--------------------------------------");


        for (int i = 0; i < players.size(); i++) {
            for (Card card : players.get(i).getCards()) {
                card.Print();
                System.out.println(".................");
                 Card[] hand=new Card[players.get(i).getCards().size()];
                 players.get(i).getCards().toArray(hand);
                 
            }
            System.out.println("\n\n\n");
        }


        turnControl tc=new turnControl(players.size());
        
        
        

    }
}