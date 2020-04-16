
public class WildCard extends Card {

    @Override
    public boolean isPossible(Card card, Card... cardsOnHand) {
        
        ///wild anywhere anytime
        if(this.getType()==CardType.wild)
            return true;
        
        
        //Current Card Is Wild +4
        ///////////////////////////////////
        for (Card target : cardsOnHand) {

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
    public String toString() {
        if (type == CardType.wild) {

            return "Wild Color";
        } else
            return "Wild +4";

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