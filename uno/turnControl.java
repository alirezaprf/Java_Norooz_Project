
public class turnControl {
    private int players=0;
    private int Turn=0;
    private boolean reversed=false;
    /**
     * 
     * @param number number of players
     */
    public turnControl(int number)
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

        return Turn%4;
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
}