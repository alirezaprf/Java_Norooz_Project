import java.util.ArrayList;

public abstract class Card {
    protected int score;
    protected CardType type;
    protected Colors color;
    /**
     * takes the current Card and Checks if you can put this card on top of it
     */
    public boolean isPossible(Card card,Card... cardsOnHand)
    {
        System.out.println("Error #1");
        return false;
    }
    public boolean isPossible(Card card,ArrayList<Card> cardsonHand)
    {
        System.out.println("Error #2");
        return false;
    }
    public void action()
    {
        //some action for cards
    }
    /**
     * 
     * @return type of the 
     */

    public void Print()
    {
        System.out.println("Unkown Card");
    }

    public CardType getType()
    {
        return type;
    }
    /**
     * 
     * @return Color of The Card
     */
    public Colors getColor()
    {
        return color;
    }
    public abstract int getScore();


    @Override
    public String toString() {
        
        return type.name()+"-";
    }

    public void setColor(Colors c)
    {
        color=c;
    }
}