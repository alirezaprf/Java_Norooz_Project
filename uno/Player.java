import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cards;


    public Player()
    {
        cards=new ArrayList<>();
    }

    /**
     * 
     * @param card adds the Card to list 
     */
    public void addCard(Card card)
    {
        cards.add(card);
    }

    /**
     * 
     * @return sum of all cards scores
     */
    public int getPlayerScore()
    {
        int sum=0;
        for (Card card : cards) {
            sum+=card.getScore();
        }
        return sum;
    }
    /**
     * 
     * @param card to be removed from list ...
     */
    public void removeCard(Card card)
    {
        cards.remove(card);
    }
    /**
     * 
     * @return all of the cards on hand
     */
    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public boolean hasWon()
    {
        return cards.size()==0;
    }
    /**
     * 
     * @param index index of card that is possible
     * @return the card to be replaced
     */
    public Card drop(int index)
    {
        Card c = cards.get(index);
        cards.remove(index);
        return c;

    }
    /**
     * 
     * @param card current card
     * @param cardsOnHand other cards on hand
     * @return -1 if you can't drop any
     * @return index if you can
     * 
     */
    
    public int canDrop(Card card)
    {
        
        for (int i = 0; i < cards.size(); i++) {
            
            if(cards.get(i).isPossible(card, cards))
            {
                return i;
            }
        }
        return -1;
    }
    

}