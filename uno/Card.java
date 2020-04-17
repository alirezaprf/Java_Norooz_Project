
public abstract class Card {
    protected int score;
    protected CardType type;
    protected Colors color;
    /**
     * takes the current Card and Checks if you can put this card on top of it
     */
    public abstract boolean isPossible(Card card,Card... cardsOnHand);
    /**
     * 
     * @return type of the 
     */
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
        
        return "|| "+type.name()+" || ";
    }
}