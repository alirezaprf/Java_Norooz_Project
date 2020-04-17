
public enum Colors {
    Red(),//1
    Green(),//2
    Blue(),//3
    Yellow(),//4
    Black();//-1
    
    Colors()
    {
        
    }
    public static Colors color(int i)
    {
        switch(i)
        {
            case 1:
            return Red;
            case 2:
            return Green;
            case 3:
            return Blue;
            case 4:
            return Yellow;
            default:
            return Black;
        }
    }
}