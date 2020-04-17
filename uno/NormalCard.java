
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
           }
           return normalCard.getType()==type || normalCard.getColor()==color;
       }
       else
       {
           //Wild Card
           // You Should Follow The Wild's Color
           return card.getColor()==color;
       }
        
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
        String s=type==CardType.numeric?color+" - "+number+" \n":color+"\n";
        return super.toString()+s;
    }

}