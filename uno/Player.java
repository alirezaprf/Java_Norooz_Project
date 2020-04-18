import java.util.ArrayList;
import java.util.Random;

public class Player {
    private ArrayList<Card> cards;
    private PlayerTypes type=PlayerTypes.Bot;

    public Player()
    {
        cards=new ArrayList<>();
    }
    /**
     * 
     * @param Type type of the player
     */
    public Player(PlayerTypes Type)
    {
        cards=new ArrayList<>();
        type=Type;
    }

    /**
     * 
     * @return type of the player
     */
    public PlayerTypes type()
    {
        return type;
    }
    
    /**
     * get 7 radnom cards from repository of cards
     * @param rep is reposittory of cards
     */
    public void Initialize(ArrayList<Card> rep)
    {
        for (int j = 0; j < 2; j++) {
            addCard(rep);
        }
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
     * @param card adds a random card to list 
     */
    public void addCard(ArrayList<Card> rep)
    {
        
        Random random=new Random();
        int index=random.nextInt(rep.size());
        cards.add(rep.get(index));
        rep.remove(index);
    }
    /**
     * 
     * @return index of a draw card if it exists
     */
    public int canDropDraw()
    {
        for (int i = 0; i < cards.size(); i++) {
            if(cards.get(i).getType()==CardType.draw)
                return i;
        }
        return -1;
    }
    /**
     * 
     * @return index of a wild draw card
     */
    public int canDropWildDraw()
    {
        for (int i = 0; i < cards.size(); i++) {
            if(cards.get(i).getType()==CardType.wildDraw)
                return i;
        }
        return -1;
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
        c.action();
        System.out.println("\n===>"+c+" Dropped ...");
        cards.remove(index);
        return c;

    }
    /**
     * 
     * @param card current card
     * 
     * @return -1 if you can't drop any
     * @return index if you can
     * 
     */
    
    public int canDrop(Card cuurentCard)
    {
        
        for (int i = 0; i < cards.size(); i++) {
            
            if(cards.get(i).isPossible(cuurentCard, cards))
            {
                return i;
            }
        }
        return -1;
    }
    /***
     * 
     * @param index index of specific card (Chosen By human)
     * @param CurrentCard Current card 
     * @return true of false as if it's possible
     */
    public boolean isPossibleToDrop(int index,Card CurrentCard)
    {
        return cards.get(index).isPossible(CurrentCard, cards);

    }
    public void printCards()
    {
        int i=1;
        for (Card card : cards) {
            System.out.print(i + "."+card);
            i++;
        }
    }
    

}