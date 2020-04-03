
public class Main {
    public static void main(String[] args) {
        
        Board board=new Board();
        board.setByCordinate(0, 0, CellType.Red);
        board.rotate(1, true);
        board.rotate(1, true);
        board.rotate(1, false);
        board.rotate(1, false);
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board.getArrayByIndex(1)[i][j]+" ");
            }
            System.out.println();
        }

    }
}