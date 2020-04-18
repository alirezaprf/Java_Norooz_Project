
public class TurnControl {
    private int players=0;
    private int Turn=0;
    private boolean reversed=false;
    /**
     * 
     * @param number number of players
     */
    public TurnControl(int number)
    {
        players=number;
    }

    public void reverse()
    {
        reversed=!reversed;
        tchange();
    }
    public int turn()
    {
        if(Turn<0)
        Turn+=players;

        return Turn%players;
    }
    public void tchange()
    {
        if(!reversed)
        {
            Turn++;
        }
        else
            Turn--;
    }
    public String getState()
    {
        if(reversed)
        {
            return "AntiClockWise";
        }
        return "ClockWise";
    }
    public int nextTurn()
    {
        int t=Turn;
        if(!reversed)
        {
            t++;
        }
        else
            t--;

        if(t<0)
            t+=players;
    
        return t%4;
    }
}