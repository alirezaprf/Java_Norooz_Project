import java.util.ArrayList;
import java.util.Random;

public class WildCard extends Card {


    public WildCard(CardType Type)
    {
        this.type=Type;
        color=Colors.Black;
    }

    @Override
    public String toString() {
        
        return super.toString()+" / ";
    }
    @Override
    public void Print() {
        if(type==CardType.wild){

            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("          Wild("+color+")        ");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$");
        }else
        {
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("           Wild("+color+")  ");
            System.out.println("           Draw   ");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$");
        
        }

    }
    public boolean isWildDraw()
    {
        return type==CardType.wildDraw;
    }
    @Override
    public void action() {
        randomColor();
    }
    public void randomColor()
    {
        Random rand=new Random();
        setColor(Colors.values()[rand.nextInt(
            Colors.values().length-1)]);
        
    }

    @Override
    public boolean isPossible(Card card, Card... cardsOnHand) {
        
        

        ///wild anywhere anytime
        if(this.getType()==CardType.wild)
            return true;
        
        
        //Current Card Is Wild +4
        ///////////////////////////////////
        for (Card target : cardsOnHand) {
            if(target.getType()==CardType.wildDraw)
            continue;


            if (target.getType() == CardType.wild)
                return false;

            if (target instanceof NormalCard) {
                if (target.isPossible(card)) {
                    return false;
                }
            }

        }
        return true;
    }
    @Override
    public boolean isPossible(Card card, ArrayList<Card> cardsOnHand) {
        
        

        ///wild anywhere anytime
        if(this.getType()==CardType.wild)
            return true;
        
        
        //Current Card Is Wild +4
        ///////////////////////////////////
        for (Card target : cardsOnHand) {
            if(target.getType()==CardType.wildDraw)
            continue;


            if (target.getType() == CardType.wild)
                return false;

            if (target instanceof NormalCard) {
                if (target.isPossible(card)) {
                    return false;
                }
            }

        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        
        if(obj instanceof WildCard)
        {
            return ((WildCard)obj).getType()==type;
        }
        
        return false;
    }

    @Override
    public int getScore() {

        return 50;
    }

    /**
     * 
     * @param color sets the color of the WildCard
     */
    public void setColor(Colors color) {
        this.color = color;
    }

}