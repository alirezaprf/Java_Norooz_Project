import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.System;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int playersSize = 3;
        
        System.out.println("Enter number of players");
        playersSize=sc.nextInt();
        ArrayList<Card> rep = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<Player>();

        for (int i = 1; i <= 4; i++) {
            Colors color = Colors.color(i);
            rep.add(new NormalCard(color, CardType.numeric, 0));
            for (int k = 0; k < 2; k++) {

                for (int j = 1; j < 10; j++) {
                    rep.add(new NormalCard(color, CardType.numeric, j));
                }
                rep.add(new NormalCard(color, CardType.skip));
                rep.add(new NormalCard(color, CardType.reverse));
                rep.add(new NormalCard(color, CardType.draw));
            }
        }
        for (int i = 0; i < 4; i++) {
            rep.add(new WildCard(CardType.wild));
            rep.add(new WildCard(CardType.wildDraw));
        }
        Random random = new Random();
        for (int i = 0; i < playersSize; i++) {
            System.out.println("type 0 if player"+(i+1)+" is human or type a number if it's bot");
            PlayerTypes playerType=PlayerTypes.Bot;
            if(sc.nextInt()==0)
                playerType=PlayerTypes.Human;
            Player p = new Player(playerType);
            p.Initialize(rep);
            players.add(p);

        }

        // players.get(1).addCard(rep.get(107));
        // players.get(2).addCard(rep.get(105));
        // players.get(1).addCard(rep.get(103));
        // players.get(2).addCard(rep.get(101));
        // players.get(0).addCard(rep.get(5));
        // players.get(0).addCard(rep.get(4));

        Card current;
        boolean firstRound = true;
        // find a non wild card
        while (true) {

            int randi = random.nextInt(rep.size());
            current = rep.get(randi);
            if (current instanceof WildCard)
                continue;
            rep.remove(randi);
            break;
        }

        TurnControl tc = new TurnControl(players.size());
        int drawPenalty = 0;
        boolean skip = false;
        boolean hasEnded = false;

        switch (current.getType()) {
            case draw:
                drawPenalty = 2;
                skip = true;
                break;

            case reverse:
                tc.reverse();
                break;

            case skip:
                tc.tchange();
                break;
            default:
                break;

        }

        /// main game play
        //// ***************************** */
        
        while (!hasEnded) {
            
            
            System.out.printf("\n↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓  %s ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓\n\n",tc.getState());
            int turn = tc.turn();
            System.out.println("Current Card is :");
            current.Print();
            System.out.println("====>                             ");
            System.out.println("     ====>                             ");
            for (int i = 0; i < players.size(); i++) {
                if(i==turn)
                System.out.print(" (*) ");
                else
                System.out.print(" () ");
                
                players.get(i).printCards();
                System.out.println("\n___________________________________________\n");
            }

            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).hasWon()) {
                    hasEnded = true;
                    break;
                }
            }

            if (hasEnded)
                break;

            
            System.out.println("player " + (turn + 1) + " :");
            
            if(players.get(turn).type()==PlayerTypes.Bot)
            {
                System.out.println("(Bot)type something and press Enter to go on");
                sc.next();
            }



            

            if (drawPenalty > 0) {
                if (drawPenalty == 2) {
                    // current card is draw
                    int canDropDraw = players.get(turn).canDropDraw();

                    if (canDropDraw != -1 && !firstRound) {
                        // custom desicion for himan player
                        if (players.get(turn).type()==PlayerTypes.Human) {
                            System.out.println("You can Drop a draw card ");
                            System.out.print("Enter a Number (number of a Draw card) between 1 to "
                                    + players.get(turn).getCards().size()+ " :") ;
                            while (true) {
                                canDropDraw = sc.nextInt();
                                canDropDraw--;
                                if (players.get(turn).getCards().get(canDropDraw).getType() == CardType.draw) {
                                    {
                                        
                                        break;
                                    }
                                }
                                System.out.println("NOt Valid :(   ");

                            }
                        }
                        rep.add(current);
                        current = players.get(turn).getCards().get(canDropDraw);
                        players.get(turn).drop(canDropDraw);
                        drawPenalty += 2;

                        tc.tchange();

                        continue;

                    }
                }
                if (drawPenalty == 4) {

                    int canDropWildDraw = players.get(turn).canDropWildDraw();
                    if (canDropWildDraw != -1) {
                        int colorIndex=1;
                        if (players.get(turn).type()==PlayerTypes.Human) {

                            System.out.println("Choose a color use one of your wild draw cards");
                            System.out.println("1.Red 2.Green 3.Blue 4.Yellow");
                            colorIndex=sc.nextInt()-1;
                            if(colorIndex>=0 && colorIndex<4)
                            {
                                players.get(turn).getCards().get(canDropWildDraw).setColor(
                                    Colors.values()[colorIndex]);
                            }
                            else
                            {
                                System.out.println("Wrong answer We'll go with green");
                                players.get(turn).getCards().get(canDropWildDraw).setColor(
                                    Colors.values()[1]);
                            }
                        }

                        rep.add(current);
                        current = players.get(turn).getCards().get(canDropWildDraw);
                        players.get(turn).drop(canDropWildDraw);
                        drawPenalty += 4;
                        tc.tchange();
                        continue;

                    }
                }
                System.out.println("You get a penalty !!!");
                for (int i = 0; i < drawPenalty; i++) {
                    players.get(turn).addCard(rep);
                }
                drawPenalty = 0;
                firstRound = false;

            }

            firstRound = false;

            if (skip) {
                skip = false;

                tc.tchange();
                continue;
            }
            if (players.get(turn).type()==PlayerTypes.Human) {
                // Main Player of the Game
                boolean cantOnce = false;
                while (true) {
                    int cardIndex = players.get(turn).canDrop(current);
                    if (cardIndex != -1) {
                        System.out.println("You can Drop a card");
                        System.out.print("Enter a Number between 1 to " +
                         players.get(turn).getCards().size()+ " :");



                        cardIndex = sc.nextInt() - 1;
                        if (players.get(turn).isPossibleToDrop(cardIndex, current)) {
                            Card droppeCard = players.get(turn).getCards().get(cardIndex);
                            int colorIndex;
                            switch (droppeCard.getType()) {
                                case wildDraw:
                                System.out.println("Choose a color use one of your wild draw cards");
                                System.out.println("1.Red 2.Green 3.Blue 4.Yellow");
                                colorIndex=sc.nextInt()-1;
                                if(colorIndex>0 && colorIndex<4)
                                {
                                    droppeCard.setColor(
                                        Colors.values()[colorIndex]);
                                }
                                else
                                {
                                    System.out.println("Wrong answer We'll go with green");
                                    droppeCard.setColor(
                                        Colors.values()[1]);
                                }
                            
                                drawPenalty = 4;
                                skip = true;
                                break;

                                case draw:
                                    drawPenalty = 2;
                                    skip = true;
                                    break;

                                case reverse:
                                    tc.reverse();
                                    break;

                                case skip:
                                    tc.tchange();
                                    break;

                                case wild:
                                System.out.println("Choose a color use one of your wild cards");
                                System.out.println("1.Red 2.Green 3.Blue 4.Yellow");
                                colorIndex=sc.nextInt()-1;
                                if(colorIndex>0 && colorIndex<4)
                                {
                                    droppeCard.setColor(
                                        Colors.values()[colorIndex]);
                                }
                                else
                                {
                                    System.out.println("Wrong answer We'll go with green");
                                    droppeCard.setColor(
                                        Colors.values()[1]);
                                }
                                break;
                                default:
                                    break;

                            }
                            rep.add(current);
                            current = droppeCard;
                            players.get(turn).drop(cardIndex);
                            break;
                        } else {
                            System.out.println("NOt Possible :)");
                        }

                    }
                    else
                    {
                        
                        if (cantOnce)
                            break;
                        System.out.println("you can't drop any");
                        // add a random
                        players.get(turn).addCard(rep);

                        cantOnce = true;
                        System.out.println("You recived this card");
                        players.get(turn).getCards().get(players.get(turn).getCards().size() - 1).Print();
                        ;
                        System.out.println("\n\n");
                        continue;
                    }
                
                }
            } else {
                boolean cantOnce = false;
                while (true) {

                    // computer dicision
                    int cardIndex = players.get(turn).canDrop(current);
                    if (cardIndex != -1) {
                        // System.out.println("you can drop");
                        Card droppeCard = players.get(turn).getCards().get(cardIndex);

                        switch (droppeCard.getType()) {
                            case wildDraw:
                                drawPenalty = 4;
                                skip = true;
                                break;

                            case draw:
                                drawPenalty = 2;
                                skip = true;
                                break;

                            case reverse:
                                tc.reverse();
                                break;

                            case skip:
                                tc.tchange();
                                break;

                            default:
                                break;

                        }
                        rep.add(current);
                        current = droppeCard;
                        players.get(turn).drop(cardIndex);
                        break;
                    } else {
                        if (cantOnce)
                            break;
                        System.out.println("you can't drop any");
                        // add a random
                        players.get(turn).addCard(rep);

                        cantOnce = true;
                        System.out.println("You recived this card");
                        players.get(turn).getCards().get(players.get(turn).getCards().size() - 1).Print();
                        ;
                        System.out.println();
                        continue;

                    }
                }

            }

            // change turns
            tc.tchange();

            // sc.nextLine();

        }
        sc.close();
    }
}