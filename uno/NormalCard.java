import java.util.ArrayList;

public class NormalCard extends Card {

    private int number=-1;
    public NormalCard(Colors color,CardType Type, int ... numbers)
    {
        super();
        this.color=color;
        this.type=Type;
        if(type==CardType.numeric)
            this.number=numbers[0];

    }
    
    @Override
    public boolean isPossible(Card card,Card... cardOnHand) {
        
       if(card instanceof NormalCard)
       {
           NormalCard normalCard=(NormalCard)card;
           if(normalCard.getType()==CardType.numeric)
           {
               //numeric card 
               //check by color or number
               if(normalCard.getColor()==color || normalCard.getNumber()==number)
                {
                    
                    return true;
                } 
                return false;
           }


           ///skip reverse draw
           return normalCard.getType()==type || normalCard.getColor()==color;
       }
       else
       {
           //Wild Card
           // You Should Follow The Wild's Color
           return card.getColor()==color;
       }
        
    }
    @Override
    public boolean isPossible(Card card, ArrayList<Card> cardsonHand) {
        return isPossible(card);
    }
    public int getNumber() {
        return number;
    }
    /**
     * @return score of card which is 
     * number of card for numerics
     * and 20 for actionCards
     */
    @Override
    public int getScore() {
        if(type==CardType.numeric)
        {
            return number;
        }
        
        return 20;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof NormalCard)
        {
            NormalCard nc=(NormalCard)obj;
            return nc.getColor()==color && nc.getType()==type 
            && nc.getNumber()==number;
        }
        return false;
    }
    @Override
    public String toString() {
        String s=type==CardType.numeric?color+"-"+number+" | " : super.toString()+color+" | ";
        return s;
    }

    @Override
    public void Print() {
        if(type==CardType.numeric)
        {
            System.out.println("   $$$$$$$$$$$$$");
            System.out.println("    "+color+"   ");
            System.out.println("    "+number+"   ");
            System.out.println("   $$$$$$$$$$$$$$");
        
        }
        else
        {
            System.out.println("   $$$$$$$$$$$$$");
            System.out.println("    "+color+"   ");
            System.out.println("    "+type+"   ");
            System.out.println("   $$$$$$$$$$$$$$");
        
        }
    }

}