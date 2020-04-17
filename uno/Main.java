import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        ArrayList<Card> arr=new ArrayList<>();
        
        for (int i = 1; i <= 4; i++) {
            Colors color=Colors.color(i);
            arr.add(new NormalCard(color, CardType.numeric, 0));
            for (int k = 0; k < 2; k++) {
                
                for (int j = 1; j < 10; j++) {
                    arr.add(new NormalCard(color, CardType.numeric, j));
                }
                arr.add(new NormalCard(color, CardType.skip));
                arr.add(new NormalCard(color, CardType.reverse));
                arr.add(new NormalCard(color, CardType.draw));
            }
        }
        for (int i = 0; i < 4; i++) {
            arr.add(new WildCard(CardType.wild));
            arr.add(new WildCard(CardType.wildDraw));
        }
       System.out.println(arr.size());


    }
}