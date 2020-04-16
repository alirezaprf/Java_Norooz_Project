
public class WildCard extends Card{

    @Override
    public boolean isPossible(Card card,Card ... cardsOnHand) {
        for (Card target : cardsOnHand) {
            if(target instanceof NormalCard)
            {
                if(target.isPossible(card))
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int getScore() {
        
        return 0;
    }

}