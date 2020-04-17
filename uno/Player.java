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

}